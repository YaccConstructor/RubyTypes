package org.jetbrains.plugins.ruby.types.controlflow

import com.intellij.codeInsight.controlflow.ControlFlow
import org.jetbrains.plugins.ruby.ruby.lang.psi.RPsiElement
import org.jetbrains.plugins.ruby.types.controlflow.data.AdjacencyRecord
import org.jetbrains.plugins.ruby.types.controlflow.data.ControlFlowData
import org.jetbrains.plugins.ruby.types.controlflow.data.NodeDescription

class RubyControlFlowWrapper(val controlFlow: ControlFlow) {
    private val instructions = controlFlow.instructions
    private val elementInfoCollector = RubyElementsInfoCollector()

    fun dump(holder: RPsiElement): ControlFlowData {
        collectNodesInfo()
        val nodesDescriptions = elementInfoCollector.getNodesDescriptions()
        val adjacencyList = makeAdjacencyListOnIds()
        return ControlFlowData(nodesDescriptions, adjacencyList, holder)
    }

    private fun collectNodesInfo(): List<NodeDescription> {
        elementInfoCollector.clearDescriptionsList()
        elementInfoCollector.visitGraph(instructions.asList())
        return elementInfoCollector.getNodesDescriptions()
    }

    private fun makeAdjacencyListOnIds(): List<AdjacencyRecord> {
        val adjacencyList = mutableListOf<AdjacencyRecord>()

        instructions.forEach { instruction ->
            val destinationIds: List<Int?> = instruction.allSucc().map { it.num() }
            adjacencyList.add(AdjacencyRecord(instruction.num(), destinationIds))
        }
        return adjacencyList
    }
}