package org.jetbrains.plugins.ruby.types.controlflow.read

import org.jetbrains.plugins.ruby.types.parser.AnnotationCompiler
import org.jetbrains.plugins.ruby.types.parser.ast.RubyTypeDeclaration
import org.jetbrains.plugins.ruby.types.parser.ast.RubyTypeDefinition
import org.json.JSONObject

class JsonResultReader {
    fun read(input: String): Set<RubyTypeDeclaration> {
        val declarations = mutableSetOf<RubyTypeDeclaration>()
        val elementsData = JSONObject(input)
        val filesData = elementsData.getJSONArray("files").map { it as JSONObject }
        for (fileData in filesData) {
            // TODO support of different files
            val fileContentsData = fileData.getJSONArray("contents").map { it as JSONObject }
            for (fileContentData in fileContentsData) {
                val identifier = fileContentData.getString("identifier")
                val offset = fileContentData.getInt("offset")
                val type = fileContentData
                        .getString("type")
                        .split(" || ")
                        .map { AnnotationCompiler.compile("##t $it") as RubyTypeDefinition }
                        .let {
                            RubyTypeDeclaration(identifier, offset, it)
                        }
                declarations.add(type)
            }
        }
        return declarations.toSet()
    }
}