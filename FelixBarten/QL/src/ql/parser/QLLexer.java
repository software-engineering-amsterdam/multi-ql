// Generated from /Users/felixbarten/Git/multi-ql/FelixBarten/QL/src/ql/parser/QL.g4 by ANTLR 4.5.2

	package ql.parser;

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
		T__0=1, T__1=2, T__2=3, T__3=4, BOOLEAN_TYPE=5, MONEY_TYPE=6, INTEGER_TYPE=7, 
		STRING_TYPE=8, WS=9, COMMENT=10, Ident=11, Int=12, Money=13, Str=14;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "BOOLEAN_TYPE", "MONEY_TYPE", "INTEGER_TYPE", 
		"STRING_TYPE", "WS", "COMMENT", "Ident", "Int", "Money", "Str"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "':'", "'boolean'", "'money'", "'integer'", 
		"'string'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "BOOLEAN_TYPE", "MONEY_TYPE", "INTEGER_TYPE", 
		"STRING_TYPE", "WS", "COMMENT", "Ident", "Int", "Money", "Str"
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

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 8:
			WS_action((RuleContext)_localctx, actionIndex);
			break;
		case 9:
			COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			  
			break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			 
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\20q\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3"+
		"\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\7\13O\n\13\f\13\16\13R\13\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\7\f[\n\f\f\f\16\f^\13\f\3\r\6\ra\n\r\r\r\16\rb\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\7\17k\n\17\f\17\16\17n\13\17\3\17\3\17\2\2\20\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\3\2"+
		"\5\5\2\13\f\17\17\"\"\4\2C\\c|\6\2\62;C\\aac|t\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\3\37\3\2\2\2\5$\3\2\2\2\7&\3\2\2\2\t(\3\2\2\2\13*\3\2"+
		"\2\2\r\62\3\2\2\2\178\3\2\2\2\21@\3\2\2\2\23G\3\2\2\2\25J\3\2\2\2\27X"+
		"\3\2\2\2\31`\3\2\2\2\33d\3\2\2\2\35h\3\2\2\2\37 \7h\2\2 !\7q\2\2!\"\7"+
		"t\2\2\"#\7o\2\2#\4\3\2\2\2$%\7}\2\2%\6\3\2\2\2&\'\7\177\2\2\'\b\3\2\2"+
		"\2()\7<\2\2)\n\3\2\2\2*+\7d\2\2+,\7q\2\2,-\7q\2\2-.\7n\2\2./\7g\2\2/\60"+
		"\7c\2\2\60\61\7p\2\2\61\f\3\2\2\2\62\63\7o\2\2\63\64\7q\2\2\64\65\7p\2"+
		"\2\65\66\7g\2\2\66\67\7{\2\2\67\16\3\2\2\289\7k\2\29:\7p\2\2:;\7v\2\2"+
		";<\7g\2\2<=\7i\2\2=>\7g\2\2>?\7t\2\2?\20\3\2\2\2@A\7u\2\2AB\7v\2\2BC\7"+
		"t\2\2CD\7k\2\2DE\7p\2\2EF\7i\2\2F\22\3\2\2\2GH\t\2\2\2HI\b\n\2\2I\24\3"+
		"\2\2\2JK\7\61\2\2KL\7,\2\2LP\3\2\2\2MO\13\2\2\2NM\3\2\2\2OR\3\2\2\2PN"+
		"\3\2\2\2PQ\3\2\2\2QS\3\2\2\2RP\3\2\2\2ST\7,\2\2TU\7\61\2\2UV\3\2\2\2V"+
		"W\b\13\3\2W\26\3\2\2\2X\\\t\3\2\2Y[\t\4\2\2ZY\3\2\2\2[^\3\2\2\2\\Z\3\2"+
		"\2\2\\]\3\2\2\2]\30\3\2\2\2^\\\3\2\2\2_a\4\62;\2`_\3\2\2\2ab\3\2\2\2b"+
		"`\3\2\2\2bc\3\2\2\2c\32\3\2\2\2de\5\31\r\2ef\7.\2\2fg\5\31\r\2g\34\3\2"+
		"\2\2hl\7$\2\2ik\13\2\2\2ji\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mo\3\2"+
		"\2\2nl\3\2\2\2op\7$\2\2p\36\3\2\2\2\7\2P\\bl\4\3\n\2\3\13\3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}