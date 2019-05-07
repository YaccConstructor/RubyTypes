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

    override fun visit(domain: RubyFunctionalDomain): RubyFunctionalDomain {
        if (domain.elements.size == method.arguments.size) {
            return RubyFunctionalDomain(
                    domain.typeOffset,
                    domain.typeLength,
                    mapBijective(domain.elements, method.arguments)
            )
        } else {
            val indexOfArrayParameter = method.argumentInfos.indexOfFirst { it.type == ArgumentInfo.Type.ARRAY }
            if (indexOfArrayParameter == -1) {
                throw RuntimeException()
            }
            /**
             * (a, b, c, d, e, f, g, h)
             * (a, b, c, *x, g, h)
             * iOAP = 3
             * lIOAPID =
             */
            val lastIndexOfArrayParameterInDomain = domain.elements.size - (method.arguments.size - indexOfArrayParameter)
            val precedingArgs = mapBijective(
                    domain.elements.subList(0, indexOfArrayParameter),
                    method.arguments.subList(0, indexOfArrayParameter)
            )
            val succeedingArgs = mapBijective(
                    domain.elements.subList(lastIndexOfArrayParameterInDomain + 1, domain.elements.size),
                    method.arguments.subList(indexOfArrayParameter + 1, method.arguments.size)
            )
            lastArgument = method.arguments[indexOfArrayParameter]
            val firstDefinitionInArray = domain.elements[indexOfArrayParameter]
            val arrayDomainElement = RubyNamedRegularArgumentType(
                    lastArgument.identifier?.name.toString(),
                    lastArgument.identifier?.neededOffset() ?: -1,
//                    RubyRegularArgumentType(
//                            firstDefinitionInArray.typeOffset,
//                            firstDefinitionInArray.typeLength,
                            RubyMultiArgumentType(
                                    firstDefinitionInArray.typeOffset,
                                    firstDefinitionInArray.typeLength,
                                    RubyListOfTypeElements(
                                            firstDefinitionInArray.typeOffset,
                                            firstDefinitionInArray.typeLength,
                                            domain.elements.subList(indexOfArrayParameter, lastIndexOfArrayParameterInDomain + 1)
                                    )
                            )
//                    )
            )
            return RubyFunctionalDomain(
                    domain.typeOffset,
                    domain.typeLength,
                    precedingArgs + listOf(arrayDomainElement) + succeedingArgs
            )
        }
    }

    private fun mapBijective(domainElements: List<RubyFunctionalArgumentType>, methodArgs: List<RArgument>): List<RubyFunctionalArgumentType> {
        return domainElements.zip(methodArgs).map {
            lastArgument = it.second
            it.first.accept(this) as RubyFunctionalArgumentType
        }
    }
}