// Generated from /Users/alexvangogen/Fall/diploma/RubyTypes/rdl/src/main/resources/org.jetbrains.plugins.ruby.types.rdl/Rdl.g4 by ANTLR 4.7
package org.jetbrains.plugins.ruby.types.rdl;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RdlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		METHOD_TYPE_DECL=1, VAR_TYPE_DECL=2, ARRAY=3, AT=4, ARROW=5, LPAREN=6, 
		RPAREN=7, LBRACE=8, RBRACE=9, LANGLE=10, RANGLE=11, COMMA=12, UNDERSCORE=13, 
		OR=14, COLON=15, SEMICOLON=16, DCOLON=17, BEGIN=18, STAR=19, QMARK=20, 
		QUOTE=21, WHITESPACE=22, NEWLINE=23, ATOM=24, FIRSTSYMBOL=25, ANYSYMBOL=26;
	public static final int
		RULE_annotation = 0, RULE_methodTypeDeclaration = 1, RULE_varTypeDeclaration = 2, 
		RULE_receiver = 3, RULE_externalMethod = 4, RULE_type = 5, RULE_ftuple = 6, 
		RULE_tuple = 7, RULE_farg = 8, RULE_array = 9, RULE_identifier = 10;
	public static final String[] ruleNames = {
		"annotation", "methodTypeDeclaration", "varTypeDeclaration", "receiver", 
		"externalMethod", "type", "ftuple", "tuple", "farg", "array", "identifier"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'type'", "'var_type'", "'Array'", "'@'", "'->'", "'('", "')'", 
		"'['", "']'", "'<'", "'>'", "','", "'_'", "'or'", "':'", "';'", "'::'", 
		"'##t '", "'*'", "'?'", "'''"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "METHOD_TYPE_DECL", "VAR_TYPE_DECL", "ARRAY", "AT", "ARROW", "LPAREN", 
		"RPAREN", "LBRACE", "RBRACE", "LANGLE", "RANGLE", "COMMA", "UNDERSCORE", 
		"OR", "COLON", "SEMICOLON", "DCOLON", "BEGIN", "STAR", "QMARK", "QUOTE", 
		"WHITESPACE", "NEWLINE", "ATOM", "FIRSTSYMBOL", "ANYSYMBOL"
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
	public String getGrammarFileName() { return "Rdl.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RdlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class AnnotationContext extends ParserRuleContext {
		public MethodTypeDeclarationContext methodTypeDeclaration() {
			return getRuleContext(MethodTypeDeclarationContext.class,0);
		}
		public VarTypeDeclarationContext varTypeDeclaration() {
			return getRuleContext(VarTypeDeclarationContext.class,0);
		}
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).exitAnnotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RdlVisitor ) return ((RdlVisitor<? extends T>)visitor).visitAnnotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_annotation);
		try {
			setState(24);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case METHOD_TYPE_DECL:
				enterOuterAlt(_localctx, 1);
				{
				setState(22);
				methodTypeDeclaration();
				}
				break;
			case VAR_TYPE_DECL:
				enterOuterAlt(_localctx, 2);
				{
				setState(23);
				varTypeDeclaration();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class MethodTypeDeclarationContext extends ParserRuleContext {
		public TerminalNode METHOD_TYPE_DECL() { return getToken(RdlParser.METHOD_TYPE_DECL, 0); }
		public List<TerminalNode> QUOTE() { return getTokens(RdlParser.QUOTE); }
		public TerminalNode QUOTE(int i) {
			return getToken(RdlParser.QUOTE, i);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ReceiverContext receiver() {
			return getRuleContext(ReceiverContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(RdlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RdlParser.COMMA, i);
		}
		public ExternalMethodContext externalMethod() {
			return getRuleContext(ExternalMethodContext.class,0);
		}
		public MethodTypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodTypeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).enterMethodTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).exitMethodTypeDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RdlVisitor ) return ((RdlVisitor<? extends T>)visitor).visitMethodTypeDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodTypeDeclarationContext methodTypeDeclaration() throws RecognitionException {
		MethodTypeDeclarationContext _localctx = new MethodTypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_methodTypeDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(METHOD_TYPE_DECL);
			setState(30);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ATOM) {
				{
				setState(27);
				receiver();
				setState(28);
				match(COMMA);
				}
			}

			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(32);
				externalMethod();
				setState(33);
				match(COMMA);
				}
			}

			setState(37);
			match(QUOTE);
			setState(38);
			type(0);
			setState(39);
			match(QUOTE);
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

	public static class VarTypeDeclarationContext extends ParserRuleContext {
		public TerminalNode VAR_TYPE_DECL() { return getToken(RdlParser.VAR_TYPE_DECL, 0); }
		public TerminalNode AT() { return getToken(RdlParser.AT, 0); }
		public TerminalNode ATOM() { return getToken(RdlParser.ATOM, 0); }
		public TerminalNode COMMA() { return getToken(RdlParser.COMMA, 0); }
		public List<TerminalNode> QUOTE() { return getTokens(RdlParser.QUOTE); }
		public TerminalNode QUOTE(int i) {
			return getToken(RdlParser.QUOTE, i);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VarTypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varTypeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).enterVarTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).exitVarTypeDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RdlVisitor ) return ((RdlVisitor<? extends T>)visitor).visitVarTypeDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarTypeDeclarationContext varTypeDeclaration() throws RecognitionException {
		VarTypeDeclarationContext _localctx = new VarTypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_varTypeDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(VAR_TYPE_DECL);
			setState(42);
			match(AT);
			setState(43);
			match(ATOM);
			setState(44);
			match(COMMA);
			setState(45);
			match(QUOTE);
			setState(46);
			type(0);
			setState(47);
			match(QUOTE);
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

	public static class ReceiverContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ReceiverContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_receiver; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).enterReceiver(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).exitReceiver(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RdlVisitor ) return ((RdlVisitor<? extends T>)visitor).visitReceiver(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReceiverContext receiver() throws RecognitionException {
		ReceiverContext _localctx = new ReceiverContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_receiver);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			identifier();
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

	public static class ExternalMethodContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(RdlParser.COLON, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ExternalMethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_externalMethod; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).enterExternalMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).exitExternalMethod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RdlVisitor ) return ((RdlVisitor<? extends T>)visitor).visitExternalMethod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExternalMethodContext externalMethod() throws RecognitionException {
		ExternalMethodContext _localctx = new ExternalMethodContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_externalMethod);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(COLON);
			setState(52);
			identifier();
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
		public TerminalNode ARROW() { return getToken(RdlParser.ARROW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FunctionalTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).enterFunctionalType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).exitFunctionalType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RdlVisitor ) return ((RdlVisitor<? extends T>)visitor).visitFunctionalType(this);
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
			if ( listener instanceof RdlListener ) ((RdlListener)listener).enterArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).exitArrayType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RdlVisitor ) return ((RdlVisitor<? extends T>)visitor).visitArrayType(this);
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
			if ( listener instanceof RdlListener ) ((RdlListener)listener).enterTupleType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).exitTupleType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RdlVisitor ) return ((RdlVisitor<? extends T>)visitor).visitTupleType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NestedTypeContext extends TypeContext {
		public TerminalNode LPAREN() { return getToken(RdlParser.LPAREN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(RdlParser.RPAREN, 0); }
		public NestedTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).enterNestedType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).exitNestedType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RdlVisitor ) return ((RdlVisitor<? extends T>)visitor).visitNestedType(this);
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
			if ( listener instanceof RdlListener ) ((RdlListener)listener).enterIdentifierType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).exitIdentifierType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RdlVisitor ) return ((RdlVisitor<? extends T>)visitor).visitIdentifierType(this);
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
		public TerminalNode OR() { return getToken(RdlParser.OR, 0); }
		public UnionTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).enterUnionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).exitUnionType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RdlVisitor ) return ((RdlVisitor<? extends T>)visitor).visitUnionType(this);
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
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				_localctx = new FunctionalTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(55);
				ftuple();
				setState(56);
				match(ARROW);
				setState(57);
				type(5);
				}
				break;
			case 2:
				{
				_localctx = new TupleTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(59);
				tuple();
				}
				break;
			case 3:
				{
				_localctx = new NestedTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(60);
				match(LPAREN);
				setState(61);
				type(0);
				setState(62);
				match(RPAREN);
				}
				break;
			case 4:
				{
				_localctx = new ArrayTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(64);
				array();
				}
				break;
			case 5:
				{
				_localctx = new IdentifierTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(65);
				identifier();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(73);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new UnionTypeContext(new TypeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(68);
					if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
					setState(69);
					match(OR);
					setState(70);
					type(7);
					}
					} 
				}
				setState(75);
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
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FtupleContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(RdlParser.LPAREN, 0); }
		public List<FargContext> farg() {
			return getRuleContexts(FargContext.class);
		}
		public FargContext farg(int i) {
			return getRuleContext(FargContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(RdlParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(RdlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RdlParser.COMMA, i);
		}
		public FtupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ftuple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).enterFtuple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).exitFtuple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RdlVisitor ) return ((RdlVisitor<? extends T>)visitor).visitFtuple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FtupleContext ftuple() throws RecognitionException {
		FtupleContext _localctx = new FtupleContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ftuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(LPAREN);
			setState(77);
			farg();
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(78);
				match(COMMA);
				setState(79);
				farg();
				}
				}
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(85);
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

	public static class TupleContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(RdlParser.LPAREN, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(RdlParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(RdlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RdlParser.COMMA, i);
		}
		public TupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tuple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).enterTuple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).exitTuple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RdlVisitor ) return ((RdlVisitor<? extends T>)visitor).visitTuple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TupleContext tuple() throws RecognitionException {
		TupleContext _localctx = new TupleContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(LPAREN);
			setState(88);
			type(0);
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(89);
				match(COMMA);
				setState(90);
				type(0);
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(96);
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
		public TerminalNode STAR() { return getToken(RdlParser.STAR, 0); }
		public TerminalNode QMARK() { return getToken(RdlParser.QMARK, 0); }
		public FargContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_farg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).enterFarg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).exitFarg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RdlVisitor ) return ((RdlVisitor<? extends T>)visitor).visitFarg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FargContext farg() throws RecognitionException {
		FargContext _localctx = new FargContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_farg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STAR) {
				{
				setState(98);
				match(STAR);
				}
			}

			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QMARK) {
				{
				setState(101);
				match(QMARK);
				}
			}

			setState(104);
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

	public static class ArrayContext extends ParserRuleContext {
		public TerminalNode ARRAY() { return getToken(RdlParser.ARRAY, 0); }
		public TerminalNode LANGLE() { return getToken(RdlParser.LANGLE, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RANGLE() { return getToken(RdlParser.RANGLE, 0); }
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).exitArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RdlVisitor ) return ((RdlVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_array);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(ARRAY);
			setState(107);
			match(LANGLE);
			setState(108);
			type(0);
			setState(109);
			match(RANGLE);
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
		public List<TerminalNode> ATOM() { return getTokens(RdlParser.ATOM); }
		public TerminalNode ATOM(int i) {
			return getToken(RdlParser.ATOM, i);
		}
		public List<TerminalNode> DCOLON() { return getTokens(RdlParser.DCOLON); }
		public TerminalNode DCOLON(int i) {
			return getToken(RdlParser.DCOLON, i);
		}
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RdlListener ) ((RdlListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RdlVisitor ) return ((RdlVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_identifier);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(ATOM);
			setState(116);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(112);
					match(DCOLON);
					setState(113);
					match(ATOM);
					}
					} 
				}
				setState(118);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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
		case 5:
			return type_sempred((TypeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\34z\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\3\2\5\2\33\n\2\3\3\3\3\3\3\3\3\5\3!\n\3\3\3\3\3\3\3\5\3&\n"+
		"\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7E\n\7\3\7\3\7\3\7"+
		"\7\7J\n\7\f\7\16\7M\13\7\3\b\3\b\3\b\3\b\7\bS\n\b\f\b\16\bV\13\b\3\b\3"+
		"\b\3\t\3\t\3\t\3\t\7\t^\n\t\f\t\16\ta\13\t\3\t\3\t\3\n\5\nf\n\n\3\n\5"+
		"\ni\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\7\fu\n\f\f\f\16\f"+
		"x\13\f\3\f\2\3\f\r\2\4\6\b\n\f\16\20\22\24\26\2\2\2{\2\32\3\2\2\2\4\34"+
		"\3\2\2\2\6+\3\2\2\2\b\63\3\2\2\2\n\65\3\2\2\2\fD\3\2\2\2\16N\3\2\2\2\20"+
		"Y\3\2\2\2\22e\3\2\2\2\24l\3\2\2\2\26q\3\2\2\2\30\33\5\4\3\2\31\33\5\6"+
		"\4\2\32\30\3\2\2\2\32\31\3\2\2\2\33\3\3\2\2\2\34 \7\3\2\2\35\36\5\b\5"+
		"\2\36\37\7\16\2\2\37!\3\2\2\2 \35\3\2\2\2 !\3\2\2\2!%\3\2\2\2\"#\5\n\6"+
		"\2#$\7\16\2\2$&\3\2\2\2%\"\3\2\2\2%&\3\2\2\2&\'\3\2\2\2\'(\7\27\2\2()"+
		"\5\f\7\2)*\7\27\2\2*\5\3\2\2\2+,\7\4\2\2,-\7\6\2\2-.\7\32\2\2./\7\16\2"+
		"\2/\60\7\27\2\2\60\61\5\f\7\2\61\62\7\27\2\2\62\7\3\2\2\2\63\64\5\26\f"+
		"\2\64\t\3\2\2\2\65\66\7\21\2\2\66\67\5\26\f\2\67\13\3\2\2\289\b\7\1\2"+
		"9:\5\16\b\2:;\7\7\2\2;<\5\f\7\7<E\3\2\2\2=E\5\20\t\2>?\7\b\2\2?@\5\f\7"+
		"\2@A\7\t\2\2AE\3\2\2\2BE\5\24\13\2CE\5\26\f\2D8\3\2\2\2D=\3\2\2\2D>\3"+
		"\2\2\2DB\3\2\2\2DC\3\2\2\2EK\3\2\2\2FG\f\b\2\2GH\7\20\2\2HJ\5\f\7\tIF"+
		"\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2L\r\3\2\2\2MK\3\2\2\2NO\7\b\2\2"+
		"OT\5\22\n\2PQ\7\16\2\2QS\5\22\n\2RP\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2"+
		"\2\2UW\3\2\2\2VT\3\2\2\2WX\7\t\2\2X\17\3\2\2\2YZ\7\b\2\2Z_\5\f\7\2[\\"+
		"\7\16\2\2\\^\5\f\7\2][\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`b\3\2\2\2"+
		"a_\3\2\2\2bc\7\t\2\2c\21\3\2\2\2df\7\25\2\2ed\3\2\2\2ef\3\2\2\2fh\3\2"+
		"\2\2gi\7\26\2\2hg\3\2\2\2hi\3\2\2\2ij\3\2\2\2jk\5\f\7\2k\23\3\2\2\2lm"+
		"\7\5\2\2mn\7\f\2\2no\5\f\7\2op\7\r\2\2p\25\3\2\2\2qv\7\32\2\2rs\7\23\2"+
		"\2su\7\32\2\2tr\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2w\27\3\2\2\2xv\3"+
		"\2\2\2\f\32 %DKT_ehv";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}