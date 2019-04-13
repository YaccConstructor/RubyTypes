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
		OR=8, COLON=9, SEMICOLON=10, DCOLON=11, BEGIN=12, WHITESPACE=13, NEWLINE=14, 
		ATOM=15, FIRSTSYMBOL=16, ANYSYMBOL=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ARROW", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "COMMA", "UNDERSCORE", 
		"OR", "COLON", "SEMICOLON", "DCOLON", "BEGIN", "WHITESPACE", "NEWLINE", 
		"LOWERCASE", "UPPERCASE", "DIGIT", "ATOM", "FIRSTSYMBOL", "ANYSYMBOL"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23h\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6"+
		"\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\16\6\16J\n\16\r\16\16\16K\3\16\3\16\3\17\3\17\3\17\5\17S\n"+
		"\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\7\23]\n\23\f\23\16\23`\13"+
		"\23\3\24\3\24\3\25\3\25\3\25\5\25g\n\25\2\2\26\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\2!\2#\2%\21\'\22)\23"+
		"\3\2\b\4\2\13\13\"\"\4\2\f\f\17\17\3\2c|\3\2C\\\3\2\62;\4\2C\\c|\2i\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\3+\3\2\2\2\5.\3\2\2\2\7\60\3\2\2\2\t\62\3\2\2\2\13\64\3\2\2\2\r\66"+
		"\3\2\2\2\178\3\2\2\2\21:\3\2\2\2\23<\3\2\2\2\25>\3\2\2\2\27@\3\2\2\2\31"+
		"C\3\2\2\2\33I\3\2\2\2\35R\3\2\2\2\37T\3\2\2\2!V\3\2\2\2#X\3\2\2\2%Z\3"+
		"\2\2\2\'a\3\2\2\2)f\3\2\2\2+,\7/\2\2,-\7@\2\2-\4\3\2\2\2./\7*\2\2/\6\3"+
		"\2\2\2\60\61\7+\2\2\61\b\3\2\2\2\62\63\7]\2\2\63\n\3\2\2\2\64\65\7_\2"+
		"\2\65\f\3\2\2\2\66\67\7.\2\2\67\16\3\2\2\289\7a\2\29\20\3\2\2\2:;\7~\2"+
		"\2;\22\3\2\2\2<=\7<\2\2=\24\3\2\2\2>?\7=\2\2?\26\3\2\2\2@A\7<\2\2AB\7"+
		"<\2\2B\30\3\2\2\2CD\7%\2\2DE\7%\2\2EF\7v\2\2FG\7\"\2\2G\32\3\2\2\2HJ\t"+
		"\2\2\2IH\3\2\2\2JK\3\2\2\2KI\3\2\2\2KL\3\2\2\2LM\3\2\2\2MN\b\16\2\2N\34"+
		"\3\2\2\2OP\7\17\2\2PS\7\f\2\2QS\t\3\2\2RO\3\2\2\2RQ\3\2\2\2S\36\3\2\2"+
		"\2TU\t\4\2\2U \3\2\2\2VW\t\5\2\2W\"\3\2\2\2XY\t\6\2\2Y$\3\2\2\2Z^\5\'"+
		"\24\2[]\5)\25\2\\[\3\2\2\2]`\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_&\3\2\2\2`^"+
		"\3\2\2\2ab\t\7\2\2b(\3\2\2\2cg\5\'\24\2dg\5\17\b\2eg\5#\22\2fc\3\2\2\2"+
		"fd\3\2\2\2fe\3\2\2\2g*\3\2\2\2\7\2KR^f\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}