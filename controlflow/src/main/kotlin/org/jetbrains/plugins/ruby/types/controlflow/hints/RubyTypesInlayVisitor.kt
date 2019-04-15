package org.jetbrains.plugins.ruby.types.controlflow.hints

import com.intellij.codeInsight.daemon.impl.HintRenderer
import com.intellij.openapi.editor.InlayModel
import org.jetbrains.plugins.ruby.ruby.lang.psi.expressions.RAssignmentExpression
import org.jetbrains.plugins.ruby.ruby.lang.psi.variables.RIdentifier
import org.jetbrains.plugins.ruby.ruby.lang.psi.visitors.RubyRecursiveElementVisitor
import org.jetbrains.plugins.ruby.types.controlflow.annotations.Annotations
import org.jetbrains.plugins.ruby.types.controlflow.typeinfo.*
import org.jetbrains.plugins.ruby.types.parser.ast.RubyTypeDeclaration

class RubyTypesInlayVisitor(val inlayModel: InlayModel, val knownTypes: Map<Int, RubyTypeDeclaration>): RubyRecursiveElementVisitor() {

    override fun visitRAssignmentExpression(assignmentExpression: RAssignmentExpression) {
        (assignmentExpression.firstChild as? RIdentifier)?.let { visitIdentifier(it) }
        super.visitRAssignmentExpression(assignmentExpression)
    }

    override fun visitRIdentifier(rIdentifier: RIdentifier) {
        if (rIdentifier.isLambdaParameterDeclaration || rIdentifier.isBlockParameterDeclaration) {
            visitIdentifier(rIdentifier)
        }
    }

    private fun visitIdentifier(rIdentifier: RIdentifier) {
        knownTypes[rIdentifier.textOffset + rIdentifier.textLength]?.let {
            inlayModel.addInlineElement(rIdentifier.rightOffset, true, HintRenderer(": ${it.typeDefinitions.joinToString(" || ")}"))
        }
    }
}