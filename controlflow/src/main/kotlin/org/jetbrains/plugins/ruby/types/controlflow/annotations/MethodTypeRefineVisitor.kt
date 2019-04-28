package org.jetbrains.plugins.ruby.types.controlflow.annotations

import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.ArgumentInfo
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RArgument
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RMethod
import org.jetbrains.plugins.ruby.types.controlflow.neededOffset
import org.jetbrains.plugins.ruby.types.parser.ast.*

// TODO doc
class MethodTypeRefineVisitor(private val method: RMethod): RubyTypesAstVisitor<RubyTypeAstElement> {

    private lateinit var lastArgument: RArgument

    override fun visit(declaration: RubyTypeDeclaration) = RubyTypeDeclaration(
            declaration.declarationIdentifier,
            declaration.declarationOffset,
            declaration.typeDefinitions.map { it.accept(this) as RubyTypeDefinition }
    )

    override fun visit(identifier: RubyAtomTypeIdentifier) = identifier

    override fun visit(elements: RubyListOfTypeElements) = elements

    override fun visit(tuple: RubyTupleType) = tuple

    override fun visit(array: RubyArrayType) = array

    override fun visit(functionArgument: RubyFunctionalArgumentType): RubyFunctionalArgumentType {
        return when (lastArgument.type) {
            ArgumentInfo.Type.NAMED -> RubyKeywordArgumentType(
                    functionArgument.typeOffset,
                    functionArgument.typeLength,
                    functionArgument.typeDefinition,
                    lastArgument.identifier?.name.toString(),
                    lastArgument.identifier?.neededOffset() ?: -1
            )
            ArgumentInfo.Type.KEYREQ -> RubyKeyreqArgumentType(
                    functionArgument.typeOffset,
                    functionArgument.typeLength,
                    functionArgument.typeDefinition,
                    lastArgument.identifier?.name.toString(),
                    lastArgument.identifier?.neededOffset() ?: -1
            )
            else -> RubyNamedRegularArgumentType(
                    lastArgument.identifier?.name.toString(),
                    lastArgument.identifier?.neededOffset() ?: -1,
                    functionArgument
            )
        }
    }

    override fun visit(function: RubyFunctionalType) = RubyFunctionalType(
            function.typeOffset,
            function.typeLength,
            function.domain.accept(this) as RubyFunctionalDomain,
            function.codomain
    )

    override fun visit(union: RubyUnionType) = union

    override fun visit(domain: RubyFunctionalDomain) = RubyFunctionalDomain(
            domain.typeOffset,
            domain.typeLength,
            domain.elements.zip(method.arguments).map {
                lastArgument = it.second
                it.first.accept(this) as RubyFunctionalArgumentType
            }
    )
}