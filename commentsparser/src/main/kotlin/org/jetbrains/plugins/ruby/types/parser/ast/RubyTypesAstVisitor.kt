package org.jetbrains.plugins.ruby.types.parser.ast

interface RubyTypesAstVisitor<out R> {

    fun visit(declaration: RubyTypeDeclaration): R

    fun visit(identifier: RubyAtomTypeIdentifier): R

    fun visit(elements: RubyListOfTypeElements): R

    fun visit(tuple: RubyTupleType): R

    fun visit(array: RubyArrayType): R

    fun visit(functionArgument: RubyFunctionalArgumentType): R

    fun visit(function: RubyFunctionalType): R

    fun visit(union: RubyUnionType): R
}