package org.jetbrains.plugins.ruby.types.controlflow

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiPolyVariantReference
import org.jetbrains.plugins.ruby.ruby.lang.psi.basicTypes.RFloatConstant
import org.jetbrains.plugins.ruby.ruby.lang.psi.basicTypes.RIntegerConstant
import org.jetbrains.plugins.ruby.ruby.lang.psi.basicTypes.stringLiterals.RRegexpLiteral
import org.jetbrains.plugins.ruby.ruby.lang.psi.basicTypes.stringLiterals.RStringLiteral
import org.jetbrains.plugins.ruby.ruby.lang.psi.basicTypes.stringLiterals.RStrings
import org.jetbrains.plugins.ruby.ruby.lang.psi.basicTypes.stringLiterals.RWords
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.ArgumentInfo
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RMethod
import org.jetbrains.plugins.ruby.ruby.lang.psi.expressions.RLiteral
import org.jetbrains.plugins.ruby.ruby.lang.psi.methodCall.RCall
import org.jetbrains.plugins.ruby.ruby.lang.psi.variables.RIdentifier
import org.jetbrains.plugins.ruby.types.controlflow.annotations.Annotations
import org.jetbrains.plugins.ruby.types.controlflow.typeinfo.rightOffset
import org.jetbrains.plugins.ruby.types.parser.ast.RubyTypeDeclaration
import org.jetbrains.plugins.ruby.types.parser.ast.RubyTypeDefinition

fun PsiElement.neededOffset(): Int =
        when (this) {
            is RIdentifier -> rightOffset
            else -> textOffset
        }

fun RCall.getCalledMethod(): RMethod? = firstChild?.reference?.resolve() as? RMethod

fun RMethod.minimalArgumentsNumber() = argumentInfos.count { it.type != ArgumentInfo.Type.ARRAY }

class TypesUtil {

    companion object {

        // TODO reaching defs to infer types in flow-sensitively way
        private val identifiersInCurrentBaseBlock = mutableSetOf<RIdentifier>()
        var types: Map<Int, RubyTypeDeclaration> = emptyMap()

        val supportedRdlAnnotations = listOf("type", "RDL.type", "var_type")

        fun analyzeLiteral(right: RLiteral): String {
            return when (right) {
                is RRegexpLiteral -> "Regexp"
                is RWords -> "Words"
                is RStringLiteral -> "String"
                is RFloatConstant -> "Float"
                is RIntegerConstant -> "Integer"
                is RStrings -> "Strings"
                else -> throw RuntimeException()
            }
        }

        fun analyzeIdentifier(right: RIdentifier): List<RubyTypeDefinition>? {
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

        fun resetIdentifiersInBaseBlock() = identifiersInCurrentBaseBlock.clear()
        fun addIdentifierInBaseBlock(identifier: RIdentifier) {
            identifiersInCurrentBaseBlock.add(identifier)
        }

        fun declarationByOffset(offset: Int): RubyTypeDeclaration? {
            return types[offset]
        }

        fun RMethod.minimalArgumentsNumber() = argumentInfos.count { it.type != ArgumentInfo.Type.ARRAY }
    }
}