package org.jetbrains.plugins.ruby.types.controlflow.annotations

import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.PsiCommentImpl
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RMethod
import org.jetbrains.plugins.ruby.ruby.lang.psi.variables.RIdentifier
import org.jetbrains.plugins.ruby.ruby.lang.psi.visitors.RubyRecursiveElementVisitor
import org.jetbrains.plugins.ruby.types.controlflow.signatureanalysis.SignatureTypeAnalysisVisitor
import org.jetbrains.plugins.ruby.types.parser.AnnotationCompiler
import org.jetbrains.plugins.ruby.types.parser.ast.AnyParsingException
import org.jetbrains.plugins.ruby.types.parser.ast.RubyAtomTypeIdentifier
import org.jetbrains.plugins.ruby.types.parser.ast.RubyTypeDeclaration
import org.jetbrains.plugins.ruby.types.parser.ast.RubyTypeDefinition

object Annotations {
    private val declaredMethodTypes = mutableMapOf<RMethod, RubyTypeDeclaration>()
    private val inferredIdentifierTypes = mutableMapOf<RIdentifier, List<RubyTypeDefinition>>()

    fun collect(rootElement: PsiElement) {
        rootElement.accept(AnnotationsCollector())
        rootElement.accept(SignatureTypeAnalysisVisitor())
    }

    fun registerMethod(method: RMethod, declaration: RubyTypeDeclaration) {
        if (declaration.declarationIdentifier == method.methodName?.name) {
            declaredMethodTypes[method] = declaration
        }
    }

    fun registerIdentifier(identifier: RIdentifier, definitions: List<RubyTypeDefinition>) {
        inferredIdentifierTypes[identifier] = definitions
    }

    fun declarationForMethod(method: RMethod) = declaredMethodTypes[method]
    fun definitionsForIdentifier(identifier: RIdentifier) = inferredIdentifierTypes[identifier]

    private class AnnotationsCollector : RubyRecursiveElementVisitor() {

        override fun visitComment(comment: PsiComment) {
            if (comment is PsiCommentImpl) {
                try {
                    val typeAnnotation = AnnotationCompiler.compile(comment.chars.toString(), comment.startOffset)
                    when (typeAnnotation) {
                        is RubyTypeDeclaration -> lastDeclaration = typeAnnotation
                        is RubyTypeDefinition -> lastDeclaration = lastDeclaration?.withAdditionalDefinition(typeAnnotation)
                    }
                } catch (_: AnyParsingException) {
                    // TODO some interaction with user?
                }
            }
        }

        override fun visitRMethod(rMethod: RMethod) {
            lastDeclaration?.let { Annotations.registerMethod(rMethod, it) }
            super.visitRMethod(rMethod)
        }

        companion object {
            var lastDeclaration: RubyTypeDeclaration? = null
        }
    }
}
