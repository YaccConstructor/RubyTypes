package org.jetbrains.plugins.ruby.types.parser.ast

interface TypeMatchable<T: TypeMatchable<T>> {
    /**
     * Compare two types with no subtyping relation considering
     */
    fun matchesInvariant(other: T): Boolean

//    TODO
//    fun matchesCovariant(other: T): Boolean
}

interface RubyTypeAstElement: TypeMatchable<RubyTypeAstElement> {
    fun <R> accept(visitor: RubyTypesAstVisitor<R>): R
}

interface RubyTypeDefinition: RubyTypeAstElement {
    val typeOffset: Int
    val typeLength: Int
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

    override fun matchesInvariant(other: RubyTypeAstElement): Boolean {
        if (other !is RubyTypeDefinition) {
            return false
        }
        return typeDefinitions.any { it.matchesInvariant(other) }
    }
}

data class RubyAtomTypeIdentifier(
        override val typeOffset: Int,
        override val typeLength: Int,
        /**
         * Generally. typeDefinition identifier can consist of several classes.
         * For example, if B is nested class of A, and C is nested class of B,
         * then it's possible to have variable of typeDefinition A::B::C.
         * Here, A, B, and C are different tupleElements of given list.
         */
        val typeIdentifier: List<String>
): RubyTypeDefinition {

    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)

    override fun matchesInvariant(other: RubyTypeAstElement): Boolean {
        if (other !is RubyAtomTypeIdentifier) {
            return false
        }
        return typeIdentifier == other.typeIdentifier
    }

    override fun toString(): String {
        return typeIdentifier.joinToString("::")
    }
}

// TODO implement it
object AnyType

data class RubyListOfTypeElements(
        override val typeOffset: Int,
        override val typeLength: Int,
        val elements: List<RubyTypeAstElement>
): RubyTypeDefinition {
    val size = elements.size

    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)

    override fun matchesInvariant(other: RubyTypeAstElement): Boolean {
        if (other !is RubyListOfTypeElements) {
            return false
        }
        return elements == other.elements
    }

    override fun toString(): String {
        return elements.joinToString(", ")
    }
}

data class RubyTupleType(
        override val typeOffset: Int,
        override val typeLength: Int,
        val tupleElements: RubyListOfTypeElements
): RubyTypeDefinition {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)

    override fun matchesInvariant(other: RubyTypeAstElement): Boolean {
        if (other !is RubyTupleType) {
            return false
        }
        return tupleElements == other.tupleElements
    }

    override fun toString(): String {
        return "($tupleElements,)"
    }
}

sealed class RubyArrayType: RubyTypeDefinition {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)
}

data class RubyShortArrayType(
        override val typeOffset: Int,
        override val typeLength: Int,
        val arrayElements: RubyListOfTypeElements
): RubyArrayType() {

    override fun matchesInvariant(other: RubyTypeAstElement): Boolean {
        if (other !is RubyShortArrayType) {
            return false
        }
        return arrayElements == other.arrayElements
    }

    override fun toString(): String {
        return "[$arrayElements]"
    }
}

data class RubyLongArrayType(
        override val typeOffset: Int,
        override val typeLength: Int,
        val arrayElements: RubyListOfTypeElements
): RubyArrayType() {

    override fun matchesInvariant(other: RubyTypeAstElement): Boolean {
        if (other !is RubyLongArrayType) {
            return false
        }
        return arrayElements == other.arrayElements
    }

    // TODO remake to union?
    override fun toString(): String {
        return "[$arrayElements]"
    }
}

sealed class RubyFunctionalArgumentType(
        override val typeOffset: Int,
        override val typeLength: Int,
        open val typeDefinition: RubyTypeDefinition
): RubyTypeDefinition {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>) = visitor.visit(this)
}

data class RubyRegularArgumentType(
        override val typeOffset: Int,
        override val typeLength: Int,
        override val typeDefinition: RubyTypeDefinition
): RubyFunctionalArgumentType(typeOffset, typeLength, typeDefinition) {
    override fun matchesInvariant(other: RubyTypeAstElement): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toString() = "$typeDefinition"
}

data class RubyVarargArgumentType(
        override val typeOffset: Int,
        override val typeLength: Int,
        override val typeDefinition: RubyTypeDefinition
): RubyFunctionalArgumentType(typeOffset, typeLength, typeDefinition) {
    override fun matchesInvariant(other: RubyTypeAstElement): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toString() = "*$typeDefinition"
}

data class RubyOptionalArgumentType(
        override val typeOffset: Int,
        override val typeLength: Int,
        override val typeDefinition: RubyTypeDefinition
): RubyFunctionalArgumentType(typeOffset, typeLength, typeDefinition) {
    override fun matchesInvariant(other: RubyTypeAstElement): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toString() = "$typeDefinition?"
}

sealed class RubyNamedArgumentType(
        override val typeOffset: Int,
        override val typeLength: Int,
        override val typeDefinition: RubyTypeDefinition,
        open val argumentName: String,
        open val argumentOffset: Int
): RubyFunctionalArgumentType(typeOffset, typeLength, typeDefinition) {

    override fun toString() = "$argumentName: $typeDefinition"
}

data class RubyKeywordArgumentType(
        override val typeOffset: Int,
        override val typeLength: Int,
        override val typeDefinition: RubyTypeDefinition,
        override val argumentName: String,
        override val argumentOffset: Int
): RubyNamedArgumentType(typeOffset, typeLength, typeDefinition, argumentName, argumentOffset) {
    override fun matchesInvariant(other: RubyTypeAstElement): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

data class RubyRequiredArgumentType(
        override val typeOffset: Int,
        override val typeLength: Int,
        override val typeDefinition: RubyTypeDefinition,
        override val argumentName: String,
        override val argumentOffset: Int
): RubyNamedArgumentType(typeOffset, typeLength, typeDefinition, argumentName, argumentOffset) {
    override fun matchesInvariant(other: RubyTypeAstElement): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

data class RubyFunctionalType(
        override val typeOffset: Int,
        override val typeLength: Int,
        val domain: RubyListOfTypeElements,
        val codomain: RubyTypeAstElement
): RubyTypeDefinition {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)

    override fun matchesInvariant(other: RubyTypeAstElement): Boolean {
        if (other !is RubyFunctionalType) {
            return false
        }
        return domain == other.domain && codomain == other.codomain
    }

    override fun toString(): String {
        val codomainRepresentation =
                if (codomain is RubyFunctionalType || codomain is RubyUnionType) "($codomain)" else "$codomain"
        return "($domain) -> $codomainRepresentation"
    }
}

/**
 * Used when variable can have different typeDefinition depending on path or context.
 */
data class RubyUnionType(
        override val typeOffset: Int,
        override val typeLength: Int,
        val possibleTypes: RubyListOfTypeElements
): RubyTypeDefinition {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)

    override fun matchesInvariant(other: RubyTypeAstElement): Boolean {
        if (other !is RubyUnionType) {
            return false
        }
        return possibleTypes == other.possibleTypes
    }

    override fun toString(): String {
        return possibleTypes.elements.joinToString(" | ")
    }
}

/**
 * Value that indicates how long an array must be to be interpreted as [RubyLongArrayType].
 */
const val ARRAY_REPR_THRESHOLD = 5