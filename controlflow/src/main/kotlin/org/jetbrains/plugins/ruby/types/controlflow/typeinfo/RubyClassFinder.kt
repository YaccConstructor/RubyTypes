package org.jetbrains.plugins.ruby.types.controlflow.typeinfo

import org.jetbrains.plugins.ruby.ruby.lang.psi.RFile
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.classes.RClass
import org.jetbrains.plugins.ruby.ruby.lang.psi.visitors.RubyRecursiveElementVisitor

class RubyClassFinder(val file: RFile) {

    fun findClassBySimpleName(className: String): RClass? {
        val classFinderVisitor = ClassFinderVisitor(className)
        file.accept(classFinderVisitor)
        return classFinderVisitor.foundClass
    }

    private class ClassFinderVisitor(val className: String): RubyRecursiveElementVisitor() {

        var foundClass: RClass? = null

        override fun visitRClass(rClass: RClass) {
            println(rClass.className?.name)
            if (rClass.className?.name == className) {
                foundClass = rClass
            }
            super.visitRClass(rClass)
        }
    }
}