// Generated from QL.g4 by ANTLR 4.5.2

	package antlr;

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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, WHITESPACE=21, COMMENT=22, TYPE=23, INT=24, 
		STRING=25, ID=26;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "WHITESPACE", "COMMENT", "TYPE", "INT", "STRING", 
		"ID"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "':'", "'('", "')'", "'if'", "'||'", "'&&'", 
		"'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'+'", "'-'", "'*'", "'/'", 
		"'!'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, "WHITESPACE", "COMMENT", 
		"TYPE", "INT", "STRING", "ID"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\34\u009b\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5"+
		"\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\f\3\f"+
		"\3\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3"+
		"\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3"+
		"\27\3\27\7\27r\n\27\f\27\16\27u\13\27\3\27\3\27\3\27\3\27\3\27\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u0088\n\30"+
		"\3\31\3\31\3\32\3\32\7\32\u008e\n\32\f\32\16\32\u0091\13\32\3\32\3\32"+
		"\3\33\3\33\7\33\u0097\n\33\f\33\16\33\u009a\13\33\4s\u008f\2\34\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\3\2\5\5\2\13\f\17"+
		"\17\"\"\4\2C\\c|\6\2\62;C\\aac|\u009e\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\3\67\3\2\2\2\5<\3\2\2\2\7>\3\2\2\2\t@\3\2\2\2\13B\3\2\2\2\rD"+
		"\3\2\2\2\17F\3\2\2\2\21I\3\2\2\2\23L\3\2\2\2\25O\3\2\2\2\27Q\3\2\2\2\31"+
		"T\3\2\2\2\33V\3\2\2\2\35Y\3\2\2\2\37\\\3\2\2\2!_\3\2\2\2#a\3\2\2\2%c\3"+
		"\2\2\2\'e\3\2\2\2)g\3\2\2\2+i\3\2\2\2-m\3\2\2\2/\u0087\3\2\2\2\61\u0089"+
		"\3\2\2\2\63\u008b\3\2\2\2\65\u0094\3\2\2\2\678\7h\2\289\7q\2\29:\7t\2"+
		"\2:;\7o\2\2;\4\3\2\2\2<=\7}\2\2=\6\3\2\2\2>?\7\177\2\2?\b\3\2\2\2@A\7"+
		"<\2\2A\n\3\2\2\2BC\7*\2\2C\f\3\2\2\2DE\7+\2\2E\16\3\2\2\2FG\7k\2\2GH\7"+
		"h\2\2H\20\3\2\2\2IJ\7~\2\2JK\7~\2\2K\22\3\2\2\2LM\7(\2\2MN\7(\2\2N\24"+
		"\3\2\2\2OP\7>\2\2P\26\3\2\2\2QR\7>\2\2RS\7?\2\2S\30\3\2\2\2TU\7@\2\2U"+
		"\32\3\2\2\2VW\7@\2\2WX\7?\2\2X\34\3\2\2\2YZ\7?\2\2Z[\7?\2\2[\36\3\2\2"+
		"\2\\]\7#\2\2]^\7?\2\2^ \3\2\2\2_`\7-\2\2`\"\3\2\2\2ab\7/\2\2b$\3\2\2\2"+
		"cd\7,\2\2d&\3\2\2\2ef\7\61\2\2f(\3\2\2\2gh\7#\2\2h*\3\2\2\2ij\t\2\2\2"+
		"jk\3\2\2\2kl\b\26\2\2l,\3\2\2\2mn\7\61\2\2no\7,\2\2os\3\2\2\2pr\13\2\2"+
		"\2qp\3\2\2\2ru\3\2\2\2st\3\2\2\2sq\3\2\2\2tv\3\2\2\2us\3\2\2\2vw\7\61"+
		"\2\2wx\7,\2\2xy\3\2\2\2yz\b\27\2\2z.\3\2\2\2{|\7d\2\2|}\7q\2\2}~\7q\2"+
		"\2~\177\7n\2\2\177\u0080\7g\2\2\u0080\u0081\7c\2\2\u0081\u0088\7p\2\2"+
		"\u0082\u0083\7o\2\2\u0083\u0084\7q\2\2\u0084\u0085\7p\2\2\u0085\u0086"+
		"\7g\2\2\u0086\u0088\7{\2\2\u0087{\3\2\2\2\u0087\u0082\3\2\2\2\u0088\60"+
		"\3\2\2\2\u0089\u008a\4\62;\2\u008a\62\3\2\2\2\u008b\u008f\7$\2\2\u008c"+
		"\u008e\13\2\2\2\u008d\u008c\3\2\2\2\u008e\u0091\3\2\2\2\u008f\u0090\3"+
		"\2\2\2\u008f\u008d\3\2\2\2\u0090\u0092\3\2\2\2\u0091\u008f\3\2\2\2\u0092"+
		"\u0093\7$\2\2\u0093\64\3\2\2\2\u0094\u0098\t\3\2\2\u0095\u0097\t\4\2\2"+
		"\u0096\u0095\3\2\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099"+
		"\3\2\2\2\u0099\66\3\2\2\2\u009a\u0098\3\2\2\2\7\2s\u0087\u008f\u0098\3"+
		"\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}