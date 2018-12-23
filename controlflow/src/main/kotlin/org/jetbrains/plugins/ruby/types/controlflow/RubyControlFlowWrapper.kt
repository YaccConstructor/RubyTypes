package org.jetbrains.plugins.ruby.types.controlflow

import com.intellij.codeInsight.controlflow.ControlFlow
import com.intellij.refactoring.extractMethod.ControlFlowWrapper
import org.jetbrains.plugins.ruby.ruby.codeInsight.resolve.scope.ControlFlowHolder
import org.jetbrains.plugins.ruby.ruby.lang.psi.RPsiElement
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RMethod
import org.jetbrains.plugins.ruby.types.controlflow.data.AdjacencyRecord
import org.jetbrains.plugins.ruby.types.controlflow.data.ControlFlowData
import org.jetbrains.plugins.ruby.types.controlflow.data.ControlFlowProvider
import org.jetbrains.plugins.ruby.types.controlflow.data.NodeDescription

class RubyControlFlowWrapper(controlFlow: ControlFlow, private val owner: ControlFlowHolder) {
    private val instructions = controlFlow.instructions
    private val elementInfoCollector = RubyElementsInfoCollector()

    fun dump(): ControlFlowData {
        collectNodesInfo()
        val nodesDescriptions = elementInfoCollector.getNodesDescriptions()
        val adjacencyList = makeAdjacencyListOnIds()
        return ControlFlowData(nodesDescriptions, adjacencyList, owner)
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

object ControlFlow {
    fun getCallsTree(controlFlow: RubyControlFlowWrapper, maxDepth: Int) = getCallsTree(controlFlow, 0, maxDepth)

    private fun getCallsTree(controlFlow: RubyControlFlowWrapper, depth: Int, maxDepth: Int): String {
        val treeBuilder = StringBuilder()

        if (depth >= maxDepth) {
            return treeBuilder.toString()
        }

        val controlFlowData = controlFlow.dump()
        controlFlowData.nodesDescription
                .filter { it.nodeType == "Call" }
                .forEach {
                    treeBuilder.append("\t".repeat(depth)).append(it.text).append("\n")
                    if (it.callee is RMethod) {
                        treeBuilder.append(getCallsTree(ControlFlowProvider.newControlFlowWrapper(it.callee), depth + 1, maxDepth))
                    }
                }
        return treeBuilder.toString()
    }
}