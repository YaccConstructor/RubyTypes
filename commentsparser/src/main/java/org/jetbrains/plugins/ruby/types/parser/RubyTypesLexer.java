// Generated from /Users/alexvangogen/Fall/diploma/RubyTypes/commentsparser/src/main/resources/org.jetbrains.plugins.ruby.types.parser/RubyTypes.g4 by ANTLR 4.7
package org.jetbrains.plugins.ruby.types.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RubyTypesLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ARROW=1, LPAREN=2, RPAREN=3, LBRACE=4, RBRACE=5, COMMA=6, UNDERSCORE=7, 
		OR=8, COLON=9, SEMICOLON=10, DCOLON=11, BEGIN=12, STAR=13, QMARK=14, WHITESPACE=15, 
		NEWLINE=16, ATOM=17, FIRSTSYMBOL=18, ANYSYMBOL=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ARROW", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "COMMA", "UNDERSCORE", 
		"OR", "COLON", "SEMICOLON", "DCOLON", "BEGIN", "STAR", "QMARK", "WHITESPACE", 
		"NEWLINE", "LOWERCASE", "UPPERCASE", "DIGIT", "ATOM", "FIRSTSYMBOL", "ANYSYMBOL"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'->'", "'('", "')'", "'['", "']'", "','", "'_'", "'|'", "':'", 
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


	public RubyTypesLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RubyTypes.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25p\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3\3\3\3"+
		"\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\6\20R\n\20\r\20"+
		"\16\20S\3\20\3\20\3\21\3\21\3\21\5\21[\n\21\3\22\3\22\3\23\3\23\3\24\3"+
		"\24\3\25\3\25\7\25e\n\25\f\25\16\25h\13\25\3\26\3\26\3\27\3\27\3\27\5"+
		"\27o\n\27\2\2\30\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\2%\2\'\2)\23+\24-\25\3\2\b\4\2\13\13\"\"\4"+
		"\2\f\f\17\17\3\2c|\3\2C\\\3\2\62;\4\2C\\c|\2q\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\3/\3\2\2\2\5\62\3\2\2\2\7\64\3\2\2\2\t\66\3\2\2\2\138\3\2\2\2\r:\3"+
		"\2\2\2\17<\3\2\2\2\21>\3\2\2\2\23@\3\2\2\2\25B\3\2\2\2\27D\3\2\2\2\31"+
		"G\3\2\2\2\33L\3\2\2\2\35N\3\2\2\2\37Q\3\2\2\2!Z\3\2\2\2#\\\3\2\2\2%^\3"+
		"\2\2\2\'`\3\2\2\2)b\3\2\2\2+i\3\2\2\2-n\3\2\2\2/\60\7/\2\2\60\61\7@\2"+
		"\2\61\4\3\2\2\2\62\63\7*\2\2\63\6\3\2\2\2\64\65\7+\2\2\65\b\3\2\2\2\66"+
		"\67\7]\2\2\67\n\3\2\2\289\7_\2\29\f\3\2\2\2:;\7.\2\2;\16\3\2\2\2<=\7a"+
		"\2\2=\20\3\2\2\2>?\7~\2\2?\22\3\2\2\2@A\7<\2\2A\24\3\2\2\2BC\7=\2\2C\26"+
		"\3\2\2\2DE\7<\2\2EF\7<\2\2F\30\3\2\2\2GH\7%\2\2HI\7%\2\2IJ\7v\2\2JK\7"+
		"\"\2\2K\32\3\2\2\2LM\7,\2\2M\34\3\2\2\2NO\7A\2\2O\36\3\2\2\2PR\t\2\2\2"+
		"QP\3\2\2\2RS\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TU\3\2\2\2UV\b\20\2\2V \3\2\2"+
		"\2WX\7\17\2\2X[\7\f\2\2Y[\t\3\2\2ZW\3\2\2\2ZY\3\2\2\2[\"\3\2\2\2\\]\t"+
		"\4\2\2]$\3\2\2\2^_\t\5\2\2_&\3\2\2\2`a\t\6\2\2a(\3\2\2\2bf\5+\26\2ce\5"+
		"-\27\2dc\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2g*\3\2\2\2hf\3\2\2\2ij\t"+
		"\7\2\2j,\3\2\2\2ko\5+\26\2lo\5\17\b\2mo\5\'\24\2nk\3\2\2\2nl\3\2\2\2n"+
		"m\3\2\2\2o.\3\2\2\2\7\2SZfn\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}