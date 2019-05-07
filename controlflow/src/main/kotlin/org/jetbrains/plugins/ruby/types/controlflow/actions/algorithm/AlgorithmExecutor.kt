package org.jetbrains.plugins.ruby.types.controlflow.actions.algorithm

import java.lang.RuntimeException

class AlgorithmExecutor(private val pathToExecutable: String) {

    fun execute(pathToInput: String, additionalParameters: String): String {
        val commandFormer = when {
            pathToExecutable.isPathToJar() -> CommandFormer.JarCommandFormer(pathToExecutable)
            pathToExecutable.isPathToSh() -> CommandFormer.ShCommandFormer(pathToExecutable)
            else -> throw RuntimeException()
        }

        val command = commandFormer.form(pathToInput, additionalParameters)
        val process = Runtime.getRuntime().exec(command)
        return process.inputStream.bufferedReader().readLine()
    }


    private fun String.isPathToJar() = endsWith(".jar")
    private fun String.isPathToOut() = endsWith(".out")
    private fun String.isPathToSh() = endsWith(".sh")


    private sealed class CommandFormer(val pathToExecutable: String) {
        abstract fun form(pathToInput: String, additionalParameters: String): String

        class JarCommandFormer(pathToExecutable: String): CommandFormer(pathToExecutable) {
            override fun form(pathToInput: String, additionalParameters: String): String {
                return "java -jar $additionalParameters $pathToExecutable $pathToInput"
            }
        }

        class ShCommandFormer(pathToExecutable: String): CommandFormer(pathToExecutable) {
            override fun form(pathToInput: String, additionalParameters: String): String {
                return "sh $pathToExecutable $pathToInput"
            }
        }
        // TODO same for the rest
    }
}