// Generated from /home/roy/Workspace/UvA/SC/multi-ql/RoydeWildt/QLlib/src/main/java/org/uva/sea/ql/parser/QL.g4 by ANTLR 4.5.1
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
import org.uva.sea.ql.ast.tree.type.Text;
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
		True=25, False=26, Boolean=27, Money=28, Text=29, Ident=30, Str=31, Int=32;
	public static final int
		RULE_form = 0, RULE_varDecl = 1, RULE_varAss = 2, RULE_question = 3, RULE_stat = 4, 
		RULE_stats = 5, RULE_primary = 6, RULE_unExpr = 7, RULE_mulExpr = 8, RULE_addExpr = 9, 
		RULE_relExpr = 10, RULE_andExpr = 11, RULE_orExpr = 12, RULE_type = 13, 
		RULE_bool = 14, RULE_num = 15, RULE_str = 16, RULE_id = 17;
	public static final String[] ruleNames = {
		"form", "varDecl", "varAss", "question", "stat", "stats", "primary", "unExpr", 
		"mulExpr", "addExpr", "relExpr", "andExpr", "orExpr", "type", "bool", 
		"num", "str", "id"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'='", "'('", "')'", "':'", "'if'", "'else'", 
		"'+'", "'-'", "'!'", "'*'", "'/'", "'<'", "'<='", "'>'", "'>='", "'=='", 
		"'!='", "'&&'", "'||'", null, null, "'true'", "'false'", "'boolean'", 
		"'money'", "'text'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "WHITESPACE", 
		"COMMENT", "True", "False", "Boolean", "Money", "Text", "Ident", "Str", 
		"Int"
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
			setState(36);
			match(T__0);
			setState(37);
			((FormContext)_localctx).i = match(Ident);
			setState(38);
			match(T__1);
			setState(39);
			((FormContext)_localctx).s = stats();
			setState(40);
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
			setState(43);
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
			setState(46);
			match(T__3);
			setState(47);
			match(T__4);
			setState(48);
			((VarAssContext)_localctx).x = orExpr();
			setState(49);
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
			setState(65);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				((QuestionContext)_localctx).l = match(Str);
				setState(53);
				((QuestionContext)_localctx).v = varDecl();
				setState(54);
				match(T__6);
				setState(55);
				((QuestionContext)_localctx).t = type();
				((QuestionContext)_localctx).result =  new Question(((QuestionContext)_localctx).l.getLine(), (((QuestionContext)_localctx).l!=null?((QuestionContext)_localctx).l.getText():null), ((QuestionContext)_localctx).v.result, ((QuestionContext)_localctx).t.result);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				((QuestionContext)_localctx).l = match(Str);
				setState(59);
				((QuestionContext)_localctx).v = varDecl();
				setState(60);
				match(T__6);
				setState(61);
				((QuestionContext)_localctx).t = type();
				setState(62);
				((QuestionContext)_localctx).e = varAss();
				((QuestionContext)_localctx).result =  new Question(((QuestionContext)_localctx).l.getLine(), (((QuestionContext)_localctx).l!=null?((QuestionContext)_localctx).l.getText():null), ((QuestionContext)_localctx).v.result, ((QuestionContext)_localctx).t.result, ((QuestionContext)_localctx).e.result);
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
			setState(92);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				((StatContext)_localctx).q = question();
				((StatContext)_localctx).result =  ((StatContext)_localctx).q.result;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				match(T__7);
				setState(71);
				match(T__4);
				setState(72);
				((StatContext)_localctx).c = orExpr();
				setState(73);
				match(T__5);
				setState(74);
				match(T__1);
				setState(75);
				((StatContext)_localctx).s = stats();
				setState(76);
				match(T__2);
				((StatContext)_localctx).result =  new If((((StatContext)_localctx).c!=null?(((StatContext)_localctx).c.start):null).getLine(), ((StatContext)_localctx).c.result, ((StatContext)_localctx).s.result);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(79);
				match(T__7);
				setState(80);
				match(T__4);
				setState(81);
				((StatContext)_localctx).c = orExpr();
				setState(82);
				match(T__5);
				setState(83);
				match(T__1);
				setState(84);
				((StatContext)_localctx).i = stats();
				setState(85);
				match(T__2);
				setState(86);
				match(T__8);
				setState(87);
				match(T__1);
				setState(88);
				((StatContext)_localctx).e = stats();
				setState(89);
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
			setState(97); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(94);
				((StatsContext)_localctx).stat = stat();
				_localctx.result.add(((StatsContext)_localctx).stat.result);
				}
				}
				setState(99); 
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
		public NumContext x1;
		public IdContext x2;
		public StrContext x3;
		public BoolContext x4;
		public NumContext num() {
			return getRuleContext(NumContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public StrContext str() {
			return getRuleContext(StrContext.class,0);
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
			setState(113);
			switch (_input.LA(1)) {
			case Int:
				enterOuterAlt(_localctx, 1);
				{
				setState(101);
				((PrimaryContext)_localctx).x1 = num();
				((PrimaryContext)_localctx).result =  new Primary((((PrimaryContext)_localctx).x1!=null?(((PrimaryContext)_localctx).x1.start):null).getLine(), ((PrimaryContext)_localctx).x1.result);
				}
				break;
			case Ident:
				enterOuterAlt(_localctx, 2);
				{
				setState(104);
				((PrimaryContext)_localctx).x2 = id();
				((PrimaryContext)_localctx).result =  new Primary((((PrimaryContext)_localctx).x2!=null?(((PrimaryContext)_localctx).x2.start):null).getLine(), ((PrimaryContext)_localctx).x2.result);
				}
				break;
			case Str:
				enterOuterAlt(_localctx, 3);
				{
				setState(107);
				((PrimaryContext)_localctx).x3 = str();
				((PrimaryContext)_localctx).result =  new Primary((((PrimaryContext)_localctx).x3!=null?(((PrimaryContext)_localctx).x3.start):null).getLine(), ((PrimaryContext)_localctx).x3.result);
				}
				break;
			case True:
			case False:
				enterOuterAlt(_localctx, 4);
				{
				setState(110);
				((PrimaryContext)_localctx).x4 = bool();
				((PrimaryContext)_localctx).result =  new Primary((((PrimaryContext)_localctx).x4!=null?(((PrimaryContext)_localctx).x4.start):null).getLine(), ((PrimaryContext)_localctx).x4.result);
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
			setState(130);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(115);
				match(T__9);
				setState(116);
				((UnExprContext)_localctx).value = unExpr();
				 ((UnExprContext)_localctx).result =  new Pos((((UnExprContext)_localctx).value!=null?(((UnExprContext)_localctx).value.start):null).getLine(), ((UnExprContext)_localctx).value.result); 
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(119);
				match(T__10);
				setState(120);
				((UnExprContext)_localctx).value = unExpr();
				 ((UnExprContext)_localctx).result =  new Neg((((UnExprContext)_localctx).value!=null?(((UnExprContext)_localctx).value.start):null).getLine(), ((UnExprContext)_localctx).value.result); 
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 3);
				{
				setState(123);
				match(T__11);
				setState(124);
				((UnExprContext)_localctx).value = unExpr();
				 ((UnExprContext)_localctx).result =  new Not((((UnExprContext)_localctx).value!=null?(((UnExprContext)_localctx).value.start):null).getLine(), ((UnExprContext)_localctx).value.result); 
				}
				break;
			case True:
			case False:
			case Ident:
			case Str:
			case Int:
				enterOuterAlt(_localctx, 4);
				{
				setState(127);
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
			setState(132);
			((MulExprContext)_localctx).lhs = unExpr();
			 ((MulExprContext)_localctx).result = ((MulExprContext)_localctx).lhs.result; 
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12 || _la==T__13) {
				{
				{
				setState(134);
				((MulExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__12 || _la==T__13) ) {
					((MulExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(135);
				((MulExprContext)_localctx).rhs = unExpr();
				 
				      if ((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getText():null).equals("*")) {
				        ((MulExprContext)_localctx).result =  new Mul((((MulExprContext)_localctx).lhs!=null?(((MulExprContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((MulExprContext)_localctx).rhs.result);
				      }
				      if ((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getText():null).equals("/")) {
				        ((MulExprContext)_localctx).result =  new Div((((MulExprContext)_localctx).lhs!=null?(((MulExprContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((MulExprContext)_localctx).rhs.result);
				      }
				    
				}
				}
				setState(142);
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
			setState(143);
			((AddExprContext)_localctx).lhs = mulExpr();
			 ((AddExprContext)_localctx).result = ((AddExprContext)_localctx).lhs.result; 
			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9 || _la==T__10) {
				{
				{
				setState(145);
				((AddExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
					((AddExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(146);
				((AddExprContext)_localctx).rhs = mulExpr();
				 
				      if ((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getText():null).equals("+")) {
				        ((AddExprContext)_localctx).result =  new Add((((AddExprContext)_localctx).lhs!=null?(((AddExprContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((AddExprContext)_localctx).rhs.result);
				      }
				      if ((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getText():null).equals("-")) {
				        ((AddExprContext)_localctx).result =  new Sub((((AddExprContext)_localctx).lhs!=null?(((AddExprContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((AddExprContext)_localctx).rhs.result);
				      }
				    
				}
				}
				setState(153);
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
			setState(154);
			((RelExprContext)_localctx).lhs = addExpr();
			 ((RelExprContext)_localctx).result = ((RelExprContext)_localctx).lhs.result; 
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19))) != 0)) {
				{
				{
				setState(156);
				((RelExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19))) != 0)) ) {
					((RelExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(157);
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
				setState(164);
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
			setState(165);
			((AndExprContext)_localctx).lhs = relExpr();
			 ((AndExprContext)_localctx).result = ((AndExprContext)_localctx).lhs.result; 
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__20) {
				{
				{
				setState(167);
				match(T__20);
				setState(168);
				((AndExprContext)_localctx).rhs = relExpr();
				 ((AndExprContext)_localctx).result =  new And((((AndExprContext)_localctx).lhs!=null?(((AndExprContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((AndExprContext)_localctx).rhs.result); 
				}
				}
				setState(175);
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
			setState(176);
			((OrExprContext)_localctx).lhs = andExpr();
			 ((OrExprContext)_localctx).result =  ((OrExprContext)_localctx).lhs.result; 
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__21) {
				{
				{
				setState(178);
				match(T__21);
				setState(179);
				((OrExprContext)_localctx).rhs = andExpr();
				 ((OrExprContext)_localctx).result =  new Or((((OrExprContext)_localctx).lhs!=null?(((OrExprContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((OrExprContext)_localctx).rhs.result); 
				}
				}
				setState(186);
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
		public TerminalNode Text() { return getToken(QLParser.Text, 0); }
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
			setState(193);
			switch (_input.LA(1)) {
			case Boolean:
				enterOuterAlt(_localctx, 1);
				{
				setState(187);
				((TypeContext)_localctx).x = match(Boolean);
				((TypeContext)_localctx).result =  new Boolean(((TypeContext)_localctx).x.getLine());
				}
				break;
			case Money:
				enterOuterAlt(_localctx, 2);
				{
				setState(189);
				((TypeContext)_localctx).x = match(Money);
				((TypeContext)_localctx).result =  new Money(((TypeContext)_localctx).x.getLine());
				}
				break;
			case Text:
				enterOuterAlt(_localctx, 3);
				{
				setState(191);
				((TypeContext)_localctx).x = match(Text);
				((TypeContext)_localctx).result =  new Text(((TypeContext)_localctx).x.getLine());
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
			setState(199);
			switch (_input.LA(1)) {
			case True:
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				((BoolContext)_localctx).value = match(True);
				((BoolContext)_localctx).result =  new Bool(((BoolContext)_localctx).value.getLine(), (((BoolContext)_localctx).value!=null?((BoolContext)_localctx).value.getText():null));
				}
				break;
			case False:
				enterOuterAlt(_localctx, 2);
				{
				setState(197);
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
			setState(201);
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

	public static class StrContext extends ParserRuleContext {
		public Val result;
		public Token value;
		public TerminalNode Str() { return getToken(QLParser.Str, 0); }
		public StrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_str; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterStr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitStr(this);
		}
	}

	public final StrContext str() throws RecognitionException {
		StrContext _localctx = new StrContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_str);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			((StrContext)_localctx).value = match(Str);
			((StrContext)_localctx).result =  new Str(((StrContext)_localctx).value.getLine(), (((StrContext)_localctx).value!=null?((StrContext)_localctx).value.getText():null)); 
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
		public Var result;
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
		enterRule(_localctx, 34, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\"\u00d5\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5D\n\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6_\n\6\3\7\3\7\3\7\6\7d\n\7\r\7\16\7e\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bt\n\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0085\n\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\7\n\u008d\n\n\f\n\16\n\u0090\13\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\7\13\u0098\n\13\f\13\16\13\u009b\13\13\3\f\3\f\3\f\3\f\3\f\3\f\7"+
		"\f\u00a3\n\f\f\f\16\f\u00a6\13\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00ae\n\r"+
		"\f\r\16\r\u00b1\13\r\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00b9\n\16\f\16"+
		"\16\16\u00bc\13\16\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00c4\n\17\3\20"+
		"\3\20\3\20\3\20\5\20\u00ca\n\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\2\2\24\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$\2\5\3\2\17"+
		"\20\3\2\f\r\3\2\21\26\u00d4\2&\3\2\2\2\4-\3\2\2\2\6\60\3\2\2\2\bC\3\2"+
		"\2\2\n^\3\2\2\2\fc\3\2\2\2\16s\3\2\2\2\20\u0084\3\2\2\2\22\u0086\3\2\2"+
		"\2\24\u0091\3\2\2\2\26\u009c\3\2\2\2\30\u00a7\3\2\2\2\32\u00b2\3\2\2\2"+
		"\34\u00c3\3\2\2\2\36\u00c9\3\2\2\2 \u00cb\3\2\2\2\"\u00ce\3\2\2\2$\u00d1"+
		"\3\2\2\2&\'\7\3\2\2\'(\7 \2\2()\7\4\2\2)*\5\f\7\2*+\7\5\2\2+,\b\2\1\2"+
		",\3\3\2\2\2-.\7 \2\2./\b\3\1\2/\5\3\2\2\2\60\61\7\6\2\2\61\62\7\7\2\2"+
		"\62\63\5\32\16\2\63\64\7\b\2\2\64\65\b\4\1\2\65\7\3\2\2\2\66\67\7!\2\2"+
		"\678\5\4\3\289\7\t\2\29:\5\34\17\2:;\b\5\1\2;D\3\2\2\2<=\7!\2\2=>\5\4"+
		"\3\2>?\7\t\2\2?@\5\34\17\2@A\5\6\4\2AB\b\5\1\2BD\3\2\2\2C\66\3\2\2\2C"+
		"<\3\2\2\2D\t\3\2\2\2EF\5\b\5\2FG\b\6\1\2G_\3\2\2\2HI\7\n\2\2IJ\7\7\2\2"+
		"JK\5\32\16\2KL\7\b\2\2LM\7\4\2\2MN\5\f\7\2NO\7\5\2\2OP\b\6\1\2P_\3\2\2"+
		"\2QR\7\n\2\2RS\7\7\2\2ST\5\32\16\2TU\7\b\2\2UV\7\4\2\2VW\5\f\7\2WX\7\5"+
		"\2\2XY\7\13\2\2YZ\7\4\2\2Z[\5\f\7\2[\\\7\5\2\2\\]\b\6\1\2]_\3\2\2\2^E"+
		"\3\2\2\2^H\3\2\2\2^Q\3\2\2\2_\13\3\2\2\2`a\5\n\6\2ab\b\7\1\2bd\3\2\2\2"+
		"c`\3\2\2\2de\3\2\2\2ec\3\2\2\2ef\3\2\2\2f\r\3\2\2\2gh\5 \21\2hi\b\b\1"+
		"\2it\3\2\2\2jk\5$\23\2kl\b\b\1\2lt\3\2\2\2mn\5\"\22\2no\b\b\1\2ot\3\2"+
		"\2\2pq\5\36\20\2qr\b\b\1\2rt\3\2\2\2sg\3\2\2\2sj\3\2\2\2sm\3\2\2\2sp\3"+
		"\2\2\2t\17\3\2\2\2uv\7\f\2\2vw\5\20\t\2wx\b\t\1\2x\u0085\3\2\2\2yz\7\r"+
		"\2\2z{\5\20\t\2{|\b\t\1\2|\u0085\3\2\2\2}~\7\16\2\2~\177\5\20\t\2\177"+
		"\u0080\b\t\1\2\u0080\u0085\3\2\2\2\u0081\u0082\5\16\b\2\u0082\u0083\b"+
		"\t\1\2\u0083\u0085\3\2\2\2\u0084u\3\2\2\2\u0084y\3\2\2\2\u0084}\3\2\2"+
		"\2\u0084\u0081\3\2\2\2\u0085\21\3\2\2\2\u0086\u0087\5\20\t\2\u0087\u008e"+
		"\b\n\1\2\u0088\u0089\t\2\2\2\u0089\u008a\5\20\t\2\u008a\u008b\b\n\1\2"+
		"\u008b\u008d\3\2\2\2\u008c\u0088\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008c"+
		"\3\2\2\2\u008e\u008f\3\2\2\2\u008f\23\3\2\2\2\u0090\u008e\3\2\2\2\u0091"+
		"\u0092\5\22\n\2\u0092\u0099\b\13\1\2\u0093\u0094\t\3\2\2\u0094\u0095\5"+
		"\22\n\2\u0095\u0096\b\13\1\2\u0096\u0098\3\2\2\2\u0097\u0093\3\2\2\2\u0098"+
		"\u009b\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\25\3\2\2"+
		"\2\u009b\u0099\3\2\2\2\u009c\u009d\5\24\13\2\u009d\u00a4\b\f\1\2\u009e"+
		"\u009f\t\4\2\2\u009f\u00a0\5\24\13\2\u00a0\u00a1\b\f\1\2\u00a1\u00a3\3"+
		"\2\2\2\u00a2\u009e\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a5\27\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00a8\5\26\f"+
		"\2\u00a8\u00af\b\r\1\2\u00a9\u00aa\7\27\2\2\u00aa\u00ab\5\26\f\2\u00ab"+
		"\u00ac\b\r\1\2\u00ac\u00ae\3\2\2\2\u00ad\u00a9\3\2\2\2\u00ae\u00b1\3\2"+
		"\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\31\3\2\2\2\u00b1\u00af"+
		"\3\2\2\2\u00b2\u00b3\5\30\r\2\u00b3\u00ba\b\16\1\2\u00b4\u00b5\7\30\2"+
		"\2\u00b5\u00b6\5\30\r\2\u00b6\u00b7\b\16\1\2\u00b7\u00b9\3\2\2\2\u00b8"+
		"\u00b4\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2"+
		"\2\2\u00bb\33\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00be\7\35\2\2\u00be\u00c4"+
		"\b\17\1\2\u00bf\u00c0\7\36\2\2\u00c0\u00c4\b\17\1\2\u00c1\u00c2\7\37\2"+
		"\2\u00c2\u00c4\b\17\1\2\u00c3\u00bd\3\2\2\2\u00c3\u00bf\3\2\2\2\u00c3"+
		"\u00c1\3\2\2\2\u00c4\35\3\2\2\2\u00c5\u00c6\7\33\2\2\u00c6\u00ca\b\20"+
		"\1\2\u00c7\u00c8\7\34\2\2\u00c8\u00ca\b\20\1\2\u00c9\u00c5\3\2\2\2\u00c9"+
		"\u00c7\3\2\2\2\u00ca\37\3\2\2\2\u00cb\u00cc\7\"\2\2\u00cc\u00cd\b\21\1"+
		"\2\u00cd!\3\2\2\2\u00ce\u00cf\7!\2\2\u00cf\u00d0\b\22\1\2\u00d0#\3\2\2"+
		"\2\u00d1\u00d2\7 \2\2\u00d2\u00d3\b\23\1\2\u00d3%\3\2\2\2\16C^es\u0084"+
		"\u008e\u0099\u00a4\u00af\u00ba\u00c3\u00c9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}