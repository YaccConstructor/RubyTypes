// Generated from /Users/alexvangogen/Fall/diploma/RubyTypes/rdl/src/main/resources/org.jetbrains.plugins.ruby.types.rdl/Rdl.g4 by ANTLR 4.7
package org.jetbrains.plugins.ruby.types.rdl;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RdlParser}.
 */
public interface RdlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RdlParser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(RdlParser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RdlParser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(RdlParser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link RdlParser#methodTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodTypeDeclaration(RdlParser.MethodTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RdlParser#methodTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodTypeDeclaration(RdlParser.MethodTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link RdlParser#varTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarTypeDeclaration(RdlParser.VarTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RdlParser#varTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarTypeDeclaration(RdlParser.VarTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link RdlParser#receiver}.
	 * @param ctx the parse tree
	 */
	void enterReceiver(RdlParser.ReceiverContext ctx);
	/**
	 * Exit a parse tree produced by {@link RdlParser#receiver}.
	 * @param ctx the parse tree
	 */
	void exitReceiver(RdlParser.ReceiverContext ctx);
	/**
	 * Enter a parse tree produced by {@link RdlParser#externalMethod}.
	 * @param ctx the parse tree
	 */
	void enterExternalMethod(RdlParser.ExternalMethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link RdlParser#externalMethod}.
	 * @param ctx the parse tree
	 */
	void exitExternalMethod(RdlParser.ExternalMethodContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionalType}
	 * labeled alternative in {@link RdlParser#type}.
	 * @param ctx the parse tree
	 */
	void enterFunctionalType(RdlParser.FunctionalTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionalType}
	 * labeled alternative in {@link RdlParser#type}.
	 * @param ctx the parse tree
	 */
	void exitFunctionalType(RdlParser.FunctionalTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link RdlParser#type}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(RdlParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link RdlParser#type}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(RdlParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tupleType}
	 * labeled alternative in {@link RdlParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTupleType(RdlParser.TupleTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tupleType}
	 * labeled alternative in {@link RdlParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTupleType(RdlParser.TupleTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nestedType}
	 * labeled alternative in {@link RdlParser#type}.
	 * @param ctx the parse tree
	 */
	void enterNestedType(RdlParser.NestedTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nestedType}
	 * labeled alternative in {@link RdlParser#type}.
	 * @param ctx the parse tree
	 */
	void exitNestedType(RdlParser.NestedTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierType}
	 * labeled alternative in {@link RdlParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierType(RdlParser.IdentifierTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierType}
	 * labeled alternative in {@link RdlParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierType(RdlParser.IdentifierTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unionType}
	 * labeled alternative in {@link RdlParser#type}.
	 * @param ctx the parse tree
	 */
	void enterUnionType(RdlParser.UnionTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unionType}
	 * labeled alternative in {@link RdlParser#type}.
	 * @param ctx the parse tree
	 */
	void exitUnionType(RdlParser.UnionTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RdlParser#ftuple}.
	 * @param ctx the parse tree
	 */
	void enterFtuple(RdlParser.FtupleContext ctx);
	/**
	 * Exit a parse tree produced by {@link RdlParser#ftuple}.
	 * @param ctx the parse tree
	 */
	void exitFtuple(RdlParser.FtupleContext ctx);
	/**
	 * Enter a parse tree produced by {@link RdlParser#tuple}.
	 * @param ctx the parse tree
	 */
	void enterTuple(RdlParser.TupleContext ctx);
	/**
	 * Exit a parse tree produced by {@link RdlParser#tuple}.
	 * @param ctx the parse tree
	 */
	void exitTuple(RdlParser.TupleContext ctx);
	/**
	 * Enter a parse tree produced by {@link RdlParser#farg}.
	 * @param ctx the parse tree
	 */
	void enterFarg(RdlParser.FargContext ctx);
	/**
	 * Exit a parse tree produced by {@link RdlParser#farg}.
	 * @param ctx the parse tree
	 */
	void exitFarg(RdlParser.FargContext ctx);
	/**
	 * Enter a parse tree produced by {@link RdlParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(RdlParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link RdlParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(RdlParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link RdlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(RdlParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link RdlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(RdlParser.IdentifierContext ctx);
}