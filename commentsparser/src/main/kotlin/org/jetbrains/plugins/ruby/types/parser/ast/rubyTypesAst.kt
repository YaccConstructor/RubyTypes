package org.jetbrains.plugins.ruby.types.parser.ast

interface RubyTypeAstElement {
    fun <R> accept(visitor: RubyTypesAstVisitor<R>): R
}

interface RubyTypeDefinition: RubyTypeAstElement {
    val typeOffset: Int
}

data class RubyTypeDeclaration(
        val declarationIdentifier: String,
        val declarationOffset: Int,
        val typeDefinition: RubyTypeDefinition
): RubyTypeAstElement {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)
}

data class RubyTypeAnnotation(
        val declarations: List<RubyTypeDeclaration>
): RubyTypeAstElement {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)
}

data class RubyAtomTypeIdentifier(
        override val typeOffset: Int,
        /**
         * Generally. type identifier can consist of several classes.
         * For example, if B is nested class of A, and C is nested class of B,
         * then it's possible to have variable of type A::B::C.
         * Here, A, B, and C are different tupleElements of given list.
         */
        val typeIdentifier: List<String>
): RubyTypeDefinition {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)
}

data class RubyListOfTypeElements(
        override val typeOffset: Int,
        val elements: List<RubyTypeAstElement>
): RubyTypeDefinition {
    val size = elements.size

    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)
}

data class RubyTupleType(
        override val typeOffset: Int,
        val tupleElements: RubyListOfTypeElements
): RubyTypeDefinition {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)
}

sealed class RubyArrayType: RubyTypeDefinition {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)
}

data class RubyShortArrayType(
        override val typeOffset: Int,
        val arrayElements: RubyListOfTypeElements
): RubyArrayType()

data class RubyLongArrayType(
        override val typeOffset: Int,
        val arrayElements: RubyListOfTypeElements
): RubyArrayType()

data class RubyFunctionalType(
        override val typeOffset: Int,
        val domain: RubyListOfTypeElements,
        val codomain: RubyTypeAstElement
): RubyTypeDefinition {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)
}

/**
 * Used when variable can have different type depending on path or context.
 */
data class RubyUnionType(
        override val typeOffset: Int,
        val possibleTypes: RubyListOfTypeElements
): RubyTypeDefinition {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)
}

/**
 * Value that indicates how long an array must be to be interpreted as [RubyLongArrayType].
 */
const val ARRAY_REPR_THRESHOLD = 5