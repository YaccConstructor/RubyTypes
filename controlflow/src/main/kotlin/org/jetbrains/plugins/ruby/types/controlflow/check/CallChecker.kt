package org.jetbrains.plugins.ruby.types.controlflow.check

import org.jetbrains.plugins.ruby.ruby.lang.psi.methodCall.RCall
import org.jetbrains.plugins.ruby.types.controlflow.TypesUtil
import org.jetbrains.plugins.ruby.types.parser.ast.RubyFunctionalType

object CallChecker {

    fun definitelyNotMatches(call: RCall, definition: RubyFunctionalType): Boolean {
        println(call.callArguments.elements.joinToString { it.toString() })
        return false
    }
}