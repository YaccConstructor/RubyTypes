package org.jetbrains.plugins.ruby.types.controlflow.annotations

import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiPolyVariantReference
import com.intellij.psi.impl.source.tree.PsiCommentImpl
import org.jetbrains.plugins.ruby.ruby.lang.psi.RFile
import org.jetbrains.plugins.ruby.ruby.lang.psi.basicTypes.RSymbol
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.classes.RClass
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.ArgumentInfo
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RMethod
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.modules.RModule
import org.jetbrains.plugins.ruby.ruby.lang.psi.methodCall.RCall
import org.jetbrains.plugins.ruby.ruby.lang.psi.references.RReference
import org.jetbrains.plugins.ruby.ruby.lang.psi.variables.RConstant
import org.jetbrains.plugins.ruby.ruby.lang.psi.variables.RIdentifier
import org.jetbrains.plugins.ruby.ruby.lang.psi.visitors.RubyRecursiveElementVisitor
import org.jetbrains.plugins.ruby.types.controlflow.TypesUtil
import org.jetbrains.plugins.ruby.types.controlflow.errors.TypeMismatchErrors
import org.jetbrains.plugins.ruby.types.controlflow.minimalArgumentsNumber
import org.jetbrains.plugins.ruby.types.controlflow.signatureanalysis.SignatureTypeAnalysisVisitor
import org.jetbrains.plugins.ruby.types.parser.AnnotationCompiler
import org.jetbrains.plugins.ruby.types.parser.ast.*
import org.jetbrains.plugins.ruby.types.rdl.RdlAnnotationCompiler

object Annotations {
    private val declaredMethodTypes = mutableMapOf<RMethod, RubyTypeDeclaration>()
    private val inferredIdentifierTypes = mutableMapOf<RIdentifier, List<RubyTypeDefinition>>()

    fun resetAndCollect(rootElement: PsiElement) {
        declaredMethodTypes.clear()
        inferredIdentifierTypes.clear()
        rootElement.accept(AnnotationsCollector())
        rootElement.accept(SignatureTypeAnalysisVisitor())
    }

    fun verifyMethod(method: RMethod, declaration: RubyTypeDeclaration): Boolean {
        if (declaration.declarationIdentifier != method.fqnWithNesting.fullPath) {
            return false
        }

        declaration.typeDefinitions.forEach {
            if (it !is RubyFunctionalType) {
                TypeMismatchErrors.registerNotAFunctionError(method, it)
                return false
            }
            if (it.domain.size < method.minimalArgumentsNumber()) {
                TypeMismatchErrors.registerNotEnoughArgumentTypes(method, it)
                return false
            }
        }

        return true
    }

    /**
     * Find all methods with given name and enclosing class
     */
    fun resolveRdlCalledMethod(receiver: RConstant, methodName: RSymbol): Set<RMethod> {
        val reference = receiver.reference
        when (reference) {
            is RReference -> return resolveRdlCalledMethodForContainer(reference.resolve(), methodName)
            is PsiPolyVariantReference -> return reference.multiResolve(false).flatMapTo(HashSet()) { resolveRdlCalledMethodForContainer(it.element, methodName) }
        }
        return emptySet()
    }

    private fun resolveRdlCalledMethodForContainer(container: PsiElement?, methodName: RSymbol): Set<RMethod> {
        return when (container) {
            is RClass -> setOf(container.findMethodByName(methodName.value) ?: return emptySet())
            is RModule -> setOf(container.findMethodByName(methodName.value) ?: return emptySet())
            is RFile -> container.children.filter { it is RMethod && it.methodName?.name == methodName.value }.map {it as RMethod}.toSet()
            else -> return emptySet()
        }
    }

    fun registerMethod(method: RMethod, declaration: RubyTypeDeclaration) {
        declaredMethodTypes[method] = declaration
    }

    fun registerMethodOrExtend(method: RMethod, declaration: RubyTypeDeclaration) {
        if (method !in declaredMethodTypes) {
            declaredMethodTypes[method] = declaration
        } else {
            for (definition in declaration.typeDefinitions) {
                declaredMethodTypes[method] = declaredMethodTypes[method]!!.withAdditionalDefinition(definition)
            }
        }
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

        override fun visitRCall(rCall: RCall) {
            if (rCall.name !in TypesUtil.supportedRdlAnnotations || rCall.callArguments.elements.isEmpty()) {
                return
            }
            // TODO calls w/o receiver / method names
            val calledMethods = resolveRdlCalledMethod(rCall.callArguments.elements[0] as RConstant, rCall.callArguments.elements[1] as RSymbol)
            val typeAnnotation = RdlAnnotationCompiler.compile(rCall.text, rCall.textOffset)
            calledMethods.forEach {
                typeAnnotation as RubyTypeDeclaration
                if (verifyMethod(it, typeAnnotation)) {
                    Annotations.registerMethodOrExtend(it, typeAnnotation.accept(MethodTypeRefineVisitor(it)) as RubyTypeDeclaration)
                }
            }
            super.visitRCall(rCall)
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

