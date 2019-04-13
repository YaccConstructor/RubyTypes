// Generated from /Users/alexvangogen/Fall/diploma/RubyTypes/commentsparser/src/main/resources/org.jetbrains.plugins.ruby.types.parser/RubyTypes.g4 by ANTLR 4.7
package org.jetbrains.plugins.ruby.types.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RubyTypesParser}.
 */
public interface RubyTypesListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RubyTypesParser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(RubyTypesParser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RubyTypesParser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(RubyTypesParser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link RubyTypesParser#additional}.
	 * @param ctx the parse tree
	 */
	void enterAdditional(RubyTypesParser.AdditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link RubyTypesParser#additional}.
	 * @param ctx the parse tree
	 */
	void exitAdditional(RubyTypesParser.AdditionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link RubyTypesParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclaration(RubyTypesParser.TypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RubyTypesParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclaration(RubyTypesParser.TypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionalType}
	 * labeled alternative in {@link RubyTypesParser#type}.
	 * @param ctx the parse tree
	 */
	void enterFunctionalType(RubyTypesParser.FunctionalTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionalType}
	 * labeled alternative in {@link RubyTypesParser#type}.
	 * @param ctx the parse tree
	 */
	void exitFunctionalType(RubyTypesParser.FunctionalTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link RubyTypesParser#type}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(RubyTypesParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link RubyTypesParser#type}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(RubyTypesParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nestedType}
	 * labeled alternative in {@link RubyTypesParser#type}.
	 * @param ctx the parse tree
	 */
	void enterNestedType(RubyTypesParser.NestedTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nestedType}
	 * labeled alternative in {@link RubyTypesParser#type}.
	 * @param ctx the parse tree
	 */
	void exitNestedType(RubyTypesParser.NestedTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tupleType}
	 * labeled alternative in {@link RubyTypesParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTupleType(RubyTypesParser.TupleTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tupleType}
	 * labeled alternative in {@link RubyTypesParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTupleType(RubyTypesParser.TupleTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierType}
	 * labeled alternative in {@link RubyTypesParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierType(RubyTypesParser.IdentifierTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierType}
	 * labeled alternative in {@link RubyTypesParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierType(RubyTypesParser.IdentifierTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unionType}
	 * labeled alternative in {@link RubyTypesParser#type}.
	 * @param ctx the parse tree
	 */
	void enterUnionType(RubyTypesParser.UnionTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unionType}
	 * labeled alternative in {@link RubyTypesParser#type}.
	 * @param ctx the parse tree
	 */
	void exitUnionType(RubyTypesParser.UnionTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RubyTypesParser#typesList}.
	 * @param ctx the parse tree
	 */
	void enterTypesList(RubyTypesParser.TypesListContext ctx);
	/**
	 * Exit a parse tree produced by {@link RubyTypesParser#typesList}.
	 * @param ctx the parse tree
	 */
	void exitTypesList(RubyTypesParser.TypesListContext ctx);
	/**
	 * Enter a parse tree produced by {@link RubyTypesParser#tuple}.
	 * @param ctx the parse tree
	 */
	void enterTuple(RubyTypesParser.TupleContext ctx);
	/**
	 * Exit a parse tree produced by {@link RubyTypesParser#tuple}.
	 * @param ctx the parse tree
	 */
	void exitTuple(RubyTypesParser.TupleContext ctx);
	/**
	 * Enter a parse tree produced by {@link RubyTypesParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(RubyTypesParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link RubyTypesParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(RubyTypesParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link RubyTypesParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(RubyTypesParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link RubyTypesParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(RubyTypesParser.IdentifierContext ctx);
}