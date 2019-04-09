package org.jetbrains.plugins.ruby.types.controlflow.actions

import com.intellij.codeInsight.daemon.impl.HintRenderer
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiElement
import org.jetbrains.plugins.ruby.ruby.lang.psi.RFile
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlFlow.impl.RControlFlowBuilder
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.classes.RClass
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RMethod
import org.jetbrains.plugins.ruby.types.controlflow.dialogs.ControlFlowDumperDialog
import org.jetbrains.plugins.ruby.types.controlflow.RubyControlFlowWrapper
import org.jetbrains.plugins.ruby.types.controlflow.dump.JsonControlFlowWriter
import org.jetbrains.plugins.ruby.types.controlflow.hints.RubyTypesInlayVisitor

class ControlFlowDumperAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val file = e.getData(PlatformDataKeys.PSI_FILE)
        val editor = e.getData(PlatformDataKeys.EDITOR)!!
        
        file!!.accept(RubyTypesInlayVisitor(editor.inlayModel))

        val builder = RControlFlowBuilder()
        if (file !is RFile) {
            Messages.showErrorDialog(
                    e.project,
                    "Active Ruby file not found",
                    "Error"
            )
            return
        }

        val controlFlow = builder.buildControlFlow(file)
        val controlFlowWrapper = RubyControlFlowWrapper(controlFlow, file)

        val fileControlFlowInfo = controlFlowWrapper.dump().writeToString(JsonControlFlowWriter())
        val nestedControlFlowInfos = getAllControlFlowGraphsInfo(file)
        val dialog = ControlFlowDumperDialog(
                file,
                fileControlFlowInfo + "\n" + nestedControlFlowInfos
        )
        dialog.show()
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
