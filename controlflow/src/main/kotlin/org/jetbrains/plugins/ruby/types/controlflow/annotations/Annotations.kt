package org.jetbrains.plugins.ruby.types.controlflow.annotations

import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.PsiCommentImpl
import org.jetbrains.plugins.ruby.ruby.lang.psi.RPsiElement
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.ArgumentInfo
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RMethod
import org.jetbrains.plugins.ruby.ruby.lang.psi.variables.RIdentifier
import org.jetbrains.plugins.ruby.ruby.lang.psi.visitors.RubyRecursiveElementVisitor
import org.jetbrains.plugins.ruby.types.controlflow.errors.TypeMismatchErrors
import org.jetbrains.plugins.ruby.types.controlflow.signatureanalysis.SignatureTypeAnalysisVisitor
import org.jetbrains.plugins.ruby.types.parser.AnnotationCompiler
import org.jetbrains.plugins.ruby.types.parser.ast.*

object Annotations {
    private val declaredMethodTypes = mutableMapOf<RMethod, RubyTypeDeclaration>()
    private val inferredIdentifierTypes = mutableMapOf<RIdentifier, List<RubyTypeDefinition>>()

    fun collect(rootElement: PsiElement) {
        rootElement.accept(AnnotationsCollector())
        rootElement.accept(SignatureTypeAnalysisVisitor())
    }

    fun verifyMethod(method: RMethod, declaration: RubyTypeDeclaration): Boolean {
        if (declaration.declarationIdentifier != method.methodName?.name) {
            return false
        }

        declaration.typeDefinitions.forEach {
            if (it !is RubyFunctionalType) {
                TypeMismatchErrors.registerNotAFunctionError(method, it)
                return false
            }
            if (method.argumentInfos.size != it.domain.size) {
                TypeMismatchErrors.registerDifferentTypeParametersNumberInSignatureError(method, it)
                return false
            }
            method.argumentInfos.indices.zip(it.domain.elements).forEach { (argId, type) ->
                var hasErrors = false
                if (!type.matches(method.argumentInfos[argId])) {
                    TypeMismatchErrors.registerDeclaredTypeMismatchError(method.arguments[argId], method.argumentInfos[argId].type, type)
                    hasErrors = true
                }
                if (hasErrors) {
                    return false
                }
            }
        }

        return true
    }

    fun registerMethod(method: RMethod, declaration: RubyTypeDeclaration) {
        declaredMethodTypes[method] = declaration
    }

    fun registerIdentifier(identifier: RIdentifier, definitions: List<RubyTypeDefinition>) {
        inferredIdentifierTypes[identifier] = definitions
    }

    fun declarationForMethod(method: RMethod) = declaredMethodTypes[method]
    fun definitionsForIdentifier(identifier: RIdentifier) = inferredIdentifierTypes[identifier]

    private fun RubyTypeAstElement.matches(declaredArgumentInfo: ArgumentInfo): Boolean {
        matchedPairs[declaredArgumentInfo.type]?.let {
            if (!it.isInstance(this)) {
                return false
            }
        }
        return true
    }

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
            lastDeclaration?.let {
                if (!verifyMethod(rMethod, it)) {
                    return@let
                }
                lastDeclaration = it.accept(MethodTypeRefineVisitor(rMethod)) as RubyTypeDeclaration
                Annotations.registerMethod(rMethod, lastDeclaration!!)
            }
            super.visitRMethod(rMethod)
        }

        companion object {
            var lastDeclaration: RubyTypeDeclaration? = null
        }
    }

    private val matchedPairs = mapOf(
            ArgumentInfo.Type.SIMPLE to RubyRegularArgumentType::class,
            ArgumentInfo.Type.ARRAY to RubyVarargArgumentType::class,
            ArgumentInfo.Type.PREDEFINED to RubyOptionalArgumentType::class
    )
}

