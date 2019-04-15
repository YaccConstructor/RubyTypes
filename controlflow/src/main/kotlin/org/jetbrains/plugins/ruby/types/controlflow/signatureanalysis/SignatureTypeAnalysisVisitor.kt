package org.jetbrains.plugins.ruby.types.controlflow.signatureanalysis

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiPolyVariantReference
import org.jetbrains.plugins.ruby.ruby.lang.psi.basicTypes.RFloatConstant
import org.jetbrains.plugins.ruby.ruby.lang.psi.basicTypes.RIntegerConstant
import org.jetbrains.plugins.ruby.ruby.lang.psi.basicTypes.stringLiterals.*
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.RBlockStatement
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.RConditionalStatement
import org.jetbrains.plugins.ruby.ruby.lang.psi.expressions.RAssignmentExpression
import org.jetbrains.plugins.ruby.ruby.lang.psi.expressions.RLiteral
import org.jetbrains.plugins.ruby.ruby.lang.psi.ruby19.basicTypes.stringLiterals.ROneCharacterString
import org.jetbrains.plugins.ruby.ruby.lang.psi.variables.RIdentifier
import org.jetbrains.plugins.ruby.ruby.lang.psi.visitors.RubyRecursiveElementVisitor
import org.jetbrains.plugins.ruby.types.controlflow.annotations.Annotations
import org.jetbrains.plugins.ruby.types.parser.ast.RubyAtomTypeIdentifier
import org.jetbrains.plugins.ruby.types.parser.ast.RubyTypeDefinition

/**
 * Some simple analyses to reduce some routine of type inference algorithm.
 */
class SignatureTypeAnalysisVisitor: RubyRecursiveElementVisitor() {

    // TODO reaching defs to infer types in flow-sensitively way
    private val identifiersInCurrentBaseBlock = mutableSetOf<RIdentifier>()

    override fun visitElement(element: PsiElement) {
        if (element is RConditionalStatement && element is RBlockStatement) {
            identifiersInCurrentBaseBlock.clear()
        }
        super.visitElement(element)
    }

    override fun visitRAssignmentExpression(assignmentExpression: RAssignmentExpression) {
        val left = assignmentExpression.firstChild
        val right = assignmentExpression.lastChild
        if (left is RIdentifier) {
            identifiersInCurrentBaseBlock.add(left)
            when (right) {
                is RLiteral -> Annotations.registerIdentifier(left, listOf(RubyAtomTypeIdentifier(right.textOffset, listOf(analyzeLiteral(right)))))
                is RIdentifier -> analyzeIdentifier(right)?.let { Annotations.registerIdentifier(left, it) }
            }
        }
        super.visitRAssignmentExpression(assignmentExpression)
    }

    private fun analyzeLiteral(right: RLiteral): String {
        return when (right) {
            is RRegexpLiteral -> "Regexp"
            is RWords -> "Words"
            is ROneCharacterString -> "Character"
            is RStringLiteral -> "String"
            is RFloatConstant -> "Float"
            is RIntegerConstant -> "Integer"
            is RStrings -> "Strings"
            else -> throw RuntimeException()
        }
    }

    private fun analyzeIdentifier(right: RIdentifier): List<RubyTypeDefinition>? {
        val reference = right.reference
        return when (reference) {
            is PsiPolyVariantReference -> reference
                    .multiResolve(false)
                    .filter { it.element is RIdentifier && (it.element as RIdentifier) in identifiersInCurrentBaseBlock}
                    .mapNotNull { Annotations.definitionsForIdentifier(it.element as RIdentifier) }
                    .flatten()
                    .also {
                        if (it.isEmpty()) {
                            return null
                        }
                    }
                    .toSet()
                    .toList()
            else -> reference?.resolve()?.let {
                if (it !is RIdentifier || it !in identifiersInCurrentBaseBlock) {
                    return null
                }
                return Annotations.definitionsForIdentifier(it)
            }
        }
    }
}