package org.jetbrains.plugins.ruby.types.parser

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonToken
import org.antlr.v4.runtime.CommonTokenStream
import org.jetbrains.plugins.ruby.types.parser.ast.*

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
        tokens.tokens.map {
            if (it is CommonToken) {
                it.startIndex += initialOffset
            }
        }
        val parser = RubyTypesParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(TypeParsingErrorListener.INSTANCE)

        val mapper = AntlrAstMapper()
        return try {
            mapper.visitAnnotation(parser.annotation())
        } catch (_: AnyParsingException) {
            mapper.visitAdditional(parser.additional())
        }
    }
}