// Generated from /Users/alexvangogen/Fall/diploma/RubyTypes/rdl/src/main/resources/org.jetbrains.plugins.ruby.types.rdl/Rdl.g4 by ANTLR 4.7
package org.jetbrains.plugins.ruby.types.rdl;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RdlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RdlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RdlParser#annotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotation(RdlParser.AnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link RdlParser#methodTypeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodTypeDeclaration(RdlParser.MethodTypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link RdlParser#varTypeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarTypeDeclaration(RdlParser.VarTypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link RdlParser#receiver}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReceiver(RdlParser.ReceiverContext ctx);
	/**
	 * Visit a parse tree produced by {@link RdlParser#externalMethod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExternalMethod(RdlParser.ExternalMethodContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionalType}
	 * labeled alternative in {@link RdlParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionalType(RdlParser.FunctionalTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link RdlParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(RdlParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tupleType}
	 * labeled alternative in {@link RdlParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTupleType(RdlParser.TupleTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nestedType}
	 * labeled alternative in {@link RdlParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedType(RdlParser.NestedTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierType}
	 * labeled alternative in {@link RdlParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierType(RdlParser.IdentifierTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unionType}
	 * labeled alternative in {@link RdlParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionType(RdlParser.UnionTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RdlParser#ftuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFtuple(RdlParser.FtupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link RdlParser#tuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTuple(RdlParser.TupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link RdlParser#farg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFarg(RdlParser.FargContext ctx);
	/**
	 * Visit a parse tree produced by {@link RdlParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(RdlParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link RdlParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(RdlParser.IdentifierContext ctx);
}