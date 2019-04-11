package org.jetbrains.plugins.ruby.types.parser.ast

interface RubyTypesAstVisitor<out R> {

    fun visit(declarationList: RubyTypeAnnotation): R

    fun visit(declaration: RubyTypeDeclaration): R

    fun visit(identifier: RubyAtomTypeIdentifier): R

    fun visit(elements: RubyListOfTypeElements): R

    fun visit(tuple: RubyTupleType): R

    fun visit(array: RubyArrayType): R

    fun visit(function: RubyFunctionalType): R

    fun visit(union: RubyUnionType): R
}