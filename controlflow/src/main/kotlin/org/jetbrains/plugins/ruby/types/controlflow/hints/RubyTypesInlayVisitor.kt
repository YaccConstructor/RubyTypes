package org.jetbrains.plugins.ruby.types.controlflow.hints

import com.intellij.codeInsight.daemon.impl.HintRenderer
import com.intellij.openapi.editor.InlayModel
import com.intellij.psi.PsiElement
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RMethod
import org.jetbrains.plugins.ruby.ruby.lang.psi.expressions.RAssignmentExpression
import org.jetbrains.plugins.ruby.ruby.lang.psi.variables.RIdentifier
import org.jetbrains.plugins.ruby.ruby.lang.psi.visitors.RubyElementVisitor
import org.jetbrains.plugins.ruby.ruby.lang.psi.visitors.RubyRecursiveElementVisitor
import org.jetbrains.plugins.ruby.types.controlflow.typeinfo.*
import java.io.File

class RubyTypesInlayVisitor(val inlayModel: InlayModel): RubyRecursiveElementVisitor() {

    private val testTypeInfoCollection = ElementTypeInfoContainer(setOf(
            ElementTypeInfo("n", ElementKind.IDENTIFIER, "Integer", 69),
            ElementTypeInfo("n", ElementKind.IDENTIFIER, "Integer", 156),
            ElementTypeInfo("arr", ElementKind.IDENTIFIER, "Array[Integer]", 163),
            ElementTypeInfo("i", ElementKind.IDENTIFIER, "Integer", 188),
            ElementTypeInfo("s", ElementKind.IDENTIFIER, "String", 262),
            ElementTypeInfo("fib_rec", ElementKind.METHOD, "(Integer) -> Integer", 70),
            ElementTypeInfo("fib_ite", ElementKind.METHOD, "(Integer) -> Integer", 157),
            ElementTypeInfo("length", ElementKind.METHOD, "(String) -> Integer", 263),
            ElementTypeInfo("return_smth", ElementKind.METHOD, "(Any, Any) -> OneOf[String, Integer]", 368),
            ElementTypeInfo("x", ElementKind.IDENTIFIER, "Integer", 281),
            ElementTypeInfo("y", ElementKind.IDENTIFIER, "Integer", 316)
    ))

    override fun visitRMethod(rMethod: RMethod) {
        inlayModel.addInlineElement(rMethod.rightOffset, true, HintRenderer(": " + rMethod.inferredType(testTypeInfoCollection)))
        super.visitRMethod(rMethod)
    }

    override fun visitRAssignmentExpression(assignmentExpression: RAssignmentExpression) {
        val rIdentifier = assignmentExpression.`object` as? RIdentifier
        if (rIdentifier != null) {
            visitIdentifier(rIdentifier)
        }
        super.visitRAssignmentExpression(assignmentExpression)
    }

    override fun visitRIdentifier(rIdentifier: RIdentifier) {
        if (rIdentifier.isLambdaParameterDeclaration || rIdentifier.isBlockParameterDeclaration) {
            visitIdentifier(rIdentifier)
        }
    }

    fun visitIdentifier(rIdentifier: RIdentifier) {
        inlayModel.addInlineElement(rIdentifier.rightOffset, true, HintRenderer(": " + rIdentifier.inferredType(testTypeInfoCollection)))
    }
}