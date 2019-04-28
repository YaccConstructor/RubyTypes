package org.jetbrains.plugins.ruby.types.parser

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonToken
import org.antlr.v4.runtime.CommonTokenStream
import org.jetbrains.plugins.ruby.types.parser.ast.*
import java.lang.Exception

object AnnotationCompiler {
    /**
     * Comments are passed as-is, without any relations to their position in source code.
     * Therefore, in order to save original offset of symbols in comments,
     * we need to pass initial offset of comment in the source file
     * and recalculate offsets of tokens produced by lexer according to this value.
     */
    fun compile(annotation: String, initialOffset: Int = 0): RubyTypeAstElement {
        val lexer = RubyTypesLexer(CharStreams.fromString(annotation))
        lexer.removeErrorListeners()
        lexer.addErrorListener(TypeParsingErrorListener.INSTANCE)

        val tokens = CommonTokenStream(lexer)
        val parser = RubyTypesParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(TypeParsingErrorListener.INSTANCE)

        val mapper = AntlrAstMapper(initialOffset)
        return if (isProbablyDeclaration(annotation)) {
            mapper.visitAnnotation(parser.annotation())
        } else {
            mapper.visitAdditional(parser.additional())
        }

    }

    fun compileNamed(annotation: String, argumentInfos: List<ArgumentInfo>): RubyTypeDefinition {
        val type = compile(annotation) as RubyFunctionalType
        val namedElements = type.domain.elements.zip(argumentInfos).map {
            when (it.second.kind) {
                ArgumentKind.KEYWORD -> RubyKeywordArgumentType(
                        it.first.typeOffset,
                        it.first.typeLength,
                        it.first,
                        it.second.name,
                        it.second.offset
                )
                ArgumentKind.KEYREQ -> RubyKeyreqArgumentType(
                        it.first.typeOffset,
                        it.first.typeLength,
                        it.first,
                        it.second.name,
                        it.second.offset
                )
                else -> RubyNamedRegularArgumentType(
                        it.second.name,
                        it.second.offset,
                        it.first
                )
            }
        }
        return RubyFunctionalType(
                type.typeOffset,
                type.typeLength,
                RubyFunctionalDomain(
                        type.domain.typeOffset,
                        type.domain.typeLength,
                        namedElements
                ),
                type.codomain
        )
    }

    private fun isProbablyDeclaration(annotation: String) = annotation.contains(":")
}