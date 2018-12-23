package org.jetbrains.plugins.ruby.types.controlflow.data

import com.intellij.codeInsight.controlflow.ControlFlow
import org.jetbrains.plugins.ruby.ruby.codeInsight.resolve.scope.ControlFlowHolder
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlFlow.RControlFlowUtil
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlFlow.impl.RControlFlowBuilder
import org.jetbrains.plugins.ruby.types.controlflow.RubyControlFlowWrapper

object ControlFlowProvider {
    fun newControlFlow(owner: ControlFlowHolder): ControlFlow {
        return RControlFlowBuilder().buildControlFlow(owner)
    }

    fun newControlFlowWrapper(owner: ControlFlowHolder): RubyControlFlowWrapper {
        return RubyControlFlowWrapper(newControlFlow(owner), owner)
    }
}