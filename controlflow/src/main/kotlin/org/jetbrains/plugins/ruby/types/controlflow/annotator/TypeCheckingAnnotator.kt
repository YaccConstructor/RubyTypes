package org.jetbrains.plugins.ruby.types.controlflow.annotator

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement
import org.jetbrains.plugins.ruby.ruby.codeInsight.ArgMapping
import org.jetbrains.plugins.ruby.ruby.lang.psi.RPsiElement
import org.jetbrains.plugins.ruby.ruby.lang.psi.assoc.RAssoc
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.ArgumentInfo
import org.jetbrains.plugins.ruby.ruby.lang.psi.expressions.RLiteral
import org.jetbrains.plugins.ruby.ruby.lang.psi.methodCall.RCall
import org.jetbrains.plugins.ruby.types.controlflow.TypesUtil
import org.jetbrains.plugins.ruby.types.controlflow.errors.TypeMismatchErrors
import org.jetbrains.plugins.ruby.types.controlflow.getCalledMethod
import org.jetbrains.plugins.ruby.types.parser.ast.*

class TypeCheckingAnnotator: Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is RPsiElement) {
            TypeMismatchErrors.get(element)?.reportError(element, holder)
            if (element !is RCall) {
                return
            }
            element.getCalledMethod()?.let { callee ->
                val methodTypeDeclaration = TypesUtil.declarationByOffset(callee.textOffset) ?: return
                val args = element.callArguments.elements
                val mapping: Map<ArgumentInfo, ArgMapping.MappingRange> = ArgMapping.get(callee.argumentInfos, args)
                        ?: return
                val probableTypeMismatchingResult = mutableListOf<ListOfTypeMismatches>()
                methodTypeDeclaration.typeDefinitions.forEach { methodType ->
                    val erroneousTypes = checkDefinition(args, (methodType as RubyFunctionalType).domain.elements.map { it as RubyNamedArgumentType }, mapping)
                    probableTypeMismatchingResult.add(ListOfTypeMismatches(erroneousTypes))
                }
                if (probableTypeMismatchingResult.none { it.mismatches.isEmpty() }) {
                    println(probableTypeMismatchingResult.joinToString(" -$- ") { it.mismatches.joinToString { it.toString() } })
                    if (probableTypeMismatchingResult.size == 1) {
                        probableTypeMismatchingResult.first().mismatches.forEach {
                            it.reportError(holder)
                        }
                    } else {
                        holder.createErrorAnnotation(
                                element,
                                "None of the following types are satisfied:\n" +
                                        methodTypeDeclaration.typeDefinitions.joinToString("\n") { it.toStringIgnoreNames() }
                        )
                    }
                }
            }
        }
    }

    /**
     * Returns list of call arguments that are definitely don't satisfy the given argument types
     */
    private fun checkDefinition(callArguments: List<RPsiElement>, argumentTypes: List<RubyNamedArgumentType>, mapping: Map<ArgumentInfo, ArgMapping.MappingRange>): List<CallTypeMismatchError> {
        val result = mutableListOf<CallTypeMismatchError>()
        mapping.forEach { info, range ->
            val argument = argumentTypes.find { it.argumentName == info.name } ?: return@forEach
            when (info.type) {
                ArgumentInfo.Type.KEYREQ -> {
                    range.iterator().forEach { i ->
                        val arg = callArguments[i]
                        if (arg is RAssoc) {
                            val value = arg.value
                            value?.let {
                                typeFrom(it)?.let { type ->
                                    if (!type.typeEquals(argument)) {
                                        result.add(
                                                CallTypeMismatchError(
                                                        element = it,
                                                        expectedType = argument,
                                                        actualType = type
                                                )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                else -> {
                    range.iterator().forEach { i ->
                        val arg = callArguments[i]
                        typeFrom(arg)?.let { type ->
                            if (!type.typeEquals(argument)) {
                                result.add(
                                        CallTypeMismatchError(
                                                element = arg,
                                                expectedType = argument,
                                                actualType = type
                                        )
                                )
                            }
                        }
                    }
                }
            }
        }
        return result
    }

    private fun typeFrom(element: RPsiElement): RubyTypeDefinition? {
        when (element) {
            is RLiteral -> return TypesUtil.analyzeLiteral(element).let { RubyAtomTypeIdentifier(0, it.length, listOf(it)) }
            else -> {
                val (id, _, typeDefinitions) = TypesUtil.declarationByOffset(element.textOffset)
                        ?: return null
                if (typeDefinitions.isEmpty()) {
                    return null
                } else if (typeDefinitions.size == 1) {
                    return typeDefinitions[0]
                } else {
                    val definition = typeDefinitions.joinToString(" | ")
                    return RubyUnionType(typeDefinitions[0].typeOffset, definition.length, RubyListOfTypeElements(typeDefinitions[0].typeOffset, definition.length, typeDefinitions))
                }
            }
        }
    }

    data class ListOfTypeMismatches(
            val mismatches: List<CallTypeMismatchError>
    ) {
        fun errorRepresentation(holder: AnnotationHolder) {
        }
    }

    data class CallTypeMismatchError(
            val element: RPsiElement,
            val expectedType: RubyTypeDefinition,
            val actualType: RubyTypeDefinition
    ) {
        fun reportError(holder: AnnotationHolder) {
            holder.createErrorAnnotation(element, "<b>Type mismatch; expected ${expectedType.toStringIgnoreNames()} but ${actualType.toStringIgnoreNames()} found</b>")
        }
    }
}