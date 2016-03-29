// Generated from /home/roy/Workspace/SC/multi-ql/RoydeWildt/QLlib/src/main/java/org/uva/sea/ql/parser/QL.g4 by ANTLR 4.5.1
package org.uva.sea.ql.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, WHITESPACE=23, COMMENT=24, 
		TRUE=25, FALSE=26, BOOLEAN=27, MONEY=28, NUMBER=29, TEXT=30, IDENT=31, 
		STR=32, INT=33, FLOAT=34;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "WHITESPACE", "COMMENT", 
		"TRUE", "FALSE", "BOOLEAN", "MONEY", "NUMBER", "TEXT", "IDENT", "STR", 
		"INT", "FLOAT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'if'", "'('", "')'", "'else'", "':'", "'='", 
		"'+'", "'-'", "'*'", "'/'", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", 
		"'&&'", "'||'", "'!'", null, null, "'true'", "'false'", "'boolean'", "'money'", 
		"'number'", "'text'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "WHITESPACE", 
		"COMMENT", "TRUE", "FALSE", "BOOLEAN", "MONEY", "NUMBER", "TEXT", "IDENT", 
		"STR", "INT", "FLOAT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2$\u00e8\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6"+
		"\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r"+
		"\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\30"+
		"\6\30\u0082\n\30\r\30\16\30\u0083\3\30\3\30\3\31\3\31\3\31\3\31\7\31\u008c"+
		"\n\31\f\31\16\31\u008f\13\31\3\31\5\31\u0092\n\31\3\31\3\31\3\31\3\31"+
		"\3\31\7\31\u0099\n\31\f\31\16\31\u009c\13\31\3\31\3\31\5\31\u00a0\n\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 \7 \u00cb"+
		"\n \f \16 \u00ce\13 \3!\3!\7!\u00d2\n!\f!\16!\u00d5\13!\3!\3!\3\"\6\""+
		"\u00da\n\"\r\"\16\"\u00db\3#\6#\u00df\n#\r#\16#\u00e0\3#\3#\6#\u00e5\n"+
		"#\r#\16#\u00e6\4\u009a\u00d3\2$\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$\3\2\6\5\2\13\f\16\17\"\"\4"+
		"\2\f\f\17\17\4\2C\\c|\6\2\62;C\\aac|\u00f1\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2"+
		"\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\3G\3\2\2\2\5L\3\2\2\2\7N\3\2\2\2\tP"+
		"\3\2\2\2\13S\3\2\2\2\rU\3\2\2\2\17W\3\2\2\2\21\\\3\2\2\2\23^\3\2\2\2\25"+
		"`\3\2\2\2\27b\3\2\2\2\31d\3\2\2\2\33f\3\2\2\2\35h\3\2\2\2\37j\3\2\2\2"+
		"!m\3\2\2\2#o\3\2\2\2%r\3\2\2\2\'u\3\2\2\2)x\3\2\2\2+{\3\2\2\2-~\3\2\2"+
		"\2/\u0081\3\2\2\2\61\u009f\3\2\2\2\63\u00a3\3\2\2\2\65\u00a8\3\2\2\2\67"+
		"\u00ae\3\2\2\29\u00b6\3\2\2\2;\u00bc\3\2\2\2=\u00c3\3\2\2\2?\u00c8\3\2"+
		"\2\2A\u00cf\3\2\2\2C\u00d9\3\2\2\2E\u00de\3\2\2\2GH\7h\2\2HI\7q\2\2IJ"+
		"\7t\2\2JK\7o\2\2K\4\3\2\2\2LM\7}\2\2M\6\3\2\2\2NO\7\177\2\2O\b\3\2\2\2"+
		"PQ\7k\2\2QR\7h\2\2R\n\3\2\2\2ST\7*\2\2T\f\3\2\2\2UV\7+\2\2V\16\3\2\2\2"+
		"WX\7g\2\2XY\7n\2\2YZ\7u\2\2Z[\7g\2\2[\20\3\2\2\2\\]\7<\2\2]\22\3\2\2\2"+
		"^_\7?\2\2_\24\3\2\2\2`a\7-\2\2a\26\3\2\2\2bc\7/\2\2c\30\3\2\2\2de\7,\2"+
		"\2e\32\3\2\2\2fg\7\61\2\2g\34\3\2\2\2hi\7>\2\2i\36\3\2\2\2jk\7>\2\2kl"+
		"\7?\2\2l \3\2\2\2mn\7@\2\2n\"\3\2\2\2op\7@\2\2pq\7?\2\2q$\3\2\2\2rs\7"+
		"?\2\2st\7?\2\2t&\3\2\2\2uv\7#\2\2vw\7?\2\2w(\3\2\2\2xy\7(\2\2yz\7(\2\2"+
		"z*\3\2\2\2{|\7~\2\2|}\7~\2\2},\3\2\2\2~\177\7#\2\2\177.\3\2\2\2\u0080"+
		"\u0082\t\2\2\2\u0081\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0081\3\2"+
		"\2\2\u0083\u0084\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\b\30\2\2\u0086"+
		"\60\3\2\2\2\u0087\u0088\7\61\2\2\u0088\u0089\7\61\2\2\u0089\u008d\3\2"+
		"\2\2\u008a\u008c\n\3\2\2\u008b\u008a\3\2\2\2\u008c\u008f\3\2\2\2\u008d"+
		"\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u0091\3\2\2\2\u008f\u008d\3\2"+
		"\2\2\u0090\u0092\7\17\2\2\u0091\u0090\3\2\2\2\u0091\u0092\3\2\2\2\u0092"+
		"\u0093\3\2\2\2\u0093\u00a0\7\f\2\2\u0094\u0095\7\61\2\2\u0095\u0096\7"+
		",\2\2\u0096\u009a\3\2\2\2\u0097\u0099\13\2\2\2\u0098\u0097\3\2\2\2\u0099"+
		"\u009c\3\2\2\2\u009a\u009b\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u009d\3\2"+
		"\2\2\u009c\u009a\3\2\2\2\u009d\u009e\7,\2\2\u009e\u00a0\7\61\2\2\u009f"+
		"\u0087\3\2\2\2\u009f\u0094\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\b\31"+
		"\2\2\u00a2\62\3\2\2\2\u00a3\u00a4\7v\2\2\u00a4\u00a5\7t\2\2\u00a5\u00a6"+
		"\7w\2\2\u00a6\u00a7\7g\2\2\u00a7\64\3\2\2\2\u00a8\u00a9\7h\2\2\u00a9\u00aa"+
		"\7c\2\2\u00aa\u00ab\7n\2\2\u00ab\u00ac\7u\2\2\u00ac\u00ad\7g\2\2\u00ad"+
		"\66\3\2\2\2\u00ae\u00af\7d\2\2\u00af\u00b0\7q\2\2\u00b0\u00b1\7q\2\2\u00b1"+
		"\u00b2\7n\2\2\u00b2\u00b3\7g\2\2\u00b3\u00b4\7c\2\2\u00b4\u00b5\7p\2\2"+
		"\u00b58\3\2\2\2\u00b6\u00b7\7o\2\2\u00b7\u00b8\7q\2\2\u00b8\u00b9\7p\2"+
		"\2\u00b9\u00ba\7g\2\2\u00ba\u00bb\7{\2\2\u00bb:\3\2\2\2\u00bc\u00bd\7"+
		"p\2\2\u00bd\u00be\7w\2\2\u00be\u00bf\7o\2\2\u00bf\u00c0\7d\2\2\u00c0\u00c1"+
		"\7g\2\2\u00c1\u00c2\7t\2\2\u00c2<\3\2\2\2\u00c3\u00c4\7v\2\2\u00c4\u00c5"+
		"\7g\2\2\u00c5\u00c6\7z\2\2\u00c6\u00c7\7v\2\2\u00c7>\3\2\2\2\u00c8\u00cc"+
		"\t\4\2\2\u00c9\u00cb\t\5\2\2\u00ca\u00c9\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc"+
		"\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd@\3\2\2\2\u00ce\u00cc\3\2\2\2"+
		"\u00cf\u00d3\7$\2\2\u00d0\u00d2\n\3\2\2\u00d1\u00d0\3\2\2\2\u00d2\u00d5"+
		"\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d4\u00d6\3\2\2\2\u00d5"+
		"\u00d3\3\2\2\2\u00d6\u00d7\7$\2\2\u00d7B\3\2\2\2\u00d8\u00da\4\62;\2\u00d9"+
		"\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2"+
		"\2\2\u00dcD\3\2\2\2\u00dd\u00df\4\62;\2\u00de\u00dd\3\2\2\2\u00df\u00e0"+
		"\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2"+
		"\u00e4\7\60\2\2\u00e3\u00e5\4\62;\2\u00e4\u00e3\3\2\2\2\u00e5\u00e6\3"+
		"\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7F\3\2\2\2\r\2\u0083"+
		"\u008d\u0091\u009a\u009f\u00cc\u00d3\u00db\u00e0\u00e6\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}