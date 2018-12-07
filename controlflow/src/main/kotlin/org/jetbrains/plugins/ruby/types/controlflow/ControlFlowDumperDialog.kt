package org.jetbrains.plugins.ruby.types.controlflow

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.psi.PsiElement
import com.intellij.ui.components.JBScrollPane
import javax.swing.JComponent
import javax.swing.JTextArea

class ControlFlowDumperDialog(val filePsi: PsiElement, val text: String): DialogWrapper(filePsi.project) {

    private val info = JTextArea(text)

    init {
        init()
        title = "Control flow info"
        info.isEditable = false

        info.setSize(info.width, info.height / 2)
    }

    override fun createCenterPanel(): JComponent {
        return JBScrollPane(info)
    }
}