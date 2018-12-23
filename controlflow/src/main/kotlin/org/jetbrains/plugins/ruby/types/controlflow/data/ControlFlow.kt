package org.jetbrains.plugins.ruby.types.controlflow.data

import org.jetbrains.plugins.ruby.ruby.lang.psi.RPsiElement
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RMethod
import org.jetbrains.plugins.ruby.types.controlflow.dump.ControlFlowWriter

class NodeDescription(
        val nodeType: String,
        val text: String? = null,
        val id: Int,
        val callee: RPsiElement? = null
) {
    private val additionalProperties = mutableMapOf<String, String?>()

    fun addProperty(name: String, value: String?) {
        additionalProperties.put(name, value)
    }

    fun getProperties(): Map<String, String?> {
        return additionalProperties
    }
}

class AdjacencyRecord(val from: Int, val to: List<Int?>)

class ControlFlowData(
        val nodesDescription: List<NodeDescription>,
        val adjacencyList: List<AdjacencyRecord>,
        val holder: RPsiElement
) {

    fun writeToString(writer: ControlFlowWriter): String = writer.write(this)
}