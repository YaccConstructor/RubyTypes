package org.jetbrains.plugins.ruby.types.controlflow

import com.intellij.codeInsight.controlflow.ControlFlow
import com.intellij.codeInsight.controlflow.Instruction
import org.jetbrains.plugins.ruby.ruby.lang.psi.RPsiElement
import org.jetbrains.plugins.ruby.types.controlflow.data.AdjacencyRecord
import org.jetbrains.plugins.ruby.types.controlflow.data.ControlFlowData

class RubyControlFlowWrapper(val controlFlow: ControlFlow) {
    private val instructions = controlFlow.instructions
    private val elementInfoCollector = RubyElementInfoCollector()

    fun dump(holder: RPsiElement): ControlFlowData {
        val instructionsWithIds = getInstructionsWithIds()
        val nodesDescriptions = elementInfoCollector.getNodesDescriptions()
        val adjacencyList = makeAdjacencyListOnIds(instructionsWithIds)
        return ControlFlowData(nodesDescriptions, adjacencyList, holder)
    }

    private fun getInstructionsWithIds(): Map<Instruction, Int> {
        val instructionsWithIds = mutableMapOf<Instruction, Int>()
        elementInfoCollector.clearDescriptionsList()
        
        instructions.filterNotNull().filter { it.element != null }.forEach { instruction ->
            val element = instruction.element as RPsiElement
            instructionsWithIds[instruction] = num
            elementInfoCollector.visit(element, num)
            num++
        }
        
        return instructionsWithIds
    }

    private fun makeAdjacencyListOnIds(instructionsWithIds: Map<Instruction, Int>): List<AdjacencyRecord> {
        val adjacencyList = mutableListOf<AdjacencyRecord>()

        instructionsWithIds.forEach { instruction, id ->
            val destinationIds: List<Int?> = instruction.allSucc().map { instructionsWithIds[it] }
            adjacencyList.add(AdjacencyRecord(id, destinationIds))
        }
        return adjacencyList
    }

    companion object {
        var num = 1
    }
}