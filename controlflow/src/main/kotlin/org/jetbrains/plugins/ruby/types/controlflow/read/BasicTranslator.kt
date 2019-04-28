package org.jetbrains.plugins.ruby.types.controlflow.read

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class BasicTranslator {
    fun translate(input: String): JSONObject {
        val result = JSONObject()
        val cfgsData = input.split("-$-").filter { it.isNotBlank() }
        val files = JSONArray()
        val fileDescription = JSONObject()
        fileDescription.put("file", "dummy")
        val fileContents = JSONArray()
        for (cfgData in cfgsData) {
            cfgData.trim()
            val cfgJson = JSONObject(cfgData)
            val nodesDescription = cfgJson
                    .getJSONArray("nodes description")
                    .map { it as JSONObject }
                    .filter { it.has("typeDefinition") && it.getString("typeDefinition") != "null" }
            for (nodeDescription in nodesDescription) {
                val translatedDescription = JSONObject()
                translatedDescription.put("typeDefinition", nodeDescription.getString("typeDefinition"))
                translatedDescription.put("identifier", nodeDescription.getString("text"))
                translatedDescription.put("offset", nodeDescription.getInt("offset"))
                fileContents.put(translatedDescription)
            }
            val holder = cfgJson.getJSONObject("holder")
            if (holder.has("typeDefinition")) {
                fileContents.put(holder)
            }
        }
        fileDescription.put("contents", fileContents)
        files.put(fileDescription)
        result.put("files", files)
        return result
    }
}