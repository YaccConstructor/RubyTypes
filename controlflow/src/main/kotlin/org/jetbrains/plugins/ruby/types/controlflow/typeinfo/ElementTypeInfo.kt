package org.jetbrains.plugins.ruby.types.controlflow.typeinfo

import java.io.File


class ElementTypeInfoContainer constructor(private val typesInfo: Set<ElementTypeInfo>) {

    fun getElementByOffset(offset: Int): ElementTypeInfo? = typesInfo.firstOrNull { it.offset == offset }

    companion object {
        fun from(reader: TypeInfoReader) = ElementTypeInfoContainer(reader.read().toSet())
    }
}

class ElementTypeInfo(
        val elementName: String,
        val elementKind: ElementKind,
        val type: String,
        val offset: Int
) {
    val isIdentifier: Boolean get() = elementKind == ElementKind.IDENTIFIER
    val isMethod: Boolean get() = elementKind == ElementKind.METHOD
}

enum class ElementKind {
    IDENTIFIER,
    METHOD
}