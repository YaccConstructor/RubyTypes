package org.jetbrains.plugins.ruby.types.rdl

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import org.jetbrains.plugins.ruby.types.parser.ast.*

class AntlrAstMapper(val initialOffset: Int): RdlVisitor<RubyTypeAstElement> {

    override fun visitAnnotation(ctx: RdlParser.AnnotationContext): RubyTypeDeclaration {
        return ctx.methodTypeDeclaration()?.let {
            visitMethodTypeDeclaration(it)
        } ?: ctx.varTypeDeclaration()?.let {
            visitVarTypeDeclaration(it)
        } ?: throw RuntimeException()
    }

    override fun visitMethodTypeDeclaration(ctx: RdlParser.MethodTypeDeclarationContext): RubyTypeDeclaration {
        return RubyTypeDeclaration(
                "${ctx.receiver()?.identifier()?.text?.let { "$it." } ?: ""}${ctx.externalMethod()?.identifier()?.text ?: ""}",
                ctx.METHOD_TYPE_DECL().symbol.startIndex,
                listOf(visitTypeDefinition(ctx.type()))
        )
    }

    override fun visitVarTypeDeclaration(ctx: RdlParser.VarTypeDeclarationContext): RubyTypeDeclaration {
        return RubyTypeDeclaration(
                ctx.ATOM().text,
                ctx.VAR_TYPE_DECL().symbol.startIndex,
                listOf(visitTypeDefinition(ctx.type()))
        )
    }

    override fun visitReceiver(ctx: RdlParser.ReceiverContext?) = null

    override fun visitExternalMethod(ctx: RdlParser.ExternalMethodContext?) = null

    override fun visitFunctionalType(ctx: RdlParser.FunctionalTypeContext): RubyFunctionalType {
        return RubyFunctionalType(
                ctx.start.startIndex.shift(),
                length(ctx),
                visitFunctionArgumentsTypesList(
                        ctx.ftuple().farg(),
                        ctx.ftuple().start.startIndex.shift(),
                        length(ctx.ftuple())
                ),
                visitTypeDefinition(ctx.type())
        )
    }

    override fun visitArrayType(ctx: RdlParser.ArrayTypeContext): RubyArrayType {
        val offset = ctx.start.startIndex.shift()
        val length = length(ctx)
        return RubyLongArrayType(offset, length, visitTypeDefinition(ctx.array().type()))
    }

    override fun visitTupleType(ctx: RdlParser.TupleTypeContext): RubyTupleType {
        return RubyTupleType(ctx.start.startIndex.shift(), length(ctx), visitTypesList(ctx.tuple().type(), ctx.tuple().start.startIndex.shift(), length(ctx)))
    }

    override fun visitNestedType(ctx: RdlParser.NestedTypeContext) = visitTypeDefinition(ctx.type())

    override fun visitIdentifierType(ctx: RdlParser.IdentifierTypeContext): RubyAtomTypeIdentifier {
        return RubyAtomTypeIdentifier(ctx.start.startIndex.shift(), length(ctx), ctx.identifier().ATOM().map { it.text })
    }

    override fun visitIdentifier(ctx: RdlParser.IdentifierContext?) = null

    override fun visit(tree: ParseTree?) = null

    override fun visitUnionType(ctx: RdlParser.UnionTypeContext): RubyUnionType {
        return RubyUnionType(
                ctx.start.startIndex.shift(),
                length(ctx),
                RubyListOfTypeElements(
                        ctx.start.startIndex.shift(),
                        length(ctx),
                        ctx.type().map { visitTypeDefinition(it) }
                )
        )
    }

    override fun visitTuple(ctx: RdlParser.TupleContext?) = null

    override fun visitFtuple(ctx: RdlParser.FtupleContext?) = null

    override fun visitFarg(ctx: RdlParser.FargContext): RubyFunctionalArgumentType {
        val offset = ctx.start.startIndex.shift()
        val length = length(ctx)
        return when {
            ctx.QMARK() == null && ctx.STAR() == null -> RubyRegularArgumentType(offset, length, visitTypeDefinition(ctx.type()))
            ctx.QMARK() == null && ctx.STAR() != null -> RubyVarargArgumentType(offset, length, visitTypeDefinition(ctx.type()))
            ctx.QMARK() != null && ctx.STAR() == null -> RubyOptionalArgumentType(offset, length, visitTypeDefinition(ctx.type()))
            else -> throw AnyParsingException("Error in ${ctx.start.line}:${ctx.start.charPositionInLine}: type ${ctx.text} that declared as variable length args list cannot have default value")
        }
    }

    override fun visitArray(ctx: RdlParser.ArrayContext?) = null

    override fun visitChildren(node: RuleNode?) = null

    override fun visitErrorNode(node: ErrorNode?) = null

    override fun visitTerminal(node: TerminalNode?) = null

    private fun visitTypeDefinition(ctx: RdlParser.TypeContext): RubyTypeDefinition {

        return when (ctx) {
            is RdlParser.FunctionalTypeContext -> visitFunctionalType(ctx)
            is RdlParser.ArrayTypeContext -> visitArrayType(ctx)
            is RdlParser.UnionTypeContext -> visitUnionType(ctx)
            is RdlParser.TupleTypeContext -> visitTupleType(ctx)
            is RdlParser.IdentifierTypeContext -> visitIdentifierType(ctx)
            is RdlParser.NestedTypeContext -> visitNestedType(ctx)
            else -> throw RuntimeException()
        }

    }

    private fun visitTypesList(ctx: List<RdlParser.TypeContext>, offset: Int, length: Int): RubyListOfTypeElements =
            RubyListOfTypeElements(offset + 1, length, ctx.map { visitTypeDefinition(it) })

    private fun visitFunctionArgumentsTypesList(ctx: List<RdlParser.FargContext>, offset: Int, length: Int): RubyFunctionalDomain =
            RubyFunctionalDomain(offset + 1, length, ctx.map { visitFarg(it) })

    private fun Int.shift() = this + initialOffset

    private fun length(context: ParserRuleContext): Int = context.stop.stopIndex - context.start.startIndex + 1
}