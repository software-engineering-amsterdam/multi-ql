// Generated from /Users/roydewildt/Workspace/UvA/SC/multi-ql/RoydeWildt/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g4 by ANTLR 4.5.1
package org.uva.sea.ql.parser.antlr;

import java.util.List;
import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.val.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, WHITESPACE=24, 
		COMMENT=25, True=26, False=27, Ident=28, Str=29, Int=30;
	public static final int
		RULE_type = 0, RULE_bool = 1, RULE_varDecl = 2, RULE_question = 3, RULE_stat = 4, 
		RULE_stats = 5, RULE_primary = 6, RULE_unExpr = 7, RULE_mulExpr = 8, RULE_addExpr = 9, 
		RULE_relExpr = 10, RULE_andExpr = 11, RULE_orExpr = 12;
	public static final String[] ruleNames = {
		"type", "bool", "varDecl", "question", "stat", "stats", "primary", "unExpr", 
		"mulExpr", "addExpr", "relExpr", "andExpr", "orExpr"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'boolean'", "'money'", "'='", "'('", "')'", "':'", "'if'", "'{'", 
		"'}'", "'else'", "'+'", "'-'", "'!'", "'*'", "'/'", "'<'", "'<='", "'>'", 
		"'>='", "'=='", "'!='", "'&&'", "'||'", null, null, "'true'", "'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"WHITESPACE", "COMMENT", "True", "False", "Ident", "Str", "Int"
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

	@Override
	public String getGrammarFileName() { return "QL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class TypeContext extends ParserRuleContext {
		public Stat result;
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			_la = _input.LA(1);
			if ( !(_la==T__0 || _la==T__1) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolContext extends ParserRuleContext {
		public Val result;
		public Token x;
		public TerminalNode True() { return getToken(QLParser.True, 0); }
		public TerminalNode False() { return getToken(QLParser.False, 0); }
		public BoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitBool(this);
		}
	}

	public final BoolContext bool() throws RecognitionException {
		BoolContext _localctx = new BoolContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_bool);
		try {
			setState(32);
			switch (_input.LA(1)) {
			case True:
				enterOuterAlt(_localctx, 1);
				{
				setState(28);
				((BoolContext)_localctx).x = match(True);
				((BoolContext)_localctx).result =  new Bool((((BoolContext)_localctx).x!=null?((BoolContext)_localctx).x.getText():null));
				}
				break;
			case False:
				enterOuterAlt(_localctx, 2);
				{
				setState(30);
				((BoolContext)_localctx).x = match(False);
				((BoolContext)_localctx).result =  new Bool((((BoolContext)_localctx).x!=null?((BoolContext)_localctx).x.getText():null));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclContext extends ParserRuleContext {
		public Expr result;
		public OrExprContext x;
		public OrExprContext orExpr() {
			return getRuleContext(OrExprContext.class,0);
		}
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitVarDecl(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_varDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(34);
			match(T__2);
			setState(35);
			match(T__3);
			setState(36);
			((VarDeclContext)_localctx).x = orExpr();
			setState(37);
			match(T__4);
			}
			((VarDeclContext)_localctx).result =  ((VarDeclContext)_localctx).x.result;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuestionContext extends ParserRuleContext {
		public Stat result;
		public Token l;
		public Token v;
		public TypeContext t;
		public VarDeclContext e;
		public TerminalNode Str() { return getToken(QLParser.Str, 0); }
		public TerminalNode Ident() { return getToken(QLParser.Ident, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitQuestion(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_question);
		try {
			setState(54);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(41);
				((QuestionContext)_localctx).l = match(Str);
				setState(42);
				((QuestionContext)_localctx).v = match(Ident);
				setState(43);
				match(T__5);
				setState(44);
				((QuestionContext)_localctx).t = type();
				((QuestionContext)_localctx).result =  new Question((((QuestionContext)_localctx).l!=null?((QuestionContext)_localctx).l.getText():null), (((QuestionContext)_localctx).v!=null?((QuestionContext)_localctx).v.getText():null), (((QuestionContext)_localctx).t!=null?_input.getText(((QuestionContext)_localctx).t.start,((QuestionContext)_localctx).t.stop):null));
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				((QuestionContext)_localctx).l = match(Str);
				setState(48);
				((QuestionContext)_localctx).v = match(Ident);
				setState(49);
				match(T__5);
				setState(50);
				((QuestionContext)_localctx).t = type();
				setState(51);
				((QuestionContext)_localctx).e = varDecl();
				((QuestionContext)_localctx).result =  new Question((((QuestionContext)_localctx).l!=null?((QuestionContext)_localctx).l.getText():null), (((QuestionContext)_localctx).v!=null?((QuestionContext)_localctx).v.getText():null), (((QuestionContext)_localctx).t!=null?_input.getText(((QuestionContext)_localctx).t.start,((QuestionContext)_localctx).t.stop):null), ((QuestionContext)_localctx).e.result);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public Stat result;
		public QuestionContext x;
		public OrExprContext c;
		public StatsContext s;
		public StatsContext i;
		public StatsContext e;
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public OrExprContext orExpr() {
			return getRuleContext(OrExprContext.class,0);
		}
		public List<StatsContext> stats() {
			return getRuleContexts(StatsContext.class);
		}
		public StatsContext stats(int i) {
			return getRuleContext(StatsContext.class,i);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitStat(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_stat);
		try {
			setState(81);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				((StatContext)_localctx).x = question();
				((StatContext)_localctx).result =  ((StatContext)_localctx).x.result;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				match(T__6);
				setState(60);
				match(T__3);
				setState(61);
				((StatContext)_localctx).c = orExpr();
				setState(62);
				match(T__4);
				setState(63);
				match(T__7);
				setState(64);
				((StatContext)_localctx).s = stats();
				setState(65);
				match(T__8);
				((StatContext)_localctx).result =  new If(((StatContext)_localctx).c.result, ((StatContext)_localctx).s.result);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				match(T__6);
				setState(69);
				match(T__3);
				setState(70);
				((StatContext)_localctx).c = orExpr();
				setState(71);
				match(T__4);
				setState(72);
				match(T__7);
				setState(73);
				((StatContext)_localctx).i = stats();
				setState(74);
				match(T__8);
				setState(75);
				match(T__9);
				setState(76);
				match(T__7);
				setState(77);
				((StatContext)_localctx).e = stats();
				setState(78);
				match(T__8);
				((StatContext)_localctx).result =  new IfElse(((StatContext)_localctx).c.result, ((StatContext)_localctx).i.result, ((StatContext)_localctx).e.result);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatsContext extends ParserRuleContext {
		public List<Stat> result;
		public StatContext stat;
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public StatsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stats; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterStats(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitStats(this);
		}
	}

	public final StatsContext stats() throws RecognitionException {
		StatsContext _localctx = new StatsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_stats);
		((StatsContext)_localctx).result =  new ArrayList<Stat>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(83);
				((StatsContext)_localctx).stat = stat();
				_localctx.result.add(((StatsContext)_localctx).stat.result);
				}
				}
				setState(88); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__6 || _la==Str );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public Expr result;
		public Token x;
		public BoolContext y;
		public TerminalNode Int() { return getToken(QLParser.Int, 0); }
		public TerminalNode Ident() { return getToken(QLParser.Ident, 0); }
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitPrimary(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_primary);
		try {
			setState(97);
			switch (_input.LA(1)) {
			case Int:
				enterOuterAlt(_localctx, 1);
				{
				setState(90);
				((PrimaryContext)_localctx).x = match(Int);
				((PrimaryContext)_localctx).result =  new Prim(new Int((((PrimaryContext)_localctx).x!=null?((PrimaryContext)_localctx).x.getText():null)));
				}
				break;
			case Ident:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				((PrimaryContext)_localctx).x = match(Ident);
				((PrimaryContext)_localctx).result =  new Prim(new Var((((PrimaryContext)_localctx).x!=null?((PrimaryContext)_localctx).x.getText():null)));
				}
				break;
			case True:
			case False:
				enterOuterAlt(_localctx, 3);
				{
				setState(94);
				((PrimaryContext)_localctx).y = bool();
				((PrimaryContext)_localctx).result =  new Prim(((PrimaryContext)_localctx).y.result);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnExprContext extends ParserRuleContext {
		public Expr result;
		public UnExprContext x;
		public PrimaryContext y;
		public UnExprContext unExpr() {
			return getRuleContext(UnExprContext.class,0);
		}
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public UnExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterUnExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitUnExpr(this);
		}
	}

	public final UnExprContext unExpr() throws RecognitionException {
		UnExprContext _localctx = new UnExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_unExpr);
		try {
			setState(114);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				match(T__10);
				setState(100);
				((UnExprContext)_localctx).x = unExpr();
				 ((UnExprContext)_localctx).result =  new Pos(((UnExprContext)_localctx).x.result); 
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				match(T__11);
				setState(104);
				((UnExprContext)_localctx).x = unExpr();
				 ((UnExprContext)_localctx).result =  new Neg(((UnExprContext)_localctx).x.result); 
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 3);
				{
				setState(107);
				match(T__12);
				setState(108);
				((UnExprContext)_localctx).x = unExpr();
				 ((UnExprContext)_localctx).result =  new Not(((UnExprContext)_localctx).x.result); 
				}
				break;
			case True:
			case False:
			case Ident:
			case Int:
				enterOuterAlt(_localctx, 4);
				{
				setState(111);
				((UnExprContext)_localctx).y = primary();
				 ((UnExprContext)_localctx).result =  ((UnExprContext)_localctx).y.result; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MulExprContext extends ParserRuleContext {
		public Expr result;
		public UnExprContext lhs;
		public Token op;
		public UnExprContext rhs;
		public List<UnExprContext> unExpr() {
			return getRuleContexts(UnExprContext.class);
		}
		public UnExprContext unExpr(int i) {
			return getRuleContext(UnExprContext.class,i);
		}
		public MulExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterMulExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitMulExpr(this);
		}
	}

	public final MulExprContext mulExpr() throws RecognitionException {
		MulExprContext _localctx = new MulExprContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_mulExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			((MulExprContext)_localctx).lhs = unExpr();
			 ((MulExprContext)_localctx).result = ((MulExprContext)_localctx).lhs.result; 
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13 || _la==T__14) {
				{
				{
				setState(118);
				((MulExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__13 || _la==T__14) ) {
					((MulExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(119);
				((MulExprContext)_localctx).rhs = unExpr();
				 
				      if ((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getText():null).equals("*")) {
				        ((MulExprContext)_localctx).result =  new Mul(_localctx.result, ((MulExprContext)_localctx).rhs.result);
				      }
				      if ((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getText():null).equals("/")) {
				        ((MulExprContext)_localctx).result =  new Div(_localctx.result, ((MulExprContext)_localctx).rhs.result);
				      }
				    
				}
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddExprContext extends ParserRuleContext {
		public Expr result;
		public MulExprContext lhs;
		public Token op;
		public MulExprContext rhs;
		public List<MulExprContext> mulExpr() {
			return getRuleContexts(MulExprContext.class);
		}
		public MulExprContext mulExpr(int i) {
			return getRuleContext(MulExprContext.class,i);
		}
		public AddExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterAddExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitAddExpr(this);
		}
	}

	public final AddExprContext addExpr() throws RecognitionException {
		AddExprContext _localctx = new AddExprContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_addExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			((AddExprContext)_localctx).lhs = mulExpr();
			 ((AddExprContext)_localctx).result = ((AddExprContext)_localctx).lhs.result; 
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10 || _la==T__11) {
				{
				{
				setState(129);
				((AddExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__10 || _la==T__11) ) {
					((AddExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(130);
				((AddExprContext)_localctx).rhs = mulExpr();
				 
				      if ((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getText():null).equals("+")) {
				        ((AddExprContext)_localctx).result =  new Add(_localctx.result, ((AddExprContext)_localctx).rhs.result);
				      }
				      if ((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getText():null).equals("-")) {
				        ((AddExprContext)_localctx).result =  new Sub(_localctx.result, ((AddExprContext)_localctx).rhs.result);
				      }
				    
				}
				}
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelExprContext extends ParserRuleContext {
		public Expr result;
		public AddExprContext lhs;
		public Token op;
		public AddExprContext rhs;
		public List<AddExprContext> addExpr() {
			return getRuleContexts(AddExprContext.class);
		}
		public AddExprContext addExpr(int i) {
			return getRuleContext(AddExprContext.class,i);
		}
		public RelExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterRelExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitRelExpr(this);
		}
	}

	public final RelExprContext relExpr() throws RecognitionException {
		RelExprContext _localctx = new RelExprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_relExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			((RelExprContext)_localctx).lhs = addExpr();
			 ((RelExprContext)_localctx).result = ((RelExprContext)_localctx).lhs.result; 
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20))) != 0)) {
				{
				{
				setState(140);
				((RelExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20))) != 0)) ) {
					((RelExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(141);
				((RelExprContext)_localctx).rhs = addExpr();
				 
				      if ((((RelExprContext)_localctx).op!=null?((RelExprContext)_localctx).op.getText():null).equals("<")) {
				        ((RelExprContext)_localctx).result =  new LT(_localctx.result, ((RelExprContext)_localctx).rhs.result);
				      }
				      if ((((RelExprContext)_localctx).op!=null?((RelExprContext)_localctx).op.getText():null).equals("<=")) {
				        ((RelExprContext)_localctx).result =  new LEq(_localctx.result, ((RelExprContext)_localctx).rhs.result);
				      }
				      if ((((RelExprContext)_localctx).op!=null?((RelExprContext)_localctx).op.getText():null).equals(">")) {
				        ((RelExprContext)_localctx).result =  new GT(_localctx.result, ((RelExprContext)_localctx).rhs.result);
				      }
				      if ((((RelExprContext)_localctx).op!=null?((RelExprContext)_localctx).op.getText():null).equals(">=")) {
				        ((RelExprContext)_localctx).result =  new GEq(_localctx.result, ((RelExprContext)_localctx).rhs.result);
				      }
				      if ((((RelExprContext)_localctx).op!=null?((RelExprContext)_localctx).op.getText():null).equals("==")) {
				        ((RelExprContext)_localctx).result =  new Eq(_localctx.result, ((RelExprContext)_localctx).rhs.result);
				      }
				      if ((((RelExprContext)_localctx).op!=null?((RelExprContext)_localctx).op.getText():null).equals("!=")) {
				        ((RelExprContext)_localctx).result =  new NEq(_localctx.result, ((RelExprContext)_localctx).rhs.result);
				      }
				    
				}
				}
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndExprContext extends ParserRuleContext {
		public Expr result;
		public RelExprContext lhs;
		public RelExprContext rhs;
		public List<RelExprContext> relExpr() {
			return getRuleContexts(RelExprContext.class);
		}
		public RelExprContext relExpr(int i) {
			return getRuleContext(RelExprContext.class,i);
		}
		public AndExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitAndExpr(this);
		}
	}

	public final AndExprContext andExpr() throws RecognitionException {
		AndExprContext _localctx = new AndExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_andExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			((AndExprContext)_localctx).lhs = relExpr();
			 ((AndExprContext)_localctx).result = ((AndExprContext)_localctx).lhs.result; 
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__21) {
				{
				{
				setState(151);
				match(T__21);
				setState(152);
				((AndExprContext)_localctx).rhs = relExpr();
				 ((AndExprContext)_localctx).result =  new And(_localctx.result, ((AndExprContext)_localctx).rhs.result); 
				}
				}
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrExprContext extends ParserRuleContext {
		public Expr result;
		public AndExprContext lhs;
		public AndExprContext rhs;
		public List<AndExprContext> andExpr() {
			return getRuleContexts(AndExprContext.class);
		}
		public AndExprContext andExpr(int i) {
			return getRuleContext(AndExprContext.class,i);
		}
		public OrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitOrExpr(this);
		}
	}

	public final OrExprContext orExpr() throws RecognitionException {
		OrExprContext _localctx = new OrExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_orExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			((OrExprContext)_localctx).lhs = andExpr();
			 ((OrExprContext)_localctx).result =  ((OrExprContext)_localctx).lhs.result; 
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(162);
				match(T__22);
				setState(163);
				((OrExprContext)_localctx).rhs = andExpr();
				 ((OrExprContext)_localctx).result =  new Or(_localctx.result, ((OrExprContext)_localctx).rhs.result); 
				}
				}
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3 \u00ae\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\3\3\3\3\3\3\3\5\3#\n\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\5\59\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6T\n\6\3\7\3\7\3\7\6\7Y\n"+
		"\7\r\7\16\7Z\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bd\n\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tu\n\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\7\n}\n\n\f\n\16\n\u0080\13\n\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u0088"+
		"\n\13\f\13\16\13\u008b\13\13\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u0093\n\f\f\f"+
		"\16\f\u0096\13\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u009e\n\r\f\r\16\r\u00a1"+
		"\13\r\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00a9\n\16\f\16\16\16\u00ac\13"+
		"\16\3\16\2\2\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\6\3\2\3\4\3\2\20\21"+
		"\3\2\r\16\3\2\22\27\u00af\2\34\3\2\2\2\4\"\3\2\2\2\6$\3\2\2\2\b8\3\2\2"+
		"\2\nS\3\2\2\2\fX\3\2\2\2\16c\3\2\2\2\20t\3\2\2\2\22v\3\2\2\2\24\u0081"+
		"\3\2\2\2\26\u008c\3\2\2\2\30\u0097\3\2\2\2\32\u00a2\3\2\2\2\34\35\t\2"+
		"\2\2\35\3\3\2\2\2\36\37\7\34\2\2\37#\b\3\1\2 !\7\35\2\2!#\b\3\1\2\"\36"+
		"\3\2\2\2\" \3\2\2\2#\5\3\2\2\2$%\7\5\2\2%&\7\6\2\2&\'\5\32\16\2\'(\7\7"+
		"\2\2()\3\2\2\2)*\b\4\1\2*\7\3\2\2\2+,\7\37\2\2,-\7\36\2\2-.\7\b\2\2./"+
		"\5\2\2\2/\60\b\5\1\2\609\3\2\2\2\61\62\7\37\2\2\62\63\7\36\2\2\63\64\7"+
		"\b\2\2\64\65\5\2\2\2\65\66\5\6\4\2\66\67\b\5\1\2\679\3\2\2\28+\3\2\2\2"+
		"8\61\3\2\2\29\t\3\2\2\2:;\5\b\5\2;<\b\6\1\2<T\3\2\2\2=>\7\t\2\2>?\7\6"+
		"\2\2?@\5\32\16\2@A\7\7\2\2AB\7\n\2\2BC\5\f\7\2CD\7\13\2\2DE\b\6\1\2ET"+
		"\3\2\2\2FG\7\t\2\2GH\7\6\2\2HI\5\32\16\2IJ\7\7\2\2JK\7\n\2\2KL\5\f\7\2"+
		"LM\7\13\2\2MN\7\f\2\2NO\7\n\2\2OP\5\f\7\2PQ\7\13\2\2QR\b\6\1\2RT\3\2\2"+
		"\2S:\3\2\2\2S=\3\2\2\2SF\3\2\2\2T\13\3\2\2\2UV\5\n\6\2VW\b\7\1\2WY\3\2"+
		"\2\2XU\3\2\2\2YZ\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[\r\3\2\2\2\\]\7 \2\2]d\b"+
		"\b\1\2^_\7\36\2\2_d\b\b\1\2`a\5\4\3\2ab\b\b\1\2bd\3\2\2\2c\\\3\2\2\2c"+
		"^\3\2\2\2c`\3\2\2\2d\17\3\2\2\2ef\7\r\2\2fg\5\20\t\2gh\b\t\1\2hu\3\2\2"+
		"\2ij\7\16\2\2jk\5\20\t\2kl\b\t\1\2lu\3\2\2\2mn\7\17\2\2no\5\20\t\2op\b"+
		"\t\1\2pu\3\2\2\2qr\5\16\b\2rs\b\t\1\2su\3\2\2\2te\3\2\2\2ti\3\2\2\2tm"+
		"\3\2\2\2tq\3\2\2\2u\21\3\2\2\2vw\5\20\t\2w~\b\n\1\2xy\t\3\2\2yz\5\20\t"+
		"\2z{\b\n\1\2{}\3\2\2\2|x\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2\2\2"+
		"\177\23\3\2\2\2\u0080~\3\2\2\2\u0081\u0082\5\22\n\2\u0082\u0089\b\13\1"+
		"\2\u0083\u0084\t\4\2\2\u0084\u0085\5\22\n\2\u0085\u0086\b\13\1\2\u0086"+
		"\u0088\3\2\2\2\u0087\u0083\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2"+
		"\2\2\u0089\u008a\3\2\2\2\u008a\25\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u008d"+
		"\5\24\13\2\u008d\u0094\b\f\1\2\u008e\u008f\t\5\2\2\u008f\u0090\5\24\13"+
		"\2\u0090\u0091\b\f\1\2\u0091\u0093\3\2\2\2\u0092\u008e\3\2\2\2\u0093\u0096"+
		"\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\27\3\2\2\2\u0096"+
		"\u0094\3\2\2\2\u0097\u0098\5\26\f\2\u0098\u009f\b\r\1\2\u0099\u009a\7"+
		"\30\2\2\u009a\u009b\5\26\f\2\u009b\u009c\b\r\1\2\u009c\u009e\3\2\2\2\u009d"+
		"\u0099\3\2\2\2\u009e\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2"+
		"\2\2\u00a0\31\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a3\5\30\r\2\u00a3\u00aa"+
		"\b\16\1\2\u00a4\u00a5\7\31\2\2\u00a5\u00a6\5\30\r\2\u00a6\u00a7\b\16\1"+
		"\2\u00a7\u00a9\3\2\2\2\u00a8\u00a4\3\2\2\2\u00a9\u00ac\3\2\2\2\u00aa\u00a8"+
		"\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\33\3\2\2\2\u00ac\u00aa\3\2\2\2\r\""+
		"8SZct~\u0089\u0094\u009f\u00aa";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}