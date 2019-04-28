package org.jetbrains.plugins.ruby.types.controlflow.read

import org.jetbrains.plugins.ruby.types.parser.AnnotationCompiler
import org.jetbrains.plugins.ruby.types.parser.ast.ArgumentInfo
import org.jetbrains.plugins.ruby.types.parser.ast.ArgumentKind
import org.jetbrains.plugins.ruby.types.parser.ast.RubyTypeDeclaration
import org.jetbrains.plugins.ruby.types.parser.ast.RubyTypeDefinition
import org.json.JSONObject

class JsonResultReader {
    fun read(input: String): Map<Int, RubyTypeDeclaration> {
        val declarations = mutableMapOf<Int, RubyTypeDeclaration>()
        val elementsData = JSONObject(input)
        val filesData = elementsData.getJSONArray("files").map { it as JSONObject }
        for (fileData in filesData) {
            // TODO support of different files
            val fileContentsData = fileData.getJSONArray("contents").map { it as JSONObject }
            for (fileContentData in fileContentsData) {
                val identifier = fileContentData.getString("identifier")
                val offset = fileContentData.getInt("offset")
                if (fileContentData.has("arguments")) {
                    val argumentInfos = fileContentData
                            .getJSONArray("arguments")
                            .map { it as JSONObject }
                            .map {
                                val argumentName = it.getString("name")
                                val argumentOffset = it.getInt("offset")
                                val argumentKind = ArgumentKind.valueOf(it.getString("kind"))
                                ArgumentInfo(argumentName, argumentOffset, argumentKind)
                            }
                    val type = fileContentData
                            .getString("typeDefinition")
                            .split(" || ")
                            .map { AnnotationCompiler.compileNamed("##t $it", argumentInfos) }
                            .let {
                                RubyTypeDeclaration(identifier, offset, it)
                            }
                    declarations[offset] = type
                } else {
                    val type = fileContentData
                            .getString("typeDefinition")
                            .split(" || ")
                            .map { AnnotationCompiler.compile("##t $it") as RubyTypeDefinition }
                            .let {
                                RubyTypeDeclaration(identifier, offset, it)
                            }
                    declarations[offset] = type
                }
            }
        }
        return declarations.toMap()
    }
}