package org.jetbrains.plugins.ruby.types.controlflow.typeinfo

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import org.jetbrains.plugins.ruby.ruby.lang.RubyLanguage
import org.jetbrains.plugins.ruby.ruby.lang.psi.RPsiElement

class RubyCompletionContributor: CompletionContributor() {
    init {
        extend(
                CompletionType.BASIC, PlatformPatterns.psiElement().withLanguage(RubyLanguage.INSTANCE),
                object : CompletionProvider<CompletionParameters>() {
                    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext?, result: CompletionResultSet) {
                        result.addElement(LookupElementBuilder.create("Hello"))
                    }
                }
        )
    }
}