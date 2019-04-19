package org.jetbrains.plugins.ruby.types.parser

import org.jetbrains.plugins.ruby.types.parser.ast.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParserTest {

    @Test
    fun `test atom identifier`() {
        val compiledResult = AnnotationCompiler.compile("##t aba: Fixnum")
        val expectedAst =
                RubyTypeDeclaration(
                        "aba",
                        4,
                        listOf(
                                RubyAtomTypeIdentifier(
                                        9,
                                        6,
                                        listOf("Fixnum")
                                )
                        )
                )
        assertEquals(expectedAst, compiledResult)
    }

    @Test
    fun `test composed identifier`() {
        val type = "A::BA::CABA::DABACABA"
        val compiledResult = AnnotationCompiler.compile("##t x: $type")
        val expectedAst = RubyTypeDeclaration(
                "x",
                4,
                listOf(
                        RubyAtomTypeIdentifier(
                                7,
                                type.length,
                                type.split("::")
                        )
                )
        )
        assertEquals(expectedAst, compiledResult)
    }

    @Test
    fun `test tuple type`() {
        val type = "Integer, String, IntegerString"
        val offsets = listOf(10, 19, 27)
        val compiledResult = AnnotationCompiler.compile("##t xyz: ($type,)")
        val expectedAst = RubyTypeDeclaration(
                "xyz",
                4,
                listOf(
                        RubyTupleType(
                                9,
                                type.length + 3,
                                RubyListOfTypeElements(
                                        10,
                                        type.length + 3,
                                        type.split(", ").zip(offsets).map { RubyAtomTypeIdentifier(it.second, it.first.length, listOf(it.first)) }
                                )
                        )
                )
        )
        assertEquals(expectedAst, compiledResult)
    }

    @Test
    fun `test short array type`() {
        val type = "Integer, String, IntegerString"
        val offsets = listOf(10, 19, 27)
        val compiledResult = AnnotationCompiler.compile("##t xyz: [$type]")
        val expectedAst = RubyTypeDeclaration(
                                "xyz",
                                4,
                                listOf(
                                        RubyShortArrayType(
                                                9,
                                                type.length + 2,
                                                RubyListOfTypeElements(
                                                        10,
                                                        type.length + 2,
                                                        type.split(", ").zip(offsets).map { RubyAtomTypeIdentifier(it.second, it.first.length, listOf(it.first)) }
                                                )
                                        )
                                )
        )
        assertEquals(expectedAst, compiledResult)
    }

    @Test
    fun `test long array type`() {
        val type = "A, B, C, D, E, F"
        val offsets = listOf(10, 13, 16, 19, 22, 25)
        val compiledResult = AnnotationCompiler.compile("##t xyz: [$type]")
        val expectedAst = RubyTypeDeclaration(
                                "xyz",
                                4,
                                listOf(
                                        RubyLongArrayType(
                                                9,
                                                type.length + 2,
                                                RubyListOfTypeElements(
                                                        10,
                                                        type.length + 2,
                                                        type.split(", ").zip(offsets).map { RubyAtomTypeIdentifier(it.second, it.first.length, listOf(it.first)) }
                                                )
                                        )
                                )
        )
        assertEquals(expectedAst, compiledResult)
    }

    @Test
    fun `test functional type`() {
        val domain = "Bar, Baz"
        val offsets = listOf(10, 15)
        val compiledResult = AnnotationCompiler.compile("##t foo: (Bar, Baz) -> Boo")
        val expectedAst = RubyTypeDeclaration(
                                "foo",
                                4,
                                listOf(
                                        RubyFunctionalType(
                                                9,
                                                17,
                                                RubyListOfTypeElements(
                                                        10,
                                                        domain.length + 2,
                                                        domain.split(", ").zip(offsets).map { RubyRegularArgumentType(it.second, it.first.length, RubyAtomTypeIdentifier(it.second, it.first.length, listOf(it.first))) }
                                                ),
                                                RubyAtomTypeIdentifier(
                                                        23,
                                                        3,
                                                        listOf("Boo")
                                                )
                                        )
                                )
        )
        assertEquals(expectedAst, compiledResult)
    }

    @Test
    fun `test union type`() {
        val type = "String | Error"
        val offsets = listOf(12, 21)
        val compiledResult = AnnotationCompiler.compile("##t result: String | Error")
        val expectedAst = RubyTypeDeclaration(
                                "result",
                                4,
                                listOf(
                                        RubyUnionType(
                                                12,
                                                type.length,
                                                RubyListOfTypeElements(
                                                        12,
                                                        type.length,
                                                        type.split(" | ").zip(offsets).map { RubyAtomTypeIdentifier(it.second, it.first.length, listOf(it.first)) }
                                                )
                                        )
                                )
        )
        assertEquals(expectedAst, compiledResult)
    }

    @Test
    fun `test complex type`() {
        val compiledResult = AnnotationCompiler.compile("##t foo: (*A::B, ([C, D], E?) -> (F | G)) -> (H, I, J::F::K,)")
        val expectedAst = RubyTypeDeclaration(
                                "foo",
                                4,
                                listOf(
                                        RubyFunctionalType(
                                                9,
                                                "(*A::B, ([C, D], E?) -> (F | G)) -> (H, I, J::F::K,)".length,
                                                RubyListOfTypeElements(
                                                        10,
                                                        "(*A::B, ([C, D], E?) -> (F | G))".length,
                                                        listOf(
                                                                RubyVarargArgumentType(
                                                                        10,
                                                                        5,
                                                                        RubyAtomTypeIdentifier(
                                                                                11,
                                                                                4,
                                                                                "A::B".split("::")
                                                                        )
                                                                ),
                                                                RubyRegularArgumentType(
                                                                        17,
                                                                        "([C, D], E?) -> (F | G)".length,
                                                                        RubyFunctionalType(
                                                                                17,
                                                                                "([C, D], E?) -> (F | G)".length,
                                                                                RubyListOfTypeElements(
                                                                                        18,
                                                                                        "([C, D], E?)".length,
                                                                                        listOf(
                                                                                                RubyRegularArgumentType(
                                                                                                        18,
                                                                                                        6,
                                                                                                        RubyShortArrayType(
                                                                                                                18,
                                                                                                                6,
                                                                                                                RubyListOfTypeElements(
                                                                                                                        19,
                                                                                                                        6,
                                                                                                                        listOf(
                                                                                                                                RubyAtomTypeIdentifier(19, 1, listOf("C")),
                                                                                                                                RubyAtomTypeIdentifier(22, 1, listOf("D"))
                                                                                                                        )
                                                                                                                )
                                                                                                        )
                                                                                                ),
                                                                                                RubyOptionalArgumentType(26, 2, RubyAtomTypeIdentifier(26, 1, listOf("E")))
                                                                                        )
                                                                                ),
                                                                                RubyUnionType(
                                                                                        34,
                                                                                        5,
                                                                                        RubyListOfTypeElements(
                                                                                                34,
                                                                                                5,
                                                                                                listOf(
                                                                                                        RubyAtomTypeIdentifier(34, 1, listOf("F")),
                                                                                                        RubyAtomTypeIdentifier(38, 1, listOf("G"))
                                                                                                )
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                ),
                                                RubyTupleType(
                                                        45,
                                                        16,
                                                        RubyListOfTypeElements(
                                                                46,
                                                                16,
                                                                listOf(
                                                                        RubyAtomTypeIdentifier(46, 1, listOf("H")),
                                                                        RubyAtomTypeIdentifier(49, 1, listOf("I")),
                                                                        RubyAtomTypeIdentifier(52, 7, "J::F::K".split("::"))
                                                                )
                                                        )
                                                )
                                        )
                                )
        )
        assertEquals(expectedAst, compiledResult)
    }

    @Test
    fun `test comments not annotations`() {
        assertThrows<AnyParsingException> { AnnotationCompiler.compile("# Some regular comment") }
        assertThrows<AnyParsingException> { AnnotationCompiler.compile("##t K: a -> b -> a") }
        assertThrows<AnyParsingException> { AnnotationCompiler.compile("##t K: (A, *B?) -> C") }
        assertThrows<AnyParsingException> { AnnotationCompiler.compile("##t K: (A, B) -> C?") }
    }
}