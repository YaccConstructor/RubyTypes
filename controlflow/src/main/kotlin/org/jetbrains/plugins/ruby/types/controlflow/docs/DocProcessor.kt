package org.jetbrains.plugins.ruby.types.controlflow.docs

import com.intellij.psi.PsiElement
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RMethod
import org.jetbrains.plugins.ruby.ruby.lang.psi.variables.RIdentifier
import org.jetbrains.plugins.ruby.types.controlflow.TypesUtil
import org.jetbrains.plugins.ruby.types.controlflow.annotations.Annotations
import org.jetbrains.plugins.ruby.types.controlflow.neededOffset
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode
import java.lang.RuntimeException

interface DocProcessor {
    fun process(doc: String): String
}

class HtmlTypeDocProcessor(val selectedElement: PsiElement): DocProcessor {

    override fun process(doc: String): String {
        return when (selectedElement) {
            is RMethod -> processMethod(doc)
            is RIdentifier -> processIdentifier(doc)
            else -> throw RuntimeException()
        }
    }

    private fun processMethod(doc: String): String {
        val documentBody = Jsoup.parse(doc).body()
        val nodesWithTypeAnnotaton = removeAndGetTypeAnnotations(documentBody)

        documentBody.childNodes()
                .last()
                .after(Annotations.declarationForMethod(selectedElement as RMethod)?.typeDefinitions?.map { it.toString() }?.toHtmlList())
                .after("Declared types:".withTag("h3"))

        return documentBody.toString()
    }

    private fun processIdentifier(doc: String): String {
        val documentBody = Jsoup.parse(doc).body()

        TypesUtil.types[selectedElement.neededOffset()]?.typeDefinitions?.let { defs ->
            documentBody.childNodes()
                    .findLast { it is TextNode && it.text() == "No documentation found." }
                    ?.remove()

            documentBody.childNodes()
                    .last()
                    .after("Inferred type: ".withTag("b") + defs.joinToString(" | ") { it.toString() })
        }
        return documentBody.toString()
    }

    private fun removeAndGetTypeAnnotations(documentBody: Element): List<TextNode> {
        val nodesWithTypeAnnotaton = mutableListOf<TextNode>()
        for (element in documentBody.childNodes()) {
            if (element is TextNode) {
                if (isElementReferToTypeDoc(element)) {
                    nodesWithTypeAnnotaton.add(element)
                }
            }
        }

        nodesWithTypeAnnotaton.forEach {
            it.previousSibling().remove()
            it.remove()
        }
        return nodesWithTypeAnnotaton
    }

    private fun isElementReferToTypeDoc(element: Node): Boolean {
        return element is TextNode && element.text().startsWith("#t ")
    }

    private fun String.withTag(tag: String) = "<$tag>$this</$tag>"
    private fun String.withDelimiter(delimiter: String) = "<$delimiter>$this"
    private fun String.withoutDeclaration() = replace(pattern, "")
    private fun List<String>.toHtmlList(): String = joinToString("", "<ul>", "</ul>") { "<li>$it</li>" }

    companion object {
        val pattern by lazy { Regex(".*:(\\s)+") }
    }
}