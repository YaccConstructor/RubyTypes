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
		OR=8, COLON=9, SEMICOLON=10, DCOLON=11, BEGIN=12, WHITESPACE=13, NEWLINE=14, 
		ATOM=15, FIRSTSYMBOL=16, ANYSYMBOL=17;
	public static final int
		RULE_annotation = 0, RULE_additional = 1, RULE_typeDeclaration = 2, RULE_type = 3, 
		RULE_typesList = 4, RULE_tuple = 5, RULE_array = 6, RULE_identifier = 7;
	public static final String[] ruleNames = {
		"annotation", "additional", "typeDeclaration", "type", "typesList", "tuple", 
		"array", "identifier"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'->'", "'('", "')'", "'['", "']'", "','", "'_'", "'|'", "':'", 
		"';'", "'::'", "'##t '"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ARROW", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "COMMA", "UNDERSCORE", 
		"OR", "COLON", "SEMICOLON", "DCOLON", "BEGIN", "WHITESPACE", "NEWLINE", 
		"ATOM", "FIRSTSYMBOL", "ANYSYMBOL"
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
		public TypeDeclarationContext typeDeclaration() {
			return getRuleContext(TypeDeclarationContext.class,0);
		}
		public TerminalNode EOF() { return getToken(RubyTypesParser.EOF, 0); }
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			match(BEGIN);
			setState(17);
			typeDeclaration();
			setState(18);
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

	public static class AdditionalContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(RubyTypesParser.BEGIN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode EOF() { return getToken(RubyTypesParser.EOF, 0); }
		public AdditionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).enterAdditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).exitAdditional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubyTypesVisitor ) return ((RubyTypesVisitor<? extends T>)visitor).visitAdditional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditionalContext additional() throws RecognitionException {
		AdditionalContext _localctx = new AdditionalContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_additional);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			match(BEGIN);
			setState(21);
			type(0);
			setState(22);
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
		enterRule(_localctx, 4, RULE_typeDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			identifier();
			setState(25);
			match(COLON);
			setState(26);
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
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				_localctx = new NestedTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(29);
				match(LPAREN);
				setState(30);
				type(0);
				setState(31);
				match(RPAREN);
				}
				break;
			case 2:
				{
				_localctx = new IdentifierTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(33);
				identifier();
				}
				break;
			case 3:
				{
				_localctx = new FunctionalTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(34);
				tuple();
				setState(35);
				match(ARROW);
				setState(36);
				type(4);
				}
				break;
			case 4:
				{
				_localctx = new TupleTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(38);
				tuple();
				}
				break;
			case 5:
				{
				_localctx = new ArrayTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(39);
				array();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(47);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new UnionTypeContext(new TypeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(42);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(43);
					match(OR);
					setState(44);
					type(2);
					}
					} 
				}
				setState(49);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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
		enterRule(_localctx, 8, RULE_typesList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			type(0);
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(51);
				match(COMMA);
				setState(52);
				type(0);
				}
				}
				setState(57);
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
		enterRule(_localctx, 10, RULE_tuple);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(LPAREN);
			setState(59);
			typesList();
			setState(60);
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
		enterRule(_localctx, 12, RULE_array);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(LBRACE);
			setState(63);
			typesList();
			setState(64);
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
		enterRule(_localctx, 14, RULE_identifier);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(ATOM);
			setState(71);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(67);
					match(DCOLON);
					setState(68);
					match(ATOM);
					}
					} 
				}
				setState(73);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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
		case 3:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\23M\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\5\5+\n\5\3\5\3\5\3\5\7\5\60\n\5\f\5\16\5\63\13\5\3\6\3\6\3\6\7"+
		"\68\n\6\f\6\16\6;\13\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\7\t"+
		"H\n\t\f\t\16\tK\13\t\3\t\2\3\b\n\2\4\6\b\n\f\16\20\2\2\2K\2\22\3\2\2\2"+
		"\4\26\3\2\2\2\6\32\3\2\2\2\b*\3\2\2\2\n\64\3\2\2\2\f<\3\2\2\2\16@\3\2"+
		"\2\2\20D\3\2\2\2\22\23\7\16\2\2\23\24\5\6\4\2\24\25\7\2\2\3\25\3\3\2\2"+
		"\2\26\27\7\16\2\2\27\30\5\b\5\2\30\31\7\2\2\3\31\5\3\2\2\2\32\33\5\20"+
		"\t\2\33\34\7\13\2\2\34\35\5\b\5\2\35\7\3\2\2\2\36\37\b\5\1\2\37 \7\4\2"+
		"\2 !\5\b\5\2!\"\7\5\2\2\"+\3\2\2\2#+\5\20\t\2$%\5\f\7\2%&\7\3\2\2&\'\5"+
		"\b\5\6\'+\3\2\2\2(+\5\f\7\2)+\5\16\b\2*\36\3\2\2\2*#\3\2\2\2*$\3\2\2\2"+
		"*(\3\2\2\2*)\3\2\2\2+\61\3\2\2\2,-\f\3\2\2-.\7\n\2\2.\60\5\b\5\4/,\3\2"+
		"\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\t\3\2\2\2\63\61\3\2\2"+
		"\2\649\5\b\5\2\65\66\7\b\2\2\668\5\b\5\2\67\65\3\2\2\28;\3\2\2\29\67\3"+
		"\2\2\29:\3\2\2\2:\13\3\2\2\2;9\3\2\2\2<=\7\4\2\2=>\5\n\6\2>?\7\5\2\2?"+
		"\r\3\2\2\2@A\7\6\2\2AB\5\n\6\2BC\7\7\2\2C\17\3\2\2\2DI\7\21\2\2EF\7\r"+
		"\2\2FH\7\21\2\2GE\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\21\3\2\2\2KI"+
		"\3\2\2\2\6*\619I";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}