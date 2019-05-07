package org.jetbrains.plugins.ruby.types.parser.ast

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import org.jetbrains.plugins.ruby.types.parser.*

/**
 * Comments are passed as-is, without any relations to their position in source code.
 * Therefore, in order to save original offset of symbols in comments,
 * we need to pass initial offset of comment in the source file
 * and recalculate offsets of tokens produced by lexer according to this value.
 */
class AntlrAstMapper(val initialOffset: Int): RubyTypesVisitor<RubyTypeAstElement> {

    private val actualDefinitions = mutableListOf<RubyTypeDefinition>()

    override fun visitIdentifier(ctx: RubyTypesParser.IdentifierContext?) = null

    override fun visitAnnotation(ctx: RubyTypesParser.AnnotationContext): RubyTypeDeclaration {
        return visitTypeDeclaration(ctx.typeDeclaration())
    }

    override fun visitAdditional(ctx: RubyTypesParser.AdditionalContext): RubyTypeDefinition {
        return visitTypeDefinition(ctx.type())
    }

    override fun visitTypeDeclaration(ctx: RubyTypesParser.TypeDeclarationContext): RubyTypeDeclaration {
        return RubyTypeDeclaration(ctx.identifier().text, ctx.identifier().start.startIndex.shift(), listOf(visitTypeDefinition(ctx.type())))
    }

    override fun visitFarg(ctx: RubyTypesParser.FargContext): RubyFunctionalArgumentType {
        val offset = ctx.start.startIndex.shift()
        val length = length(ctx)
        return when {
            ctx.QMARK() == null && ctx.STAR() == null -> RubyRegularArgumentType(offset, length, visitTypeDefinition(ctx.type()))
            ctx.QMARK() == null && ctx.STAR() != null -> RubyVarargArgumentType(offset, length, visitTypeDefinition(ctx.type()))
            ctx.QMARK() != null && ctx.STAR() == null -> RubyOptionalArgumentType(offset, length, visitTypeDefinition(ctx.type()))
            else -> throw AnyParsingException("Error in ${ctx.start.line}:${ctx.start.charPositionInLine}: type ${ctx.text} that declared as variable length args list cannot have default value")
        }
    }

    override fun visitFunctionalType(ctx: RubyTypesParser.FunctionalTypeContext): RubyFunctionalType {
        return RubyFunctionalType(ctx.start.startIndex.shift(), length(ctx), visitFunctionArgumentsTypesList(ctx.ftuple().farg(), ctx.ftuple().start.startIndex.shift(), length(ctx.ftuple())), visitTypeDefinition(ctx.type()))
    }

    override fun visitArrayType(ctx: RubyTypesParser.ArrayTypeContext): RubyArrayType {
        val typesList = visitTypesList(ctx.array().type(), ctx.array().start.startIndex.shift(), length(ctx.array()))
        val offset = ctx.start.startIndex.shift()
        val length = length(ctx)
        if (typesList.size <= ARRAY_REPR_THRESHOLD) {
            return RubyShortArrayType(offset, length, typesList)
        } else {
            return RubyLongArrayType(offset, length, typesList)
        }
    }

    override fun visitUnionType(ctx: RubyTypesParser.UnionTypeContext): RubyUnionType {
        return RubyUnionType(ctx.start.startIndex.shift(), length(ctx), RubyListOfTypeElements(ctx.start.startIndex.shift(), length(ctx), ctx.type().map { visitTypeDefinition(it) }))
    }

    override fun visitTupleType(ctx: RubyTypesParser.TupleTypeContext): RubyTupleType {
        return RubyTupleType(ctx.start.startIndex.shift(), length(ctx), visitTypesList(ctx.tuple().type(), ctx.tuple().start.startIndex.shift(), length(ctx)))
    }

    override fun visitIdentifierType(ctx: RubyTypesParser.IdentifierTypeContext): RubyAtomTypeIdentifier {
        return RubyAtomTypeIdentifier(ctx.start.startIndex.shift(), length(ctx), ctx.identifier().ATOM().map { it.text })
    }

    override fun visit(tree: ParseTree) = null

    override fun visitTuple(ctx: RubyTypesParser.TupleContext) = null

    override fun visitFtuple(ctx: RubyTypesParser.FtupleContext?) = null

    override fun visitArray(ctx: RubyTypesParser.ArrayContext) = null

    override fun visitChildren(node: RuleNode?) = null

    override fun visitErrorNode(node: ErrorNode?) = null

    override fun visitTerminal(node: TerminalNode?): RubyTypeAstElement {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitNestedType(ctx: RubyTypesParser.NestedTypeContext) = visitTypeDefinition(ctx.type())

    private fun visitTypeDefinition(ctx: RubyTypesParser.TypeContext): RubyTypeDefinition {

        return when (ctx) {
            is RubyTypesParser.FunctionalTypeContext -> visitFunctionalType(ctx)
            is RubyTypesParser.ArrayTypeContext -> visitArrayType(ctx)
            is RubyTypesParser.UnionTypeContext -> visitUnionType(ctx)
            is RubyTypesParser.TupleTypeContext -> visitTupleType(ctx)
            is RubyTypesParser.IdentifierTypeContext -> visitIdentifierType(ctx)
            is RubyTypesParser.NestedTypeContext -> visitNestedType(ctx)
            // TODO better exception
            else -> throw RuntimeException()
        }

    }

    private fun visitTypesList(ctx: List<RubyTypesParser.TypeContext>, offset: Int, length: Int): RubyListOfTypeElements =
            RubyListOfTypeElements(offset + 1, length, ctx.map { visitTypeDefinition(it) })

    private fun visitFunctionArgumentsTypesList(ctx: List<RubyTypesParser.FargContext>, offset: Int, length: Int): RubyFunctionalDomain =
            RubyFunctionalDomain(offset + 1, length, ctx.map { visitFarg(it) })
    
    private fun Int.shift() = this + initialOffset
    
    private fun length(context: ParserRuleContext): Int = context.stop.stopIndex - context.start.startIndex + 1
}