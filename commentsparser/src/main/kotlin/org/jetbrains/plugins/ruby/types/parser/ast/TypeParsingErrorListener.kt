package org.jetbrains.plugins.ruby.types.parser.ast

import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Recognizer
import org.antlr.v4.runtime.misc.ParseCancellationException

/**
 * Here, any errors occurred during parsing are treated as comment is not a typeDefinition annotation.
 */
class TypeParsingErrorListener: BaseErrorListener() {
    override fun syntaxError(recognizer: Recognizer<*, *>?, offendingSymbol: Any?, line: Int, charPositionInLine: Int, msg: String?, e: RecognitionException?) {
        throw AnyParsingException("Some error at $line:$charPositionInLine")
    }

    companion object {
        val INSTANCE = TypeParsingErrorListener()
    }
}

class AnyParsingException: ParseCancellationException {
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
}