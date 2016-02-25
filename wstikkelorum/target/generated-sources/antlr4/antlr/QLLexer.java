// Generated from QL.g4 by ANTLR 4.4
package antlr;

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
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__19=1, T__18=2, T__17=3, T__16=4, T__15=5, T__14=6, T__13=7, T__12=8, 
		T__11=9, T__10=10, T__9=11, T__8=12, T__7=13, T__6=14, T__5=15, T__4=16, 
		T__3=17, T__2=18, T__1=19, T__0=20, WHITESPACE=21, COMMENT=22, BOOLEAN=23, 
		INTEGER=24, STRING=25, BOOL=26, INT=27, STR=28, ID=29;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'"
	};
	public static final String[] ruleNames = {
		"T__19", "T__18", "T__17", "T__16", "T__15", "T__14", "T__13", "T__12", 
		"T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", 
		"T__2", "T__1", "T__0", "WHITESPACE", "COMMENT", "BOOLEAN", "INTEGER", 
		"STRING", "BOOL", "INT", "STR", "ID"
	};


	public QLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QL.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\37\u00b4\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\3\3"+
		"\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3"+
		"\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3"+
		"\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\7\27x\n\27\f\27\16\27{\13\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3"+
		"\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\5\33\u009e\n\33\3\34\6\34\u00a1\n\34\r\34\16\34"+
		"\u00a2\3\35\3\35\7\35\u00a7\n\35\f\35\16\35\u00aa\13\35\3\35\3\35\3\36"+
		"\3\36\7\36\u00b0\n\36\f\36\16\36\u00b3\13\36\4y\u00a8\2\37\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37\3\2\5\5\2\13"+
		"\f\17\17\"\"\4\2C\\c|\6\2\62;C\\aac|\u00b8\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\3=\3\2\2\2\5?\3\2\2\2"+
		"\7B\3\2\2\2\tD\3\2\2\2\13G\3\2\2\2\rJ\3\2\2\2\17L\3\2\2\2\21O\3\2\2\2"+
		"\23Q\3\2\2\2\25T\3\2\2\2\27V\3\2\2\2\31X\3\2\2\2\33[\3\2\2\2\35]\3\2\2"+
		"\2\37`\3\2\2\2!b\3\2\2\2#d\3\2\2\2%f\3\2\2\2\'h\3\2\2\2)m\3\2\2\2+o\3"+
		"\2\2\2-s\3\2\2\2/\u0081\3\2\2\2\61\u0089\3\2\2\2\63\u008d\3\2\2\2\65\u009d"+
		"\3\2\2\2\67\u00a0\3\2\2\29\u00a4\3\2\2\2;\u00ad\3\2\2\2=>\7\61\2\2>\4"+
		"\3\2\2\2?@\7#\2\2@A\7?\2\2A\6\3\2\2\2BC\7<\2\2C\b\3\2\2\2DE\7@\2\2EF\7"+
		"?\2\2F\n\3\2\2\2GH\7~\2\2HI\7~\2\2I\f\3\2\2\2JK\7}\2\2K\16\3\2\2\2LM\7"+
		"?\2\2MN\7?\2\2N\20\3\2\2\2OP\7>\2\2P\22\3\2\2\2QR\7(\2\2RS\7(\2\2S\24"+
		"\3\2\2\2TU\7\177\2\2U\26\3\2\2\2VW\7@\2\2W\30\3\2\2\2XY\7k\2\2YZ\7h\2"+
		"\2Z\32\3\2\2\2[\\\7#\2\2\\\34\3\2\2\2]^\7>\2\2^_\7?\2\2_\36\3\2\2\2`a"+
		"\7*\2\2a \3\2\2\2bc\7+\2\2c\"\3\2\2\2de\7,\2\2e$\3\2\2\2fg\7-\2\2g&\3"+
		"\2\2\2hi\7h\2\2ij\7q\2\2jk\7t\2\2kl\7o\2\2l(\3\2\2\2mn\7/\2\2n*\3\2\2"+
		"\2op\t\2\2\2pq\3\2\2\2qr\b\26\2\2r,\3\2\2\2st\7\61\2\2tu\7,\2\2uy\3\2"+
		"\2\2vx\13\2\2\2wv\3\2\2\2x{\3\2\2\2yz\3\2\2\2yw\3\2\2\2z|\3\2\2\2{y\3"+
		"\2\2\2|}\7\61\2\2}~\7,\2\2~\177\3\2\2\2\177\u0080\b\27\2\2\u0080.\3\2"+
		"\2\2\u0081\u0082\7d\2\2\u0082\u0083\7q\2\2\u0083\u0084\7q\2\2\u0084\u0085"+
		"\7n\2\2\u0085\u0086\7g\2\2\u0086\u0087\7c\2\2\u0087\u0088\7p\2\2\u0088"+
		"\60\3\2\2\2\u0089\u008a\7k\2\2\u008a\u008b\7p\2\2\u008b\u008c\7v\2\2\u008c"+
		"\62\3\2\2\2\u008d\u008e\7u\2\2\u008e\u008f\7v\2\2\u008f\u0090\7t\2\2\u0090"+
		"\u0091\7k\2\2\u0091\u0092\7p\2\2\u0092\u0093\7i\2\2\u0093\64\3\2\2\2\u0094"+
		"\u0095\7v\2\2\u0095\u0096\7t\2\2\u0096\u0097\7w\2\2\u0097\u009e\7g\2\2"+
		"\u0098\u0099\7h\2\2\u0099\u009a\7c\2\2\u009a\u009b\7n\2\2\u009b\u009c"+
		"\7u\2\2\u009c\u009e\7g\2\2\u009d\u0094\3\2\2\2\u009d\u0098\3\2\2\2\u009e"+
		"\66\3\2\2\2\u009f\u00a1\4\62;\2\u00a0\u009f\3\2\2\2\u00a1\u00a2\3\2\2"+
		"\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a38\3\2\2\2\u00a4\u00a8"+
		"\7$\2\2\u00a5\u00a7\13\2\2\2\u00a6\u00a5\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8"+
		"\u00a9\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00ab\3\2\2\2\u00aa\u00a8\3\2"+
		"\2\2\u00ab\u00ac\7$\2\2\u00ac:\3\2\2\2\u00ad\u00b1\t\3\2\2\u00ae\u00b0"+
		"\t\4\2\2\u00af\u00ae\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1"+
		"\u00b2\3\2\2\2\u00b2<\3\2\2\2\u00b3\u00b1\3\2\2\2\b\2y\u009d\u00a2\u00a8"+
		"\u00b1\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}