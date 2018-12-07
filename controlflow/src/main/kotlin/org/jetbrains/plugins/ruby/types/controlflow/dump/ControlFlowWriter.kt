package org.jetbrains.plugins.ruby.types.controlflow.dump

import org.jetbrains.plugins.ruby.types.controlflow.data.ControlFlowData

interface ControlFlowWriter {
    fun write(data: ControlFlowData): String
}