package org.jetbrains.plugins.ruby.types.controlflow.errors

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.openapi.util.TextRange
import org.jetbrains.plugins.ruby.ruby.lang.psi.RPsiElement
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.ArgumentInfo
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RArgument
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RMethod
import org.jetbrains.plugins.ruby.types.controlflow.minimalArgumentsNumber
import org.jetbrains.plugins.ruby.types.parser.ast.RubyFunctionalArgumentType
import org.jetbrains.plugins.ruby.types.parser.ast.RubyFunctionalType
import org.jetbrains.plugins.ruby.types.parser.ast.RubyTypeDefinition

object TypeMismatchErrors {
    private val typeMismatchErrors = mutableMapOf<RPsiElement, TypeMismatchError>()

    fun registerNotEnoughArgumentTypes(element: RMethod, type: RubyFunctionalType) {
        NotEnoughArgumentsInSignatureError(
                element,
                type
        ).let {
            typeMismatchErrors[element] = it
        }
    }

    fun registerNotAFunctionError(element: RMethod, type: RubyTypeDefinition) {
        NotAFunctionalTypeError(
                element,
                type
        ).let {
            typeMismatchErrors[element] = it
        }
    }

    fun registerDeclaredTypeMismatchError(element: RArgument, argumentKind: ArgumentInfo.Type, type: RubyFunctionalArgumentType) {
        DeclaredTypeMismatchError(
                argumentKind,
                type
        ).let {
            typeMismatchErrors[element] = it
        }
    }

    fun get() = typeMismatchErrors
    fun get(element: RPsiElement) = typeMismatchErrors[element]

    fun reset() {
        typeMismatchErrors.clear()
    }
}

interface TypeMismatchError {
    /**
     * Represents how many symbols must be highlighted by annotator
     */
    val errorRange: TextRange

    fun reportError(element: RPsiElement, annotationHolder: AnnotationHolder) {
        reportError(errorRange, annotationHolder)
    }

    fun reportError(range: TextRange, annotationHolder: AnnotationHolder)
}

data class NotEnoughArgumentsInSignatureError(
        val method: RMethod,
        val type: RubyFunctionalType
): TypeMismatchError {

    override val errorRange = TextRange(type.typeOffset, type.typeOffset + type.typeLength)

    override fun reportError(range: TextRange, annotationHolder: AnnotationHolder) {
        annotationHolder.createErrorAnnotation(
                range,
                "Method declaration has at least ${method.minimalArgumentsNumber()} type parameters " +
                        "but one of type annotations has ${type.domain.size}"
        )
    }
}

data class NotAFunctionalTypeError(
        val method: RMethod,
        val type: RubyTypeDefinition
): TypeMismatchError {

    override val errorRange = TextRange(type.typeOffset, type.typeOffset + type.typeLength)

    override fun reportError(range: TextRange, annotationHolder: AnnotationHolder) {
        annotationHolder.createErrorAnnotation(
                range,
                "$type is not a functional type"
        )
    }
}

data class DeclaredTypeMismatchError(
        val argumentKind: ArgumentInfo.Type,
        val type: RubyFunctionalArgumentType
): TypeMismatchError {

    override val errorRange = TextRange(type.typeOffset, type.typeOffset + type.typeLength)

    override fun reportError(range: TextRange, annotationHolder: AnnotationHolder) {
        annotationHolder.createErrorAnnotation(
                range,
                "Type mismatch: declared as ${argumentKind.name} argument but ${type.kind} found"
        )
    }
}