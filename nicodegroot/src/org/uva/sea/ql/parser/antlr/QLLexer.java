// Generated from QL.g4 by ANTLR 4.5.2

	package org.uva.sea.ql.parser.antlr;

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
		FORM=1, IF=2, ELSE=3, QUESTION_TYPE=4, COLON=5, LEFT_CURLY_BRACKET=6, 
		RIGHT_CURLY_BRACKET=7, LEFT_PARENTHESES=8, RIGHT_PARENTHESES=9, NOT=10, 
		IS=11, MATHEMATICAL_OPERATOR_HIGH=12, MATHEMATICAL_OPERATOR_LOW=13, BOOLEAN_OPERATOR=14, 
		RELATION_OPERATOR=15, WHITESPACE=16, COMMENT=17, LINE_COMMENT=18, BOOLEAN=19, 
		IDENTIFIER=20, INTEGER=21, STRING=22;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"FORM", "IF", "ELSE", "QUESTION_TYPE", "COLON", "LEFT_CURLY_BRACKET", 
		"RIGHT_CURLY_BRACKET", "LEFT_PARENTHESES", "RIGHT_PARENTHESES", "NOT", 
		"IS", "MATHEMATICAL_OPERATOR_HIGH", "MATHEMATICAL_OPERATOR_LOW", "BOOLEAN_OPERATOR", 
		"RELATION_OPERATOR", "WHITESPACE", "COMMENT", "LINE_COMMENT", "BOOLEAN", 
		"IDENTIFIER", "INTEGER", "STRING"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'if'", "'else'", null, "':'", "'{'", "'}'", "'('", "')'", 
		"'!'", "'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "FORM", "IF", "ELSE", "QUESTION_TYPE", "COLON", "LEFT_CURLY_BRACKET", 
		"RIGHT_CURLY_BRACKET", "LEFT_PARENTHESES", "RIGHT_PARENTHESES", "NOT", 
		"IS", "MATHEMATICAL_OPERATOR_HIGH", "MATHEMATICAL_OPERATOR_LOW", "BOOLEAN_OPERATOR", 
		"RELATION_OPERATOR", "WHITESPACE", "COMMENT", "LINE_COMMENT", "BOOLEAN", 
		"IDENTIFIER", "INTEGER", "STRING"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\30\u00c6\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5"+
		"\5V\n\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r"+
		"\3\r\3\16\3\16\3\17\3\17\3\17\3\17\5\17n\n\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\5\20z\n\20\3\21\6\21}\n\21\r\21\16\21~\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\7\22\u0087\n\22\f\22\16\22\u008a\13\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\7\23\u0095\n\23\f\23\16\23\u0098"+
		"\13\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u00b1\n\24\3\25"+
		"\3\25\6\25\u00b5\n\25\r\25\16\25\u00b6\3\26\6\26\u00ba\n\26\r\26\16\26"+
		"\u00bb\3\27\3\27\7\27\u00c0\n\27\f\27\16\27\u00c3\13\27\3\27\3\27\5\u0088"+
		"\u0096\u00c1\2\30\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30\3\2\b\4\2,,\61\61\4"+
		"\2--//\5\2\13\f\17\17\"\"\3\2c|\5\2\62;C\\c|\3\2\62;\u00d7\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\3/\3\2\2\2\5\64\3\2\2"+
		"\2\7\67\3\2\2\2\tU\3\2\2\2\13W\3\2\2\2\rY\3\2\2\2\17[\3\2\2\2\21]\3\2"+
		"\2\2\23_\3\2\2\2\25a\3\2\2\2\27c\3\2\2\2\31e\3\2\2\2\33g\3\2\2\2\35m\3"+
		"\2\2\2\37y\3\2\2\2!|\3\2\2\2#\u0082\3\2\2\2%\u0090\3\2\2\2\'\u00b0\3\2"+
		"\2\2)\u00b2\3\2\2\2+\u00b9\3\2\2\2-\u00bd\3\2\2\2/\60\7h\2\2\60\61\7q"+
		"\2\2\61\62\7t\2\2\62\63\7o\2\2\63\4\3\2\2\2\64\65\7k\2\2\65\66\7h\2\2"+
		"\66\6\3\2\2\2\678\7g\2\289\7n\2\29:\7u\2\2:;\7g\2\2;\b\3\2\2\2<=\7k\2"+
		"\2=>\7p\2\2>?\7v\2\2?@\7g\2\2@A\7i\2\2AB\7g\2\2BV\7t\2\2CD\7u\2\2DE\7"+
		"v\2\2EF\7t\2\2FG\7k\2\2GH\7p\2\2HV\7i\2\2IJ\7d\2\2JK\7q\2\2KL\7q\2\2L"+
		"M\7n\2\2MN\7g\2\2NO\7c\2\2OV\7p\2\2PQ\7o\2\2QR\7q\2\2RS\7p\2\2ST\7g\2"+
		"\2TV\7{\2\2U<\3\2\2\2UC\3\2\2\2UI\3\2\2\2UP\3\2\2\2V\n\3\2\2\2WX\7<\2"+
		"\2X\f\3\2\2\2YZ\7}\2\2Z\16\3\2\2\2[\\\7\177\2\2\\\20\3\2\2\2]^\7*\2\2"+
		"^\22\3\2\2\2_`\7+\2\2`\24\3\2\2\2ab\7#\2\2b\26\3\2\2\2cd\7?\2\2d\30\3"+
		"\2\2\2ef\t\2\2\2f\32\3\2\2\2gh\t\3\2\2h\34\3\2\2\2ij\7(\2\2jn\7(\2\2k"+
		"l\7~\2\2ln\7~\2\2mi\3\2\2\2mk\3\2\2\2n\36\3\2\2\2op\7?\2\2pz\7?\2\2qr"+
		"\7#\2\2rz\7?\2\2sz\7@\2\2tu\7@\2\2uz\7?\2\2vz\7>\2\2wx\7>\2\2xz\7?\2\2"+
		"yo\3\2\2\2yq\3\2\2\2ys\3\2\2\2yt\3\2\2\2yv\3\2\2\2yw\3\2\2\2z \3\2\2\2"+
		"{}\t\4\2\2|{\3\2\2\2}~\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0080\3\2\2"+
		"\2\u0080\u0081\b\21\2\2\u0081\"\3\2\2\2\u0082\u0083\7\61\2\2\u0083\u0084"+
		"\7,\2\2\u0084\u0088\3\2\2\2\u0085\u0087\13\2\2\2\u0086\u0085\3\2\2\2\u0087"+
		"\u008a\3\2\2\2\u0088\u0089\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u008b\3\2"+
		"\2\2\u008a\u0088\3\2\2\2\u008b\u008c\7,\2\2\u008c\u008d\7\61\2\2\u008d"+
		"\u008e\3\2\2\2\u008e\u008f\b\22\2\2\u008f$\3\2\2\2\u0090\u0091\7\61\2"+
		"\2\u0091\u0092\7\61\2\2\u0092\u0096\3\2\2\2\u0093\u0095\13\2\2\2\u0094"+
		"\u0093\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0097\3\2\2\2\u0096\u0094\3\2"+
		"\2\2\u0097\u0099\3\2\2\2\u0098\u0096\3\2\2\2\u0099\u009a\7\61\2\2\u009a"+
		"\u009b\7p\2\2\u009b\u009c\3\2\2\2\u009c\u009d\b\23\2\2\u009d&\3\2\2\2"+
		"\u009e\u009f\7v\2\2\u009f\u00a0\7t\2\2\u00a0\u00a1\7w\2\2\u00a1\u00b1"+
		"\7g\2\2\u00a2\u00a3\7V\2\2\u00a3\u00a4\7T\2\2\u00a4\u00a5\7W\2\2\u00a5"+
		"\u00b1\7G\2\2\u00a6\u00a7\7h\2\2\u00a7\u00a8\7c\2\2\u00a8\u00a9\7n\2\2"+
		"\u00a9\u00aa\7u\2\2\u00aa\u00b1\7g\2\2\u00ab\u00ac\7H\2\2\u00ac\u00ad"+
		"\7C\2\2\u00ad\u00ae\7N\2\2\u00ae\u00af\7U\2\2\u00af\u00b1\7G\2\2\u00b0"+
		"\u009e\3\2\2\2\u00b0\u00a2\3\2\2\2\u00b0\u00a6\3\2\2\2\u00b0\u00ab\3\2"+
		"\2\2\u00b1(\3\2\2\2\u00b2\u00b4\t\5\2\2\u00b3\u00b5\t\6\2\2\u00b4\u00b3"+
		"\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7"+
		"*\3\2\2\2\u00b8\u00ba\t\7\2\2\u00b9\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2"+
		"\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc,\3\2\2\2\u00bd\u00c1\7"+
		"$\2\2\u00be\u00c0\13\2\2\2\u00bf\u00be\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1"+
		"\u00c2\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00c1\3\2"+
		"\2\2\u00c4\u00c5\7$\2\2\u00c5.\3\2\2\2\r\2Umy~\u0088\u0096\u00b0\u00b6"+
		"\u00bb\u00c1\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}