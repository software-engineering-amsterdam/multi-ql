// Generated from A:\Users\sander\Documents\NetBeansProjects\multi-ql\savahu\QL\src\ql\antlr\QL.g4 by ANTLR 4.5.2

    package ql.antlr.generatedcode;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, BOOLEAN_TYPE=9, 
		MONEY_TYPE=10, INTEGER_TYPE=11, STRING_TYPE=12, WS=13, COMMENT=14, Ident=15, 
		Int=16, Str=17, OrSymbol=18, AndSymbol=19, RelSymbol=20, AddSymbol=21, 
		PlusSymbol=22, MinSymbol=23, MulSymbol=24, True=25, False=26, ExclamationMark=27;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "BOOLEAN_TYPE", 
		"MONEY_TYPE", "INTEGER_TYPE", "STRING_TYPE", "WS", "COMMENT", "Ident", 
		"Int", "Str", "OrSymbol", "AndSymbol", "RelSymbol", "AddSymbol", "PlusSymbol", 
		"MinSymbol", "MulSymbol", "True", "False", "ExclamationMark"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'if'", "'('", "')'", "'else'", "':'", "'boolean'", 
		"'money'", "'integer'", "'string'", null, null, null, null, null, "'||'", 
		"'&&'", null, null, "'+'", "'-'", null, "'true'", "'false'", "'!'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, "BOOLEAN_TYPE", 
		"MONEY_TYPE", "INTEGER_TYPE", "STRING_TYPE", "WS", "COMMENT", "Ident", 
		"Int", "Str", "OrSymbol", "AndSymbol", "RelSymbol", "AddSymbol", "PlusSymbol", 
		"MinSymbol", "MulSymbol", "True", "False", "ExclamationMark"
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


	public QLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\35\u00bd\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3"+
		"\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\7\17v\n\17\f\17\16\17y\13\17\3\17\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\7\20\u0082\n\20\f\20\16\20\u0085\13\20\3\21\6\21\u0088\n\21\r\21"+
		"\16\21\u0089\3\22\3\22\7\22\u008e\n\22\f\22\16\22\u0091\13\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\5\25\u00a5\n\25\3\26\3\26\5\26\u00a9\n\26\3\27\3\27\3\30\3"+
		"\30\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\34\3\34\3\u008f\2\35\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\35\3\2\6\5\2\13\f\17\17\"\"\4\2C\\c|\6\2\62;C\\aac|\4\2,,\61"+
		"\61\u00c6\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\39\3\2"+
		"\2\2\5>\3\2\2\2\7@\3\2\2\2\tB\3\2\2\2\13E\3\2\2\2\rG\3\2\2\2\17I\3\2\2"+
		"\2\21N\3\2\2\2\23P\3\2\2\2\25X\3\2\2\2\27^\3\2\2\2\31f\3\2\2\2\33m\3\2"+
		"\2\2\35q\3\2\2\2\37\177\3\2\2\2!\u0087\3\2\2\2#\u008b\3\2\2\2%\u0094\3"+
		"\2\2\2\'\u0097\3\2\2\2)\u00a4\3\2\2\2+\u00a8\3\2\2\2-\u00aa\3\2\2\2/\u00ac"+
		"\3\2\2\2\61\u00ae\3\2\2\2\63\u00b0\3\2\2\2\65\u00b5\3\2\2\2\67\u00bb\3"+
		"\2\2\29:\7h\2\2:;\7q\2\2;<\7t\2\2<=\7o\2\2=\4\3\2\2\2>?\7}\2\2?\6\3\2"+
		"\2\2@A\7\177\2\2A\b\3\2\2\2BC\7k\2\2CD\7h\2\2D\n\3\2\2\2EF\7*\2\2F\f\3"+
		"\2\2\2GH\7+\2\2H\16\3\2\2\2IJ\7g\2\2JK\7n\2\2KL\7u\2\2LM\7g\2\2M\20\3"+
		"\2\2\2NO\7<\2\2O\22\3\2\2\2PQ\7d\2\2QR\7q\2\2RS\7q\2\2ST\7n\2\2TU\7g\2"+
		"\2UV\7c\2\2VW\7p\2\2W\24\3\2\2\2XY\7o\2\2YZ\7q\2\2Z[\7p\2\2[\\\7g\2\2"+
		"\\]\7{\2\2]\26\3\2\2\2^_\7k\2\2_`\7p\2\2`a\7v\2\2ab\7g\2\2bc\7i\2\2cd"+
		"\7g\2\2de\7t\2\2e\30\3\2\2\2fg\7u\2\2gh\7v\2\2hi\7t\2\2ij\7k\2\2jk\7p"+
		"\2\2kl\7i\2\2l\32\3\2\2\2mn\t\2\2\2no\3\2\2\2op\b\16\2\2p\34\3\2\2\2q"+
		"r\7\61\2\2rs\7,\2\2sw\3\2\2\2tv\13\2\2\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2"+
		"wx\3\2\2\2xz\3\2\2\2yw\3\2\2\2z{\7,\2\2{|\7\61\2\2|}\3\2\2\2}~\b\17\2"+
		"\2~\36\3\2\2\2\177\u0083\t\3\2\2\u0080\u0082\t\4\2\2\u0081\u0080\3\2\2"+
		"\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084 "+
		"\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0088\4\62;\2\u0087\u0086\3\2\2\2\u0088"+
		"\u0089\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\"\3\2\2\2"+
		"\u008b\u008f\7$\2\2\u008c\u008e\13\2\2\2\u008d\u008c\3\2\2\2\u008e\u0091"+
		"\3\2\2\2\u008f\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0092\3\2\2\2\u0091"+
		"\u008f\3\2\2\2\u0092\u0093\7$\2\2\u0093$\3\2\2\2\u0094\u0095\7~\2\2\u0095"+
		"\u0096\7~\2\2\u0096&\3\2\2\2\u0097\u0098\7(\2\2\u0098\u0099\7(\2\2\u0099"+
		"(\3\2\2\2\u009a\u00a5\7>\2\2\u009b\u009c\7>\2\2\u009c\u00a5\7?\2\2\u009d"+
		"\u00a5\7@\2\2\u009e\u009f\7@\2\2\u009f\u00a5\7?\2\2\u00a0\u00a1\7?\2\2"+
		"\u00a1\u00a5\7?\2\2\u00a2\u00a3\7#\2\2\u00a3\u00a5\7?\2\2\u00a4\u009a"+
		"\3\2\2\2\u00a4\u009b\3\2\2\2\u00a4\u009d\3\2\2\2\u00a4\u009e\3\2\2\2\u00a4"+
		"\u00a0\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5*\3\2\2\2\u00a6\u00a9\5-\27\2"+
		"\u00a7\u00a9\5/\30\2\u00a8\u00a6\3\2\2\2\u00a8\u00a7\3\2\2\2\u00a9,\3"+
		"\2\2\2\u00aa\u00ab\7-\2\2\u00ab.\3\2\2\2\u00ac\u00ad\7/\2\2\u00ad\60\3"+
		"\2\2\2\u00ae\u00af\t\5\2\2\u00af\62\3\2\2\2\u00b0\u00b1\7v\2\2\u00b1\u00b2"+
		"\7t\2\2\u00b2\u00b3\7w\2\2\u00b3\u00b4\7g\2\2\u00b4\64\3\2\2\2\u00b5\u00b6"+
		"\7h\2\2\u00b6\u00b7\7c\2\2\u00b7\u00b8\7n\2\2\u00b8\u00b9\7u\2\2\u00b9"+
		"\u00ba\7g\2\2\u00ba\66\3\2\2\2\u00bb\u00bc\7#\2\2\u00bc8\3\2\2\2\t\2w"+
		"\u0083\u0089\u008f\u00a4\u00a8\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}