// Generated from /Users/roydewildt/Workspace/UvA/SC/multi-ql/RoydeWildt/QLJava/src/org/uva/sea/ql/parser/QL.g4 by ANTLR 4.5.1
package org.uva.sea.ql.parser;

import java.util.List;

import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.val.*;
import org.uva.sea.ql.ast.tree.form.*;
import org.uva.sea.ql.ast.tree.type.Boolean;
import org.uva.sea.ql.ast.tree.type.Money;
import org.uva.sea.ql.ast.tree.type.Type;

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
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, WHITESPACE=23, COMMENT=24, 
		True=25, False=26, Boolean=27, Money=28, Ident=29, Str=30, Int=31;
	public static final int
		RULE_form = 0, RULE_varDecl = 1, RULE_varAss = 2, RULE_question = 3, RULE_stat = 4, 
		RULE_stats = 5, RULE_primary = 6, RULE_unExpr = 7, RULE_mulExpr = 8, RULE_addExpr = 9, 
		RULE_relExpr = 10, RULE_andExpr = 11, RULE_orExpr = 12, RULE_type = 13, 
		RULE_bool = 14, RULE_num = 15, RULE_id = 16;
	public static final String[] ruleNames = {
		"form", "varDecl", "varAss", "question", "stat", "stats", "primary", "unExpr", 
		"mulExpr", "addExpr", "relExpr", "andExpr", "orExpr", "type", "bool", 
		"num", "id"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'='", "'('", "')'", "':'", "'if'", "'else'", 
		"'+'", "'-'", "'!'", "'*'", "'/'", "'<'", "'<='", "'>'", "'>='", "'=='", 
		"'!='", "'&&'", "'||'", null, null, "'true'", "'false'", "'boolean'", 
		"'money'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "WHITESPACE", 
		"COMMENT", "True", "False", "Boolean", "Money", "Ident", "Str", "Int"
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
	public static class FormContext extends ParserRuleContext {
		public Form result;
		public Token i;
		public StatsContext s;
		public TerminalNode Ident() { return getToken(QLParser.Ident, 0); }
		public StatsContext stats() {
			return getRuleContext(StatsContext.class,0);
		}
		public FormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterForm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitForm(this);
		}
	}

	public final FormContext form() throws RecognitionException {
		FormContext _localctx = new FormContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_form);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__0);
			setState(35);
			((FormContext)_localctx).i = match(Ident);
			setState(36);
			match(T__1);
			setState(37);
			((FormContext)_localctx).s = stats();
			setState(38);
			match(T__2);
			((FormContext)_localctx).result =  new Form(((FormContext)_localctx).i.getLine(), (((FormContext)_localctx).i!=null?((FormContext)_localctx).i.getText():null), ((FormContext)_localctx).s.result);
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
		public Var result;
		public Token i;
		public TerminalNode Ident() { return getToken(QLParser.Ident, 0); }
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
		enterRule(_localctx, 2, RULE_varDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			((VarDeclContext)_localctx).i = match(Ident);
			((VarDeclContext)_localctx).result =  new Var(((VarDeclContext)_localctx).i.getLine(), (((VarDeclContext)_localctx).i!=null?((VarDeclContext)_localctx).i.getText():null));
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

	public static class VarAssContext extends ParserRuleContext {
		public Expr result;
		public OrExprContext x;
		public OrExprContext orExpr() {
			return getRuleContext(OrExprContext.class,0);
		}
		public VarAssContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varAss; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterVarAss(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitVarAss(this);
		}
	}

	public final VarAssContext varAss() throws RecognitionException {
		VarAssContext _localctx = new VarAssContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_varAss);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(T__3);
			setState(45);
			match(T__4);
			setState(46);
			((VarAssContext)_localctx).x = orExpr();
			setState(47);
			match(T__5);
			((VarAssContext)_localctx).result =  ((VarAssContext)_localctx).x.result;
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
		public VarDeclContext v;
		public TypeContext t;
		public VarAssContext e;
		public TerminalNode Str() { return getToken(QLParser.Str, 0); }
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VarAssContext varAss() {
			return getRuleContext(VarAssContext.class,0);
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
			setState(63);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				((QuestionContext)_localctx).l = match(Str);
				setState(51);
				((QuestionContext)_localctx).v = varDecl();
				setState(52);
				match(T__6);
				setState(53);
				((QuestionContext)_localctx).t = type();
				((QuestionContext)_localctx).result =  new Question(((QuestionContext)_localctx).l.getLine(), (((QuestionContext)_localctx).l!=null?((QuestionContext)_localctx).l.getText():null), ((QuestionContext)_localctx).v.result, ((QuestionContext)_localctx).t.result);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
				((QuestionContext)_localctx).l = match(Str);
				setState(57);
				((QuestionContext)_localctx).v = varDecl();
				setState(58);
				match(T__6);
				setState(59);
				((QuestionContext)_localctx).t = type();
				setState(60);
				((QuestionContext)_localctx).e = varAss();
				((QuestionContext)_localctx).result =  new AssQuestion(((QuestionContext)_localctx).l.getLine(), (((QuestionContext)_localctx).l!=null?((QuestionContext)_localctx).l.getText():null), ((QuestionContext)_localctx).v.result, ((QuestionContext)_localctx).t.result, ((QuestionContext)_localctx).e.result);
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
		public QuestionContext q;
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
			setState(90);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				((StatContext)_localctx).q = question();
				((StatContext)_localctx).result =  ((StatContext)_localctx).q.result;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(68);
				match(T__7);
				setState(69);
				match(T__4);
				setState(70);
				((StatContext)_localctx).c = orExpr();
				setState(71);
				match(T__5);
				setState(72);
				match(T__1);
				setState(73);
				((StatContext)_localctx).s = stats();
				setState(74);
				match(T__2);
				((StatContext)_localctx).result =  new If((((StatContext)_localctx).c!=null?(((StatContext)_localctx).c.start):null).getLine(), ((StatContext)_localctx).c.result, ((StatContext)_localctx).s.result);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(77);
				match(T__7);
				setState(78);
				match(T__4);
				setState(79);
				((StatContext)_localctx).c = orExpr();
				setState(80);
				match(T__5);
				setState(81);
				match(T__1);
				setState(82);
				((StatContext)_localctx).i = stats();
				setState(83);
				match(T__2);
				setState(84);
				match(T__8);
				setState(85);
				match(T__1);
				setState(86);
				((StatContext)_localctx).e = stats();
				setState(87);
				match(T__2);
				((StatContext)_localctx).result =  new IfElse((((StatContext)_localctx).c!=null?(((StatContext)_localctx).c.start):null).getLine(), ((StatContext)_localctx).c.result, ((StatContext)_localctx).i.result, ((StatContext)_localctx).e.result);
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
			setState(95); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(92);
				((StatsContext)_localctx).stat = stat();
				_localctx.result.add(((StatsContext)_localctx).stat.result);
				}
				}
				setState(97); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__7 || _la==Str );
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
		public NumContext x;
		public IdContext y;
		public BoolContext z;
		public NumContext num() {
			return getRuleContext(NumContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
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
			setState(108);
			switch (_input.LA(1)) {
			case Int:
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				((PrimaryContext)_localctx).x = num();
				((PrimaryContext)_localctx).result =  new Primary((((PrimaryContext)_localctx).x!=null?(((PrimaryContext)_localctx).x.start):null).getLine(), ((PrimaryContext)_localctx).x.result);
				}
				break;
			case Ident:
				enterOuterAlt(_localctx, 2);
				{
				setState(102);
				((PrimaryContext)_localctx).y = id();
				((PrimaryContext)_localctx).result =  new Primary((((PrimaryContext)_localctx).y!=null?(((PrimaryContext)_localctx).y.start):null).getLine(), ((PrimaryContext)_localctx).y.result);
				}
				break;
			case True:
			case False:
				enterOuterAlt(_localctx, 3);
				{
				setState(105);
				((PrimaryContext)_localctx).z = bool();
				((PrimaryContext)_localctx).result =  new Primary((((PrimaryContext)_localctx).z!=null?(((PrimaryContext)_localctx).z.start):null).getLine(), ((PrimaryContext)_localctx).z.result);
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
		public UnExprContext value;
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
			setState(125);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				match(T__9);
				setState(111);
				((UnExprContext)_localctx).value = unExpr();
				 ((UnExprContext)_localctx).result =  new Pos((((UnExprContext)_localctx).value!=null?(((UnExprContext)_localctx).value.start):null).getLine(), ((UnExprContext)_localctx).value.result); 
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
				match(T__10);
				setState(115);
				((UnExprContext)_localctx).value = unExpr();
				 ((UnExprContext)_localctx).result =  new Neg((((UnExprContext)_localctx).value!=null?(((UnExprContext)_localctx).value.start):null).getLine(), ((UnExprContext)_localctx).value.result); 
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 3);
				{
				setState(118);
				match(T__11);
				setState(119);
				((UnExprContext)_localctx).value = unExpr();
				 ((UnExprContext)_localctx).result =  new Not((((UnExprContext)_localctx).value!=null?(((UnExprContext)_localctx).value.start):null).getLine(), ((UnExprContext)_localctx).value.result); 
				}
				break;
			case True:
			case False:
			case Ident:
			case Int:
				enterOuterAlt(_localctx, 4);
				{
				setState(122);
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
			setState(127);
			((MulExprContext)_localctx).lhs = unExpr();
			 ((MulExprContext)_localctx).result = ((MulExprContext)_localctx).lhs.result; 
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12 || _la==T__13) {
				{
				{
				setState(129);
				((MulExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__12 || _la==T__13) ) {
					((MulExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(130);
				((MulExprContext)_localctx).rhs = unExpr();
				 
				      if ((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getText():null).equals("*")) {
				        ((MulExprContext)_localctx).result =  new Mul((((MulExprContext)_localctx).lhs!=null?(((MulExprContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((MulExprContext)_localctx).rhs.result);
				      }
				      if ((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getText():null).equals("/")) {
				        ((MulExprContext)_localctx).result =  new Div((((MulExprContext)_localctx).lhs!=null?(((MulExprContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((MulExprContext)_localctx).rhs.result);
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
			setState(138);
			((AddExprContext)_localctx).lhs = mulExpr();
			 ((AddExprContext)_localctx).result = ((AddExprContext)_localctx).lhs.result; 
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9 || _la==T__10) {
				{
				{
				setState(140);
				((AddExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
					((AddExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(141);
				((AddExprContext)_localctx).rhs = mulExpr();
				 
				      if ((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getText():null).equals("+")) {
				        ((AddExprContext)_localctx).result =  new Add((((AddExprContext)_localctx).lhs!=null?(((AddExprContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((AddExprContext)_localctx).rhs.result);
				      }
				      if ((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getText():null).equals("-")) {
				        ((AddExprContext)_localctx).result =  new Sub((((AddExprContext)_localctx).lhs!=null?(((AddExprContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((AddExprContext)_localctx).rhs.result);
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
			setState(149);
			((RelExprContext)_localctx).lhs = addExpr();
			 ((RelExprContext)_localctx).result = ((RelExprContext)_localctx).lhs.result; 
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19))) != 0)) {
				{
				{
				setState(151);
				((RelExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19))) != 0)) ) {
					((RelExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(152);
				((RelExprContext)_localctx).rhs = addExpr();
				 
				      if ((((RelExprContext)_localctx).op!=null?((RelExprContext)_localctx).op.getText():null).equals("<")) {
				        ((RelExprContext)_localctx).result =  new LT((((RelExprContext)_localctx).lhs!=null?(((RelExprContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((RelExprContext)_localctx).rhs.result);
				      }
				      if ((((RelExprContext)_localctx).op!=null?((RelExprContext)_localctx).op.getText():null).equals("<=")) {
				        ((RelExprContext)_localctx).result =  new LEq((((RelExprContext)_localctx).lhs!=null?(((RelExprContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((RelExprContext)_localctx).rhs.result);
				      }
				      if ((((RelExprContext)_localctx).op!=null?((RelExprContext)_localctx).op.getText():null).equals(">")) {
				        ((RelExprContext)_localctx).result =  new GT((((RelExprContext)_localctx).lhs!=null?(((RelExprContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((RelExprContext)_localctx).rhs.result);
				      }
				      if ((((RelExprContext)_localctx).op!=null?((RelExprContext)_localctx).op.getText():null).equals(">=")) {
				        ((RelExprContext)_localctx).result =  new GEq((((RelExprContext)_localctx).lhs!=null?(((RelExprContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((RelExprContext)_localctx).rhs.result);
				      }
				      if ((((RelExprContext)_localctx).op!=null?((RelExprContext)_localctx).op.getText():null).equals("==")) {
				        ((RelExprContext)_localctx).result =  new Eq((((RelExprContext)_localctx).lhs!=null?(((RelExprContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((RelExprContext)_localctx).rhs.result);
				      }
				      if ((((RelExprContext)_localctx).op!=null?((RelExprContext)_localctx).op.getText():null).equals("!=")) {
				        ((RelExprContext)_localctx).result =  new NEq((((RelExprContext)_localctx).lhs!=null?(((RelExprContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((RelExprContext)_localctx).rhs.result);
				      }
				    
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
			setState(160);
			((AndExprContext)_localctx).lhs = relExpr();
			 ((AndExprContext)_localctx).result = ((AndExprContext)_localctx).lhs.result; 
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__20) {
				{
				{
				setState(162);
				match(T__20);
				setState(163);
				((AndExprContext)_localctx).rhs = relExpr();
				 ((AndExprContext)_localctx).result =  new And((((AndExprContext)_localctx).lhs!=null?(((AndExprContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((AndExprContext)_localctx).rhs.result); 
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
			setState(171);
			((OrExprContext)_localctx).lhs = andExpr();
			 ((OrExprContext)_localctx).result =  ((OrExprContext)_localctx).lhs.result; 
			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__21) {
				{
				{
				setState(173);
				match(T__21);
				setState(174);
				((OrExprContext)_localctx).rhs = andExpr();
				 ((OrExprContext)_localctx).result =  new Or((((OrExprContext)_localctx).lhs!=null?(((OrExprContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((OrExprContext)_localctx).rhs.result); 
				}
				}
				setState(181);
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

	public static class TypeContext extends ParserRuleContext {
		public Type result;
		public Token x;
		public TerminalNode Boolean() { return getToken(QLParser.Boolean, 0); }
		public TerminalNode Money() { return getToken(QLParser.Money, 0); }
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
		enterRule(_localctx, 26, RULE_type);
		try {
			setState(186);
			switch (_input.LA(1)) {
			case Boolean:
				enterOuterAlt(_localctx, 1);
				{
				setState(182);
				((TypeContext)_localctx).x = match(Boolean);
				((TypeContext)_localctx).result =  new Boolean(((TypeContext)_localctx).x.getLine());
				}
				break;
			case Money:
				enterOuterAlt(_localctx, 2);
				{
				setState(184);
				((TypeContext)_localctx).x = match(Money);
				((TypeContext)_localctx).result =  new Money(((TypeContext)_localctx).x.getLine());
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

	public static class BoolContext extends ParserRuleContext {
		public Val result;
		public Token value;
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
		enterRule(_localctx, 28, RULE_bool);
		try {
			setState(192);
			switch (_input.LA(1)) {
			case True:
				enterOuterAlt(_localctx, 1);
				{
				setState(188);
				((BoolContext)_localctx).value = match(True);
				((BoolContext)_localctx).result =  new Bool(((BoolContext)_localctx).value.getLine(), (((BoolContext)_localctx).value!=null?((BoolContext)_localctx).value.getText():null));
				}
				break;
			case False:
				enterOuterAlt(_localctx, 2);
				{
				setState(190);
				((BoolContext)_localctx).value = match(False);
				((BoolContext)_localctx).result =  new Bool(((BoolContext)_localctx).value.getLine(), (((BoolContext)_localctx).value!=null?((BoolContext)_localctx).value.getText():null));
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

	public static class NumContext extends ParserRuleContext {
		public Val result;
		public Token value;
		public TerminalNode Int() { return getToken(QLParser.Int, 0); }
		public NumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_num; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterNum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitNum(this);
		}
	}

	public final NumContext num() throws RecognitionException {
		NumContext _localctx = new NumContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_num);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			((NumContext)_localctx).value = match(Int);
			((NumContext)_localctx).result =  new Int(((NumContext)_localctx).value.getLine(), (((NumContext)_localctx).value!=null?((NumContext)_localctx).value.getText():null)); 
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

	public static class IdContext extends ParserRuleContext {
		public Val result;
		public Token value;
		public TerminalNode Ident() { return getToken(QLParser.Ident, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitId(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			((IdContext)_localctx).value = match(Ident);
			((IdContext)_localctx).result =  new Var(((IdContext)_localctx).value.getLine(), (((IdContext)_localctx).value!=null?((IdContext)_localctx).value.getText():null)); 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3!\u00cb\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5B\n\5\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\5\6]\n\6\3\7\3\7\3\7\6\7b\n\7\r\7\16\7c\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\5\bo\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\5\t\u0080\n\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u0088"+
		"\n\n\f\n\16\n\u008b\13\n\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u0093\n\13"+
		"\f\13\16\13\u0096\13\13\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u009e\n\f\f\f\16\f"+
		"\u00a1\13\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00a9\n\r\f\r\16\r\u00ac\13\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00b4\n\16\f\16\16\16\u00b7\13\16"+
		"\3\17\3\17\3\17\3\17\5\17\u00bd\n\17\3\20\3\20\3\20\3\20\5\20\u00c3\n"+
		"\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\2\2\23\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"\2\5\3\2\17\20\3\2\f\r\3\2\21\26\u00c9\2$\3\2\2\2\4"+
		"+\3\2\2\2\6.\3\2\2\2\bA\3\2\2\2\n\\\3\2\2\2\fa\3\2\2\2\16n\3\2\2\2\20"+
		"\177\3\2\2\2\22\u0081\3\2\2\2\24\u008c\3\2\2\2\26\u0097\3\2\2\2\30\u00a2"+
		"\3\2\2\2\32\u00ad\3\2\2\2\34\u00bc\3\2\2\2\36\u00c2\3\2\2\2 \u00c4\3\2"+
		"\2\2\"\u00c7\3\2\2\2$%\7\3\2\2%&\7\37\2\2&\'\7\4\2\2\'(\5\f\7\2()\7\5"+
		"\2\2)*\b\2\1\2*\3\3\2\2\2+,\7\37\2\2,-\b\3\1\2-\5\3\2\2\2./\7\6\2\2/\60"+
		"\7\7\2\2\60\61\5\32\16\2\61\62\7\b\2\2\62\63\b\4\1\2\63\7\3\2\2\2\64\65"+
		"\7 \2\2\65\66\5\4\3\2\66\67\7\t\2\2\678\5\34\17\289\b\5\1\29B\3\2\2\2"+
		":;\7 \2\2;<\5\4\3\2<=\7\t\2\2=>\5\34\17\2>?\5\6\4\2?@\b\5\1\2@B\3\2\2"+
		"\2A\64\3\2\2\2A:\3\2\2\2B\t\3\2\2\2CD\5\b\5\2DE\b\6\1\2E]\3\2\2\2FG\7"+
		"\n\2\2GH\7\7\2\2HI\5\32\16\2IJ\7\b\2\2JK\7\4\2\2KL\5\f\7\2LM\7\5\2\2M"+
		"N\b\6\1\2N]\3\2\2\2OP\7\n\2\2PQ\7\7\2\2QR\5\32\16\2RS\7\b\2\2ST\7\4\2"+
		"\2TU\5\f\7\2UV\7\5\2\2VW\7\13\2\2WX\7\4\2\2XY\5\f\7\2YZ\7\5\2\2Z[\b\6"+
		"\1\2[]\3\2\2\2\\C\3\2\2\2\\F\3\2\2\2\\O\3\2\2\2]\13\3\2\2\2^_\5\n\6\2"+
		"_`\b\7\1\2`b\3\2\2\2a^\3\2\2\2bc\3\2\2\2ca\3\2\2\2cd\3\2\2\2d\r\3\2\2"+
		"\2ef\5 \21\2fg\b\b\1\2go\3\2\2\2hi\5\"\22\2ij\b\b\1\2jo\3\2\2\2kl\5\36"+
		"\20\2lm\b\b\1\2mo\3\2\2\2ne\3\2\2\2nh\3\2\2\2nk\3\2\2\2o\17\3\2\2\2pq"+
		"\7\f\2\2qr\5\20\t\2rs\b\t\1\2s\u0080\3\2\2\2tu\7\r\2\2uv\5\20\t\2vw\b"+
		"\t\1\2w\u0080\3\2\2\2xy\7\16\2\2yz\5\20\t\2z{\b\t\1\2{\u0080\3\2\2\2|"+
		"}\5\16\b\2}~\b\t\1\2~\u0080\3\2\2\2\177p\3\2\2\2\177t\3\2\2\2\177x\3\2"+
		"\2\2\177|\3\2\2\2\u0080\21\3\2\2\2\u0081\u0082\5\20\t\2\u0082\u0089\b"+
		"\n\1\2\u0083\u0084\t\2\2\2\u0084\u0085\5\20\t\2\u0085\u0086\b\n\1\2\u0086"+
		"\u0088\3\2\2\2\u0087\u0083\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2"+
		"\2\2\u0089\u008a\3\2\2\2\u008a\23\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u008d"+
		"\5\22\n\2\u008d\u0094\b\13\1\2\u008e\u008f\t\3\2\2\u008f\u0090\5\22\n"+
		"\2\u0090\u0091\b\13\1\2\u0091\u0093\3\2\2\2\u0092\u008e\3\2\2\2\u0093"+
		"\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\25\3\2\2"+
		"\2\u0096\u0094\3\2\2\2\u0097\u0098\5\24\13\2\u0098\u009f\b\f\1\2\u0099"+
		"\u009a\t\4\2\2\u009a\u009b\5\24\13\2\u009b\u009c\b\f\1\2\u009c\u009e\3"+
		"\2\2\2\u009d\u0099\3\2\2\2\u009e\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u009f"+
		"\u00a0\3\2\2\2\u00a0\27\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a3\5\26\f"+
		"\2\u00a3\u00aa\b\r\1\2\u00a4\u00a5\7\27\2\2\u00a5\u00a6\5\26\f\2\u00a6"+
		"\u00a7\b\r\1\2\u00a7\u00a9\3\2\2\2\u00a8\u00a4\3\2\2\2\u00a9\u00ac\3\2"+
		"\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\31\3\2\2\2\u00ac\u00aa"+
		"\3\2\2\2\u00ad\u00ae\5\30\r\2\u00ae\u00b5\b\16\1\2\u00af\u00b0\7\30\2"+
		"\2\u00b0\u00b1\5\30\r\2\u00b1\u00b2\b\16\1\2\u00b2\u00b4\3\2\2\2\u00b3"+
		"\u00af\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2"+
		"\2\2\u00b6\33\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\u00b9\7\35\2\2\u00b9\u00bd"+
		"\b\17\1\2\u00ba\u00bb\7\36\2\2\u00bb\u00bd\b\17\1\2\u00bc\u00b8\3\2\2"+
		"\2\u00bc\u00ba\3\2\2\2\u00bd\35\3\2\2\2\u00be\u00bf\7\33\2\2\u00bf\u00c3"+
		"\b\20\1\2\u00c0\u00c1\7\34\2\2\u00c1\u00c3\b\20\1\2\u00c2\u00be\3\2\2"+
		"\2\u00c2\u00c0\3\2\2\2\u00c3\37\3\2\2\2\u00c4\u00c5\7!\2\2\u00c5\u00c6"+
		"\b\21\1\2\u00c6!\3\2\2\2\u00c7\u00c8\7\37\2\2\u00c8\u00c9\b\22\1\2\u00c9"+
		"#\3\2\2\2\16A\\cn\177\u0089\u0094\u009f\u00aa\u00b5\u00bc\u00c2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}