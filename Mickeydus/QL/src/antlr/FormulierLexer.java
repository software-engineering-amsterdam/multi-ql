// Generated from /Users/Dominique/NetBeansProjects/multi-ql/Mickeydus/QL/src/antlr/Formulier.g4 by ANTLR 4.5.2

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
public class FormulierLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, WS=5, DIGIT=6, SMALLERTHAN=7, BIGGERTHAN=8, 
		SMALLER_EQUAL=9, BIGGER_EQUAL=10, EQUAL=11, NOT_EQUAL=12, AND=13, OR=14, 
		NOT=15, ASSIGN=16, MINUS=17, ADD=18, MULTIPLY=19, DIVIDE=20, BOOLEAN=21, 
		STRING=22, INT=23, FLOAT=24, MONEY=25, DATE=26, ID=27, COMMA=28, COMMENT=29, 
		NEW_LINE=30;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "WS", "DIGIT", "SMALLERTHAN", "BIGGERTHAN", 
		"SMALLER_EQUAL", "BIGGER_EQUAL", "EQUAL", "NOT_EQUAL", "AND", "OR", "NOT", 
		"ASSIGN", "MINUS", "ADD", "MULTIPLY", "DIVIDE", "BOOLEAN", "STRING", "INT", 
		"FLOAT", "MONEY", "DATE", "ID", "COMMA", "COMMENT", "NEW_LINE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'true'", "'false'", null, null, "'<'", "'>'", "'<='", 
		"'>='", "'=='", "'!='", "'&&'", "'||'", "'!'", "'='", "'-'", "'+'", "'*'", 
		"'/'", null, null, null, null, "'money'", "'date'", null, "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "WS", "DIGIT", "SMALLERTHAN", "BIGGERTHAN", 
		"SMALLER_EQUAL", "BIGGER_EQUAL", "EQUAL", "NOT_EQUAL", "AND", "OR", "NOT", 
		"ASSIGN", "MINUS", "ADD", "MULTIPLY", "DIVIDE", "BOOLEAN", "STRING", "INT", 
		"FLOAT", "MONEY", "DATE", "ID", "COMMA", "COMMENT", "NEW_LINE"
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


	public FormulierLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Formulier.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2 \u00e1\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3"+
		"\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3"+
		"\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5"+
		"\26\u0080\n\26\3\27\3\27\7\27\u0084\n\27\f\27\16\27\u0087\13\27\3\27\3"+
		"\27\3\30\6\30\u008c\n\30\r\30\16\30\u008d\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\5\30\u0097\n\30\3\31\6\31\u009a\n\31\r\31\16\31\u009b\3\31\3\31"+
		"\7\31\u00a0\n\31\f\31\16\31\u00a3\13\31\3\31\7\31\u00a6\n\31\f\31\16\31"+
		"\u00a9\13\31\3\31\3\31\6\31\u00ad\n\31\r\31\16\31\u00ae\5\31\u00b1\n\31"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\7\34"+
		"\u00c0\n\34\f\34\16\34\u00c3\13\34\3\35\3\35\3\36\3\36\3\36\3\36\7\36"+
		"\u00cb\n\36\f\36\16\36\u00ce\13\36\3\36\3\36\3\36\3\36\3\36\3\36\7\36"+
		"\u00d6\n\36\f\36\16\36\u00d9\13\36\5\36\u00db\n\36\3\37\5\37\u00de\n\37"+
		"\3\37\3\37\3\u0085\2 \3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= \3\2\b\5\2\13\f\17\17\"\"\3\2\62;\4\2..\60\60\3"+
		"\2c|\5\2\62;C\\c|\4\2\f\f\17\17\u00ee\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\3?\3\2\2\2\5A\3"+
		"\2\2\2\7C\3\2\2\2\tH\3\2\2\2\13N\3\2\2\2\rR\3\2\2\2\17T\3\2\2\2\21V\3"+
		"\2\2\2\23X\3\2\2\2\25[\3\2\2\2\27^\3\2\2\2\31a\3\2\2\2\33d\3\2\2\2\35"+
		"g\3\2\2\2\37j\3\2\2\2!l\3\2\2\2#n\3\2\2\2%p\3\2\2\2\'r\3\2\2\2)t\3\2\2"+
		"\2+\177\3\2\2\2-\u0081\3\2\2\2/\u0096\3\2\2\2\61\u00b0\3\2\2\2\63\u00b2"+
		"\3\2\2\2\65\u00b8\3\2\2\2\67\u00bd\3\2\2\29\u00c4\3\2\2\2;\u00da\3\2\2"+
		"\2=\u00dd\3\2\2\2?@\7*\2\2@\4\3\2\2\2AB\7+\2\2B\6\3\2\2\2CD\7v\2\2DE\7"+
		"t\2\2EF\7w\2\2FG\7g\2\2G\b\3\2\2\2HI\7h\2\2IJ\7c\2\2JK\7n\2\2KL\7u\2\2"+
		"LM\7g\2\2M\n\3\2\2\2NO\t\2\2\2OP\3\2\2\2PQ\b\6\2\2Q\f\3\2\2\2RS\t\3\2"+
		"\2S\16\3\2\2\2TU\7>\2\2U\20\3\2\2\2VW\7@\2\2W\22\3\2\2\2XY\7>\2\2YZ\7"+
		"?\2\2Z\24\3\2\2\2[\\\7@\2\2\\]\7?\2\2]\26\3\2\2\2^_\7?\2\2_`\7?\2\2`\30"+
		"\3\2\2\2ab\7#\2\2bc\7?\2\2c\32\3\2\2\2de\7(\2\2ef\7(\2\2f\34\3\2\2\2g"+
		"h\7~\2\2hi\7~\2\2i\36\3\2\2\2jk\7#\2\2k \3\2\2\2lm\7?\2\2m\"\3\2\2\2n"+
		"o\7/\2\2o$\3\2\2\2pq\7-\2\2q&\3\2\2\2rs\7,\2\2s(\3\2\2\2tu\7\61\2\2u*"+
		"\3\2\2\2vw\7v\2\2wx\7t\2\2xy\7w\2\2y\u0080\7g\2\2z{\7h\2\2{|\7c\2\2|}"+
		"\7n\2\2}~\7u\2\2~\u0080\7g\2\2\177v\3\2\2\2\177z\3\2\2\2\u0080,\3\2\2"+
		"\2\u0081\u0085\7$\2\2\u0082\u0084\13\2\2\2\u0083\u0082\3\2\2\2\u0084\u0087"+
		"\3\2\2\2\u0085\u0086\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0088\3\2\2\2\u0087"+
		"\u0085\3\2\2\2\u0088\u0089\7$\2\2\u0089.\3\2\2\2\u008a\u008c\5\r\7\2\u008b"+
		"\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2"+
		"\2\2\u008e\u0097\3\2\2\2\u008f\u0090\7k\2\2\u0090\u0091\7p\2\2\u0091\u0092"+
		"\7v\2\2\u0092\u0093\7g\2\2\u0093\u0094\7i\2\2\u0094\u0095\7g\2\2\u0095"+
		"\u0097\7t\2\2\u0096\u008b\3\2\2\2\u0096\u008f\3\2\2\2\u0097\60\3\2\2\2"+
		"\u0098\u009a\5\r\7\2\u0099\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u0099"+
		"\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u00a1\t\4\2\2\u009e"+
		"\u00a0\5\r\7\2\u009f\u009e\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2"+
		"\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00b1\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4"+
		"\u00a6\5\r\7\2\u00a5\u00a4\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7\u00a5\3\2"+
		"\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00aa\3\2\2\2\u00a9\u00a7\3\2\2\2\u00aa"+
		"\u00ac\t\4\2\2\u00ab\u00ad\5\r\7\2\u00ac\u00ab\3\2\2\2\u00ad\u00ae\3\2"+
		"\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b1\3\2\2\2\u00b0"+
		"\u0099\3\2\2\2\u00b0\u00a7\3\2\2\2\u00b1\62\3\2\2\2\u00b2\u00b3\7o\2\2"+
		"\u00b3\u00b4\7q\2\2\u00b4\u00b5\7p\2\2\u00b5\u00b6\7g\2\2\u00b6\u00b7"+
		"\7{\2\2\u00b7\64\3\2\2\2\u00b8\u00b9\7f\2\2\u00b9\u00ba\7c\2\2\u00ba\u00bb"+
		"\7v\2\2\u00bb\u00bc\7g\2\2\u00bc\66\3\2\2\2\u00bd\u00c1\t\5\2\2\u00be"+
		"\u00c0\t\6\2\2\u00bf\u00be\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1\u00bf\3\2"+
		"\2\2\u00c1\u00c2\3\2\2\2\u00c28\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00c5"+
		"\7.\2\2\u00c5:\3\2\2\2\u00c6\u00c7\7\61\2\2\u00c7\u00c8\7,\2\2\u00c8\u00cc"+
		"\3\2\2\2\u00c9\u00cb\13\2\2\2\u00ca\u00c9\3\2\2\2\u00cb\u00ce\3\2\2\2"+
		"\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00cf\3\2\2\2\u00ce\u00cc"+
		"\3\2\2\2\u00cf\u00d0\7,\2\2\u00d0\u00db\7\61\2\2\u00d1\u00d2\7\61\2\2"+
		"\u00d2\u00d3\7\61\2\2\u00d3\u00d7\3\2\2\2\u00d4\u00d6\n\7\2\2\u00d5\u00d4"+
		"\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8"+
		"\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00c6\3\2\2\2\u00da\u00d1\3\2"+
		"\2\2\u00db<\3\2\2\2\u00dc\u00de\7\17\2\2\u00dd\u00dc\3\2\2\2\u00dd\u00de"+
		"\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e0\7\f\2\2\u00e0>\3\2\2\2\21\2\177"+
		"\u0085\u008d\u0096\u009b\u00a1\u00a7\u00ae\u00b0\u00c1\u00cc\u00d7\u00da"+
		"\u00dd\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}