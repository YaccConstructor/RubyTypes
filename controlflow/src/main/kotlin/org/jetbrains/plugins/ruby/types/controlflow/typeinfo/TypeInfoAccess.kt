package org.jetbrains.plugins.ruby.types.controlflow.typeinfo

import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RMethod
import org.jetbrains.plugins.ruby.ruby.lang.psi.variables.RIdentifier
import java.io.File

sealed class TypeInfoReader {
    abstract fun read(): List<ElementTypeInfo>
}

class CustomTypeInfoReader(val file: File): TypeInfoReader() {

    override fun read(): List<ElementTypeInfo> {
        val recordsIterator = file.readLines().iterator()
        val typesInfo = mutableListOf<ElementTypeInfo>()
        while (recordsIterator.hasNext()) {
            val nextElementName = recordsIterator.next()
            val nextElementKind = recordsIterator.next()
            val nextElementType = recordsIterator.next()
            val nextElementOffset = recordsIterator.next()

            typesInfo.add(ElementTypeInfo(
                    nextElementName,
                    ElementKind.valueOf(nextElementKind),
                    nextElementType,
                    nextElementOffset.toInt()
            ))
        }
        return typesInfo.toList()
    }
}

val RIdentifier.rightOffset: Int get() = textOffset + textLength

fun RIdentifier.inferredType(typeInfoContainer: ElementTypeInfoContainer): String? {
    val element = typeInfoContainer.getElementByOffset(rightOffset)
    return if (element?.isIdentifier == true) element.type else null
}

val RMethod.rightOffset: Int get() = argumentList!!.let { it.textOffset + it.textLength } + 1

fun RMethod.inferredType(typeInfoContainer: ElementTypeInfoContainer): String? {
    val element = typeInfoContainer.getElementByOffset(rightOffset)
    return if (element?.isMethod == true) element.type else null
}