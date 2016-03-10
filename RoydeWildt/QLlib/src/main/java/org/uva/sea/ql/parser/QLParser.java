// Generated from /home/roy/Workspace/UvA/SC/multi-ql/RoydeWildt/QLlib/src/main/java/org/uva/sea/ql/parser/QL.g4 by ANTLR 4.5.1
package org.uva.sea.ql.parser;

import java.util.List;

import org.uva.sea.ql.ast.tree.form.*;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.type.Boolean;
import org.uva.sea.ql.ast.tree.type.Money;
import org.uva.sea.ql.ast.tree.type.Number;
import org.uva.sea.ql.ast.tree.type.Text;
import org.uva.sea.ql.ast.tree.type.Type;
import org.uva.sea.ql.ast.tree.atom.var.*;
import org.uva.sea.ql.ast.tree.atom.val.*;
import org.uva.sea.ql.ast.tree.atom.val.numeric.Float;
import org.uva.sea.ql.ast.tree.atom.val.numeric.Int;

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
		True=25, False=26, Boolean=27, Money=28, Number=29, Text=30, Ident=31, 
		Str=32, Int=33, Double=34;
	public static final int
		RULE_form = 0, RULE_varDecl = 1, RULE_varAss = 2, RULE_question = 3, RULE_stat = 4, 
		RULE_stats = 5, RULE_primary = 6, RULE_expr = 7, RULE_type = 8, RULE_bool = 9, 
		RULE_num = 10, RULE_str = 11, RULE_id = 12;
	public static final String[] ruleNames = {
		"form", "varDecl", "varAss", "question", "stat", "stats", "primary", "expr", 
		"type", "bool", "num", "str", "id"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'='", "':'", "'if'", "'('", "')'", "'else'", 
		"'+'", "'-'", "'*'", "'/'", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", 
		"'&&'", "'||'", "'!'", null, null, "'true'", "'false'", "'boolean'", "'money'", 
		"'number'", "'text'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "WHITESPACE", 
		"COMMENT", "True", "False", "Boolean", "Money", "Number", "Text", "Ident", 
		"Str", "Int", "Double"
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
			setState(26);
			match(T__0);
			setState(27);
			((FormContext)_localctx).i = match(Ident);
			setState(28);
			match(T__1);
			setState(29);
			((FormContext)_localctx).s = stats();
			setState(30);
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
			setState(33);
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
		public ExprContext x;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
			setState(36);
			match(T__3);
			setState(37);
			((VarAssContext)_localctx).x = expr(0);
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
			setState(53);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				((QuestionContext)_localctx).l = match(Str);
				setState(41);
				((QuestionContext)_localctx).v = varDecl();
				setState(42);
				match(T__4);
				setState(43);
				((QuestionContext)_localctx).t = type();
				((QuestionContext)_localctx).result =  new Question(((QuestionContext)_localctx).l.getLine(), (((QuestionContext)_localctx).l!=null?((QuestionContext)_localctx).l.getText():null), ((QuestionContext)_localctx).v.result, ((QuestionContext)_localctx).t.result);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(46);
				((QuestionContext)_localctx).l = match(Str);
				setState(47);
				((QuestionContext)_localctx).v = varDecl();
				setState(48);
				match(T__4);
				setState(49);
				((QuestionContext)_localctx).t = type();
				setState(50);
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
		public ExprContext c;
		public StatsContext s;
		public StatsContext i;
		public StatsContext e;
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
			setState(80);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				((StatContext)_localctx).q = question();
				((StatContext)_localctx).result =  ((StatContext)_localctx).q.result;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				match(T__5);
				setState(59);
				match(T__6);
				setState(60);
				((StatContext)_localctx).c = expr(0);
				setState(61);
				match(T__7);
				setState(62);
				match(T__1);
				setState(63);
				((StatContext)_localctx).s = stats();
				setState(64);
				match(T__2);
				((StatContext)_localctx).result =  new If((((StatContext)_localctx).c!=null?(((StatContext)_localctx).c.start):null).getLine(), ((StatContext)_localctx).c.result, ((StatContext)_localctx).s.result);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(67);
				match(T__5);
				setState(68);
				match(T__6);
				setState(69);
				((StatContext)_localctx).c = expr(0);
				setState(70);
				match(T__7);
				setState(71);
				match(T__1);
				setState(72);
				((StatContext)_localctx).i = stats();
				setState(73);
				match(T__2);
				setState(74);
				match(T__8);
				setState(75);
				match(T__1);
				setState(76);
				((StatContext)_localctx).e = stats();
				setState(77);
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
			setState(85); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(82);
				((StatsContext)_localctx).stat = stat();
				_localctx.result.add(((StatsContext)_localctx).stat.result);
				}
				}
				setState(87); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__5 || _la==Str );
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
			setState(101);
			switch (_input.LA(1)) {
			case Int:
			case Double:
				enterOuterAlt(_localctx, 1);
				{
				setState(89);
				((PrimaryContext)_localctx).x1 = num();
				((PrimaryContext)_localctx).result =  new Primary((((PrimaryContext)_localctx).x1!=null?(((PrimaryContext)_localctx).x1.start):null).getLine(), ((PrimaryContext)_localctx).x1.result);
				}
				break;
			case Ident:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				((PrimaryContext)_localctx).x2 = id();
				((PrimaryContext)_localctx).result =  new Primary((((PrimaryContext)_localctx).x2!=null?(((PrimaryContext)_localctx).x2.start):null).getLine(), ((PrimaryContext)_localctx).x2.result);
				}
				break;
			case Str:
				enterOuterAlt(_localctx, 3);
				{
				setState(95);
				((PrimaryContext)_localctx).x3 = str();
				((PrimaryContext)_localctx).result =  new Primary((((PrimaryContext)_localctx).x3!=null?(((PrimaryContext)_localctx).x3.start):null).getLine(), ((PrimaryContext)_localctx).x3.result);
				}
				break;
			case True:
			case False:
				enterOuterAlt(_localctx, 4);
				{
				setState(98);
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

	public static class ExprContext extends ParserRuleContext {
		public Expr result;
		public ExprContext lhs;
		public Token op;
		public ExprContext value;
		public PrimaryContext y;
		public ExprContext rhs;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			switch (_input.LA(1)) {
			case T__9:
			case T__10:
				{
				setState(104);
				((ExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
					((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(105);
				((ExprContext)_localctx).value = expr(9);

				          if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("+")) {
				            ((ExprContext)_localctx).result =  new Pos((((ExprContext)_localctx).value!=null?(((ExprContext)_localctx).value.start):null).getLine(), ((ExprContext)_localctx).value.result);
				          }
				          if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("-")) {
				            ((ExprContext)_localctx).result =  new Neg((((ExprContext)_localctx).value!=null?(((ExprContext)_localctx).value.start):null).getLine(), ((ExprContext)_localctx).value.result);
				          }
				        
				}
				break;
			case T__21:
				{
				setState(108);
				match(T__21);
				setState(109);
				((ExprContext)_localctx).value = expr(3);

				            ((ExprContext)_localctx).result =  new Not((((ExprContext)_localctx).value!=null?(((ExprContext)_localctx).value.start):null).getLine(), ((ExprContext)_localctx).value.result);
				        
				}
				break;
			case T__6:
				{
				setState(112);
				match(T__6);
				setState(113);
				((ExprContext)_localctx).value = expr(0);
				setState(114);
				match(T__7);

				            ((ExprContext)_localctx).result =  ((ExprContext)_localctx).value.result;
				        
				}
				break;
			case True:
			case False:
			case Ident:
			case Str:
			case Int:
			case Double:
				{
				setState(117);
				((ExprContext)_localctx).y = primary();

				            ((ExprContext)_localctx).result =  ((ExprContext)_localctx).y.result;
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(149);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(147);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.lhs = _prevctx;
						_localctx.lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(122);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(123);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__11 || _la==T__12) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(124);
						((ExprContext)_localctx).rhs = expr(9);

						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("*")) {
						                      ((ExprContext)_localctx).result =  new Mul((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null).getLine(), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("/")) {
						                      ((ExprContext)_localctx).result =  new Div((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null).getLine(), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                  
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.lhs = _prevctx;
						_localctx.lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(127);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(128);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__9 || _la==T__10) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(129);
						((ExprContext)_localctx).rhs = expr(8);

						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("+")) {
						                      ((ExprContext)_localctx).result =  new Add((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null).getLine(), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("-")) {
						                      ((ExprContext)_localctx).result =  new Sub((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null).getLine(), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                  
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.lhs = _prevctx;
						_localctx.lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(132);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(133);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(134);
						((ExprContext)_localctx).rhs = expr(7);

						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("<")) {
						                      ((ExprContext)_localctx).result =  new LT((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null).getLine(), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("<=")) {
						                      ((ExprContext)_localctx).result =  new LEq((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null).getLine(), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals(">")) {
						                      ((ExprContext)_localctx).result =  new GT((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null).getLine(), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals(">=")) {
						                      ((ExprContext)_localctx).result =  new GEq((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null).getLine(), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("==")) {
						                      ((ExprContext)_localctx).result =  new Eq((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null).getLine(), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("!=")) {
						                      ((ExprContext)_localctx).result =  new NEq((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null).getLine(), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                  
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.lhs = _prevctx;
						_localctx.lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(137);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(138);
						match(T__19);
						setState(139);
						((ExprContext)_localctx).rhs = expr(6);

						                      ((ExprContext)_localctx).result =  new And((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null).getLine(), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                  
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.lhs = _prevctx;
						_localctx.lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(142);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(143);
						match(T__20);
						setState(144);
						((ExprContext)_localctx).rhs = expr(5);

						                      ((ExprContext)_localctx).result =  new Or((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null).getLine(), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                  
						}
						break;
					}
					} 
				}
				setState(151);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public Type result;
		public Token x;
		public TerminalNode Boolean() { return getToken(QLParser.Boolean, 0); }
		public TerminalNode Money() { return getToken(QLParser.Money, 0); }
		public TerminalNode Number() { return getToken(QLParser.Number, 0); }
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
		enterRule(_localctx, 16, RULE_type);
		try {
			setState(160);
			switch (_input.LA(1)) {
			case Boolean:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				((TypeContext)_localctx).x = match(Boolean);
				((TypeContext)_localctx).result =  new Boolean(((TypeContext)_localctx).x.getLine());
				}
				break;
			case Money:
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				((TypeContext)_localctx).x = match(Money);
				((TypeContext)_localctx).result =  new Money(((TypeContext)_localctx).x.getLine());
				}
				break;
			case Number:
				enterOuterAlt(_localctx, 3);
				{
				setState(156);
				((TypeContext)_localctx).x = match(Number);
				((TypeContext)_localctx).result =  new Number(((TypeContext)_localctx).x.getLine());
				}
				break;
			case Text:
				enterOuterAlt(_localctx, 4);
				{
				setState(158);
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
		enterRule(_localctx, 18, RULE_bool);
		try {
			setState(166);
			switch (_input.LA(1)) {
			case True:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				((BoolContext)_localctx).value = match(True);
				((BoolContext)_localctx).result =  new Bool(((BoolContext)_localctx).value.getLine(), (((BoolContext)_localctx).value!=null?((BoolContext)_localctx).value.getText():null));
				}
				break;
			case False:
				enterOuterAlt(_localctx, 2);
				{
				setState(164);
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
		public TerminalNode Double() { return getToken(QLParser.Double, 0); }
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
		enterRule(_localctx, 20, RULE_num);
		try {
			setState(172);
			switch (_input.LA(1)) {
			case Int:
				enterOuterAlt(_localctx, 1);
				{
				setState(168);
				((NumContext)_localctx).value = match(Int);
				((NumContext)_localctx).result =  new Int(((NumContext)_localctx).value.getLine(), (((NumContext)_localctx).value!=null?((NumContext)_localctx).value.getText():null)); 
				}
				break;
			case Double:
				enterOuterAlt(_localctx, 2);
				{
				setState(170);
				((NumContext)_localctx).value = match(Double);
				((NumContext)_localctx).result =  new Float(((NumContext)_localctx).value.getLine(), (((NumContext)_localctx).value!=null?((NumContext)_localctx).value.getText():null)); 
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
		enterRule(_localctx, 22, RULE_str);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
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
		enterRule(_localctx, 24, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3$\u00b7\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5"+
		"\58\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6S\n\6\3\7\3\7\3\7\6\7X\n\7\r"+
		"\7\16\7Y\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bh\n\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t{"+
		"\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u0096\n\t\f\t\16\t\u0099\13\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00a3\n\n\3\13\3\13\3\13\3\13\5\13"+
		"\u00a9\n\13\3\f\3\f\3\f\3\f\5\f\u00af\n\f\3\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\2\3\20\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\5\3\2\f\r\3\2\16\17\3"+
		"\2\20\25\u00bd\2\34\3\2\2\2\4#\3\2\2\2\6&\3\2\2\2\b\67\3\2\2\2\nR\3\2"+
		"\2\2\fW\3\2\2\2\16g\3\2\2\2\20z\3\2\2\2\22\u00a2\3\2\2\2\24\u00a8\3\2"+
		"\2\2\26\u00ae\3\2\2\2\30\u00b0\3\2\2\2\32\u00b3\3\2\2\2\34\35\7\3\2\2"+
		"\35\36\7!\2\2\36\37\7\4\2\2\37 \5\f\7\2 !\7\5\2\2!\"\b\2\1\2\"\3\3\2\2"+
		"\2#$\7!\2\2$%\b\3\1\2%\5\3\2\2\2&\'\7\6\2\2\'(\5\20\t\2()\b\4\1\2)\7\3"+
		"\2\2\2*+\7\"\2\2+,\5\4\3\2,-\7\7\2\2-.\5\22\n\2./\b\5\1\2/8\3\2\2\2\60"+
		"\61\7\"\2\2\61\62\5\4\3\2\62\63\7\7\2\2\63\64\5\22\n\2\64\65\5\6\4\2\65"+
		"\66\b\5\1\2\668\3\2\2\2\67*\3\2\2\2\67\60\3\2\2\28\t\3\2\2\29:\5\b\5\2"+
		":;\b\6\1\2;S\3\2\2\2<=\7\b\2\2=>\7\t\2\2>?\5\20\t\2?@\7\n\2\2@A\7\4\2"+
		"\2AB\5\f\7\2BC\7\5\2\2CD\b\6\1\2DS\3\2\2\2EF\7\b\2\2FG\7\t\2\2GH\5\20"+
		"\t\2HI\7\n\2\2IJ\7\4\2\2JK\5\f\7\2KL\7\5\2\2LM\7\13\2\2MN\7\4\2\2NO\5"+
		"\f\7\2OP\7\5\2\2PQ\b\6\1\2QS\3\2\2\2R9\3\2\2\2R<\3\2\2\2RE\3\2\2\2S\13"+
		"\3\2\2\2TU\5\n\6\2UV\b\7\1\2VX\3\2\2\2WT\3\2\2\2XY\3\2\2\2YW\3\2\2\2Y"+
		"Z\3\2\2\2Z\r\3\2\2\2[\\\5\26\f\2\\]\b\b\1\2]h\3\2\2\2^_\5\32\16\2_`\b"+
		"\b\1\2`h\3\2\2\2ab\5\30\r\2bc\b\b\1\2ch\3\2\2\2de\5\24\13\2ef\b\b\1\2"+
		"fh\3\2\2\2g[\3\2\2\2g^\3\2\2\2ga\3\2\2\2gd\3\2\2\2h\17\3\2\2\2ij\b\t\1"+
		"\2jk\t\2\2\2kl\5\20\t\13lm\b\t\1\2m{\3\2\2\2no\7\30\2\2op\5\20\t\5pq\b"+
		"\t\1\2q{\3\2\2\2rs\7\t\2\2st\5\20\t\2tu\7\n\2\2uv\b\t\1\2v{\3\2\2\2wx"+
		"\5\16\b\2xy\b\t\1\2y{\3\2\2\2zi\3\2\2\2zn\3\2\2\2zr\3\2\2\2zw\3\2\2\2"+
		"{\u0097\3\2\2\2|}\f\n\2\2}~\t\3\2\2~\177\5\20\t\13\177\u0080\b\t\1\2\u0080"+
		"\u0096\3\2\2\2\u0081\u0082\f\t\2\2\u0082\u0083\t\2\2\2\u0083\u0084\5\20"+
		"\t\n\u0084\u0085\b\t\1\2\u0085\u0096\3\2\2\2\u0086\u0087\f\b\2\2\u0087"+
		"\u0088\t\4\2\2\u0088\u0089\5\20\t\t\u0089\u008a\b\t\1\2\u008a\u0096\3"+
		"\2\2\2\u008b\u008c\f\7\2\2\u008c\u008d\7\26\2\2\u008d\u008e\5\20\t\b\u008e"+
		"\u008f\b\t\1\2\u008f\u0096\3\2\2\2\u0090\u0091\f\6\2\2\u0091\u0092\7\27"+
		"\2\2\u0092\u0093\5\20\t\7\u0093\u0094\b\t\1\2\u0094\u0096\3\2\2\2\u0095"+
		"|\3\2\2\2\u0095\u0081\3\2\2\2\u0095\u0086\3\2\2\2\u0095\u008b\3\2\2\2"+
		"\u0095\u0090\3\2\2\2\u0096\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098"+
		"\3\2\2\2\u0098\21\3\2\2\2\u0099\u0097\3\2\2\2\u009a\u009b\7\35\2\2\u009b"+
		"\u00a3\b\n\1\2\u009c\u009d\7\36\2\2\u009d\u00a3\b\n\1\2\u009e\u009f\7"+
		"\37\2\2\u009f\u00a3\b\n\1\2\u00a0\u00a1\7 \2\2\u00a1\u00a3\b\n\1\2\u00a2"+
		"\u009a\3\2\2\2\u00a2\u009c\3\2\2\2\u00a2\u009e\3\2\2\2\u00a2\u00a0\3\2"+
		"\2\2\u00a3\23\3\2\2\2\u00a4\u00a5\7\33\2\2\u00a5\u00a9\b\13\1\2\u00a6"+
		"\u00a7\7\34\2\2\u00a7\u00a9\b\13\1\2\u00a8\u00a4\3\2\2\2\u00a8\u00a6\3"+
		"\2\2\2\u00a9\25\3\2\2\2\u00aa\u00ab\7#\2\2\u00ab\u00af\b\f\1\2\u00ac\u00ad"+
		"\7$\2\2\u00ad\u00af\b\f\1\2\u00ae\u00aa\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af"+
		"\27\3\2\2\2\u00b0\u00b1\7\"\2\2\u00b1\u00b2\b\r\1\2\u00b2\31\3\2\2\2\u00b3"+
		"\u00b4\7!\2\2\u00b4\u00b5\b\16\1\2\u00b5\33\3\2\2\2\f\67RYgz\u0095\u0097"+
		"\u00a2\u00a8\u00ae";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}