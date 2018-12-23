package org.jetbrains.plugins.ruby.types.controlflow.dialogs

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.psi.PsiElement
import com.intellij.ui.components.JBScrollPane
import javax.swing.JComponent
import javax.swing.JTextArea

class CallsTreeDumperDialog(filePsi: PsiElement, text: String): DialogWrapper(filePsi.project) {

    private val info = JTextArea(text)

    init {
        init()
        title = "Calls tree"
        info.isEditable = false

        info.setSize(info.width, info.height / 2)
    }

    override fun createCenterPanel(): JComponent {
        return JBScrollPane(info)
    }
}