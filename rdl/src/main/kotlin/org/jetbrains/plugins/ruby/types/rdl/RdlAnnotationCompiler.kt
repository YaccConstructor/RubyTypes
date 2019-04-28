package org.jetbrains.plugins.ruby.types.rdl

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.jetbrains.plugins.ruby.types.parser.ast.*

// TODO merge with its origin?
object RdlAnnotationCompiler {
    /**
     * Comments are passed as-is, without any relations to their position in source code.
     * Therefore, in order to save original offset of symbols in comments,
     * we need to pass initial offset of comment in the source file
     * and recalculate offsets of tokens produced by lexer according to this value.
     */
    fun compile(annotation: String, initialOffset: Int = 0): RubyTypeAstElement {
        val lexer = RdlLexer(CharStreams.fromString(annotation))
        lexer.removeErrorListeners()
        lexer.addErrorListener(TypeParsingErrorListener.INSTANCE)

        val tokens = CommonTokenStream(lexer)
        val parser = RdlParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(TypeParsingErrorListener.INSTANCE)

        val mapper = AntlrAstMapper(initialOffset)
        return mapper.visitAnnotation(parser.annotation())
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
}