package org.jetbrains.plugins.ruby.types.controlflow.annotations

import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.ArgumentInfo
import org.jetbrains.plugins.ruby.types.parser.ast.RubyTypeDefinition

class ArgumentTypesMapping {

    private val mapping = mutableMapOf<ArgumentInfo, RubyTypeDefinition>()
}