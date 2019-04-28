package org.jetbrains.plugins.ruby.types.controlflow.signatureanalysis

import com.intellij.psi.PsiElement
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.RBlockStatement
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.RConditionalStatement
import org.jetbrains.plugins.ruby.ruby.lang.psi.expressions.RAssignmentExpression
import org.jetbrains.plugins.ruby.ruby.lang.psi.expressions.RLiteral
import org.jetbrains.plugins.ruby.ruby.lang.psi.variables.RIdentifier
import org.jetbrains.plugins.ruby.ruby.lang.psi.visitors.RubyRecursiveElementVisitor
import org.jetbrains.plugins.ruby.types.controlflow.TypesUtil
import org.jetbrains.plugins.ruby.types.controlflow.annotations.Annotations
import org.jetbrains.plugins.ruby.types.parser.ast.RubyAtomTypeIdentifier

/**
 * Some simple analyses to reduce some routine of typeDefinition inference algorithm.
 */
class SignatureTypeAnalysisVisitor: RubyRecursiveElementVisitor() {

    override fun visitElement(element: PsiElement) {
        if (element is RConditionalStatement && element is RBlockStatement) {
            TypesUtil.resetIdentifiersInBaseBlock()
        }
        super.visitElement(element)
    }

    override fun visitRAssignmentExpression(assignmentExpression: RAssignmentExpression) {
        val left = assignmentExpression.firstChild
        val right = assignmentExpression.lastChild
        if (left is RIdentifier) {
            TypesUtil.addIdentifierInBaseBlock(left)
            when (right) {
                is RLiteral -> {
                    TypesUtil.analyzeLiteral(right).let {
                        Annotations.registerIdentifier(left, listOf(RubyAtomTypeIdentifier(right.textOffset, it.length, listOf(it))))
                    }

                }
                is RIdentifier -> TypesUtil.analyzeIdentifier(right)?.let { Annotations.registerIdentifier(left, it) }
            }
        }
        super.visitRAssignmentExpression(assignmentExpression)
    }
}