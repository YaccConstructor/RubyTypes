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
		OR=8, COLON=9, SEMICOLON=10, DCOLON=11, BEGIN=12, STAR=13, QMARK=14, WHITESPACE=15, 
		NEWLINE=16, ATOM=17, FIRSTSYMBOL=18, ANYSYMBOL=19;
	public static final int
		RULE_annotation = 0, RULE_additional = 1, RULE_typeDeclaration = 2, RULE_type = 3, 
		RULE_tuple = 4, RULE_ftuple = 5, RULE_farg = 6, RULE_array = 7, RULE_identifier = 8;
	public static final String[] ruleNames = {
		"annotation", "additional", "typeDeclaration", "type", "tuple", "ftuple", 
		"farg", "array", "identifier"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'->'", "'('", "')'", "'['", "']'", "','", "'_'", "'or'", "':'", 
		"';'", "'::'", "'##t '", "'*'", "'?'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ARROW", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "COMMA", "UNDERSCORE", 
		"OR", "COLON", "SEMICOLON", "DCOLON", "BEGIN", "STAR", "QMARK", "WHITESPACE", 
		"NEWLINE", "ATOM", "FIRSTSYMBOL", "ANYSYMBOL"
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
			setState(18);
			match(BEGIN);
			setState(19);
			typeDeclaration();
			setState(20);
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
			setState(22);
			match(BEGIN);
			setState(23);
			type(0);
			setState(24);
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
			setState(26);
			identifier();
			setState(27);
			match(COLON);
			setState(28);
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
		public FtupleContext ftuple() {
			return getRuleContext(FtupleContext.class,0);
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
			setState(42);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				_localctx = new FunctionalTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(31);
				ftuple();
				setState(32);
				match(ARROW);
				setState(33);
				type(6);
				}
				break;
			case 2:
				{
				_localctx = new TupleTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(35);
				tuple();
				}
				break;
			case 3:
				{
				_localctx = new NestedTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(36);
				match(LPAREN);
				setState(37);
				type(0);
				setState(38);
				match(RPAREN);
				}
				break;
			case 4:
				{
				_localctx = new ArrayTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(40);
				array();
				}
				break;
			case 5:
				{
				_localctx = new IdentifierTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(41);
				identifier();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(49);
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
					setState(44);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(45);
					match(OR);
					setState(46);
					type(2);
					}
					} 
				}
				setState(51);
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

	public static class TupleContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(RubyTypesParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(RubyTypesParser.RPAREN, 0); }
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(LPAREN);
			setState(56); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(53);
				type(0);
				setState(54);
				match(COMMA);
				}
				}
				setState(58); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << LBRACE) | (1L << ATOM))) != 0) );
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

	public static class FtupleContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(RubyTypesParser.LPAREN, 0); }
		public List<FargContext> farg() {
			return getRuleContexts(FargContext.class);
		}
		public FargContext farg(int i) {
			return getRuleContext(FargContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(RubyTypesParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(RubyTypesParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RubyTypesParser.COMMA, i);
		}
		public FtupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ftuple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).enterFtuple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).exitFtuple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubyTypesVisitor ) return ((RubyTypesVisitor<? extends T>)visitor).visitFtuple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FtupleContext ftuple() throws RecognitionException {
		FtupleContext _localctx = new FtupleContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_ftuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(LPAREN);
			setState(63);
			farg();
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(64);
				match(COMMA);
				setState(65);
				farg();
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(71);
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

	public static class FargContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode STAR() { return getToken(RubyTypesParser.STAR, 0); }
		public TerminalNode QMARK() { return getToken(RubyTypesParser.QMARK, 0); }
		public FargContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_farg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).enterFarg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubyTypesListener ) ((RubyTypesListener)listener).exitFarg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubyTypesVisitor ) return ((RubyTypesVisitor<? extends T>)visitor).visitFarg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FargContext farg() throws RecognitionException {
		FargContext _localctx = new FargContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_farg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STAR) {
				{
				setState(73);
				match(STAR);
				}
			}

			setState(76);
			type(0);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QMARK) {
				{
				setState(77);
				match(QMARK);
				}
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

	public static class ArrayContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(RubyTypesParser.LBRACE, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode RBRACE() { return getToken(RubyTypesParser.RBRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(RubyTypesParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RubyTypesParser.COMMA, i);
		}
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
		enterRule(_localctx, 14, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(LBRACE);
			setState(81);
			type(0);
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(82);
				match(COMMA);
				setState(83);
				type(0);
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(89);
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
		enterRule(_localctx, 16, RULE_identifier);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(ATOM);
			setState(96);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(92);
					match(DCOLON);
					setState(93);
					match(ATOM);
					}
					} 
				}
				setState(98);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\25f\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\5\5-\n\5\3\5\3\5\3\5\7\5\62\n\5\f\5\16\5\65\13\5\3\6\3"+
		"\6\3\6\3\6\6\6;\n\6\r\6\16\6<\3\6\3\6\3\7\3\7\3\7\3\7\7\7E\n\7\f\7\16"+
		"\7H\13\7\3\7\3\7\3\b\5\bM\n\b\3\b\3\b\5\bQ\n\b\3\t\3\t\3\t\3\t\7\tW\n"+
		"\t\f\t\16\tZ\13\t\3\t\3\t\3\n\3\n\3\n\7\na\n\n\f\n\16\nd\13\n\3\n\2\3"+
		"\b\13\2\4\6\b\n\f\16\20\22\2\2\2g\2\24\3\2\2\2\4\30\3\2\2\2\6\34\3\2\2"+
		"\2\b,\3\2\2\2\n\66\3\2\2\2\f@\3\2\2\2\16L\3\2\2\2\20R\3\2\2\2\22]\3\2"+
		"\2\2\24\25\7\16\2\2\25\26\5\6\4\2\26\27\7\2\2\3\27\3\3\2\2\2\30\31\7\16"+
		"\2\2\31\32\5\b\5\2\32\33\7\2\2\3\33\5\3\2\2\2\34\35\5\22\n\2\35\36\7\13"+
		"\2\2\36\37\5\b\5\2\37\7\3\2\2\2 !\b\5\1\2!\"\5\f\7\2\"#\7\3\2\2#$\5\b"+
		"\5\b$-\3\2\2\2%-\5\n\6\2&\'\7\4\2\2\'(\5\b\5\2()\7\5\2\2)-\3\2\2\2*-\5"+
		"\20\t\2+-\5\22\n\2, \3\2\2\2,%\3\2\2\2,&\3\2\2\2,*\3\2\2\2,+\3\2\2\2-"+
		"\63\3\2\2\2./\f\3\2\2/\60\7\n\2\2\60\62\5\b\5\4\61.\3\2\2\2\62\65\3\2"+
		"\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\t\3\2\2\2\65\63\3\2\2\2\66:\7\4\2"+
		"\2\678\5\b\5\289\7\b\2\29;\3\2\2\2:\67\3\2\2\2;<\3\2\2\2<:\3\2\2\2<=\3"+
		"\2\2\2=>\3\2\2\2>?\7\5\2\2?\13\3\2\2\2@A\7\4\2\2AF\5\16\b\2BC\7\b\2\2"+
		"CE\5\16\b\2DB\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF\3\2\2"+
		"\2IJ\7\5\2\2J\r\3\2\2\2KM\7\17\2\2LK\3\2\2\2LM\3\2\2\2MN\3\2\2\2NP\5\b"+
		"\5\2OQ\7\20\2\2PO\3\2\2\2PQ\3\2\2\2Q\17\3\2\2\2RS\7\6\2\2SX\5\b\5\2TU"+
		"\7\b\2\2UW\5\b\5\2VT\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y[\3\2\2\2Z"+
		"X\3\2\2\2[\\\7\7\2\2\\\21\3\2\2\2]b\7\23\2\2^_\7\r\2\2_a\7\23\2\2`^\3"+
		"\2\2\2ad\3\2\2\2b`\3\2\2\2bc\3\2\2\2c\23\3\2\2\2db\3\2\2\2\n,\63<FLPX"+
		"b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}