// Generated from QL.g4 by ANTLR 4.4
package antlr;

	package antlr;
	import ast.expression.*;
	import ast.form.*;
	import ast.literal.*;
	import ast.statement.*;

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
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__19=1, T__18=2, T__17=3, T__16=4, T__15=5, T__14=6, T__13=7, T__12=8, 
		T__11=9, T__10=10, T__9=11, T__8=12, T__7=13, T__6=14, T__5=15, T__4=16, 
		T__3=17, T__2=18, T__1=19, T__0=20, WHITESPACE=21, COMMENT=22, BOOLEAN=23, 
		INTEGER=24, STRING=25, BOOL=26, INT=27, STR=28, ID=29;
	public static final String[] tokenNames = {
		"<INVALID>", "'/'", "'!='", "':'", "'>='", "'||'", "'{'", "'=='", "'<'", 
		"'&&'", "'}'", "'>'", "'if'", "'!'", "'<='", "'('", "')'", "'*'", "'+'", 
		"'form'", "'-'", "WHITESPACE", "COMMENT", "'boolean'", "'int'", "'string'", 
		"BOOL", "INT", "STR", "ID"
	};
	public static final int
		RULE_file = 0, RULE_form = 1, RULE_body = 2, RULE_statement = 3, RULE_question = 4, 
		RULE_ifStatement = 5, RULE_variable = 6, RULE_variableType = 7, RULE_orExpression = 8, 
		RULE_andExpression = 9, RULE_relExpression = 10, RULE_addExpression = 11, 
		RULE_mulExpression = 12, RULE_unExpression = 13, RULE_literal = 14, RULE_intLiteral = 15, 
		RULE_boolLiteral = 16, RULE_stringLiteral = 17, RULE_variableExpression = 18;
	public static final String[] ruleNames = {
		"file", "form", "body", "statement", "question", "ifStatement", "variable", 
		"variableType", "orExpression", "andExpression", "relExpression", "addExpression", 
		"mulExpression", "unExpression", "literal", "intLiteral", "boolLiteral", 
		"stringLiteral", "variableExpression"
	};

	@Override
	public String getGrammarFileName() { return "QL.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

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
	public static class FileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(QLParser.EOF, 0); }
		public FormContext form() {
			return getRuleContext(FormContext.class,0);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38); form();
			setState(39); match(EOF);
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

	public static class FormContext extends ParserRuleContext {
		public Form result;
		public Token ID;
		public BodyContext body;
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
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
		enterRule(_localctx, 2, RULE_form);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41); match(T__1);
			setState(42); ((FormContext)_localctx).ID = match(ID);
			setState(43); ((FormContext)_localctx).body = body();
			 ((FormContext)_localctx).result =  new Form(((FormContext)_localctx).ID.getLine(), (((FormContext)_localctx).ID!=null?((FormContext)_localctx).ID.getText():null), ((FormContext)_localctx).body.result); 
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

	public static class BodyContext extends ParserRuleContext {
		public Body result;
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitBody(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_body);
		 ((BodyContext)_localctx).result =  new Body(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46); match(T__14);
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8 || _la==ID) {
				{
				{
				setState(47); statement(_localctx.result);
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53); match(T__10);
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

	public static class StatementContext extends ParserRuleContext {
		public Body result;
		public QuestionContext question;
		public IfStatementContext ifStatement;
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public StatementContext(ParserRuleContext parent, int invokingState, Body result) {
			super(parent, invokingState);
			this.result = result;
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement(Body result) throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState(), result);
		enterRule(_localctx, 6, RULE_statement);
		try {
			setState(61);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(55); ((StatementContext)_localctx).question = question();
				 _localctx.result.add(new Statement(((StatementContext)_localctx).question.result)); 
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(58); ((StatementContext)_localctx).ifStatement = ifStatement();
				 _localctx.result.add(new Statement(((StatementContext)_localctx).ifStatement.result)); 
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

	public static class QuestionContext extends ParserRuleContext {
		public Question result;
		public VariableContext variable;
		public Token STR;
		public OrExpressionContext orExpression;
		public TerminalNode STR() { return getToken(QLParser.STR, 0); }
		public OrExpressionContext orExpression() {
			return getRuleContext(OrExpressionContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
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
		enterRule(_localctx, 8, RULE_question);
		try {
			setState(74);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(63); ((QuestionContext)_localctx).variable = variable();
				setState(64); ((QuestionContext)_localctx).STR = match(STR);
				 ((QuestionContext)_localctx).result =  new InputQuestion((((QuestionContext)_localctx).variable!=null?(((QuestionContext)_localctx).variable.start):null).getLine(), ((QuestionContext)_localctx).variable.result, (((QuestionContext)_localctx).STR!=null?((QuestionContext)_localctx).STR.getText():null)); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(67); ((QuestionContext)_localctx).variable = variable();
				setState(68); ((QuestionContext)_localctx).STR = match(STR);
				setState(69); match(T__5);
				setState(70); ((QuestionContext)_localctx).orExpression = orExpression();
				setState(71); match(T__4);
				 ((QuestionContext)_localctx).result =  new ComputedQuestion((((QuestionContext)_localctx).variable!=null?(((QuestionContext)_localctx).variable.start):null).getLine(), ((QuestionContext)_localctx).variable.result, (((QuestionContext)_localctx).STR!=null?((QuestionContext)_localctx).STR.getText():null), ((QuestionContext)_localctx).orExpression.result); 
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

	public static class IfStatementContext extends ParserRuleContext {
		public IfStatement result;
		public OrExpressionContext orExpression;
		public BodyContext body;
		public OrExpressionContext orExpression() {
			return getRuleContext(OrExpressionContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitIfStatement(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76); match(T__8);
			setState(77); match(T__5);
			setState(78); ((IfStatementContext)_localctx).orExpression = orExpression();
			setState(79); match(T__4);
			setState(80); ((IfStatementContext)_localctx).body = body();
			 ((IfStatementContext)_localctx).result =  new IfStatement((((IfStatementContext)_localctx).orExpression!=null?(((IfStatementContext)_localctx).orExpression.start):null).getLine(), ((IfStatementContext)_localctx).orExpression.result, ((IfStatementContext)_localctx).body.result); 
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

	public static class VariableContext extends ParserRuleContext {
		public Variable result;
		public Token ID;
		public VariableTypeContext variableType;
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public VariableTypeContext variableType() {
			return getRuleContext(VariableTypeContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83); ((VariableContext)_localctx).ID = match(ID);
			setState(84); match(T__17);
			setState(85); ((VariableContext)_localctx).variableType = variableType();
			 ((VariableContext)_localctx).result =  new Variable(((VariableContext)_localctx).ID.getLine(), (((VariableContext)_localctx).ID!=null?((VariableContext)_localctx).ID.getText():null), ((VariableContext)_localctx).variableType.result); 
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

	public static class VariableTypeContext extends ParserRuleContext {
		public VariableType result;
		public Token type;
		public TerminalNode INTEGER() { return getToken(QLParser.INTEGER, 0); }
		public TerminalNode STRING() { return getToken(QLParser.STRING, 0); }
		public TerminalNode BOOLEAN() { return getToken(QLParser.BOOLEAN, 0); }
		public VariableTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterVariableType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitVariableType(this);
		}
	}

	public final VariableTypeContext variableType() throws RecognitionException {
		VariableTypeContext _localctx = new VariableTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_variableType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			((VariableTypeContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << INTEGER) | (1L << STRING))) != 0)) ) {
				((VariableTypeContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			}
			consume();
			 ((VariableTypeContext)_localctx).result =  new VariableType(((VariableTypeContext)_localctx).type.getLine(), (((VariableTypeContext)_localctx).type!=null?((VariableTypeContext)_localctx).type.getText():null)); 
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

	public static class OrExpressionContext extends ParserRuleContext {
		public Expression result;
		public AndExpressionContext lhs;
		public AndExpressionContext rhs;
		public List<AndExpressionContext> andExpression() {
			return getRuleContexts(AndExpressionContext.class);
		}
		public AndExpressionContext andExpression(int i) {
			return getRuleContext(AndExpressionContext.class,i);
		}
		public OrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitOrExpression(this);
		}
	}

	public final OrExpressionContext orExpression() throws RecognitionException {
		OrExpressionContext _localctx = new OrExpressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_orExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91); ((OrExpressionContext)_localctx).lhs = andExpression();
			 ((OrExpressionContext)_localctx).result =  ((OrExpressionContext)_localctx).lhs.result; 
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__15) {
				{
				{
				setState(93); match(T__15);
				setState(94); ((OrExpressionContext)_localctx).rhs = andExpression();
				 ((OrExpressionContext)_localctx).result =  new OrExpression((((OrExpressionContext)_localctx).lhs!=null?(((OrExpressionContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((OrExpressionContext)_localctx).rhs.result); 
				}
				}
				setState(101);
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

	public static class AndExpressionContext extends ParserRuleContext {
		public Expression result;
		public RelExpressionContext lhs;
		public RelExpressionContext rhs;
		public RelExpressionContext relExpression(int i) {
			return getRuleContext(RelExpressionContext.class,i);
		}
		public List<RelExpressionContext> relExpression() {
			return getRuleContexts(RelExpressionContext.class);
		}
		public AndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitAndExpression(this);
		}
	}

	public final AndExpressionContext andExpression() throws RecognitionException {
		AndExpressionContext _localctx = new AndExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_andExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102); ((AndExpressionContext)_localctx).lhs = relExpression();
			 ((AndExpressionContext)_localctx).result =  ((AndExpressionContext)_localctx).lhs.result; 
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(104); match(T__11);
				setState(105); ((AndExpressionContext)_localctx).rhs = relExpression();
				 ((AndExpressionContext)_localctx).result =  new AndExpression((((AndExpressionContext)_localctx).lhs!=null?(((AndExpressionContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((AndExpressionContext)_localctx).rhs.result); 
				}
				}
				setState(112);
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

	public static class RelExpressionContext extends ParserRuleContext {
		public Expression result;
		public AddExpressionContext lhs;
		public Token op;
		public AddExpressionContext rhs;
		public List<AddExpressionContext> addExpression() {
			return getRuleContexts(AddExpressionContext.class);
		}
		public AddExpressionContext addExpression(int i) {
			return getRuleContext(AddExpressionContext.class,i);
		}
		public RelExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterRelExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitRelExpression(this);
		}
	}

	public final RelExpressionContext relExpression() throws RecognitionException {
		RelExpressionContext _localctx = new RelExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_relExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113); ((RelExpressionContext)_localctx).lhs = addExpression();
			 ((RelExpressionContext)_localctx).result =  ((RelExpressionContext)_localctx).lhs.result; 
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__16) | (1L << T__13) | (1L << T__12) | (1L << T__9) | (1L << T__6))) != 0)) {
				{
				{
				setState(115);
				((RelExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__16) | (1L << T__13) | (1L << T__12) | (1L << T__9) | (1L << T__6))) != 0)) ) {
					((RelExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(116); ((RelExpressionContext)_localctx).rhs = addExpression();

							if ((((RelExpressionContext)_localctx).op!=null?((RelExpressionContext)_localctx).op.getText():null).equals("<")) {
							  ((RelExpressionContext)_localctx).result =  new LT((((RelExpressionContext)_localctx).lhs!=null?(((RelExpressionContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((RelExpressionContext)_localctx).rhs.result);
							}
							if ((((RelExpressionContext)_localctx).op!=null?((RelExpressionContext)_localctx).op.getText():null).equals("<=")) {
							  ((RelExpressionContext)_localctx).result =  new LEq((((RelExpressionContext)_localctx).lhs!=null?(((RelExpressionContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((RelExpressionContext)_localctx).rhs.result);      
							}
							if ((((RelExpressionContext)_localctx).op!=null?((RelExpressionContext)_localctx).op.getText():null).equals(">")) {
							  ((RelExpressionContext)_localctx).result =  new GT((((RelExpressionContext)_localctx).lhs!=null?(((RelExpressionContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((RelExpressionContext)_localctx).rhs.result);
							}
							if ((((RelExpressionContext)_localctx).op!=null?((RelExpressionContext)_localctx).op.getText():null).equals(">=")) {
							  ((RelExpressionContext)_localctx).result =  new GEq((((RelExpressionContext)_localctx).lhs!=null?(((RelExpressionContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((RelExpressionContext)_localctx).rhs.result);      
							}
							if ((((RelExpressionContext)_localctx).op!=null?((RelExpressionContext)_localctx).op.getText():null).equals("==")) {
							  ((RelExpressionContext)_localctx).result =  new Eq((((RelExpressionContext)_localctx).lhs!=null?(((RelExpressionContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((RelExpressionContext)_localctx).rhs.result);
							}
							if ((((RelExpressionContext)_localctx).op!=null?((RelExpressionContext)_localctx).op.getText():null).equals("!=")) {
							  ((RelExpressionContext)_localctx).result =  new NEq((((RelExpressionContext)_localctx).lhs!=null?(((RelExpressionContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((RelExpressionContext)_localctx).rhs.result);
							}
						
				}
				}
				setState(123);
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

	public static class AddExpressionContext extends ParserRuleContext {
		public Expression result;
		public MulExpressionContext lhs;
		public Token op;
		public MulExpressionContext rhs;
		public List<MulExpressionContext> mulExpression() {
			return getRuleContexts(MulExpressionContext.class);
		}
		public MulExpressionContext mulExpression(int i) {
			return getRuleContext(MulExpressionContext.class,i);
		}
		public AddExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterAddExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitAddExpression(this);
		}
	}

	public final AddExpressionContext addExpression() throws RecognitionException {
		AddExpressionContext _localctx = new AddExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_addExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124); ((AddExpressionContext)_localctx).lhs = mulExpression();
			 ((AddExpressionContext)_localctx).result =  ((AddExpressionContext)_localctx).lhs.result; 
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2 || _la==T__0) {
				{
				{
				setState(126);
				((AddExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__2 || _la==T__0) ) {
					((AddExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(127); ((AddExpressionContext)_localctx).rhs = mulExpression();

							if((((AddExpressionContext)_localctx).op!=null?((AddExpressionContext)_localctx).op.getText():null).equals("+")){
								((AddExpressionContext)_localctx).result =  new Add((((AddExpressionContext)_localctx).lhs!=null?(((AddExpressionContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((AddExpressionContext)_localctx).rhs.result);
							}
							if((((AddExpressionContext)_localctx).op!=null?((AddExpressionContext)_localctx).op.getText():null).equals("-")){
								((AddExpressionContext)_localctx).result =  new Sub((((AddExpressionContext)_localctx).lhs!=null?(((AddExpressionContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((AddExpressionContext)_localctx).rhs.result);
							}
						
				}
				}
				setState(134);
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

	public static class MulExpressionContext extends ParserRuleContext {
		public Expression result;
		public UnExpressionContext lhs;
		public Token op;
		public UnExpressionContext rhs;
		public UnExpressionContext unExpression(int i) {
			return getRuleContext(UnExpressionContext.class,i);
		}
		public List<UnExpressionContext> unExpression() {
			return getRuleContexts(UnExpressionContext.class);
		}
		public MulExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterMulExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitMulExpression(this);
		}
	}

	public final MulExpressionContext mulExpression() throws RecognitionException {
		MulExpressionContext _localctx = new MulExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_mulExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135); ((MulExpressionContext)_localctx).lhs = unExpression();
			 ((MulExpressionContext)_localctx).result =  ((MulExpressionContext)_localctx).lhs.result; 
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__19 || _la==T__3) {
				{
				{
				setState(137);
				((MulExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__3) ) {
					((MulExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(138); ((MulExpressionContext)_localctx).rhs = unExpression();

							if((((MulExpressionContext)_localctx).op!=null?((MulExpressionContext)_localctx).op.getText():null).equals("*")){
								((MulExpressionContext)_localctx).result =  new Mul((((MulExpressionContext)_localctx).lhs!=null?(((MulExpressionContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((MulExpressionContext)_localctx).rhs.result);
							}
							if((((MulExpressionContext)_localctx).op!=null?((MulExpressionContext)_localctx).op.getText():null).equals("/")){
								((MulExpressionContext)_localctx).result =  new Div((((MulExpressionContext)_localctx).lhs!=null?(((MulExpressionContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((MulExpressionContext)_localctx).rhs.result);
							}
						
				}
				}
				setState(145);
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

	public static class UnExpressionContext extends ParserRuleContext {
		public Expression result;
		public UnExpressionContext x;
		public LiteralContext y;
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public UnExpressionContext unExpression() {
			return getRuleContext(UnExpressionContext.class,0);
		}
		public UnExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterUnExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitUnExpression(this);
		}
	}

	public final UnExpressionContext unExpression() throws RecognitionException {
		UnExpressionContext _localctx = new UnExpressionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_unExpression);
		try {
			setState(161);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(146); match(T__2);
				setState(147); ((UnExpressionContext)_localctx).x = unExpression();
				 ((UnExpressionContext)_localctx).result =  new Pos(((UnExpressionContext)_localctx).x.result); 
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(150); match(T__0);
				setState(151); ((UnExpressionContext)_localctx).x = unExpression();
				 ((UnExpressionContext)_localctx).result =  new Neg(((UnExpressionContext)_localctx).x.result); 
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 3);
				{
				setState(154); match(T__7);
				setState(155); ((UnExpressionContext)_localctx).x = unExpression();
				 ((UnExpressionContext)_localctx).result =  new Not(((UnExpressionContext)_localctx).x.result); 
				}
				break;
			case BOOL:
			case INT:
			case STR:
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(158); ((UnExpressionContext)_localctx).y = literal();
				 ((UnExpressionContext)_localctx).result =  ((UnExpressionContext)_localctx).y.result; 
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

	public static class LiteralContext extends ParserRuleContext {
		public Literal result;
		public IntLiteralContext intLiteral;
		public BoolLiteralContext boolLiteral;
		public StringLiteralContext stringLiteral;
		public VariableExpressionContext variableExpression;
		public IntLiteralContext intLiteral() {
			return getRuleContext(IntLiteralContext.class,0);
		}
		public VariableExpressionContext variableExpression() {
			return getRuleContext(VariableExpressionContext.class,0);
		}
		public BoolLiteralContext boolLiteral() {
			return getRuleContext(BoolLiteralContext.class,0);
		}
		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class,0);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_literal);
		try {
			setState(175);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(163); ((LiteralContext)_localctx).intLiteral = intLiteral();
				 ((LiteralContext)_localctx).result =  new Literal(((LiteralContext)_localctx).intLiteral.result); 
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(166); ((LiteralContext)_localctx).boolLiteral = boolLiteral();
				 ((LiteralContext)_localctx).result =  new Literal(((LiteralContext)_localctx).boolLiteral.result); 
				}
				break;
			case STR:
				enterOuterAlt(_localctx, 3);
				{
				setState(169); ((LiteralContext)_localctx).stringLiteral = stringLiteral();
				 ((LiteralContext)_localctx).result =  new Literal(((LiteralContext)_localctx).stringLiteral.result); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(172); ((LiteralContext)_localctx).variableExpression = variableExpression();
				((LiteralContext)_localctx).result =  new Literal(((LiteralContext)_localctx).variableExpression.result); 
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

	public static class IntLiteralContext extends ParserRuleContext {
		public IntLiteral result;
		public Token INT;
		public TerminalNode INT() { return getToken(QLParser.INT, 0); }
		public IntLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterIntLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitIntLiteral(this);
		}
	}

	public final IntLiteralContext intLiteral() throws RecognitionException {
		IntLiteralContext _localctx = new IntLiteralContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_intLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177); ((IntLiteralContext)_localctx).INT = match(INT);
			 ((IntLiteralContext)_localctx).result =  new IntLiteral(((IntLiteralContext)_localctx).INT.getLine(), Integer.valueOf((((IntLiteralContext)_localctx).INT!=null?((IntLiteralContext)_localctx).INT.getText():null))); 
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

	public static class BoolLiteralContext extends ParserRuleContext {
		public BoolLiteral result;
		public Token BOOL;
		public TerminalNode BOOL() { return getToken(QLParser.BOOL, 0); }
		public BoolLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterBoolLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitBoolLiteral(this);
		}
	}

	public final BoolLiteralContext boolLiteral() throws RecognitionException {
		BoolLiteralContext _localctx = new BoolLiteralContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_boolLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180); ((BoolLiteralContext)_localctx).BOOL = match(BOOL);
			 ((BoolLiteralContext)_localctx).result =  new BoolLiteral(((BoolLiteralContext)_localctx).BOOL.getLine(), Boolean.valueOf((((BoolLiteralContext)_localctx).BOOL!=null?((BoolLiteralContext)_localctx).BOOL.getText():null))); 
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

	public static class StringLiteralContext extends ParserRuleContext {
		public StringLiteral result;
		public Token STR;
		public TerminalNode STR() { return getToken(QLParser.STR, 0); }
		public StringLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitStringLiteral(this);
		}
	}

	public final StringLiteralContext stringLiteral() throws RecognitionException {
		StringLiteralContext _localctx = new StringLiteralContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_stringLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183); ((StringLiteralContext)_localctx).STR = match(STR);
			 ((StringLiteralContext)_localctx).result =  new StringLiteral(((StringLiteralContext)_localctx).STR.getLine(), (((StringLiteralContext)_localctx).STR!=null?((StringLiteralContext)_localctx).STR.getText():null)); 
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

	public static class VariableExpressionContext extends ParserRuleContext {
		public VariableExpression result;
		public Token ID;
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public VariableExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterVariableExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitVariableExpression(this);
		}
	}

	public final VariableExpressionContext variableExpression() throws RecognitionException {
		VariableExpressionContext _localctx = new VariableExpressionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_variableExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186); ((VariableExpressionContext)_localctx).ID = match(ID);
			 ((VariableExpressionContext)_localctx).result =  new VariableExpression(((VariableExpressionContext)_localctx).ID.getLine(), (((VariableExpressionContext)_localctx).ID!=null?((VariableExpressionContext)_localctx).ID.getText():null)); 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\37\u00c0\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\7\4\63\n"+
		"\4\f\4\16\4\66\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5@\n\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6M\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\7\nd\n\n\f"+
		"\n\16\ng\13\n\3\13\3\13\3\13\3\13\3\13\3\13\7\13o\n\13\f\13\16\13r\13"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\7\fz\n\f\f\f\16\f}\13\f\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\7\r\u0085\n\r\f\r\16\r\u0088\13\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\7\16\u0090\n\16\f\16\16\16\u0093\13\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00a4\n\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00b2\n\20\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\2\2\25\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&\2\6\3\2\31\33\7\2\4\4\6\6\t"+
		"\n\r\r\20\20\4\2\24\24\26\26\4\2\3\3\23\23\u00ba\2(\3\2\2\2\4+\3\2\2\2"+
		"\6\60\3\2\2\2\b?\3\2\2\2\nL\3\2\2\2\fN\3\2\2\2\16U\3\2\2\2\20Z\3\2\2\2"+
		"\22]\3\2\2\2\24h\3\2\2\2\26s\3\2\2\2\30~\3\2\2\2\32\u0089\3\2\2\2\34\u00a3"+
		"\3\2\2\2\36\u00b1\3\2\2\2 \u00b3\3\2\2\2\"\u00b6\3\2\2\2$\u00b9\3\2\2"+
		"\2&\u00bc\3\2\2\2()\5\4\3\2)*\7\2\2\3*\3\3\2\2\2+,\7\25\2\2,-\7\37\2\2"+
		"-.\5\6\4\2./\b\3\1\2/\5\3\2\2\2\60\64\7\b\2\2\61\63\5\b\5\2\62\61\3\2"+
		"\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\67\3\2\2\2\66\64\3\2"+
		"\2\2\678\7\f\2\28\7\3\2\2\29:\5\n\6\2:;\b\5\1\2;@\3\2\2\2<=\5\f\7\2=>"+
		"\b\5\1\2>@\3\2\2\2?9\3\2\2\2?<\3\2\2\2@\t\3\2\2\2AB\5\16\b\2BC\7\36\2"+
		"\2CD\b\6\1\2DM\3\2\2\2EF\5\16\b\2FG\7\36\2\2GH\7\21\2\2HI\5\22\n\2IJ\7"+
		"\22\2\2JK\b\6\1\2KM\3\2\2\2LA\3\2\2\2LE\3\2\2\2M\13\3\2\2\2NO\7\16\2\2"+
		"OP\7\21\2\2PQ\5\22\n\2QR\7\22\2\2RS\5\6\4\2ST\b\7\1\2T\r\3\2\2\2UV\7\37"+
		"\2\2VW\7\5\2\2WX\5\20\t\2XY\b\b\1\2Y\17\3\2\2\2Z[\t\2\2\2[\\\b\t\1\2\\"+
		"\21\3\2\2\2]^\5\24\13\2^e\b\n\1\2_`\7\7\2\2`a\5\24\13\2ab\b\n\1\2bd\3"+
		"\2\2\2c_\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2f\23\3\2\2\2ge\3\2\2\2h"+
		"i\5\26\f\2ip\b\13\1\2jk\7\13\2\2kl\5\26\f\2lm\b\13\1\2mo\3\2\2\2nj\3\2"+
		"\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2q\25\3\2\2\2rp\3\2\2\2st\5\30\r\2t{"+
		"\b\f\1\2uv\t\3\2\2vw\5\30\r\2wx\b\f\1\2xz\3\2\2\2yu\3\2\2\2z}\3\2\2\2"+
		"{y\3\2\2\2{|\3\2\2\2|\27\3\2\2\2}{\3\2\2\2~\177\5\32\16\2\177\u0086\b"+
		"\r\1\2\u0080\u0081\t\4\2\2\u0081\u0082\5\32\16\2\u0082\u0083\b\r\1\2\u0083"+
		"\u0085\3\2\2\2\u0084\u0080\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2"+
		"\2\2\u0086\u0087\3\2\2\2\u0087\31\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u008a"+
		"\5\34\17\2\u008a\u0091\b\16\1\2\u008b\u008c\t\5\2\2\u008c\u008d\5\34\17"+
		"\2\u008d\u008e\b\16\1\2\u008e\u0090\3\2\2\2\u008f\u008b\3\2\2\2\u0090"+
		"\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\33\3\2\2"+
		"\2\u0093\u0091\3\2\2\2\u0094\u0095\7\24\2\2\u0095\u0096\5\34\17\2\u0096"+
		"\u0097\b\17\1\2\u0097\u00a4\3\2\2\2\u0098\u0099\7\26\2\2\u0099\u009a\5"+
		"\34\17\2\u009a\u009b\b\17\1\2\u009b\u00a4\3\2\2\2\u009c\u009d\7\17\2\2"+
		"\u009d\u009e\5\34\17\2\u009e\u009f\b\17\1\2\u009f\u00a4\3\2\2\2\u00a0"+
		"\u00a1\5\36\20\2\u00a1\u00a2\b\17\1\2\u00a2\u00a4\3\2\2\2\u00a3\u0094"+
		"\3\2\2\2\u00a3\u0098\3\2\2\2\u00a3\u009c\3\2\2\2\u00a3\u00a0\3\2\2\2\u00a4"+
		"\35\3\2\2\2\u00a5\u00a6\5 \21\2\u00a6\u00a7\b\20\1\2\u00a7\u00b2\3\2\2"+
		"\2\u00a8\u00a9\5\"\22\2\u00a9\u00aa\b\20\1\2\u00aa\u00b2\3\2\2\2\u00ab"+
		"\u00ac\5$\23\2\u00ac\u00ad\b\20\1\2\u00ad\u00b2\3\2\2\2\u00ae\u00af\5"+
		"&\24\2\u00af\u00b0\b\20\1\2\u00b0\u00b2\3\2\2\2\u00b1\u00a5\3\2\2\2\u00b1"+
		"\u00a8\3\2\2\2\u00b1\u00ab\3\2\2\2\u00b1\u00ae\3\2\2\2\u00b2\37\3\2\2"+
		"\2\u00b3\u00b4\7\35\2\2\u00b4\u00b5\b\21\1\2\u00b5!\3\2\2\2\u00b6\u00b7"+
		"\7\34\2\2\u00b7\u00b8\b\22\1\2\u00b8#\3\2\2\2\u00b9\u00ba\7\36\2\2\u00ba"+
		"\u00bb\b\23\1\2\u00bb%\3\2\2\2\u00bc\u00bd\7\37\2\2\u00bd\u00be\b\24\1"+
		"\2\u00be\'\3\2\2\2\f\64?Lep{\u0086\u0091\u00a3\u00b1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}