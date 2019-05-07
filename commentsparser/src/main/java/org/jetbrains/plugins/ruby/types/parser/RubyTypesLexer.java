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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25q\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3\3\3\3"+
		"\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\13\3\13"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\6\20S\n\20\r"+
		"\20\16\20T\3\20\3\20\3\21\3\21\3\21\5\21\\\n\21\3\22\3\22\3\23\3\23\3"+
		"\24\3\24\3\25\3\25\7\25f\n\25\f\25\16\25i\13\25\3\26\3\26\3\27\3\27\3"+
		"\27\5\27p\n\27\2\2\30\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\2%\2\'\2)\23+\24-\25\3\2\b\4\2\13\13\""+
		"\"\4\2\f\f\17\17\3\2c|\3\2C\\\3\2\62;\4\2C\\c|\2r\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\3/\3\2\2\2\5\62\3\2\2\2\7\64\3\2\2\2\t\66\3\2\2\2\138\3\2\2\2\r:"+
		"\3\2\2\2\17<\3\2\2\2\21>\3\2\2\2\23A\3\2\2\2\25C\3\2\2\2\27E\3\2\2\2\31"+
		"H\3\2\2\2\33M\3\2\2\2\35O\3\2\2\2\37R\3\2\2\2![\3\2\2\2#]\3\2\2\2%_\3"+
		"\2\2\2\'a\3\2\2\2)c\3\2\2\2+j\3\2\2\2-o\3\2\2\2/\60\7/\2\2\60\61\7@\2"+
		"\2\61\4\3\2\2\2\62\63\7*\2\2\63\6\3\2\2\2\64\65\7+\2\2\65\b\3\2\2\2\66"+
		"\67\7]\2\2\67\n\3\2\2\289\7_\2\29\f\3\2\2\2:;\7.\2\2;\16\3\2\2\2<=\7a"+
		"\2\2=\20\3\2\2\2>?\7q\2\2?@\7t\2\2@\22\3\2\2\2AB\7<\2\2B\24\3\2\2\2CD"+
		"\7=\2\2D\26\3\2\2\2EF\7<\2\2FG\7<\2\2G\30\3\2\2\2HI\7%\2\2IJ\7%\2\2JK"+
		"\7v\2\2KL\7\"\2\2L\32\3\2\2\2MN\7,\2\2N\34\3\2\2\2OP\7A\2\2P\36\3\2\2"+
		"\2QS\t\2\2\2RQ\3\2\2\2ST\3\2\2\2TR\3\2\2\2TU\3\2\2\2UV\3\2\2\2VW\b\20"+
		"\2\2W \3\2\2\2XY\7\17\2\2Y\\\7\f\2\2Z\\\t\3\2\2[X\3\2\2\2[Z\3\2\2\2\\"+
		"\"\3\2\2\2]^\t\4\2\2^$\3\2\2\2_`\t\5\2\2`&\3\2\2\2ab\t\6\2\2b(\3\2\2\2"+
		"cg\5+\26\2df\5-\27\2ed\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2h*\3\2\2\2"+
		"ig\3\2\2\2jk\t\7\2\2k,\3\2\2\2lp\5+\26\2mp\5\17\b\2np\5\'\24\2ol\3\2\2"+
		"\2om\3\2\2\2on\3\2\2\2p.\3\2\2\2\7\2T[go\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}