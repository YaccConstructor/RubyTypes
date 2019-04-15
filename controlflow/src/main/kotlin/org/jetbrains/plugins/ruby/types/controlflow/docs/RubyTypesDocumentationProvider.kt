package org.jetbrains.plugins.ruby.types.controlflow.docs

import com.intellij.psi.PsiElement
import org.jetbrains.plugins.ruby.ruby.lang.documentation.RubyDocumentationProvider
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RMethod
import org.jetbrains.plugins.ruby.ruby.lang.psi.methodCall.RCall
import org.jetbrains.plugins.ruby.ruby.lang.psi.variables.RIdentifier
import org.jetbrains.plugins.ruby.types.controlflow.annotations.Annotations
import org.jetbrains.plugins.ruby.types.controlflow.typeinfo.rightOffset
import org.jetbrains.plugins.ruby.types.parser.ast.RubyTypeDeclaration

class RubyTypesDocumentationProvider: RubyDocumentationProvider() {
    override fun getQuickNavigateInfo(element: PsiElement, originalElement: PsiElement): String? {
        when {
            element is RIdentifier && originalElement is RIdentifier -> {
                typeDeclarations[element.neededOffset()]?.let {
                    return "<b>${element.text}</b>: ${it.typeDefinitions.joinToString(" <b>or</b> ")}"
                } ?: return super.getQuickNavigateInfo(element, originalElement)
            }
            element is RMethod -> {
                typeDeclarations[originalElement.neededOffset()]?.let {
                    return "<b>${originalElement.text}</b>: ${it.typeDefinitions.joinToString(" <b>or</b> ") { "($it)" }}"
                } ?: return super.getQuickNavigateInfo(element, originalElement)
            }
            else -> return super.getQuickNavigateInfo(element, originalElement)
        }
    }

    companion object {
        var typeDeclarations: Map<Int, RubyTypeDeclaration> = emptyMap()
    }

    override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
        val s = super.generateDoc(element, originalElement)
        if (s == null) {
            return s
        }
        val docParts = s.split("#t ").toMutableList()
        if (docParts.size > 1) {
            docParts[0] = docParts[0].removeSuffix("No documentation found.")
            docParts[1] = docParts[1].dropWhile { it != ':' }.drop(2)
            val firstPart = docParts[0] + "<h3>Declared types:</h3>${docParts.drop(1).toHtmlList()}"
            typeDeclarations[element?.neededOffset()]?.typeDefinitions?.map { it.toString() }?.toHtmlList()?.let {
                return "$firstPart<h3>Inferred types:</h3>$it"
            }
            return firstPart
        } else {
            typeDeclarations[element?.neededOffset()]?.typeDefinitions?.map { it.toString() }?.toHtmlList()?.let {

                return "${s.removeSuffix("No documentation found.")}<h3>Inferred types:</h3>$it"
            }
            return s
        }
    }

    private fun List<String>.toHtmlList(): String = joinToString("", "<ul>", "</ul>") { "<li>$it</li>" }
    private fun PsiElement.neededOffset(): Int =
            when (this) {
                is RIdentifier -> rightOffset
                else -> textOffset
            }
}