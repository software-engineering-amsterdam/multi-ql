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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, BOOL_TYPE=8, MONEY_TYPE=9, 
		STRING_TYPE=10, INTEGER_TYPE=11, WS=12, DIGIT=13, SMALLERTHAN=14, BIGGERTHAN=15, 
		SMALLER_EQUAL=16, BIGGER_EQUAL=17, EQUAL=18, NOT_EQUAL=19, AND=20, OR=21, 
		NOT=22, IF=23, ELSE=24, FORM=25, ASSIGN=26, MINUS=27, ADD=28, MULTIPLY=29, 
		DIVIDE=30, BOOLEAN=31, STR=32, INT=33, FLOAT=34, CURRENCYSYMBOL=35, MONEY=36, 
		DATE=37, ID=38, COMMA=39, COMMENT=40;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "BOOL_TYPE", "MONEY_TYPE", 
		"STRING_TYPE", "INTEGER_TYPE", "WS", "DIGIT", "SMALLERTHAN", "BIGGERTHAN", 
		"SMALLER_EQUAL", "BIGGER_EQUAL", "EQUAL", "NOT_EQUAL", "AND", "OR", "NOT", 
		"IF", "ELSE", "FORM", "ASSIGN", "MINUS", "ADD", "MULTIPLY", "DIVIDE", 
		"BOOLEAN", "STR", "INT", "FLOAT", "CURRENCYSYMBOL", "MONEY", "DATE", "ID", 
		"COMMA", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "'}'", "'('", "')'", "':'", "'true'", "'false'", "'boolean'", 
		"'money'", "'string'", "'integer'", null, null, "'<'", "'>'", "'<='", 
		"'>='", "'=='", "'!='", "'&&'", "'||'", "'!'", "'if'", "'else'", "'form'", 
		"'='", "'-'", "'+'", "'*'", "'/'", null, null, null, null, "'€'", null, 
		"'date'", null, "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "BOOL_TYPE", "MONEY_TYPE", 
		"STRING_TYPE", "INTEGER_TYPE", "WS", "DIGIT", "SMALLERTHAN", "BIGGERTHAN", 
		"SMALLER_EQUAL", "BIGGER_EQUAL", "EQUAL", "NOT_EQUAL", "AND", "OR", "NOT", 
		"IF", "ELSE", "FORM", "ASSIGN", "MINUS", "ADD", "MULTIPLY", "DIVIDE", 
		"BOOLEAN", "STR", "INT", "FLOAT", "CURRENCYSYMBOL", "MONEY", "DATE", "ID", 
		"COMMA", "COMMENT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2*\u00f8\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3"+
		"\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3"+
		"\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3"+
		"\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\34\3"+
		"\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \5 \u00c4"+
		"\n \3!\3!\7!\u00c8\n!\f!\16!\u00cb\13!\3!\3!\3\"\6\"\u00d0\n\"\r\"\16"+
		"\"\u00d1\3#\3#\3$\3$\3%\6%\u00d9\n%\r%\16%\u00da\3&\3&\3&\3&\3&\3\'\3"+
		"\'\7\'\u00e4\n\'\f\'\16\'\u00e7\13\'\3(\3(\3)\3)\3)\3)\7)\u00ef\n)\f)"+
		"\16)\u00f2\13)\3)\3)\3)\3)\3)\3\u00c9\2*\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27"+
		"-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*\3\2\6"+
		"\5\2\13\f\17\17\"\"\3\2\62;\4\2C\\c|\6\2\62;C\\aac|\u00fd\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2"+
		"\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2"+
		"\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2"+
		"\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\3S\3\2\2\2\5U\3\2\2\2\7W"+
		"\3\2\2\2\tY\3\2\2\2\13[\3\2\2\2\r]\3\2\2\2\17b\3\2\2\2\21h\3\2\2\2\23"+
		"p\3\2\2\2\25v\3\2\2\2\27}\3\2\2\2\31\u0085\3\2\2\2\33\u0089\3\2\2\2\35"+
		"\u008b\3\2\2\2\37\u008d\3\2\2\2!\u008f\3\2\2\2#\u0092\3\2\2\2%\u0095\3"+
		"\2\2\2\'\u0098\3\2\2\2)\u009b\3\2\2\2+\u009e\3\2\2\2-\u00a1\3\2\2\2/\u00a3"+
		"\3\2\2\2\61\u00a6\3\2\2\2\63\u00ab\3\2\2\2\65\u00b0\3\2\2\2\67\u00b2\3"+
		"\2\2\29\u00b4\3\2\2\2;\u00b6\3\2\2\2=\u00b8\3\2\2\2?\u00c3\3\2\2\2A\u00c5"+
		"\3\2\2\2C\u00cf\3\2\2\2E\u00d3\3\2\2\2G\u00d5\3\2\2\2I\u00d8\3\2\2\2K"+
		"\u00dc\3\2\2\2M\u00e1\3\2\2\2O\u00e8\3\2\2\2Q\u00ea\3\2\2\2ST\7}\2\2T"+
		"\4\3\2\2\2UV\7\177\2\2V\6\3\2\2\2WX\7*\2\2X\b\3\2\2\2YZ\7+\2\2Z\n\3\2"+
		"\2\2[\\\7<\2\2\\\f\3\2\2\2]^\7v\2\2^_\7t\2\2_`\7w\2\2`a\7g\2\2a\16\3\2"+
		"\2\2bc\7h\2\2cd\7c\2\2de\7n\2\2ef\7u\2\2fg\7g\2\2g\20\3\2\2\2hi\7d\2\2"+
		"ij\7q\2\2jk\7q\2\2kl\7n\2\2lm\7g\2\2mn\7c\2\2no\7p\2\2o\22\3\2\2\2pq\7"+
		"o\2\2qr\7q\2\2rs\7p\2\2st\7g\2\2tu\7{\2\2u\24\3\2\2\2vw\7u\2\2wx\7v\2"+
		"\2xy\7t\2\2yz\7k\2\2z{\7p\2\2{|\7i\2\2|\26\3\2\2\2}~\7k\2\2~\177\7p\2"+
		"\2\177\u0080\7v\2\2\u0080\u0081\7g\2\2\u0081\u0082\7i\2\2\u0082\u0083"+
		"\7g\2\2\u0083\u0084\7t\2\2\u0084\30\3\2\2\2\u0085\u0086\t\2\2\2\u0086"+
		"\u0087\3\2\2\2\u0087\u0088\b\r\2\2\u0088\32\3\2\2\2\u0089\u008a\t\3\2"+
		"\2\u008a\34\3\2\2\2\u008b\u008c\7>\2\2\u008c\36\3\2\2\2\u008d\u008e\7"+
		"@\2\2\u008e \3\2\2\2\u008f\u0090\7>\2\2\u0090\u0091\7?\2\2\u0091\"\3\2"+
		"\2\2\u0092\u0093\7@\2\2\u0093\u0094\7?\2\2\u0094$\3\2\2\2\u0095\u0096"+
		"\7?\2\2\u0096\u0097\7?\2\2\u0097&\3\2\2\2\u0098\u0099\7#\2\2\u0099\u009a"+
		"\7?\2\2\u009a(\3\2\2\2\u009b\u009c\7(\2\2\u009c\u009d\7(\2\2\u009d*\3"+
		"\2\2\2\u009e\u009f\7~\2\2\u009f\u00a0\7~\2\2\u00a0,\3\2\2\2\u00a1\u00a2"+
		"\7#\2\2\u00a2.\3\2\2\2\u00a3\u00a4\7k\2\2\u00a4\u00a5\7h\2\2\u00a5\60"+
		"\3\2\2\2\u00a6\u00a7\7g\2\2\u00a7\u00a8\7n\2\2\u00a8\u00a9\7u\2\2\u00a9"+
		"\u00aa\7g\2\2\u00aa\62\3\2\2\2\u00ab\u00ac\7h\2\2\u00ac\u00ad\7q\2\2\u00ad"+
		"\u00ae\7t\2\2\u00ae\u00af\7o\2\2\u00af\64\3\2\2\2\u00b0\u00b1\7?\2\2\u00b1"+
		"\66\3\2\2\2\u00b2\u00b3\7/\2\2\u00b38\3\2\2\2\u00b4\u00b5\7-\2\2\u00b5"+
		":\3\2\2\2\u00b6\u00b7\7,\2\2\u00b7<\3\2\2\2\u00b8\u00b9\7\61\2\2\u00b9"+
		">\3\2\2\2\u00ba\u00bb\7v\2\2\u00bb\u00bc\7t\2\2\u00bc\u00bd\7w\2\2\u00bd"+
		"\u00c4\7g\2\2\u00be\u00bf\7h\2\2\u00bf\u00c0\7c\2\2\u00c0\u00c1\7n\2\2"+
		"\u00c1\u00c2\7u\2\2\u00c2\u00c4\7g\2\2\u00c3\u00ba\3\2\2\2\u00c3\u00be"+
		"\3\2\2\2\u00c4@\3\2\2\2\u00c5\u00c9\7$\2\2\u00c6\u00c8\13\2\2\2\u00c7"+
		"\u00c6\3\2\2\2\u00c8\u00cb\3\2\2\2\u00c9\u00ca\3\2\2\2\u00c9\u00c7\3\2"+
		"\2\2\u00ca\u00cc\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc\u00cd\7$\2\2\u00cd"+
		"B\3\2\2\2\u00ce\u00d0\4\62;\2\u00cf\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2"+
		"\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2D\3\2\2\2\u00d3\u00d4\5"+
		"C\"\2\u00d4F\3\2\2\2\u00d5\u00d6\7\u20ae\2\2\u00d6H\3\2\2\2\u00d7\u00d9"+
		"\5\33\16\2\u00d8\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00d8\3\2\2\2"+
		"\u00da\u00db\3\2\2\2\u00dbJ\3\2\2\2\u00dc\u00dd\7f\2\2\u00dd\u00de\7c"+
		"\2\2\u00de\u00df\7v\2\2\u00df\u00e0\7g\2\2\u00e0L\3\2\2\2\u00e1\u00e5"+
		"\t\4\2\2\u00e2\u00e4\t\5\2\2\u00e3\u00e2\3\2\2\2\u00e4\u00e7\3\2\2\2\u00e5"+
		"\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6N\3\2\2\2\u00e7\u00e5\3\2\2\2"+
		"\u00e8\u00e9\7.\2\2\u00e9P\3\2\2\2\u00ea\u00eb\7\61\2\2\u00eb\u00ec\7"+
		",\2\2\u00ec\u00f0\3\2\2\2\u00ed\u00ef\13\2\2\2\u00ee\u00ed\3\2\2\2\u00ef"+
		"\u00f2\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f3\3\2"+
		"\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00f4\7,\2\2\u00f4\u00f5\7\61\2\2\u00f5"+
		"\u00f6\3\2\2\2\u00f6\u00f7\b)\2\2\u00f7R\3\2\2\2\t\2\u00c3\u00c9\u00d1"+
		"\u00da\u00e5\u00f0\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}