package org.jetbrains.plugins.ruby.types.controlflow.dump

import org.jetbrains.plugins.ruby.ruby.lang.psi.RPsiElement
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RMethod
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RNamedArgument
import org.jetbrains.plugins.ruby.types.controlflow.annotations.Annotations
import org.jetbrains.plugins.ruby.types.controlflow.data.AdjacencyRecord
import org.jetbrains.plugins.ruby.types.controlflow.data.ControlFlowData
import org.jetbrains.plugins.ruby.types.controlflow.data.NodeDescription
import org.jetbrains.plugins.ruby.types.controlflow.typeinfo.rightOffset
import org.jetbrains.plugins.ruby.types.parser.ast.RubyFunctionalType
import org.jetbrains.plugins.ruby.types.parser.ast.RubyNamedArgumentType
import org.json.JSONArray
import org.json.JSONObject

class JsonControlFlowWriter: ControlFlowWriter {

    private val controlFlowJson = JSONObject()

    override fun write(data: ControlFlowData): String {
        writeNodesDescription(data.nodesDescription)
        writeAdjacencyList(data.adjacencyList)
        writeHolder(data.holder)

        val indentation = 4
        return controlFlowJson.toString(indentation)
    }

    private fun writeNodesDescription(nodesDescription: List<NodeDescription>) {
        val nodesDescriptionJson = JSONArray()
        nodesDescription.forEach { description ->
            val nodeDescriptionJson = JSONObject()
            nodeDescriptionJson.put("element type", description.nodeType)
            nodeDescriptionJson.put("id", description.id)
            if (description.text != null) {
                nodeDescriptionJson.put("text", description.text)
            }
            description.getProperties().forEach { property, value ->
                nodeDescriptionJson.put(property, value)
            }
            description.callee?.let { nodeDescriptionJson.put("callee", it.name) }
            if (description.offset != 0) {
                nodeDescriptionJson.put("offset", description.offset)
            }
            nodesDescriptionJson.put(nodeDescriptionJson)
        }
        controlFlowJson.put("nodes description", nodesDescriptionJson)
    }

    private fun writeAdjacencyList(adjacencyList: List<AdjacencyRecord>) {
        val adjacencyListJson = JSONArray()
        adjacencyList.forEach {record ->
            val adjacencyRecordJson = JSONObject()
            adjacencyRecordJson.put("from", record.from)

            val destinationIdsJson = JSONArray()
            record.to.forEach { destinationIdsJson.put(it) }
            adjacencyRecordJson.put("to", destinationIdsJson)

            adjacencyListJson.put(adjacencyRecordJson)
        }
        controlFlowJson.put("adjacency", adjacencyListJson)
    }

    private fun writeHolder(holder: RPsiElement) {
        val holderInfo = JSONObject()
        holderInfo.put("identifier", holder.name)
        if (holder is RMethod) {
            Annotations.declarationForMethod(holder)?.typeDefinitions?.let { defs ->
                val def = defs.first() as RubyFunctionalType
                val arguments = def.domain.elements.map { it as RubyNamedArgumentType }
                val argumentInfos = JSONArray()
                arguments.forEach {
                    val argumentInfo = JSONObject()
                    argumentInfo.put("name", it.argumentName)
                    argumentInfo.put("kind", it.kind)
                    argumentInfo.put("offset", it.argumentOffset)
                    argumentInfos.put(argumentInfo)
                }
                holderInfo.put("arguments", argumentInfos)
                holderInfo.put("typeDefinition", defs.joinToString(" || ") { it.toStringIgnoreNames() })
                holderInfo.put("offset", holder.textOffset)
            }
        }
        controlFlowJson.put("holder", holderInfo)
    }
}