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
        val typeDefinitions: List<RubyTypeDefinition>
): RubyTypeAstElement {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)

    override fun toString(): String {
        return "$declarationIdentifier: ${typeDefinitions.joinToString(" || ")}"
    }

    fun withAdditionalDefinition(definition: RubyTypeDefinition): RubyTypeDeclaration {
        return RubyTypeDeclaration(
                declarationIdentifier,
                declarationOffset,
                typeDefinitions + definition
        )
    }
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

    override fun toString(): String {
        return typeIdentifier.joinToString("::")
    }
}

data class RubyListOfTypeElements(
        override val typeOffset: Int,
        val elements: List<RubyTypeAstElement>
): RubyTypeDefinition {
    val size = elements.size

    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)

    override fun toString(): String {
        return elements.joinToString(", ")
    }
}

data class RubyTupleType(
        override val typeOffset: Int,
        val tupleElements: RubyListOfTypeElements
): RubyTypeDefinition {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)

    override fun toString(): String {
        return "($tupleElements)"
    }
}

sealed class RubyArrayType: RubyTypeDefinition {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)
}

data class RubyShortArrayType(
        override val typeOffset: Int,
        val arrayElements: RubyListOfTypeElements
): RubyArrayType() {
    override fun toString(): String {
        return "[$arrayElements]"
    }
}

data class RubyLongArrayType(
        override val typeOffset: Int,
        val arrayElements: RubyListOfTypeElements
): RubyArrayType() {
    // TODO remake to union?
    override fun toString(): String {
        return "[$arrayElements]"
    }
}

data class RubyFunctionalType(
        override val typeOffset: Int,
        val domain: RubyListOfTypeElements,
        val codomain: RubyTypeAstElement
): RubyTypeDefinition {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)

    override fun toString(): String {
        val codomainRepresentation =
                if (codomain is RubyFunctionalType || codomain is RubyUnionType) "($codomain)" else "$codomain"
        return "($domain) -> $codomainRepresentation"
    }
}

/**
 * Used when variable can have different type depending on path or context.
 */
data class RubyUnionType(
        override val typeOffset: Int,
        val possibleTypes: RubyListOfTypeElements
): RubyTypeDefinition {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)

    override fun toString(): String {
        return possibleTypes.elements.joinToString(" | ")
    }
}

/**
 * Value that indicates how long an array must be to be interpreted as [RubyLongArrayType].
 */
const val ARRAY_REPR_THRESHOLD = 5