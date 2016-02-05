// Generated from /Users/roydewildt/Workspace/UvA/SC/multi-ql/RoydeWildt/QLJava/src/org/uva/sea/ql/parser/QL.g4 by ANTLR 4.5.1
package org.uva.sea.ql.parser;

import java.util.List;
import java.io.IOException;
import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.val.*;
import org.uva.sea.ql.ast.form.*;

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
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		WHITESPACE=25, COMMENT=26, True=27, False=28, Ident=29, Str=30, Int=31;
	public static final int
		RULE_form = 0, RULE_forms = 1, RULE_varDecl = 2, RULE_question = 3, RULE_stat = 4, 
		RULE_stats = 5, RULE_primary = 6, RULE_unExpr = 7, RULE_mulExpr = 8, RULE_addExpr = 9, 
		RULE_relExpr = 10, RULE_andExpr = 11, RULE_orExpr = 12, RULE_type = 13, 
		RULE_bool = 14;
	public static final String[] ruleNames = {
		"form", "forms", "varDecl", "question", "stat", "stats", "primary", "unExpr", 
		"mulExpr", "addExpr", "relExpr", "andExpr", "orExpr", "type", "bool"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'='", "'('", "')'", "':'", "'if'", "'else'", 
		"'+'", "'-'", "'!'", "'*'", "'/'", "'<'", "'<='", "'>'", "'>='", "'=='", 
		"'!='", "'&&'", "'||'", "'boolean'", "'money'", null, null, "'true'", 
		"'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "WHITESPACE", "COMMENT", "True", "False", "Ident", "Str", "Int"
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


	public static List<Form> ParseForm(String path) {
			QLLexer lex = null;
			try {
				lex = new QLLexer(new ANTLRFileStream(path));
			} catch (IOException e) {
				e.printStackTrace();
			}

			CommonTokenStream tok = new CommonTokenStream(lex);
		    QLParser par = new QLParser(tok);

			List<Form> result = null;
		    try{
		        result = par.forms().result;
		    } catch (RecognitionException e) {
				e.printStackTrace();
		    }
			return result;
	}

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
			setState(30);
			match(T__0);
			setState(31);
			((FormContext)_localctx).i = match(Ident);
			setState(32);
			match(T__1);
			setState(33);
			((FormContext)_localctx).s = stats();
			setState(34);
			match(T__2);
			((FormContext)_localctx).result =  new Form((((FormContext)_localctx).i!=null?((FormContext)_localctx).i.getText():null), ((FormContext)_localctx).s.result);
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

	public static class FormsContext extends ParserRuleContext {
		public List<Form> result;
		public FormContext form;
		public List<FormContext> form() {
			return getRuleContexts(FormContext.class);
		}
		public FormContext form(int i) {
			return getRuleContext(FormContext.class,i);
		}
		public FormsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterForms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitForms(this);
		}
	}

	public final FormsContext forms() throws RecognitionException {
		FormsContext _localctx = new FormsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_forms);
		((FormsContext)_localctx).result =  new ArrayList<Form>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(37);
				((FormsContext)_localctx).form = form();
				_localctx.result.add(((FormsContext)_localctx).form.result);
				}
				}
				setState(42); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
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
			setState(44);
			match(T__3);
			setState(45);
			match(T__4);
			setState(46);
			((VarDeclContext)_localctx).x = orExpr();
			setState(47);
			match(T__5);
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
			setState(64);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				((QuestionContext)_localctx).l = match(Str);
				setState(52);
				((QuestionContext)_localctx).v = match(Ident);
				setState(53);
				match(T__6);
				setState(54);
				((QuestionContext)_localctx).t = type();
				((QuestionContext)_localctx).result =  new Question((((QuestionContext)_localctx).l!=null?((QuestionContext)_localctx).l.getText():null), (((QuestionContext)_localctx).v!=null?((QuestionContext)_localctx).v.getText():null), (((QuestionContext)_localctx).t!=null?_input.getText(((QuestionContext)_localctx).t.start,((QuestionContext)_localctx).t.stop):null));
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				((QuestionContext)_localctx).l = match(Str);
				setState(58);
				((QuestionContext)_localctx).v = match(Ident);
				setState(59);
				match(T__6);
				setState(60);
				((QuestionContext)_localctx).t = type();
				setState(61);
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
			setState(91);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				((StatContext)_localctx).q = question();
				((StatContext)_localctx).result =  ((StatContext)_localctx).q.result;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				match(T__7);
				setState(70);
				match(T__4);
				setState(71);
				((StatContext)_localctx).c = orExpr();
				setState(72);
				match(T__5);
				setState(73);
				match(T__1);
				setState(74);
				((StatContext)_localctx).s = stats();
				setState(75);
				match(T__2);
				((StatContext)_localctx).result =  new If(((StatContext)_localctx).c.result, ((StatContext)_localctx).s.result);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				match(T__7);
				setState(79);
				match(T__4);
				setState(80);
				((StatContext)_localctx).c = orExpr();
				setState(81);
				match(T__5);
				setState(82);
				match(T__1);
				setState(83);
				((StatContext)_localctx).i = stats();
				setState(84);
				match(T__2);
				setState(85);
				match(T__8);
				setState(86);
				match(T__1);
				setState(87);
				((StatContext)_localctx).e = stats();
				setState(88);
				match(T__2);
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
			setState(96); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(93);
				((StatsContext)_localctx).stat = stat();
				_localctx.result.add(((StatsContext)_localctx).stat.result);
				}
				}
				setState(98); 
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
			setState(107);
			switch (_input.LA(1)) {
			case Int:
				enterOuterAlt(_localctx, 1);
				{
				setState(100);
				((PrimaryContext)_localctx).x = match(Int);
				((PrimaryContext)_localctx).result =  new Prim(new Int((((PrimaryContext)_localctx).x!=null?((PrimaryContext)_localctx).x.getText():null)));
				}
				break;
			case Ident:
				enterOuterAlt(_localctx, 2);
				{
				setState(102);
				((PrimaryContext)_localctx).x = match(Ident);
				((PrimaryContext)_localctx).result =  new Prim(new Var((((PrimaryContext)_localctx).x!=null?((PrimaryContext)_localctx).x.getText():null)));
				}
				break;
			case True:
			case False:
				enterOuterAlt(_localctx, 3);
				{
				setState(104);
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
			setState(124);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(109);
				match(T__9);
				setState(110);
				((UnExprContext)_localctx).x = unExpr();
				 ((UnExprContext)_localctx).result =  new Pos(((UnExprContext)_localctx).x.result); 
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				match(T__10);
				setState(114);
				((UnExprContext)_localctx).x = unExpr();
				 ((UnExprContext)_localctx).result =  new Neg(((UnExprContext)_localctx).x.result); 
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 3);
				{
				setState(117);
				match(T__11);
				setState(118);
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
				setState(121);
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
			setState(126);
			((MulExprContext)_localctx).lhs = unExpr();
			 ((MulExprContext)_localctx).result = ((MulExprContext)_localctx).lhs.result; 
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12 || _la==T__13) {
				{
				{
				setState(128);
				((MulExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__12 || _la==T__13) ) {
					((MulExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(129);
				((MulExprContext)_localctx).rhs = unExpr();
				 
				      if ((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getText():null).equals("*")) {
				        ((MulExprContext)_localctx).result =  new Mul(_localctx.result, ((MulExprContext)_localctx).rhs.result);
				      }
				      if ((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getText():null).equals("/")) {
				        ((MulExprContext)_localctx).result =  new Div(_localctx.result, ((MulExprContext)_localctx).rhs.result);
				      }
				    
				}
				}
				setState(136);
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
			setState(137);
			((AddExprContext)_localctx).lhs = mulExpr();
			 ((AddExprContext)_localctx).result = ((AddExprContext)_localctx).lhs.result; 
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9 || _la==T__10) {
				{
				{
				setState(139);
				((AddExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
					((AddExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(140);
				((AddExprContext)_localctx).rhs = mulExpr();
				 
				      if ((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getText():null).equals("+")) {
				        ((AddExprContext)_localctx).result =  new Add(_localctx.result, ((AddExprContext)_localctx).rhs.result);
				      }
				      if ((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getText():null).equals("-")) {
				        ((AddExprContext)_localctx).result =  new Sub(_localctx.result, ((AddExprContext)_localctx).rhs.result);
				      }
				    
				}
				}
				setState(147);
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
			setState(148);
			((RelExprContext)_localctx).lhs = addExpr();
			 ((RelExprContext)_localctx).result = ((RelExprContext)_localctx).lhs.result; 
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19))) != 0)) {
				{
				{
				setState(150);
				((RelExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19))) != 0)) ) {
					((RelExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(151);
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
				setState(158);
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
			setState(159);
			((AndExprContext)_localctx).lhs = relExpr();
			 ((AndExprContext)_localctx).result = ((AndExprContext)_localctx).lhs.result; 
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__20) {
				{
				{
				setState(161);
				match(T__20);
				setState(162);
				((AndExprContext)_localctx).rhs = relExpr();
				 ((AndExprContext)_localctx).result =  new And(_localctx.result, ((AndExprContext)_localctx).rhs.result); 
				}
				}
				setState(169);
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
			setState(170);
			((OrExprContext)_localctx).lhs = andExpr();
			 ((OrExprContext)_localctx).result =  ((OrExprContext)_localctx).lhs.result; 
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__21) {
				{
				{
				setState(172);
				match(T__21);
				setState(173);
				((OrExprContext)_localctx).rhs = andExpr();
				 ((OrExprContext)_localctx).result =  new Or(_localctx.result, ((OrExprContext)_localctx).rhs.result); 
				}
				}
				setState(180);
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
		enterRule(_localctx, 26, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			_la = _input.LA(1);
			if ( !(_la==T__22 || _la==T__23) ) {
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
		enterRule(_localctx, 28, RULE_bool);
		try {
			setState(187);
			switch (_input.LA(1)) {
			case True:
				enterOuterAlt(_localctx, 1);
				{
				setState(183);
				((BoolContext)_localctx).x = match(True);
				((BoolContext)_localctx).result =  new Bool((((BoolContext)_localctx).x!=null?((BoolContext)_localctx).x.getText():null));
				}
				break;
			case False:
				enterOuterAlt(_localctx, 2);
				{
				setState(185);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3!\u00c0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\3\3\3\3\3\6\3+\n\3\r\3\16\3,\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5C\n\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\5\6^\n\6\3\7\3\7\3\7\6\7c\n\7\r\7\16\7d\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\5\bn\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\5\t\177\n\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u0087\n\n\f"+
		"\n\16\n\u008a\13\n\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u0092\n\13\f\13"+
		"\16\13\u0095\13\13\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u009d\n\f\f\f\16\f\u00a0"+
		"\13\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00a8\n\r\f\r\16\r\u00ab\13\r\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\7\16\u00b3\n\16\f\16\16\16\u00b6\13\16\3\17\3"+
		"\17\3\20\3\20\3\20\3\20\5\20\u00be\n\20\3\20\2\2\21\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36\2\6\3\2\17\20\3\2\f\r\3\2\21\26\3\2\31\32\u00c0"+
		"\2 \3\2\2\2\4*\3\2\2\2\6.\3\2\2\2\bB\3\2\2\2\n]\3\2\2\2\fb\3\2\2\2\16"+
		"m\3\2\2\2\20~\3\2\2\2\22\u0080\3\2\2\2\24\u008b\3\2\2\2\26\u0096\3\2\2"+
		"\2\30\u00a1\3\2\2\2\32\u00ac\3\2\2\2\34\u00b7\3\2\2\2\36\u00bd\3\2\2\2"+
		" !\7\3\2\2!\"\7\37\2\2\"#\7\4\2\2#$\5\f\7\2$%\7\5\2\2%&\b\2\1\2&\3\3\2"+
		"\2\2\'(\5\2\2\2()\b\3\1\2)+\3\2\2\2*\'\3\2\2\2+,\3\2\2\2,*\3\2\2\2,-\3"+
		"\2\2\2-\5\3\2\2\2./\7\6\2\2/\60\7\7\2\2\60\61\5\32\16\2\61\62\7\b\2\2"+
		"\62\63\3\2\2\2\63\64\b\4\1\2\64\7\3\2\2\2\65\66\7 \2\2\66\67\7\37\2\2"+
		"\678\7\t\2\289\5\34\17\29:\b\5\1\2:C\3\2\2\2;<\7 \2\2<=\7\37\2\2=>\7\t"+
		"\2\2>?\5\34\17\2?@\5\6\4\2@A\b\5\1\2AC\3\2\2\2B\65\3\2\2\2B;\3\2\2\2C"+
		"\t\3\2\2\2DE\5\b\5\2EF\b\6\1\2F^\3\2\2\2GH\7\n\2\2HI\7\7\2\2IJ\5\32\16"+
		"\2JK\7\b\2\2KL\7\4\2\2LM\5\f\7\2MN\7\5\2\2NO\b\6\1\2O^\3\2\2\2PQ\7\n\2"+
		"\2QR\7\7\2\2RS\5\32\16\2ST\7\b\2\2TU\7\4\2\2UV\5\f\7\2VW\7\5\2\2WX\7\13"+
		"\2\2XY\7\4\2\2YZ\5\f\7\2Z[\7\5\2\2[\\\b\6\1\2\\^\3\2\2\2]D\3\2\2\2]G\3"+
		"\2\2\2]P\3\2\2\2^\13\3\2\2\2_`\5\n\6\2`a\b\7\1\2ac\3\2\2\2b_\3\2\2\2c"+
		"d\3\2\2\2db\3\2\2\2de\3\2\2\2e\r\3\2\2\2fg\7!\2\2gn\b\b\1\2hi\7\37\2\2"+
		"in\b\b\1\2jk\5\36\20\2kl\b\b\1\2ln\3\2\2\2mf\3\2\2\2mh\3\2\2\2mj\3\2\2"+
		"\2n\17\3\2\2\2op\7\f\2\2pq\5\20\t\2qr\b\t\1\2r\177\3\2\2\2st\7\r\2\2t"+
		"u\5\20\t\2uv\b\t\1\2v\177\3\2\2\2wx\7\16\2\2xy\5\20\t\2yz\b\t\1\2z\177"+
		"\3\2\2\2{|\5\16\b\2|}\b\t\1\2}\177\3\2\2\2~o\3\2\2\2~s\3\2\2\2~w\3\2\2"+
		"\2~{\3\2\2\2\177\21\3\2\2\2\u0080\u0081\5\20\t\2\u0081\u0088\b\n\1\2\u0082"+
		"\u0083\t\2\2\2\u0083\u0084\5\20\t\2\u0084\u0085\b\n\1\2\u0085\u0087\3"+
		"\2\2\2\u0086\u0082\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086\3\2\2\2\u0088"+
		"\u0089\3\2\2\2\u0089\23\3\2\2\2\u008a\u0088\3\2\2\2\u008b\u008c\5\22\n"+
		"\2\u008c\u0093\b\13\1\2\u008d\u008e\t\3\2\2\u008e\u008f\5\22\n\2\u008f"+
		"\u0090\b\13\1\2\u0090\u0092\3\2\2\2\u0091\u008d\3\2\2\2\u0092\u0095\3"+
		"\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\25\3\2\2\2\u0095"+
		"\u0093\3\2\2\2\u0096\u0097\5\24\13\2\u0097\u009e\b\f\1\2\u0098\u0099\t"+
		"\4\2\2\u0099\u009a\5\24\13\2\u009a\u009b\b\f\1\2\u009b\u009d\3\2\2\2\u009c"+
		"\u0098\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2"+
		"\2\2\u009f\27\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a2\5\26\f\2\u00a2\u00a9"+
		"\b\r\1\2\u00a3\u00a4\7\27\2\2\u00a4\u00a5\5\26\f\2\u00a5\u00a6\b\r\1\2"+
		"\u00a6\u00a8\3\2\2\2\u00a7\u00a3\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00a7"+
		"\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\31\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac"+
		"\u00ad\5\30\r\2\u00ad\u00b4\b\16\1\2\u00ae\u00af\7\30\2\2\u00af\u00b0"+
		"\5\30\r\2\u00b0\u00b1\b\16\1\2\u00b1\u00b3\3\2\2\2\u00b2\u00ae\3\2\2\2"+
		"\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\33"+
		"\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00b8\t\5\2\2\u00b8\35\3\2\2\2\u00b9"+
		"\u00ba\7\35\2\2\u00ba\u00be\b\20\1\2\u00bb\u00bc\7\36\2\2\u00bc\u00be"+
		"\b\20\1\2\u00bd\u00b9\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be\37\3\2\2\2\16"+
		",B]dm~\u0088\u0093\u009e\u00a9\u00b4\u00bd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}