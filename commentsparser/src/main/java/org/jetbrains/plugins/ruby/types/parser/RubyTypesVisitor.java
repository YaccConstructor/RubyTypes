// Generated from /Users/alexvangogen/Fall/diploma/RubyTypes/commentsparser/src/main/resources/org.jetbrains.plugins.ruby.types.parser/RubyTypes.g4 by ANTLR 4.7
package org.jetbrains.plugins.ruby.types.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RubyTypesParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RubyTypesVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RubyTypesParser#annotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotation(RubyTypesParser.AnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link RubyTypesParser#additional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditional(RubyTypesParser.AdditionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link RubyTypesParser#typeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclaration(RubyTypesParser.TypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionalType}
	 * labeled alternative in {@link RubyTypesParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionalType(RubyTypesParser.FunctionalTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link RubyTypesParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(RubyTypesParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tupleType}
	 * labeled alternative in {@link RubyTypesParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTupleType(RubyTypesParser.TupleTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nestedType}
	 * labeled alternative in {@link RubyTypesParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedType(RubyTypesParser.NestedTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierType}
	 * labeled alternative in {@link RubyTypesParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierType(RubyTypesParser.IdentifierTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unionType}
	 * labeled alternative in {@link RubyTypesParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionType(RubyTypesParser.UnionTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RubyTypesParser#tuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTuple(RubyTypesParser.TupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link RubyTypesParser#ftuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFtuple(RubyTypesParser.FtupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link RubyTypesParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(RubyTypesParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link RubyTypesParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(RubyTypesParser.IdentifierContext ctx);
}