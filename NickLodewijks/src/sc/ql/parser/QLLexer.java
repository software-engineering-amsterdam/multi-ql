// Generated from QL.g4 by ANTLR 4.5.3
package sc.ql.parser;
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
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, WS=20, COMMENT=21, LINE_COMMENT=22, BOOLEAN=23, INTEGER=24, 
		STRING=25, BOOL=26, INT=27, STR=28, ID=29;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "WS", "COMMENT", "LINE_COMMENT", "BOOLEAN", "INTEGER", 
		"STRING", "BOOL", "INT", "STR", "ID"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'if'", "'('", "')'", "'+'", "'-'", "'<'", 
		"'<='", "'>'", "'>='", "'=='", "'!='", "'*'", "'/'", "'&&'", "'||'", "'!'", 
		null, null, null, "'bool'", "'int'", "'str'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, "WS", "COMMENT", "LINE_COMMENT", 
		"BOOLEAN", "INTEGER", "STRING", "BOOL", "INT", "STR", "ID"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\37\u00b7\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\25\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\26\7\26v\n\26\f\26\16\26y\13\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\27\3\27\7\27\u0084\n\27\f\27\16\27\u0087\13"+
		"\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3"+
		"\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u00a1\n\33"+
		"\3\34\6\34\u00a4\n\34\r\34\16\34\u00a5\3\35\3\35\7\35\u00aa\n\35\f\35"+
		"\16\35\u00ad\13\35\3\35\3\35\3\36\3\36\7\36\u00b3\n\36\f\36\16\36\u00b6"+
		"\13\36\4w\u00ab\2\37\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37\3\2\6\5\2\13\f\17\17\"\"\4\2\f\f\17\17\4\2C\\c|\6"+
		"\2\62;C\\aac|\u00bc\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2"+
		"\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2"+
		"\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2"+
		"\2\2\29\3\2\2\2\2;\3\2\2\2\3=\3\2\2\2\5B\3\2\2\2\7D\3\2\2\2\tF\3\2\2\2"+
		"\13I\3\2\2\2\rK\3\2\2\2\17M\3\2\2\2\21O\3\2\2\2\23Q\3\2\2\2\25S\3\2\2"+
		"\2\27V\3\2\2\2\31X\3\2\2\2\33[\3\2\2\2\35^\3\2\2\2\37a\3\2\2\2!c\3\2\2"+
		"\2#e\3\2\2\2%h\3\2\2\2\'k\3\2\2\2)m\3\2\2\2+q\3\2\2\2-\177\3\2\2\2/\u008a"+
		"\3\2\2\2\61\u008f\3\2\2\2\63\u0093\3\2\2\2\65\u00a0\3\2\2\2\67\u00a3\3"+
		"\2\2\29\u00a7\3\2\2\2;\u00b0\3\2\2\2=>\7h\2\2>?\7q\2\2?@\7t\2\2@A\7o\2"+
		"\2A\4\3\2\2\2BC\7}\2\2C\6\3\2\2\2DE\7\177\2\2E\b\3\2\2\2FG\7k\2\2GH\7"+
		"h\2\2H\n\3\2\2\2IJ\7*\2\2J\f\3\2\2\2KL\7+\2\2L\16\3\2\2\2MN\7-\2\2N\20"+
		"\3\2\2\2OP\7/\2\2P\22\3\2\2\2QR\7>\2\2R\24\3\2\2\2ST\7>\2\2TU\7?\2\2U"+
		"\26\3\2\2\2VW\7@\2\2W\30\3\2\2\2XY\7@\2\2YZ\7?\2\2Z\32\3\2\2\2[\\\7?\2"+
		"\2\\]\7?\2\2]\34\3\2\2\2^_\7#\2\2_`\7?\2\2`\36\3\2\2\2ab\7,\2\2b \3\2"+
		"\2\2cd\7\61\2\2d\"\3\2\2\2ef\7(\2\2fg\7(\2\2g$\3\2\2\2hi\7~\2\2ij\7~\2"+
		"\2j&\3\2\2\2kl\7#\2\2l(\3\2\2\2mn\t\2\2\2no\3\2\2\2op\b\25\2\2p*\3\2\2"+
		"\2qr\7\61\2\2rs\7,\2\2sw\3\2\2\2tv\13\2\2\2ut\3\2\2\2vy\3\2\2\2wx\3\2"+
		"\2\2wu\3\2\2\2xz\3\2\2\2yw\3\2\2\2z{\7,\2\2{|\7\61\2\2|}\3\2\2\2}~\b\26"+
		"\2\2~,\3\2\2\2\177\u0080\7\61\2\2\u0080\u0081\7\61\2\2\u0081\u0085\3\2"+
		"\2\2\u0082\u0084\n\3\2\2\u0083\u0082\3\2\2\2\u0084\u0087\3\2\2\2\u0085"+
		"\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087\u0085\3\2"+
		"\2\2\u0088\u0089\b\27\2\2\u0089.\3\2\2\2\u008a\u008b\7d\2\2\u008b\u008c"+
		"\7q\2\2\u008c\u008d\7q\2\2\u008d\u008e\7n\2\2\u008e\60\3\2\2\2\u008f\u0090"+
		"\7k\2\2\u0090\u0091\7p\2\2\u0091\u0092\7v\2\2\u0092\62\3\2\2\2\u0093\u0094"+
		"\7u\2\2\u0094\u0095\7v\2\2\u0095\u0096\7t\2\2\u0096\64\3\2\2\2\u0097\u0098"+
		"\7v\2\2\u0098\u0099\7t\2\2\u0099\u009a\7w\2\2\u009a\u00a1\7g\2\2\u009b"+
		"\u009c\7h\2\2\u009c\u009d\7c\2\2\u009d\u009e\7n\2\2\u009e\u009f\7u\2\2"+
		"\u009f\u00a1\7g\2\2\u00a0\u0097\3\2\2\2\u00a0\u009b\3\2\2\2\u00a1\66\3"+
		"\2\2\2\u00a2\u00a4\4\62;\2\u00a3\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5"+
		"\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a68\3\2\2\2\u00a7\u00ab\7$\2\2\u00a8"+
		"\u00aa\13\2\2\2\u00a9\u00a8\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00ac\3"+
		"\2\2\2\u00ab\u00a9\3\2\2\2\u00ac\u00ae\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae"+
		"\u00af\7$\2\2\u00af:\3\2\2\2\u00b0\u00b4\t\4\2\2\u00b1\u00b3\t\5\2\2\u00b2"+
		"\u00b1\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2"+
		"\2\2\u00b5<\3\2\2\2\u00b6\u00b4\3\2\2\2\t\2w\u0085\u00a0\u00a5\u00ab\u00b4"+
		"\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}