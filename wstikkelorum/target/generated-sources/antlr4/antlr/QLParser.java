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
		T__3=17, T__2=18, T__1=19, T__0=20, WHITESPACE=21, COMMENT=22, TYPE=23, 
		INT=24, STRING=25, ID=26;
	public static final String[] tokenNames = {
		"<INVALID>", "'/'", "'!='", "':'", "'>='", "'||'", "'{'", "'=='", "'<'", 
		"'&&'", "'}'", "'>'", "'if'", "'!'", "'<='", "'('", "')'", "'*'", "'+'", 
		"'form'", "'-'", "WHITESPACE", "COMMENT", "TYPE", "INT", "STRING", "ID"
	};
	public static final int
		RULE_file = 0, RULE_form = 1, RULE_body = 2, RULE_statement = 3, RULE_question = 4, 
		RULE_assignmentQuestion = 5, RULE_ifStatement = 6, RULE_orExpression = 7, 
		RULE_andExpression = 8, RULE_relExpression = 9, RULE_addExpression = 10, 
		RULE_mulExpression = 11, RULE_unExpression = 12, RULE_literal = 13, RULE_intLiteral = 14, 
		RULE_variable = 15;
	public static final String[] ruleNames = {
		"file", "form", "body", "statement", "question", "assignmentQuestion", 
		"ifStatement", "orExpression", "andExpression", "relExpression", "addExpression", 
		"mulExpression", "unExpression", "literal", "intLiteral", "variable"
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
			setState(32); form();
			setState(33); match(EOF);
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
			setState(35); match(T__1);
			setState(36); ((FormContext)_localctx).ID = match(ID);
			setState(37); ((FormContext)_localctx).body = body();
			 ((FormContext)_localctx).result =  new Form((((FormContext)_localctx).ID!=null?((FormContext)_localctx).ID.getText():null), ((FormContext)_localctx).body.result); 
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
		public StatementContext statement;
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
			setState(40); match(T__14);
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8 || _la==ID) {
				{
				{
				setState(41); ((BodyContext)_localctx).statement = statement();
				}
				}
				setState(46);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(47); match(T__10);
			 _localctx.result.add(((BodyContext)_localctx).statement.result); 
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
		public Statement result;
		public QuestionContext question;
		public AssignmentQuestionContext assignmentQuestion;
		public IfStatementContext ifStatement;
		public AssignmentQuestionContext assignmentQuestion() {
			return getRuleContext(AssignmentQuestionContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_statement);
		try {
			setState(59);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(50); ((StatementContext)_localctx).question = question();
				 ((StatementContext)_localctx).result =  new Statement(((StatementContext)_localctx).question.result); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(53); ((StatementContext)_localctx).assignmentQuestion = assignmentQuestion();
				 ((StatementContext)_localctx).result =  new Statement(((StatementContext)_localctx).assignmentQuestion.result); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(56); ((StatementContext)_localctx).ifStatement = ifStatement();
				 ((StatementContext)_localctx).result =  new Statement(((StatementContext)_localctx).ifStatement.result); 
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
		public Question result;
		public Token ID;
		public Token STRING;
		public Token TYPE;
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public TerminalNode TYPE() { return getToken(QLParser.TYPE, 0); }
		public TerminalNode STRING() { return getToken(QLParser.STRING, 0); }
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
			enterOuterAlt(_localctx, 1);
			{
			setState(61); ((QuestionContext)_localctx).ID = match(ID);
			setState(62); match(T__17);
			setState(63); ((QuestionContext)_localctx).STRING = match(STRING);
			setState(64); ((QuestionContext)_localctx).TYPE = match(TYPE);
			 ((QuestionContext)_localctx).result =  new Question((((QuestionContext)_localctx).ID!=null?((QuestionContext)_localctx).ID.getText():null), (((QuestionContext)_localctx).STRING!=null?((QuestionContext)_localctx).STRING.getText():null), (((QuestionContext)_localctx).TYPE!=null?((QuestionContext)_localctx).TYPE.getText():null)); 
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

	public static class AssignmentQuestionContext extends ParserRuleContext {
		public AssignmentQuestion result;
		public Token ID;
		public Token STRING;
		public Token TYPE;
		public OrExpressionContext orExpression;
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public TerminalNode TYPE() { return getToken(QLParser.TYPE, 0); }
		public OrExpressionContext orExpression() {
			return getRuleContext(OrExpressionContext.class,0);
		}
		public TerminalNode STRING() { return getToken(QLParser.STRING, 0); }
		public AssignmentQuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentQuestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterAssignmentQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitAssignmentQuestion(this);
		}
	}

	public final AssignmentQuestionContext assignmentQuestion() throws RecognitionException {
		AssignmentQuestionContext _localctx = new AssignmentQuestionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignmentQuestion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67); ((AssignmentQuestionContext)_localctx).ID = match(ID);
			setState(68); match(T__17);
			setState(69); ((AssignmentQuestionContext)_localctx).STRING = match(STRING);
			setState(70); ((AssignmentQuestionContext)_localctx).TYPE = match(TYPE);
			setState(71); match(T__5);
			setState(72); ((AssignmentQuestionContext)_localctx).orExpression = orExpression();
			setState(73); match(T__4);
			 ((AssignmentQuestionContext)_localctx).result =  new AssignmentQuestion((((AssignmentQuestionContext)_localctx).ID!=null?((AssignmentQuestionContext)_localctx).ID.getText():null), (((AssignmentQuestionContext)_localctx).STRING!=null?((AssignmentQuestionContext)_localctx).STRING.getText():null), (((AssignmentQuestionContext)_localctx).TYPE!=null?((AssignmentQuestionContext)_localctx).TYPE.getText():null), ((AssignmentQuestionContext)_localctx).orExpression.result); 
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
		enterRule(_localctx, 12, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76); match(T__8);
			setState(77); match(T__5);
			setState(78); ((IfStatementContext)_localctx).orExpression = orExpression();
			setState(79); match(T__4);
			setState(80); ((IfStatementContext)_localctx).body = body();
			 ((IfStatementContext)_localctx).result =  new IfStatement(((IfStatementContext)_localctx).orExpression.result, ((IfStatementContext)_localctx).body.result); 
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
		enterRule(_localctx, 14, RULE_orExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83); ((OrExpressionContext)_localctx).lhs = andExpression();
			 ((OrExpressionContext)_localctx).result =  ((OrExpressionContext)_localctx).lhs.result; 
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__15) {
				{
				{
				setState(85); match(T__15);
				setState(86); ((OrExpressionContext)_localctx).rhs = andExpression();
				 ((OrExpressionContext)_localctx).result =  new OrExpression(_localctx.result, ((OrExpressionContext)_localctx).rhs.result); 
				}
				}
				setState(93);
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
		enterRule(_localctx, 16, RULE_andExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94); ((AndExpressionContext)_localctx).lhs = relExpression();
			 ((AndExpressionContext)_localctx).result =  ((AndExpressionContext)_localctx).lhs.result; 
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(96); match(T__11);
				setState(97); ((AndExpressionContext)_localctx).rhs = relExpression();
				 ((AndExpressionContext)_localctx).result =  new AndExpression(_localctx.result, ((AndExpressionContext)_localctx).rhs.result); 
				}
				}
				setState(104);
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
		enterRule(_localctx, 18, RULE_relExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105); ((RelExpressionContext)_localctx).lhs = addExpression();
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__16) | (1L << T__13) | (1L << T__12) | (1L << T__9) | (1L << T__6))) != 0)) {
				{
				{
				setState(106);
				((RelExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__16) | (1L << T__13) | (1L << T__12) | (1L << T__9) | (1L << T__6))) != 0)) ) {
					((RelExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(107); ((RelExpressionContext)_localctx).rhs = addExpression();

							if ((((RelExpressionContext)_localctx).op!=null?((RelExpressionContext)_localctx).op.getText():null).equals("<")) {
							  ((RelExpressionContext)_localctx).result =  new LT(_localctx.result, ((RelExpressionContext)_localctx).rhs.result);
							}
							if ((((RelExpressionContext)_localctx).op!=null?((RelExpressionContext)_localctx).op.getText():null).equals("<=")) {
							  ((RelExpressionContext)_localctx).result =  new LEq(_localctx.result, ((RelExpressionContext)_localctx).rhs.result);      
							}
							if ((((RelExpressionContext)_localctx).op!=null?((RelExpressionContext)_localctx).op.getText():null).equals(">")) {
							  ((RelExpressionContext)_localctx).result =  new GT(_localctx.result, ((RelExpressionContext)_localctx).rhs.result);
							}
							if ((((RelExpressionContext)_localctx).op!=null?((RelExpressionContext)_localctx).op.getText():null).equals(">=")) {
							  ((RelExpressionContext)_localctx).result =  new GEq(_localctx.result, ((RelExpressionContext)_localctx).rhs.result);      
							}
							if ((((RelExpressionContext)_localctx).op!=null?((RelExpressionContext)_localctx).op.getText():null).equals("==")) {
							  ((RelExpressionContext)_localctx).result =  new Eq(_localctx.result, ((RelExpressionContext)_localctx).rhs.result);
							}
							if ((((RelExpressionContext)_localctx).op!=null?((RelExpressionContext)_localctx).op.getText():null).equals("!=")) {
							  ((RelExpressionContext)_localctx).result =  new Neq(_localctx.result, ((RelExpressionContext)_localctx).rhs.result);
							}
						
				}
				}
				setState(114);
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
		enterRule(_localctx, 20, RULE_addExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); ((AddExpressionContext)_localctx).lhs = mulExpression();
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2 || _la==T__0) {
				{
				{
				setState(116);
				((AddExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__2 || _la==T__0) ) {
					((AddExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(117); ((AddExpressionContext)_localctx).rhs = mulExpression();

							if((((AddExpressionContext)_localctx).op!=null?((AddExpressionContext)_localctx).op.getText():null).equals("+")){
								((AddExpressionContext)_localctx).result =  new Add(_localctx.result, ((AddExpressionContext)_localctx).rhs.result);
							}
							if((((AddExpressionContext)_localctx).op!=null?((AddExpressionContext)_localctx).op.getText():null).equals("-")){
								((AddExpressionContext)_localctx).result =  new Sub(_localctx.result, ((AddExpressionContext)_localctx).rhs.result);
							}
						
				}
				}
				setState(124);
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
		enterRule(_localctx, 22, RULE_mulExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125); ((MulExpressionContext)_localctx).lhs = unExpression();
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__19 || _la==T__3) {
				{
				{
				setState(126);
				((MulExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__3) ) {
					((MulExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(127); ((MulExpressionContext)_localctx).rhs = unExpression();

							if((((MulExpressionContext)_localctx).op!=null?((MulExpressionContext)_localctx).op.getText():null).equals("*")){
								((MulExpressionContext)_localctx).result =  new Mul(_localctx.result, ((MulExpressionContext)_localctx).rhs.result);
							}
							if((((MulExpressionContext)_localctx).op!=null?((MulExpressionContext)_localctx).op.getText():null).equals("/")){
								((MulExpressionContext)_localctx).result =  new Div(_localctx.result, ((MulExpressionContext)_localctx).rhs.result);
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

	public static class UnExpressionContext extends ParserRuleContext {
		public Expression result;
		public UnExpressionContext unExpression;
		public LiteralContext literal;
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
		enterRule(_localctx, 24, RULE_unExpression);
		try {
			setState(150);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(135); match(T__2);
				setState(136); ((UnExpressionContext)_localctx).unExpression = unExpression();
				 ((UnExpressionContext)_localctx).result =  new Pos(_localctx.result); 
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(139); match(T__0);
				setState(140); ((UnExpressionContext)_localctx).unExpression = unExpression();
				 ((UnExpressionContext)_localctx).result =  new Neg(_localctx.result); 
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 3);
				{
				setState(143); match(T__7);
				setState(144); ((UnExpressionContext)_localctx).unExpression = unExpression();
				 ((UnExpressionContext)_localctx).result =  new Not(_localctx.result); 
				}
				break;
			case INT:
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(147); ((UnExpressionContext)_localctx).literal = literal();
				 ((UnExpressionContext)_localctx).result =  ((UnExpressionContext)_localctx).literal.result; 
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
		public VariableContext variable;
		public IntLiteralContext intLiteral() {
			return getRuleContext(IntLiteralContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
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
		enterRule(_localctx, 26, RULE_literal);
		try {
			setState(158);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(152); ((LiteralContext)_localctx).intLiteral = intLiteral();
				 ((LiteralContext)_localctx).result =  new Literal(((LiteralContext)_localctx).intLiteral.result); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(155); ((LiteralContext)_localctx).variable = variable();
				((LiteralContext)_localctx).result =  new Literal(((LiteralContext)_localctx).variable.result); 
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
		enterRule(_localctx, 28, RULE_intLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160); ((IntLiteralContext)_localctx).INT = match(INT);
			 ((IntLiteralContext)_localctx).result =  new IntLiteral(Integer.valueOf((((IntLiteralContext)_localctx).INT!=null?((IntLiteralContext)_localctx).INT.getText():null))); 
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
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
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
		enterRule(_localctx, 30, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163); ((VariableContext)_localctx).ID = match(ID);
			 ((VariableContext)_localctx).result =  new Variable((((VariableContext)_localctx).ID!=null?((VariableContext)_localctx).ID.getText():null)); 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\34\u00a9\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\7\4-\n\4\f\4\16\4\60\13\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5>\n\5\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\7\t\\\n\t\f\t\16\t_\13\t\3\n\3\n\3\n\3\n\3\n\3\n\7"+
		"\ng\n\n\f\n\16\nj\13\n\3\13\3\13\3\13\3\13\3\13\7\13q\n\13\f\13\16\13"+
		"t\13\13\3\f\3\f\3\f\3\f\3\f\7\f{\n\f\f\f\16\f~\13\f\3\r\3\r\3\r\3\r\3"+
		"\r\7\r\u0085\n\r\f\r\16\r\u0088\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0099\n\16\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\5\17\u00a1\n\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\2\2"+
		"\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\5\7\2\4\4\6\6\t\n\r\r\20"+
		"\20\4\2\24\24\26\26\4\2\3\3\23\23\u00a4\2\"\3\2\2\2\4%\3\2\2\2\6*\3\2"+
		"\2\2\b=\3\2\2\2\n?\3\2\2\2\fE\3\2\2\2\16N\3\2\2\2\20U\3\2\2\2\22`\3\2"+
		"\2\2\24k\3\2\2\2\26u\3\2\2\2\30\177\3\2\2\2\32\u0098\3\2\2\2\34\u00a0"+
		"\3\2\2\2\36\u00a2\3\2\2\2 \u00a5\3\2\2\2\"#\5\4\3\2#$\7\2\2\3$\3\3\2\2"+
		"\2%&\7\25\2\2&\'\7\34\2\2\'(\5\6\4\2()\b\3\1\2)\5\3\2\2\2*.\7\b\2\2+-"+
		"\5\b\5\2,+\3\2\2\2-\60\3\2\2\2.,\3\2\2\2./\3\2\2\2/\61\3\2\2\2\60.\3\2"+
		"\2\2\61\62\7\f\2\2\62\63\b\4\1\2\63\7\3\2\2\2\64\65\5\n\6\2\65\66\b\5"+
		"\1\2\66>\3\2\2\2\678\5\f\7\289\b\5\1\29>\3\2\2\2:;\5\16\b\2;<\b\5\1\2"+
		"<>\3\2\2\2=\64\3\2\2\2=\67\3\2\2\2=:\3\2\2\2>\t\3\2\2\2?@\7\34\2\2@A\7"+
		"\5\2\2AB\7\33\2\2BC\7\31\2\2CD\b\6\1\2D\13\3\2\2\2EF\7\34\2\2FG\7\5\2"+
		"\2GH\7\33\2\2HI\7\31\2\2IJ\7\21\2\2JK\5\20\t\2KL\7\22\2\2LM\b\7\1\2M\r"+
		"\3\2\2\2NO\7\16\2\2OP\7\21\2\2PQ\5\20\t\2QR\7\22\2\2RS\5\6\4\2ST\b\b\1"+
		"\2T\17\3\2\2\2UV\5\22\n\2V]\b\t\1\2WX\7\7\2\2XY\5\22\n\2YZ\b\t\1\2Z\\"+
		"\3\2\2\2[W\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2\2^\21\3\2\2\2_]\3\2\2"+
		"\2`a\5\24\13\2ah\b\n\1\2bc\7\13\2\2cd\5\24\13\2de\b\n\1\2eg\3\2\2\2fb"+
		"\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2i\23\3\2\2\2jh\3\2\2\2kr\5\26\f"+
		"\2lm\t\2\2\2mn\5\26\f\2no\b\13\1\2oq\3\2\2\2pl\3\2\2\2qt\3\2\2\2rp\3\2"+
		"\2\2rs\3\2\2\2s\25\3\2\2\2tr\3\2\2\2u|\5\30\r\2vw\t\3\2\2wx\5\30\r\2x"+
		"y\b\f\1\2y{\3\2\2\2zv\3\2\2\2{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\27\3\2\2"+
		"\2~|\3\2\2\2\177\u0086\5\32\16\2\u0080\u0081\t\4\2\2\u0081\u0082\5\32"+
		"\16\2\u0082\u0083\b\r\1\2\u0083\u0085\3\2\2\2\u0084\u0080\3\2\2\2\u0085"+
		"\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\31\3\2\2"+
		"\2\u0088\u0086\3\2\2\2\u0089\u008a\7\24\2\2\u008a\u008b\5\32\16\2\u008b"+
		"\u008c\b\16\1\2\u008c\u0099\3\2\2\2\u008d\u008e\7\26\2\2\u008e\u008f\5"+
		"\32\16\2\u008f\u0090\b\16\1\2\u0090\u0099\3\2\2\2\u0091\u0092\7\17\2\2"+
		"\u0092\u0093\5\32\16\2\u0093\u0094\b\16\1\2\u0094\u0099\3\2\2\2\u0095"+
		"\u0096\5\34\17\2\u0096\u0097\b\16\1\2\u0097\u0099\3\2\2\2\u0098\u0089"+
		"\3\2\2\2\u0098\u008d\3\2\2\2\u0098\u0091\3\2\2\2\u0098\u0095\3\2\2\2\u0099"+
		"\33\3\2\2\2\u009a\u009b\5\36\20\2\u009b\u009c\b\17\1\2\u009c\u00a1\3\2"+
		"\2\2\u009d\u009e\5 \21\2\u009e\u009f\b\17\1\2\u009f\u00a1\3\2\2\2\u00a0"+
		"\u009a\3\2\2\2\u00a0\u009d\3\2\2\2\u00a1\35\3\2\2\2\u00a2\u00a3\7\32\2"+
		"\2\u00a3\u00a4\b\20\1\2\u00a4\37\3\2\2\2\u00a5\u00a6\7\34\2\2\u00a6\u00a7"+
		"\b\21\1\2\u00a7!\3\2\2\2\13.=]hr|\u0086\u0098\u00a0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}