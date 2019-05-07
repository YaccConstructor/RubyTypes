package org.jetbrains.plugins.ruby.types.controlflow.actions

import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.codeInsight.daemon.impl.HintRenderer
import com.intellij.ide.IdeEventQueue
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.editor.impl.InlayModelImpl
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiElement
import org.jetbrains.plugins.ruby.ruby.lang.psi.RFile
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlFlow.impl.RControlFlowBuilder
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.classes.RClass
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RMethod
import org.jetbrains.plugins.ruby.types.controlflow.dialogs.ControlFlowDumperDialog
import org.jetbrains.plugins.ruby.types.controlflow.RubyControlFlowWrapper
import org.jetbrains.plugins.ruby.types.controlflow.TypesUtil
import org.jetbrains.plugins.ruby.types.controlflow.annotations.Annotations
import org.jetbrains.plugins.ruby.types.controlflow.docs.RubyTypesDocumentationProvider
import org.jetbrains.plugins.ruby.types.controlflow.dump.JsonControlFlowWriter
import org.jetbrains.plugins.ruby.types.controlflow.errors.TypeMismatchErrors
import org.jetbrains.plugins.ruby.types.controlflow.hints.RubyTypesInlayVisitor
import org.jetbrains.plugins.ruby.types.controlflow.read.BasicTranslator
import org.jetbrains.plugins.ruby.types.controlflow.read.JsonResultReader
import org.jetbrains.plugins.ruby.types.parser.ast.RubyTypeDeclaration
import java.io.File
import org.jetbrains.plugins.ruby.types.controlflow.actions.algorithm.AlgorithmExecutor
import org.jetbrains.plugins.ruby.types.controlflow.dialogs.SelectTypeInferenceAlgorithmDialogWrapper
import java.nio.file.Paths


class ControlFlowDumperAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val file = e.getData(PlatformDataKeys.PSI_FILE)
        val editor = e.getData(PlatformDataKeys.EDITOR)!!

        if (file == null) {
            return
        }

        val builder = RControlFlowBuilder()
        if (file !is RFile) {
            Messages.showErrorDialog(
                    e.project,
                    "Active Ruby file not found",
                    "Error"
            )
            return
        }

        TypeMismatchErrors.reset()
        Annotations.collect(file)

        val selectTypeInferenceAlgorithmDialogWrapper = SelectTypeInferenceAlgorithmDialogWrapper(e.project ?: throw RuntimeException())

        if (!selectTypeInferenceAlgorithmDialogWrapper.showAndGet()) {
            return
        }

        val controlFlow = builder.buildControlFlow(file)
        val controlFlowWrapper = RubyControlFlowWrapper(controlFlow, file)

        val fileControlFlowInfo = controlFlowWrapper.dump().writeToString(JsonControlFlowWriter())
        val nestedControlFlowInfos = getAllControlFlowGraphsInfo(file)
        val input = File("data.txt")
        input.writeText("$fileControlFlowInfo\n$nestedControlFlowInfos")

        val pathToOutput = AlgorithmExecutor(selectTypeInferenceAlgorithmDialogWrapper.pathToSelectedBinary ?: throw RuntimeException())
                .execute(input.absolutePath, "")

        // !!! This line is just for debugging;
        //     comment it if you have real algorithm
        val output = File("datanew.txt")
        output.writeText(BasicTranslator().translate("$fileControlFlowInfo\n$nestedControlFlowInfos").toString())

        val translatedData = File(pathToOutput).readText()//BasicTranslator().translate("$fileControlFlowInfo\n$nestedControlFlowInfos")

        input.deleteOnExit()
        output.deleteOnExit()

//        println(translatedData)

//        val dialog = ControlFlowDumperDialog(
//                file,
//                fileControlFlowInfo + "\n" + nestedControlFlowInfos
//        )
//        dialog.show()

        val knownTypes: Map<Int, RubyTypeDeclaration> = JsonResultReader().read(translatedData)

        TypesUtil.types = knownTypes

        editor.inlayModel.let {
            file.accept(RubyTypesInlayVisitor(it, knownTypes))
        }

        DaemonCodeAnalyzer.getInstance(e.project).restart(file)
    }

    private fun getAllControlFlowGraphsInfo(element: PsiElement): String =
        if (element is RClass) {
            getControlFlowInfoOfClass(element)
        } else if (element is RMethod) {
            getControlFlowInfoOfMethod(element)
        } else {
            element.children
                    .map { getAllControlFlowGraphsInfo(it) }
                    .filter { it.isNotBlank() }
                    .joinToString(separator = "\n")
        }

    private fun getControlFlowInfoOfClass(element: RClass): String {
        val builder = RControlFlowBuilder()
        val controlFlow = builder.buildControlFlow(element)
        val cfg = RubyControlFlowWrapper(controlFlow, element)
        val childInfo = element.children.joinToString(separator = System.lineSeparator()) { getAllControlFlowGraphsInfo(it) }
        return cfg.dump().writeToString(JsonControlFlowWriter()) + System.lineSeparator() + childInfo
    }

    private fun getControlFlowInfoOfMethod(element: RMethod): String {
        val builder = RControlFlowBuilder()
        val controlFlow = builder.buildControlFlow(element)
        val cfg = RubyControlFlowWrapper(controlFlow, element)
        val childInfo = element.children.joinToString(separator = System.lineSeparator()) { getAllControlFlowGraphsInfo(it) }
        return cfg.dump().writeToString(JsonControlFlowWriter()) + System.lineSeparator() + childInfo
    }
}
