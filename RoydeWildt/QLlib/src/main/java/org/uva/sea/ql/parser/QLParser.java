// Generated from /home/roy/Workspace/SC/multi-ql/RoydeWildt/QLlib/src/main/java/org/uva/sea/ql/parser/QL.g4 by ANTLR 4.5.1
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
    import org.uva.sea.ql.ast.tree.atom.val.Float;
    import org.uva.sea.ql.ast.tree.atom.val.Int;

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
		TRUE=25, FALSE=26, BOOLEAN=27, MONEY=28, NUMBER=29, TEXT=30, IDENT=31, 
		STR=32, INT=33, FLOAT=34;
	public static final int
		RULE_form = 0, RULE_stats = 1, RULE_stat = 2, RULE_question = 3, RULE_expr = 4, 
		RULE_primary = 5, RULE_type = 6, RULE_bool = 7, RULE_num = 8, RULE_str = 9, 
		RULE_id = 10;
	public static final String[] ruleNames = {
		"form", "stats", "stat", "question", "expr", "primary", "type", "bool", 
		"num", "str", "id"
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
		public TerminalNode IDENT() { return getToken(QLParser.IDENT, 0); }
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
			setState(22);
			match(T__0);
			setState(23);
			((FormContext)_localctx).i = match(IDENT);
			setState(24);
			match(T__1);
			setState(25);
			((FormContext)_localctx).s = stats();
			setState(26);
			match(T__2);

			            ((FormContext)_localctx).result =  new Form(((FormContext)_localctx).i, (((FormContext)_localctx).i!=null?((FormContext)_localctx).i.getText():null), ((FormContext)_localctx).s.result);
			        
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
		public StatContext s;
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
		enterRule(_localctx, 2, RULE_stats);

		            ((StatsContext)_localctx).result =  new ArrayList<Stat>();
		        
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(29);
				((StatsContext)_localctx).s = stat();

				            _localctx.result.add(((StatsContext)_localctx).s.result);
				        
				}
				}
				setState(34); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 || _la==STR );
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
		enterRule(_localctx, 4, RULE_stat);
		try {
			setState(61);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				((StatContext)_localctx).q = question();

				            ((StatContext)_localctx).result =  ((StatContext)_localctx).q.result;
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				match(T__3);
				setState(40);
				match(T__4);
				setState(41);
				((StatContext)_localctx).c = expr(0);
				setState(42);
				match(T__5);
				setState(43);
				match(T__1);
				setState(44);
				((StatContext)_localctx).s = stats();
				setState(45);
				match(T__2);

				            ((StatContext)_localctx).result =  new If((((StatContext)_localctx).c!=null?(((StatContext)_localctx).c.start):null), ((StatContext)_localctx).c.result, ((StatContext)_localctx).s.result);
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(48);
				match(T__3);
				setState(49);
				match(T__4);
				setState(50);
				((StatContext)_localctx).c = expr(0);
				setState(51);
				match(T__5);
				setState(52);
				match(T__1);
				setState(53);
				((StatContext)_localctx).i = stats();
				setState(54);
				match(T__2);
				setState(55);
				match(T__6);
				setState(56);
				match(T__1);
				setState(57);
				((StatContext)_localctx).e = stats();
				setState(58);
				match(T__2);

				            ((StatContext)_localctx).result =  new IfElse((((StatContext)_localctx).c!=null?(((StatContext)_localctx).c.start):null), ((StatContext)_localctx).c.result, ((StatContext)_localctx).i.result, ((StatContext)_localctx).e.result);
				        
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

	public static class QuestionContext extends ParserRuleContext {
		public Stat result;
		public Token label;
		public Token decl;
		public TypeContext typ;
		public ExprContext exp;
		public TerminalNode STR() { return getToken(QLParser.STR, 0); }
		public TerminalNode IDENT() { return getToken(QLParser.IDENT, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
			setState(77);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				((QuestionContext)_localctx).label = match(STR);
				setState(64);
				((QuestionContext)_localctx).decl = match(IDENT);
				setState(65);
				match(T__7);
				setState(66);
				((QuestionContext)_localctx).typ = type();

				            ((QuestionContext)_localctx).result =  new Question(((QuestionContext)_localctx).label, (((QuestionContext)_localctx).label!=null?((QuestionContext)_localctx).label.getText():null), new Var(((QuestionContext)_localctx).decl, (((QuestionContext)_localctx).decl!=null?((QuestionContext)_localctx).decl.getText():null)), ((QuestionContext)_localctx).typ.result);
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				((QuestionContext)_localctx).label = match(STR);
				setState(70);
				((QuestionContext)_localctx).decl = match(IDENT);
				setState(71);
				match(T__7);
				setState(72);
				((QuestionContext)_localctx).typ = type();
				setState(73);
				match(T__8);
				setState(74);
				((QuestionContext)_localctx).exp = expr(0);

				            ((QuestionContext)_localctx).result =  new Question(((QuestionContext)_localctx).label, (((QuestionContext)_localctx).label!=null?((QuestionContext)_localctx).label.getText():null), new Var(((QuestionContext)_localctx).decl, (((QuestionContext)_localctx).decl!=null?((QuestionContext)_localctx).decl.getText():null)), ((QuestionContext)_localctx).typ.result, ((QuestionContext)_localctx).exp.result);
				        
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
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			switch (_input.LA(1)) {
			case T__9:
			case T__10:
				{
				setState(80);
				((ExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
					((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(81);
				((ExprContext)_localctx).value = expr(9);

				          if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("+")) {
				            ((ExprContext)_localctx).result =  new Pos((((ExprContext)_localctx).value!=null?(((ExprContext)_localctx).value.start):null), ((ExprContext)_localctx).value.result);
				          }
				          if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("-")) {
				            ((ExprContext)_localctx).result =  new Neg((((ExprContext)_localctx).value!=null?(((ExprContext)_localctx).value.start):null), ((ExprContext)_localctx).value.result);
				          }
				        
				}
				break;
			case T__21:
				{
				setState(84);
				match(T__21);
				setState(85);
				((ExprContext)_localctx).value = expr(3);

				            ((ExprContext)_localctx).result =  new Not((((ExprContext)_localctx).value!=null?(((ExprContext)_localctx).value.start):null), ((ExprContext)_localctx).value.result);
				        
				}
				break;
			case T__4:
				{
				setState(88);
				match(T__4);
				setState(89);
				((ExprContext)_localctx).value = expr(0);
				setState(90);
				match(T__5);

				            ((ExprContext)_localctx).result =  ((ExprContext)_localctx).value.result;
				        
				}
				break;
			case TRUE:
			case FALSE:
			case IDENT:
			case STR:
			case INT:
			case FLOAT:
				{
				setState(93);
				((ExprContext)_localctx).y = primary();

				            ((ExprContext)_localctx).result =  ((ExprContext)_localctx).y.result;
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(125);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(123);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.lhs = _prevctx;
						_localctx.lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(98);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(99);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__11 || _la==T__12) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(100);
						((ExprContext)_localctx).rhs = expr(9);

						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("*")) {
						                      ((ExprContext)_localctx).result =  new Mul((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("/")) {
						                      ((ExprContext)_localctx).result =  new Div((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                  
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.lhs = _prevctx;
						_localctx.lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(103);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(104);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__9 || _la==T__10) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(105);
						((ExprContext)_localctx).rhs = expr(8);

						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("+")) {
						                      ((ExprContext)_localctx).result =  new Add((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("-")) {
						                      ((ExprContext)_localctx).result =  new Sub((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                  
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.lhs = _prevctx;
						_localctx.lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(108);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(109);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(110);
						((ExprContext)_localctx).rhs = expr(7);

						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("<")) {
						                      ((ExprContext)_localctx).result =  new LT((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("<=")) {
						                      ((ExprContext)_localctx).result =  new LEq((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals(">")) {
						                      ((ExprContext)_localctx).result =  new GT((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals(">=")) {
						                      ((ExprContext)_localctx).result =  new GEq((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("==")) {
						                      ((ExprContext)_localctx).result =  new Eq((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                    if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("!=")) {
						                      ((ExprContext)_localctx).result =  new NEq((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                    }
						                  
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.lhs = _prevctx;
						_localctx.lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(113);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(114);
						match(T__19);
						setState(115);
						((ExprContext)_localctx).rhs = expr(6);

						                      ((ExprContext)_localctx).result =  new And((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                  
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.lhs = _prevctx;
						_localctx.lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(118);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(119);
						match(T__20);
						setState(120);
						((ExprContext)_localctx).rhs = expr(5);

						                      ((ExprContext)_localctx).result =  new Or((((ExprContext)_localctx).lhs!=null?(((ExprContext)_localctx).lhs.start):null), ((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result);
						                  
						}
						break;
					}
					} 
				}
				setState(127);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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
		enterRule(_localctx, 10, RULE_primary);
		try {
			setState(140);
			switch (_input.LA(1)) {
			case INT:
			case FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				((PrimaryContext)_localctx).x1 = num();

				            ((PrimaryContext)_localctx).result =  new Primary((((PrimaryContext)_localctx).x1!=null?(((PrimaryContext)_localctx).x1.start):null), ((PrimaryContext)_localctx).x1.result);
				        
				}
				break;
			case IDENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(131);
				((PrimaryContext)_localctx).x2 = id();

				            ((PrimaryContext)_localctx).result =  new Primary((((PrimaryContext)_localctx).x2!=null?(((PrimaryContext)_localctx).x2.start):null), ((PrimaryContext)_localctx).x2.result);
				        
				}
				break;
			case STR:
				enterOuterAlt(_localctx, 3);
				{
				setState(134);
				((PrimaryContext)_localctx).x3 = str();

				            ((PrimaryContext)_localctx).result =  new Primary((((PrimaryContext)_localctx).x3!=null?(((PrimaryContext)_localctx).x3.start):null), ((PrimaryContext)_localctx).x3.result);
				        
				}
				break;
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 4);
				{
				setState(137);
				((PrimaryContext)_localctx).x4 = bool();

				            ((PrimaryContext)_localctx).result =  new Primary((((PrimaryContext)_localctx).x4!=null?(((PrimaryContext)_localctx).x4.start):null), ((PrimaryContext)_localctx).x4.result);
				        
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

	public static class TypeContext extends ParserRuleContext {
		public Type result;
		public Token x;
		public TerminalNode BOOLEAN() { return getToken(QLParser.BOOLEAN, 0); }
		public TerminalNode MONEY() { return getToken(QLParser.MONEY, 0); }
		public TerminalNode NUMBER() { return getToken(QLParser.NUMBER, 0); }
		public TerminalNode TEXT() { return getToken(QLParser.TEXT, 0); }
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
		enterRule(_localctx, 12, RULE_type);
		try {
			setState(150);
			switch (_input.LA(1)) {
			case BOOLEAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(142);
				((TypeContext)_localctx).x = match(BOOLEAN);

				            ((TypeContext)_localctx).result =  new Boolean(((TypeContext)_localctx).x);
				        
				}
				break;
			case MONEY:
				enterOuterAlt(_localctx, 2);
				{
				setState(144);
				((TypeContext)_localctx).x = match(MONEY);

				            ((TypeContext)_localctx).result =  new Money(((TypeContext)_localctx).x);
				        
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 3);
				{
				setState(146);
				((TypeContext)_localctx).x = match(NUMBER);

				            ((TypeContext)_localctx).result =  new Number(((TypeContext)_localctx).x);
				        
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 4);
				{
				setState(148);
				((TypeContext)_localctx).x = match(TEXT);

				            ((TypeContext)_localctx).result =  new Text(((TypeContext)_localctx).x);
				        
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
		public TerminalNode TRUE() { return getToken(QLParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(QLParser.FALSE, 0); }
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
		enterRule(_localctx, 14, RULE_bool);
		try {
			setState(156);
			switch (_input.LA(1)) {
			case TRUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				((BoolContext)_localctx).value = match(TRUE);

				            ((BoolContext)_localctx).result =  new Bool(((BoolContext)_localctx).value, (((BoolContext)_localctx).value!=null?((BoolContext)_localctx).value.getText():null));
				        
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				((BoolContext)_localctx).value = match(FALSE);

				            ((BoolContext)_localctx).result =  new Bool(((BoolContext)_localctx).value, (((BoolContext)_localctx).value!=null?((BoolContext)_localctx).value.getText():null));
				        
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
		public TerminalNode INT() { return getToken(QLParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(QLParser.FLOAT, 0); }
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
		enterRule(_localctx, 16, RULE_num);
		try {
			setState(162);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				((NumContext)_localctx).value = match(INT);

				            ((NumContext)_localctx).result =  new Int(((NumContext)_localctx).value, (((NumContext)_localctx).value!=null?((NumContext)_localctx).value.getText():null));
				        
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(160);
				((NumContext)_localctx).value = match(FLOAT);

				            ((NumContext)_localctx).result =  new Float(((NumContext)_localctx).value, (((NumContext)_localctx).value!=null?((NumContext)_localctx).value.getText():null));
				        
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
		public TerminalNode STR() { return getToken(QLParser.STR, 0); }
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
		enterRule(_localctx, 18, RULE_str);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			((StrContext)_localctx).value = match(STR);

			            ((StrContext)_localctx).result =  new Str(((StrContext)_localctx).value, (((StrContext)_localctx).value!=null?((StrContext)_localctx).value.getText():null));
			        
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
		public TerminalNode IDENT() { return getToken(QLParser.IDENT, 0); }
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
		enterRule(_localctx, 20, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			((IdContext)_localctx).value = match(IDENT);

			            ((IdContext)_localctx).result =  new Var(((IdContext)_localctx).value, (((IdContext)_localctx).value!=null?((IdContext)_localctx).value.getText():null));
			        
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
		case 4:
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3$\u00ad\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\6\3#\n\3\r\3\16\3"+
		"$\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4@\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5P\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6c\n\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\7\6~\n\6\f\6\16\6\u0081\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\5\7\u008f\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0099"+
		"\n\b\3\t\3\t\3\t\3\t\5\t\u009f\n\t\3\n\3\n\3\n\3\n\5\n\u00a5\n\n\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\2\3\n\r\2\4\6\b\n\f\16\20\22\24\26\2\5\3\2"+
		"\f\r\3\2\16\17\3\2\20\25\u00b5\2\30\3\2\2\2\4\"\3\2\2\2\6?\3\2\2\2\bO"+
		"\3\2\2\2\nb\3\2\2\2\f\u008e\3\2\2\2\16\u0098\3\2\2\2\20\u009e\3\2\2\2"+
		"\22\u00a4\3\2\2\2\24\u00a6\3\2\2\2\26\u00a9\3\2\2\2\30\31\7\3\2\2\31\32"+
		"\7!\2\2\32\33\7\4\2\2\33\34\5\4\3\2\34\35\7\5\2\2\35\36\b\2\1\2\36\3\3"+
		"\2\2\2\37 \5\6\4\2 !\b\3\1\2!#\3\2\2\2\"\37\3\2\2\2#$\3\2\2\2$\"\3\2\2"+
		"\2$%\3\2\2\2%\5\3\2\2\2&\'\5\b\5\2\'(\b\4\1\2(@\3\2\2\2)*\7\6\2\2*+\7"+
		"\7\2\2+,\5\n\6\2,-\7\b\2\2-.\7\4\2\2./\5\4\3\2/\60\7\5\2\2\60\61\b\4\1"+
		"\2\61@\3\2\2\2\62\63\7\6\2\2\63\64\7\7\2\2\64\65\5\n\6\2\65\66\7\b\2\2"+
		"\66\67\7\4\2\2\678\5\4\3\289\7\5\2\29:\7\t\2\2:;\7\4\2\2;<\5\4\3\2<=\7"+
		"\5\2\2=>\b\4\1\2>@\3\2\2\2?&\3\2\2\2?)\3\2\2\2?\62\3\2\2\2@\7\3\2\2\2"+
		"AB\7\"\2\2BC\7!\2\2CD\7\n\2\2DE\5\16\b\2EF\b\5\1\2FP\3\2\2\2GH\7\"\2\2"+
		"HI\7!\2\2IJ\7\n\2\2JK\5\16\b\2KL\7\13\2\2LM\5\n\6\2MN\b\5\1\2NP\3\2\2"+
		"\2OA\3\2\2\2OG\3\2\2\2P\t\3\2\2\2QR\b\6\1\2RS\t\2\2\2ST\5\n\6\13TU\b\6"+
		"\1\2Uc\3\2\2\2VW\7\30\2\2WX\5\n\6\5XY\b\6\1\2Yc\3\2\2\2Z[\7\7\2\2[\\\5"+
		"\n\6\2\\]\7\b\2\2]^\b\6\1\2^c\3\2\2\2_`\5\f\7\2`a\b\6\1\2ac\3\2\2\2bQ"+
		"\3\2\2\2bV\3\2\2\2bZ\3\2\2\2b_\3\2\2\2c\177\3\2\2\2de\f\n\2\2ef\t\3\2"+
		"\2fg\5\n\6\13gh\b\6\1\2h~\3\2\2\2ij\f\t\2\2jk\t\2\2\2kl\5\n\6\nlm\b\6"+
		"\1\2m~\3\2\2\2no\f\b\2\2op\t\4\2\2pq\5\n\6\tqr\b\6\1\2r~\3\2\2\2st\f\7"+
		"\2\2tu\7\26\2\2uv\5\n\6\bvw\b\6\1\2w~\3\2\2\2xy\f\6\2\2yz\7\27\2\2z{\5"+
		"\n\6\7{|\b\6\1\2|~\3\2\2\2}d\3\2\2\2}i\3\2\2\2}n\3\2\2\2}s\3\2\2\2}x\3"+
		"\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\13\3\2\2\2"+
		"\u0081\177\3\2\2\2\u0082\u0083\5\22\n\2\u0083\u0084\b\7\1\2\u0084\u008f"+
		"\3\2\2\2\u0085\u0086\5\26\f\2\u0086\u0087\b\7\1\2\u0087\u008f\3\2\2\2"+
		"\u0088\u0089\5\24\13\2\u0089\u008a\b\7\1\2\u008a\u008f\3\2\2\2\u008b\u008c"+
		"\5\20\t\2\u008c\u008d\b\7\1\2\u008d\u008f\3\2\2\2\u008e\u0082\3\2\2\2"+
		"\u008e\u0085\3\2\2\2\u008e\u0088\3\2\2\2\u008e\u008b\3\2\2\2\u008f\r\3"+
		"\2\2\2\u0090\u0091\7\35\2\2\u0091\u0099\b\b\1\2\u0092\u0093\7\36\2\2\u0093"+
		"\u0099\b\b\1\2\u0094\u0095\7\37\2\2\u0095\u0099\b\b\1\2\u0096\u0097\7"+
		" \2\2\u0097\u0099\b\b\1\2\u0098\u0090\3\2\2\2\u0098\u0092\3\2\2\2\u0098"+
		"\u0094\3\2\2\2\u0098\u0096\3\2\2\2\u0099\17\3\2\2\2\u009a\u009b\7\33\2"+
		"\2\u009b\u009f\b\t\1\2\u009c\u009d\7\34\2\2\u009d\u009f\b\t\1\2\u009e"+
		"\u009a\3\2\2\2\u009e\u009c\3\2\2\2\u009f\21\3\2\2\2\u00a0\u00a1\7#\2\2"+
		"\u00a1\u00a5\b\n\1\2\u00a2\u00a3\7$\2\2\u00a3\u00a5\b\n\1\2\u00a4\u00a0"+
		"\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\23\3\2\2\2\u00a6\u00a7\7\"\2\2\u00a7"+
		"\u00a8\b\13\1\2\u00a8\25\3\2\2\2\u00a9\u00aa\7!\2\2\u00aa\u00ab\b\f\1"+
		"\2\u00ab\27\3\2\2\2\f$?Ob}\177\u008e\u0098\u009e\u00a4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}