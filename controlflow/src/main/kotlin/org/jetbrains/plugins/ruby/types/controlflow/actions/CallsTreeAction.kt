package org.jetbrains.plugins.ruby.types.controlflow.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ui.Messages
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RMethod
import org.jetbrains.plugins.ruby.types.controlflow.dialogs.CallsTreeDumperDialog
import org.jetbrains.plugins.ruby.types.controlflow.ControlFlow
import org.jetbrains.plugins.ruby.types.controlflow.data.ControlFlowProvider

class CallsTreeAction: AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val file = e.getData(PlatformDataKeys.PSI_ELEMENT)
        if (file !is RMethod) {
            Messages.showErrorDialog(
                    e.project,
                    "Ruby method not found",
                    "Error"
            )
            return
        }
        val callsTreeAsString = ControlFlow.getCallsTree(ControlFlowProvider.newControlFlowWrapper(file), 10)

        val dialog = CallsTreeDumperDialog(
                file,
                callsTreeAsString
        )
        dialog.show()
    }
}