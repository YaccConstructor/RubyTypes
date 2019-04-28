package org.jetbrains.plugins.ruby.types.parser.ast

interface TypeAcceptable<T: TypeAcceptable<T>> {
    /**
     * Compare two types with no subtyping relation considering.
     * Check whether [other] can be used in contexts where
     * receiver is used.
     * Other words, it checks relation other <: this.
     */
    fun acceptsInvariant(other: T): Boolean

//    TODO
//    fun matchesCovariant(other: T): Boolean
}

interface RubyTypeAstElement {
    fun <R> accept(visitor: RubyTypesAstVisitor<R>): R
}

interface RubyTypeDefinition: RubyTypeAstElement, TypeAcceptable<RubyTypeDefinition> {
    val typeOffset: Int
    val typeLength: Int

    fun typeEquals(other: RubyTypeDefinition): Boolean

    fun toStringIgnoreNames(): String
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

    fun hasType(type: RubyTypeDefinition) = typeDefinitions.any { it.typeEquals(type) }

    fun acceptsInvariant(other: RubyTypeDefinition): Boolean {
        return typeDefinitions.any { it.acceptsInvariant(other) }
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

    override fun typeEquals(other: RubyTypeDefinition): Boolean {
        return when (other) {
            is RubyAtomTypeIdentifier -> typeIdentifier == other.typeIdentifier
            is RubyFunctionalArgumentType -> other.typeEquals(this)
            else -> false
        }
    }

    override fun acceptsInvariant(other: RubyTypeDefinition) = typeEquals(other)

    override fun toString(): String {
        return typeIdentifier.joinToString("::")
    }

    override fun toStringIgnoreNames() = toString()
}

data class RubyListOfTypeElements(
        override val typeOffset: Int,
        override val typeLength: Int,
        val elements: List<RubyTypeDefinition>
): RubyTypeDefinition {
    val size = elements.size

    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)

    override fun typeEquals(other: RubyTypeDefinition): Boolean {
        if (other !is RubyListOfTypeElements || elements.size != other.elements.size) {
            return false
        }
        return elements.zip(other.elements).all { it.first.typeEquals(it.second) }
    }

    override fun acceptsInvariant(other: RubyTypeDefinition): Boolean {
        if (other !is RubyListOfTypeElements) {
            return false
        }
        return elements == other.elements
    }

    override fun toString(): String {
        return elements.joinToString(", ")
    }

    override fun toStringIgnoreNames(): String {
        return elements.joinToString(", ") { it.toStringIgnoreNames() }
    }
}

data class RubyFunctionalDomain(
        override val typeOffset: Int,
        override val typeLength: Int,
        val elements: List<RubyFunctionalArgumentType>
): RubyTypeDefinition {
    val size = elements.size

    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)

    override fun typeEquals(other: RubyTypeDefinition): Boolean {
        if (other !is RubyFunctionalDomain || elements.size != other.elements.size) {
            return false
        }
        return elements.zip(other.elements).all { it.first.typeEquals(it.second) }
    }

    // TODO REMAAAAAKE (consider keywords and so on)
    override fun acceptsInvariant(other: RubyTypeDefinition): Boolean {
        if (other !is RubyListOfTypeElements) {
            return false
        }
        return elements == other.elements
    }

    override fun toString(): String {
        return elements.joinToString(", ")
    }

    override fun toStringIgnoreNames(): String {
        return elements.joinToString(", ") { it.toStringIgnoreNames() }
    }
}

data class RubyTupleType(
        override val typeOffset: Int,
        override val typeLength: Int,
        val tupleElements: RubyListOfTypeElements
): RubyTypeDefinition {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)

    override fun typeEquals(other: RubyTypeDefinition): Boolean {
        if (other is RubyFunctionalArgumentType) {
            return other.acceptsInvariant(this)
        }
        if (other !is RubyTupleType) {
            return false
        }
        return tupleElements.typeEquals(other.tupleElements)
    }

    override fun acceptsInvariant(other: RubyTypeDefinition): Boolean {
        if (other is RubyFunctionalArgumentType) {
            return other.acceptsInvariant(this)
        }
        if (other !is RubyTupleType) {
            return false
        }
        return tupleElements == other.tupleElements
    }

    override fun toString(): String {
        return "($tupleElements,)"
    }

    override fun toStringIgnoreNames() = toString()
}

sealed class RubyArrayType: RubyTypeDefinition {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)
}

data class RubyShortArrayType(
        override val typeOffset: Int,
        override val typeLength: Int,
        val arrayElements: RubyListOfTypeElements
): RubyArrayType() {

    override fun typeEquals(other: RubyTypeDefinition): Boolean {
        if (other is RubyFunctionalArgumentType) {
            return other.acceptsInvariant(this)
        }
        if (other !is RubyShortArrayType) {
            return false
        }
        return arrayElements.typeEquals(other.arrayElements)
    }

    override fun acceptsInvariant(other: RubyTypeDefinition): Boolean {
        if (other is RubyFunctionalArgumentType) {
            return other.acceptsInvariant(this)
        }
        if (other !is RubyShortArrayType) {
            return false
        }
        return arrayElements == other.arrayElements
    }

    override fun toString(): String {
        return "[$arrayElements]"
    }

    override fun toStringIgnoreNames() = toString()
}

data class RubyLongArrayType(
        override val typeOffset: Int,
        override val typeLength: Int,
        val lub: RubyTypeDefinition
): RubyArrayType() {

    override fun typeEquals(other: RubyTypeDefinition): Boolean {
        if (other is RubyFunctionalArgumentType) {
            return other.acceptsInvariant(this)
        }
        if (other !is RubyLongArrayType) {
            return false
        }
        return lub.typeEquals(other.lub)
    }

    override fun acceptsInvariant(other: RubyTypeDefinition): Boolean {
        if (other is RubyFunctionalArgumentType) {
            return other.acceptsInvariant(this)
        }
        if (other !is RubyLongArrayType) {
            return false
        }
        return lub == other.lub
    }

    override fun toString(): String {
        return "Array<$lub>"
    }

    override fun toStringIgnoreNames() = toString()
}

sealed class RubyFunctionalArgumentType(
        override val typeOffset: Int,
        override val typeLength: Int,
        open val typeDefinition: RubyTypeDefinition
): RubyTypeDefinition {

    abstract val kind: ArgumentKind

    override fun typeEquals(other: RubyTypeDefinition): Boolean {
        return typeDefinition.typeEquals(other)
    }

    override fun acceptsInvariant(other: RubyTypeDefinition): Boolean {
        return typeDefinition == other
    }

    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)
}

data class RubyRegularArgumentType(
        override val typeOffset: Int,
        override val typeLength: Int,
        override val typeDefinition: RubyTypeDefinition
): RubyFunctionalArgumentType(typeOffset, typeLength, typeDefinition) {

    override val kind = ArgumentKind.REGULAR

    override fun toString() = "$typeDefinition"

    override fun toStringIgnoreNames() = typeDefinition.toStringIgnoreNames()
}

data class RubyVarargArgumentType(
        override val typeOffset: Int,
        override val typeLength: Int,
        override val typeDefinition: RubyTypeDefinition
): RubyFunctionalArgumentType(typeOffset, typeLength, typeDefinition) {

    override val kind = ArgumentKind.VARARG

    override fun toString() = "*$typeDefinition"

    override fun toStringIgnoreNames() = "*${typeDefinition.toStringIgnoreNames()}"
}

data class RubyOptionalArgumentType(
        override val typeOffset: Int,
        override val typeLength: Int,
        override val typeDefinition: RubyTypeDefinition
): RubyFunctionalArgumentType(typeOffset, typeLength, typeDefinition) {

    override val kind = ArgumentKind.OPTIONAL

    override fun toString() = "$typeDefinition?"

    override fun toStringIgnoreNames() = "${typeDefinition.toStringIgnoreNames()}?"
}

sealed class RubyNamedArgumentType(
        override val typeOffset: Int,
        override val typeLength: Int,
        override val typeDefinition: RubyTypeDefinition,
        open val argumentName: String,
        open val argumentOffset: Int
): RubyFunctionalArgumentType(typeOffset, typeLength, typeDefinition)

data class RubyNamedRegularArgumentType(
        override val argumentName: String,
        override val argumentOffset: Int,
        val underlyingType: RubyFunctionalArgumentType
): RubyNamedArgumentType(
        underlyingType.typeOffset,
        underlyingType.typeLength,
        underlyingType.typeDefinition,
        argumentName,
        argumentOffset
) {

    override val kind = underlyingType.kind

    override fun toString() = "$argumentName: $underlyingType"

    override fun toStringIgnoreNames() = underlyingType.toStringIgnoreNames()
}

data class RubyKeywordArgumentType(
        override val typeOffset: Int,
        override val typeLength: Int,
        override val typeDefinition: RubyTypeDefinition,
        override val argumentName: String,
        override val argumentOffset: Int
): RubyNamedArgumentType(typeOffset, typeLength, typeDefinition, argumentName, argumentOffset) {

    override val kind = ArgumentKind.KEYWORD

    override fun toString() = "$argumentName: $typeDefinition"

    override fun toStringIgnoreNames() = typeDefinition.toStringIgnoreNames()
}

data class RubyKeyreqArgumentType(
        override val typeOffset: Int,
        override val typeLength: Int,
        override val typeDefinition: RubyTypeDefinition,
        override val argumentName: String,
        override val argumentOffset: Int
): RubyNamedArgumentType(typeOffset, typeLength, typeDefinition, argumentName, argumentOffset) {

    override val kind = ArgumentKind.KEYREQ

    override fun toString() = "$argumentName: $typeDefinition"

    override fun toStringIgnoreNames() = typeDefinition.toStringIgnoreNames()
}

data class RubyFunctionalType(
        override val typeOffset: Int,
        override val typeLength: Int,
        val domain: RubyFunctionalDomain,
        val codomain: RubyTypeDefinition
): RubyTypeDefinition {
    override fun <R> accept(visitor: RubyTypesAstVisitor<R>): R = visitor.visit(this)

    override fun typeEquals(other: RubyTypeDefinition): Boolean {
        if (other !is RubyFunctionalType) {
            return false
        }
        return domain.typeEquals(other.domain) && codomain.typeEquals(other.codomain)
    }

    override fun acceptsInvariant(other: RubyTypeDefinition): Boolean {
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

    override fun toStringIgnoreNames(): String {
        val codomainRepresentation =
                if (codomain is RubyFunctionalType || codomain is RubyUnionType) "($codomain)" else "$codomain"
        return "(${domain.toStringIgnoreNames()}) -> $codomainRepresentation"
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

    override fun typeEquals(other: RubyTypeDefinition): Boolean {
        if (other is RubyFunctionalArgumentType) {
            return other.acceptsInvariant(this)
        }
        if (other !is RubyUnionType) {
            return false
        }
        return possibleTypes.typeEquals(other.possibleTypes)
    }

    override fun acceptsInvariant(other: RubyTypeDefinition): Boolean {
        if (other !is RubyUnionType) {
            return false
        }
        return possibleTypes == other.possibleTypes
    }

    override fun toString(): String {
        return possibleTypes.elements.joinToString(" or ")
    }

    override fun toStringIgnoreNames() = possibleTypes.elements.joinToString(" or ") { it.toStringIgnoreNames() }
}

data class ArgumentInfo(
        val name: String,
        val offset: Int,
        val kind: ArgumentKind
)

enum class ArgumentKind {
    REGULAR,
    OPTIONAL,
    VARARG,
    KEYWORD,
    KEYREQ
}

/**
 * Value that indicates how long an array must be to be interpreted as [RubyLongArrayType].
 */
const val ARRAY_REPR_THRESHOLD = 5