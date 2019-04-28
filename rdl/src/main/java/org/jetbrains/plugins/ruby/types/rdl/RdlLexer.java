// Generated from /Users/alexvangogen/Fall/diploma/RubyTypes/rdl/src/main/resources/org.jetbrains.plugins.ruby.types.rdl/Rdl.g4 by ANTLR 4.7
package org.jetbrains.plugins.ruby.types.rdl;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RdlLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		METHOD_TYPE_DECL=1, VAR_TYPE_DECL=2, ARRAY=3, AT=4, ARROW=5, LPAREN=6, 
		RPAREN=7, LBRACE=8, RBRACE=9, LANGLE=10, RANGLE=11, COMMA=12, UNDERSCORE=13, 
		OR=14, COLON=15, SEMICOLON=16, DCOLON=17, BEGIN=18, STAR=19, QMARK=20, 
		QUOTE=21, WHITESPACE=22, NEWLINE=23, ATOM=24, FIRSTSYMBOL=25, ANYSYMBOL=26;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"METHOD_TYPE_DECL", "VAR_TYPE_DECL", "ARRAY", "AT", "ARROW", "LPAREN", 
		"RPAREN", "LBRACE", "RBRACE", "LANGLE", "RANGLE", "COMMA", "UNDERSCORE", 
		"OR", "COLON", "SEMICOLON", "DCOLON", "BEGIN", "STAR", "QMARK", "QUOTE", 
		"WHITESPACE", "NEWLINE", "LOWERCASE", "UPPERCASE", "DIGIT", "ATOM", "FIRSTSYMBOL", 
		"ANYSYMBOL"
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


	public RdlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Rdl.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\34\u009b\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r"+
		"\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23"+
		"\3\23\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\6\27}\n\27\r\27"+
		"\16\27~\3\27\3\27\3\30\3\30\3\30\5\30\u0086\n\30\3\31\3\31\3\32\3\32\3"+
		"\33\3\33\3\34\6\34\u008f\n\34\r\34\16\34\u0090\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\3\36\5\36\u009a\n\36\2\2\37\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\2\63\2\65\2\67\329\33;\34\3\2\b\4\2\13\13\"\"\4\2\f\f\17\17\3\2c|"+
		"\3\2C\\\3\2\62;\4\2C\\c|\2\u009e\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2"+
		"\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\3"+
		"=\3\2\2\2\5B\3\2\2\2\7K\3\2\2\2\tQ\3\2\2\2\13S\3\2\2\2\rV\3\2\2\2\17X"+
		"\3\2\2\2\21Z\3\2\2\2\23\\\3\2\2\2\25^\3\2\2\2\27`\3\2\2\2\31b\3\2\2\2"+
		"\33d\3\2\2\2\35f\3\2\2\2\37i\3\2\2\2!k\3\2\2\2#m\3\2\2\2%p\3\2\2\2\'u"+
		"\3\2\2\2)w\3\2\2\2+y\3\2\2\2-|\3\2\2\2/\u0085\3\2\2\2\61\u0087\3\2\2\2"+
		"\63\u0089\3\2\2\2\65\u008b\3\2\2\2\67\u008e\3\2\2\29\u0092\3\2\2\2;\u0099"+
		"\3\2\2\2=>\7v\2\2>?\7{\2\2?@\7r\2\2@A\7g\2\2A\4\3\2\2\2BC\7x\2\2CD\7c"+
		"\2\2DE\7t\2\2EF\7a\2\2FG\7v\2\2GH\7{\2\2HI\7r\2\2IJ\7g\2\2J\6\3\2\2\2"+
		"KL\7C\2\2LM\7t\2\2MN\7t\2\2NO\7c\2\2OP\7{\2\2P\b\3\2\2\2QR\7B\2\2R\n\3"+
		"\2\2\2ST\7/\2\2TU\7@\2\2U\f\3\2\2\2VW\7*\2\2W\16\3\2\2\2XY\7+\2\2Y\20"+
		"\3\2\2\2Z[\7]\2\2[\22\3\2\2\2\\]\7_\2\2]\24\3\2\2\2^_\7>\2\2_\26\3\2\2"+
		"\2`a\7@\2\2a\30\3\2\2\2bc\7.\2\2c\32\3\2\2\2de\7a\2\2e\34\3\2\2\2fg\7"+
		"q\2\2gh\7t\2\2h\36\3\2\2\2ij\7<\2\2j \3\2\2\2kl\7=\2\2l\"\3\2\2\2mn\7"+
		"<\2\2no\7<\2\2o$\3\2\2\2pq\7%\2\2qr\7%\2\2rs\7v\2\2st\7\"\2\2t&\3\2\2"+
		"\2uv\7,\2\2v(\3\2\2\2wx\7A\2\2x*\3\2\2\2yz\7)\2\2z,\3\2\2\2{}\t\2\2\2"+
		"|{\3\2\2\2}~\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081"+
		"\b\27\2\2\u0081.\3\2\2\2\u0082\u0083\7\17\2\2\u0083\u0086\7\f\2\2\u0084"+
		"\u0086\t\3\2\2\u0085\u0082\3\2\2\2\u0085\u0084\3\2\2\2\u0086\60\3\2\2"+
		"\2\u0087\u0088\t\4\2\2\u0088\62\3\2\2\2\u0089\u008a\t\5\2\2\u008a\64\3"+
		"\2\2\2\u008b\u008c\t\6\2\2\u008c\66\3\2\2\2\u008d\u008f\5;\36\2\u008e"+
		"\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2"+
		"\2\2\u00918\3\2\2\2\u0092\u0093\t\7\2\2\u0093:\3\2\2\2\u0094\u009a\59"+
		"\35\2\u0095\u009a\5\33\16\2\u0096\u009a\5\65\33\2\u0097\u009a\5\21\t\2"+
		"\u0098\u009a\5\23\n\2\u0099\u0094\3\2\2\2\u0099\u0095\3\2\2\2\u0099\u0096"+
		"\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u0098\3\2\2\2\u009a<\3\2\2\2\7\2~\u0085"+
		"\u0090\u0099\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}