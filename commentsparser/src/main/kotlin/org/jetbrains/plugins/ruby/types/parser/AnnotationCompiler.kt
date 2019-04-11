package org.jetbrains.plugins.ruby.types.parser

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.jetbrains.plugins.ruby.types.parser.ast.AntlrAstMapper
import org.jetbrains.plugins.ruby.types.parser.ast.RelativePositionLexer
import org.jetbrains.plugins.ruby.types.parser.ast.RubyTypeAnnotation
import org.jetbrains.plugins.ruby.types.parser.ast.TypeParsingErrorListener

object AnnotationCompiler {
    /**
     * Comments are passed as-is, without any relations to their position in source code.
     * Therefore, in order to save original offset of symbols in comments,
     * we need to pass initial offset of comment in the source file
     * and recalculate offsets of tokens produced by lexer according to this value.
     */
    fun compile(annotation: String, initialOffset: Int = 0): RubyTypeAnnotation {
        val lexer = RelativePositionLexer(CharStreams.fromString(annotation), initialOffset)
        lexer.removeErrorListeners()
        lexer.addErrorListener(TypeParsingErrorListener.INSTANCE)

        val tokens = CommonTokenStream(lexer)
        val parser = RubyTypesParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(TypeParsingErrorListener.INSTANCE)

        return AntlrAstMapper().visitAnnotation(parser.annotation())
    }
}