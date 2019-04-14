package org.jetbrains.plugins.ruby.types.controlflow.signatureanalysis

import org.jetbrains.plugins.ruby.ruby.lang.psi.basicTypes.RFloatConstant
import org.jetbrains.plugins.ruby.ruby.lang.psi.basicTypes.RIntegerConstant
import org.jetbrains.plugins.ruby.ruby.lang.psi.basicTypes.stringLiterals.*
import org.jetbrains.plugins.ruby.ruby.lang.psi.expressions.RAssignmentExpression
import org.jetbrains.plugins.ruby.ruby.lang.psi.expressions.RLiteral
import org.jetbrains.plugins.ruby.ruby.lang.psi.impl.basicTypes.stringLiterals.xString.RXStringLiteralImpl
import org.jetbrains.plugins.ruby.ruby.lang.psi.ruby19.basicTypes.stringLiterals.ROneCharacterString
import org.jetbrains.plugins.ruby.ruby.lang.psi.variables.RIdentifier
import org.jetbrains.plugins.ruby.ruby.lang.psi.visitors.RubyRecursiveElementVisitor
import org.jetbrains.plugins.ruby.types.controlflow.annotations.Annotations
import org.jetbrains.plugins.ruby.types.parser.ast.RubyAtomTypeIdentifier

/**
 * Some simple analyses to reduce some routine of type inference algorithm.
 */
class SignatureTypeAnalysisVisitor: RubyRecursiveElementVisitor() {

    override fun visitRAssignmentExpression(assignmentExpression: RAssignmentExpression) {
        val left = assignmentExpression.firstChild
        val right = assignmentExpression.lastChild
        if (left is RIdentifier) {
            when (right) {
                is RLiteral -> Annotations.registerIdentifier(left, listOf(RubyAtomTypeIdentifier(right.textOffset, listOf(analyzeLiteral(left, right)))))
            }
        }
        super.visitRAssignmentExpression(assignmentExpression)
    }

    private fun analyzeLiteral(left: RIdentifier, right: RLiteral): String {
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
}