package org.jetbrains.plugins.ruby.types.controlflow.dialogs

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.panels.HorizontalLayout
import javax.swing.JComponent
import java.awt.Dimension
import javax.swing.JPanel

/**
 * Dialog that asks user to select custom type inference algorithm.
 *
 * TODO use [Kotlin UI DSL](https://www.jetbrains.org/intellij/sdk/docs/user_interface_components/kotlin_ui_dsl.html)
 */
class SelectTypeInferenceAlgorithmDialogWrapper(private val project: Project): DialogWrapper(true) {

    init {
        init()
        setResizable(false)
        title = "Type inference algorithm"
    }

    private lateinit var algorithmSelectionField: TextFieldWithBrowseButton
    var pathToSelectedBinary: String? = null

    override fun createCenterPanel(): JComponent? {

        val promptText = JBLabel("Algorithm path:")

        algorithmSelectionField = TextFieldWithBrowseButton()
        algorithmSelectionField.addBrowseFolderListener(
                "Select algorithm",
                "Select algorithm of type inference",
                project,
                FileChooserDescriptorFactory.createSingleFileDescriptor()
        )

        val dialogPanel = JPanel(HorizontalLayout(1))
        dialogPanel.preferredSize = Dimension(600, 30)
        algorithmSelectionField.preferredSize = Dimension(500, 30)
        dialogPanel.add(promptText, HorizontalLayout.LEFT)
        dialogPanel.add(algorithmSelectionField, HorizontalLayout.RIGHT)

        this.pack()
        return dialogPanel

    }

    override fun doOKAction() {
        pathToSelectedBinary = algorithmSelectionField.text
        super.doOKAction()
    }
}