// Generated from /Users/alexvangogen/Fall/diploma/RubyTypes/commentsparser/src/main/resources/org.jetbrains.plugins.ruby.types.parser/RubyTypes.g4 by ANTLR 4.7
package org.jetbrains.plugins.ruby.types.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RubyTypesParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ARROW=1, LPAREN=2, RPAREN=3, LBRACE=4, RBRACE=5, COMMA=6, UNDERSCORE=7, 
		OR=8, COLON=9, SEMICOLON=10, DCOLON=11, BEGIN=12, WHITESPACE=13, ATOM=14, 
		FIRSTSYMBOL=15, ANYSYMBOL=16;
	public static final int
		RULE_annotation = 0, RULE_typeDeclaration = 1, RULE_type = 2, RULE_typesList = 3, 
		RULE_tuple = 4, RULE_array = 5, RULE_identifier = 6;
	public static final String[] ruleNames = {
		"annotation", "typeDeclaration", "type", "typesList", "tuple", "array", 
		"identifier"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'->'", "'('", "')'", "'['", "']'", "','", "'_'", "'|'", "':'", 
		"';'", "'::'", "'##t '"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ARROW", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "COMMA", "UNDERSCORE", 
		"OR", "COLON", "SEMICOLON", "DCOLON", "BEGIN", "WHITESPACE", "ATOM", "FIRSTSYMBOL", 
		"ANYSYMBOL"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "RubyTypes.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RubyTypesParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class AnnotationContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(RubyTypesParser.BEGIN, 0); }
		public List<TypeDeclarationContext> typeDeclaration() {
			return getRuleContexts(TypeDeclarationContext.class);
		}
		public TypeDeclarationContext typeDeclaration(int i) {
			return getRuleContext(TypeDeclarationContext.class,i);
		}
		public TerminalNode EOF() { return getToken(RubyTypesParser.EOF, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(RubyTypesParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(RubyTypesParser.SEMICOLON, i);
		}
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).exitAnnotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubyTypesVisitor ) return ((RubyTypesVisitor<? extends T>)visitor).visitAnnotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			match(BEGIN);
			setState(15);
			typeDeclaration();
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(16);
				match(SEMICOLON);
				setState(17);
				typeDeclaration();
				}
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(23);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeDeclarationContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode COLON() { return getToken(RubyTypesParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).enterTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).exitTypeDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubyTypesVisitor ) return ((RubyTypesVisitor<? extends T>)visitor).visitTypeDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDeclarationContext typeDeclaration() throws RecognitionException {
		TypeDeclarationContext _localctx = new TypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_typeDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			identifier();
			setState(26);
			match(COLON);
			setState(27);
			type(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FunctionalTypeContext extends TypeContext {
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(RubyTypesParser.ARROW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FunctionalTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).enterFunctionalType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).exitFunctionalType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubyTypesVisitor ) return ((RubyTypesVisitor<? extends T>)visitor).visitFunctionalType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayTypeContext extends TypeContext {
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public ArrayTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).enterArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).exitArrayType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubyTypesVisitor ) return ((RubyTypesVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NestedTypeContext extends TypeContext {
		public TerminalNode LPAREN() { return getToken(RubyTypesParser.LPAREN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(RubyTypesParser.RPAREN, 0); }
		public NestedTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).enterNestedType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).exitNestedType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubyTypesVisitor ) return ((RubyTypesVisitor<? extends T>)visitor).visitNestedType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TupleTypeContext extends TypeContext {
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
		}
		public TupleTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).enterTupleType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).exitTupleType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubyTypesVisitor ) return ((RubyTypesVisitor<? extends T>)visitor).visitTupleType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentifierTypeContext extends TypeContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public IdentifierTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).enterIdentifierType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).exitIdentifierType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubyTypesVisitor ) return ((RubyTypesVisitor<? extends T>)visitor).visitIdentifierType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnionTypeContext extends TypeContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode OR() { return getToken(RubyTypesParser.OR, 0); }
		public UnionTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).enterUnionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).exitUnionType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubyTypesVisitor ) return ((RubyTypesVisitor<? extends T>)visitor).visitUnionType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				_localctx = new NestedTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(30);
				match(LPAREN);
				setState(31);
				type(0);
				setState(32);
				match(RPAREN);
				}
				break;
			case 2:
				{
				_localctx = new IdentifierTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(34);
				identifier();
				}
				break;
			case 3:
				{
				_localctx = new FunctionalTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(35);
				tuple();
				setState(36);
				match(ARROW);
				setState(37);
				type(4);
				}
				break;
			case 4:
				{
				_localctx = new TupleTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(39);
				tuple();
				}
				break;
			case 5:
				{
				_localctx = new ArrayTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(40);
				array();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(48);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new UnionTypeContext(new TypeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(43);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(44);
					match(OR);
					setState(45);
					type(2);
					}
					} 
				}
				setState(50);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TypesListContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(RubyTypesParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RubyTypesParser.COMMA, i);
		}
		public TypesListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typesList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).enterTypesList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).exitTypesList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubyTypesVisitor ) return ((RubyTypesVisitor<? extends T>)visitor).visitTypesList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypesListContext typesList() throws RecognitionException {
		TypesListContext _localctx = new TypesListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_typesList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			type(0);
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(52);
				match(COMMA);
				setState(53);
				type(0);
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TupleContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(RubyTypesParser.LPAREN, 0); }
		public TypesListContext typesList() {
			return getRuleContext(TypesListContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(RubyTypesParser.RPAREN, 0); }
		public TupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tuple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).enterTuple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).exitTuple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubyTypesVisitor ) return ((RubyTypesVisitor<? extends T>)visitor).visitTuple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TupleContext tuple() throws RecognitionException {
		TupleContext _localctx = new TupleContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_tuple);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(LPAREN);
			setState(60);
			typesList();
			setState(61);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(RubyTypesParser.LBRACE, 0); }
		public TypesListContext typesList() {
			return getRuleContext(TypesListContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(RubyTypesParser.RBRACE, 0); }
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).exitArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubyTypesVisitor ) return ((RubyTypesVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_array);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(LBRACE);
			setState(64);
			typesList();
			setState(65);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public List<TerminalNode> ATOM() { return getTokens(RubyTypesParser.ATOM); }
		public TerminalNode ATOM(int i) {
			return getToken(RubyTypesParser.ATOM, i);
		}
		public List<TerminalNode> DCOLON() { return getTokens(RubyTypesParser.DCOLON); }
		public TerminalNode DCOLON(int i) {
			return getToken(RubyTypesParser.DCOLON, i);
		}
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubyTypesVisitor ) return ((RubyTypesVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_identifier);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(ATOM);
			setState(72);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(68);
					match(DCOLON);
					setState(69);
					match(ATOM);
					}
					} 
				}
				setState(74);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return type_sempred((TypeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\22N\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\7\2\25\n\2"+
		"\f\2\16\2\30\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\5\4,\n\4\3\4\3\4\3\4\7\4\61\n\4\f\4\16\4\64\13\4"+
		"\3\5\3\5\3\5\7\59\n\5\f\5\16\5<\13\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\7\bI\n\b\f\b\16\bL\13\b\3\b\2\3\6\t\2\4\6\b\n\f\16\2\2\2N\2"+
		"\20\3\2\2\2\4\33\3\2\2\2\6+\3\2\2\2\b\65\3\2\2\2\n=\3\2\2\2\fA\3\2\2\2"+
		"\16E\3\2\2\2\20\21\7\16\2\2\21\26\5\4\3\2\22\23\7\f\2\2\23\25\5\4\3\2"+
		"\24\22\3\2\2\2\25\30\3\2\2\2\26\24\3\2\2\2\26\27\3\2\2\2\27\31\3\2\2\2"+
		"\30\26\3\2\2\2\31\32\7\2\2\3\32\3\3\2\2\2\33\34\5\16\b\2\34\35\7\13\2"+
		"\2\35\36\5\6\4\2\36\5\3\2\2\2\37 \b\4\1\2 !\7\4\2\2!\"\5\6\4\2\"#\7\5"+
		"\2\2#,\3\2\2\2$,\5\16\b\2%&\5\n\6\2&\'\7\3\2\2\'(\5\6\4\6(,\3\2\2\2),"+
		"\5\n\6\2*,\5\f\7\2+\37\3\2\2\2+$\3\2\2\2+%\3\2\2\2+)\3\2\2\2+*\3\2\2\2"+
		",\62\3\2\2\2-.\f\3\2\2./\7\n\2\2/\61\5\6\4\4\60-\3\2\2\2\61\64\3\2\2\2"+
		"\62\60\3\2\2\2\62\63\3\2\2\2\63\7\3\2\2\2\64\62\3\2\2\2\65:\5\6\4\2\66"+
		"\67\7\b\2\2\679\5\6\4\28\66\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;\t\3"+
		"\2\2\2<:\3\2\2\2=>\7\4\2\2>?\5\b\5\2?@\7\5\2\2@\13\3\2\2\2AB\7\6\2\2B"+
		"C\5\b\5\2CD\7\7\2\2D\r\3\2\2\2EJ\7\20\2\2FG\7\r\2\2GI\7\20\2\2HF\3\2\2"+
		"\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\17\3\2\2\2LJ\3\2\2\2\7\26+\62:J";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}