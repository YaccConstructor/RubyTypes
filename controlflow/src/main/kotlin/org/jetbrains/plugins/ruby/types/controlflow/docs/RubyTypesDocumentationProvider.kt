package org.jetbrains.plugins.ruby.types.controlflow.docs

import com.intellij.psi.PsiElement
import org.jetbrains.plugins.ruby.ruby.lang.documentation.RubyDocumentationProvider
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RMethod
import org.jetbrains.plugins.ruby.ruby.lang.psi.variables.RIdentifier
import org.jetbrains.plugins.ruby.types.controlflow.TypesUtil
import org.jetbrains.plugins.ruby.types.controlflow.neededOffset

class RubyTypesDocumentationProvider: RubyDocumentationProvider() {
    override fun getQuickNavigateInfo(element: PsiElement, originalElement: PsiElement): String? {
        when {
            element is RIdentifier && originalElement is RIdentifier -> {
                TypesUtil.types[element.neededOffset()]?.let {
                    return "<b>${element.text}</b>: ${it.typeDefinitions.joinToString(" <b>or</b> ")}"
                } ?: return super.getQuickNavigateInfo(element, originalElement)
            }
            element is RMethod -> {
                TypesUtil.types[originalElement.neededOffset()]?.let {
                    return "<b>${originalElement.text}</b>: ${it.typeDefinitions.joinToString(" <b>or</b> ") { "($it)" }}"
                } ?: return super.getQuickNavigateInfo(element, originalElement)
            }
            else -> return super.getQuickNavigateInfo(element, originalElement)
        }
    }

    override fun generateDoc(element: PsiElement, originalElement: PsiElement?): String? {
        val originalDoc = super.generateDoc(element, originalElement) ?: return null
        return HtmlTypeDocProcessor(element).process(originalDoc)
    }

    private fun List<String>.toHtmlList(): String = joinToString("", "<ul>", "</ul>") { "<li>$it</li>" }
}