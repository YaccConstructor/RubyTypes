package org.jetbrains.plugins.ruby.types.parser

import org.jetbrains.plugins.ruby.types.parser.ast.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ParserTest {

    @Test
    fun `test atom identifier`() {
        val compiledResult = AnnotationCompiler.compile("##t aba: Fixnum")
        val expectedAst = RubyTypeAnnotation(
                listOf(
                        RubyTypeDeclaration(
                                "aba",
                                4,
                                RubyAtomTypeIdentifier(
                                        9,
                                        listOf("Fixnum")
                                )
                        )

                )
        )
        assertEquals(expectedAst, compiledResult)
    }

    @Test
    fun `test composed identifier`() {
        val type = "A::BA::CABA::DABACABA"
        val compiledResult = AnnotationCompiler.compile("##t x: $type")
        val expectedAst = RubyTypeAnnotation(
                listOf(
                        RubyTypeDeclaration(
                                "x",
                                4,
                                RubyAtomTypeIdentifier(
                                        7,
                                        type.split("::")
                                )
                        )
                )
        )
        assertEquals(expectedAst, compiledResult)
    }

    @Test
    fun `test tuple type`() {
        val type = "Integer, String, IntegerString"
        val offsets = listOf(10, 19, 27)
        val compiledResult = AnnotationCompiler.compile("##t xyz: ($type)")
        val expectedAst = RubyTypeAnnotation(
                listOf(
                        RubyTypeDeclaration(
                                "xyz",
                                4,
                                RubyTupleType(
                                        9,
                                        RubyListOfTypeElements(
                                                10,
                                                type.split(", ").zip(offsets).map { RubyAtomTypeIdentifier(it.second, listOf(it.first)) }
                                        )
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
        val expectedAst = RubyTypeAnnotation(
                listOf(
                        RubyTypeDeclaration(
                                "xyz",
                                4,
                                RubyShortArrayType(
                                        9,
                                        RubyListOfTypeElements(
                                                10,
                                                type.split(", ").zip(offsets).map { RubyAtomTypeIdentifier(it.second, listOf(it.first)) }
                                        )
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
        val expectedAst = RubyTypeAnnotation(
                listOf(
                        RubyTypeDeclaration(
                                "xyz",
                                4,
                                RubyLongArrayType(
                                        9,
                                        RubyListOfTypeElements(
                                                10,
                                                type.split(", ").zip(offsets).map { RubyAtomTypeIdentifier(it.second, listOf(it.first)) }
                                        )
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
        val expectedAst = RubyTypeAnnotation(
                listOf(
                        RubyTypeDeclaration(
                                "foo",
                                4,
                                RubyFunctionalType(
                                        9,
                                        RubyListOfTypeElements(
                                                10,
                                                domain.split(", ").zip(offsets).map { RubyAtomTypeIdentifier(it.second, listOf(it.first)) }
                                        ),
                                        RubyAtomTypeIdentifier(
                                                23,
                                                listOf("Boo")
                                        )
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
        val expectedAst = RubyTypeAnnotation(
                listOf(
                        RubyTypeDeclaration(
                                "result",
                                4,
                                RubyUnionType(
                                        12,
                                        RubyListOfTypeElements(
                                                12,
                                                type.split(" | ").zip(offsets).map { RubyAtomTypeIdentifier(it.second, listOf(it.first)) }
                                        )
                                )
                        )
                )
        )
        assertEquals(expectedAst, compiledResult)
    }

    @Test
    fun `test complex type`() {
        val compiledResult = AnnotationCompiler.compile("##t foo: (A::B, ([C, D], E) -> (F | G)) -> (H, I, J::F::K)")
        val expectedAst = RubyTypeAnnotation(
                listOf(
                        RubyTypeDeclaration(
                                "foo",
                                4,
                                RubyFunctionalType(
                                        9,
                                        RubyListOfTypeElements(
                                                10,
                                                listOf(
                                                        RubyAtomTypeIdentifier(
                                                                10,
                                                                "A::B".split("::")
                                                        ),
                                                        RubyFunctionalType(
                                                                16,
                                                                RubyListOfTypeElements(
                                                                        17,
                                                                        listOf(
                                                                                RubyShortArrayType(
                                                                                        17,
                                                                                        RubyListOfTypeElements(
                                                                                                18,
                                                                                                listOf(
                                                                                                        RubyAtomTypeIdentifier(18, listOf("C")),
                                                                                                        RubyAtomTypeIdentifier(21, listOf("D"))
                                                                                                )
                                                                                        )
                                                                                ),
                                                                                RubyAtomTypeIdentifier(25, listOf("E"))
                                                                        )
                                                                ),
                                                                RubyUnionType(
                                                                        32,
                                                                        RubyListOfTypeElements(
                                                                                32,
                                                                                listOf(
                                                                                        RubyAtomTypeIdentifier(32, listOf("F")),
                                                                                        RubyAtomTypeIdentifier(36, listOf("G"))
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        ),
                                        RubyTupleType(
                                             43,
                                                RubyListOfTypeElements(
                                                        44,
                                                        listOf(
                                                                RubyAtomTypeIdentifier(44, listOf("H")),
                                                                RubyAtomTypeIdentifier(47, listOf("I")),
                                                                RubyAtomTypeIdentifier(50, "J::F::K".split("::"))
                                                        )
                                                )
                                        )
                                )
                        )
                )
        )
        assertEquals(expectedAst, compiledResult)
    }

    @Test
    fun `test multiple definitions`() {
        val compiledResult = AnnotationCompiler.compile("##t a: A; b: BB; c: CCC")
        val expectedAst = RubyTypeAnnotation(
                listOf(
                        RubyTypeDeclaration(
                                "a",
                                4,
                                RubyAtomTypeIdentifier(7, listOf("A"))
                        ),
                        RubyTypeDeclaration(
                                "b",
                                10,
                                RubyAtomTypeIdentifier(13, listOf("BB"))
                        ),
                        RubyTypeDeclaration(
                                "c",
                                17,
                                RubyAtomTypeIdentifier(20, listOf("CCC"))
                        )
                )
        )
        assertEquals(expectedAst, compiledResult)
    }
}