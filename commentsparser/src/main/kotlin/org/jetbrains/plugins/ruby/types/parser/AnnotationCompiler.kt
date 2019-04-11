package org.jetbrains.plugins.ruby.types.parser

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.jetbrains.plugins.ruby.types.parser.ast.AntlrAstMapper
import org.jetbrains.plugins.ruby.types.parser.ast.RubyTypeAnnotation

object AnnotationCompiler {
    fun compile(annotation: String): RubyTypeAnnotation {
        val lexer = RubyTypesLexer(CharStreams.fromString(annotation))
        val tokens = CommonTokenStream(lexer)
        val parser = RubyTypesParser(tokens)
        return AntlrAstMapper().visitAnnotation(parser.annotation())
    }
}