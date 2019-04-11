package org.jetbrains.plugins.ruby.types.parser.ast

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CommonToken
import org.antlr.v4.runtime.Lexer
import org.antlr.v4.runtime.Token
import org.jetbrains.plugins.ruby.types.parser.RubyTypesLexer

class RelativePositionLexer(
        input: CharStream,
        /**
         * Comments are passed as-is, without any relations to their position in source code.
         * Therefore, in order to save original offset of symbols in comments,
         * we need to pass initial offset of comment in the source file
         * and recalculate offsets of tokens produced by lexer according to this value.
         */
        val initialOffset: Int
): RubyTypesLexer(input) {

    override fun nextToken(): Token {
        val token = super.nextToken()
        if (token is CommonToken) {
            token.startIndex += initialOffset
        }
        return token
    }
}