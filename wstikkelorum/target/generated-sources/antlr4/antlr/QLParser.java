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
				setState(41); statement(_localctx.result);
				}
				}
				setState(46);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(47); match(T__10);
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
			setState(58);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(49); ((StatementContext)_localctx).question = question();
				 _localctx.result.add(new Statement(((StatementContext)_localctx).question.result)); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(52); ((StatementContext)_localctx).assignmentQuestion = assignmentQuestion();
				 _localctx.result.add(new Statement(((StatementContext)_localctx).assignmentQuestion.result)); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(55); ((StatementContext)_localctx).ifStatement = ifStatement();
				 _localctx.result.add(new Statement(((StatementContext)_localctx).ifStatement.result)); 
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
			setState(60); ((QuestionContext)_localctx).ID = match(ID);
			setState(61); match(T__17);
			setState(62); ((QuestionContext)_localctx).STRING = match(STRING);
			setState(63); ((QuestionContext)_localctx).TYPE = match(TYPE);
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
			setState(66); ((AssignmentQuestionContext)_localctx).ID = match(ID);
			setState(67); match(T__17);
			setState(68); ((AssignmentQuestionContext)_localctx).STRING = match(STRING);
			setState(69); ((AssignmentQuestionContext)_localctx).TYPE = match(TYPE);
			setState(70); match(T__5);
			setState(71); ((AssignmentQuestionContext)_localctx).orExpression = orExpression();
			setState(72); match(T__4);
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
			setState(75); match(T__8);
			setState(76); match(T__5);
			setState(77); ((IfStatementContext)_localctx).orExpression = orExpression();
			setState(78); match(T__4);
			setState(79); ((IfStatementContext)_localctx).body = body();
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
			setState(82); ((OrExpressionContext)_localctx).lhs = andExpression();
			 ((OrExpressionContext)_localctx).result =  ((OrExpressionContext)_localctx).lhs.result; 
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__15) {
				{
				{
				setState(84); match(T__15);
				setState(85); ((OrExpressionContext)_localctx).rhs = andExpression();
				 ((OrExpressionContext)_localctx).result =  new OrExpression(_localctx.result, ((OrExpressionContext)_localctx).rhs.result); 
				}
				}
				setState(92);
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
			setState(93); ((AndExpressionContext)_localctx).lhs = relExpression();
			 ((AndExpressionContext)_localctx).result =  ((AndExpressionContext)_localctx).lhs.result; 
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(95); match(T__11);
				setState(96); ((AndExpressionContext)_localctx).rhs = relExpression();
				 ((AndExpressionContext)_localctx).result =  new AndExpression(_localctx.result, ((AndExpressionContext)_localctx).rhs.result); 
				}
				}
				setState(103);
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
			setState(104); ((RelExpressionContext)_localctx).lhs = addExpression();
			 ((RelExpressionContext)_localctx).result =  ((RelExpressionContext)_localctx).lhs.result; 
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
							  ((RelExpressionContext)_localctx).result =  new NEq(_localctx.result, ((RelExpressionContext)_localctx).rhs.result);
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
			 ((AddExpressionContext)_localctx).result =  ((AddExpressionContext)_localctx).lhs.result; 
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2 || _la==T__0) {
				{
				{
				setState(117);
				((AddExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__2 || _la==T__0) ) {
					((AddExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(118); ((AddExpressionContext)_localctx).rhs = mulExpression();

							if((((AddExpressionContext)_localctx).op!=null?((AddExpressionContext)_localctx).op.getText():null).equals("+")){
								((AddExpressionContext)_localctx).result =  new Add(_localctx.result, ((AddExpressionContext)_localctx).rhs.result);
							}
							if((((AddExpressionContext)_localctx).op!=null?((AddExpressionContext)_localctx).op.getText():null).equals("-")){
								((AddExpressionContext)_localctx).result =  new Sub(_localctx.result, ((AddExpressionContext)_localctx).rhs.result);
							}
						
				}
				}
				setState(125);
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
			setState(126); ((MulExpressionContext)_localctx).lhs = unExpression();
			 ((MulExpressionContext)_localctx).result =  ((MulExpressionContext)_localctx).lhs.result; 
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__19 || _la==T__3) {
				{
				{
				setState(128);
				((MulExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__3) ) {
					((MulExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(129); ((MulExpressionContext)_localctx).rhs = unExpression();

							if((((MulExpressionContext)_localctx).op!=null?((MulExpressionContext)_localctx).op.getText():null).equals("*")){
								((MulExpressionContext)_localctx).result =  new Mul(_localctx.result, ((MulExpressionContext)_localctx).rhs.result);
							}
							if((((MulExpressionContext)_localctx).op!=null?((MulExpressionContext)_localctx).op.getText():null).equals("/")){
								((MulExpressionContext)_localctx).result =  new Div(_localctx.result, ((MulExpressionContext)_localctx).rhs.result);
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
		enterRule(_localctx, 24, RULE_unExpression);
		try {
			setState(152);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(137); match(T__2);
				setState(138); ((UnExpressionContext)_localctx).x = unExpression();
				 ((UnExpressionContext)_localctx).result =  new Pos(((UnExpressionContext)_localctx).x.result); 
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(141); match(T__0);
				setState(142); ((UnExpressionContext)_localctx).x = unExpression();
				 ((UnExpressionContext)_localctx).result =  new Neg(((UnExpressionContext)_localctx).x.result); 
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 3);
				{
				setState(145); match(T__7);
				setState(146); ((UnExpressionContext)_localctx).x = unExpression();
				 ((UnExpressionContext)_localctx).result =  new Not(((UnExpressionContext)_localctx).x.result); 
				}
				break;
			case INT:
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(149); ((UnExpressionContext)_localctx).y = literal();
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
			setState(160);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(154); ((LiteralContext)_localctx).intLiteral = intLiteral();
				 ((LiteralContext)_localctx).result =  new Literal(((LiteralContext)_localctx).intLiteral.result); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(157); ((LiteralContext)_localctx).variable = variable();
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
			setState(162); ((IntLiteralContext)_localctx).INT = match(INT);
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
			setState(165); ((VariableContext)_localctx).ID = match(ID);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\34\u00ab\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\7\4-\n\4\f\4\16\4\60\13\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5=\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\7\t[\n\t\f\t\16\t^\13\t\3\n\3\n\3\n\3\n\3\n\3\n\7\nf\n"+
		"\n\f\n\16\ni\13\n\3\13\3\13\3\13\3\13\3\13\3\13\7\13q\n\13\f\13\16\13"+
		"t\13\13\3\f\3\f\3\f\3\f\3\f\3\f\7\f|\n\f\f\f\16\f\177\13\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\7\r\u0087\n\r\f\r\16\r\u008a\13\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u009b\n\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\5\17\u00a3\n\17\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\5\7\2\4\4\6\6\t"+
		"\n\r\r\20\20\4\2\24\24\26\26\4\2\3\3\23\23\u00a6\2\"\3\2\2\2\4%\3\2\2"+
		"\2\6*\3\2\2\2\b<\3\2\2\2\n>\3\2\2\2\fD\3\2\2\2\16M\3\2\2\2\20T\3\2\2\2"+
		"\22_\3\2\2\2\24j\3\2\2\2\26u\3\2\2\2\30\u0080\3\2\2\2\32\u009a\3\2\2\2"+
		"\34\u00a2\3\2\2\2\36\u00a4\3\2\2\2 \u00a7\3\2\2\2\"#\5\4\3\2#$\7\2\2\3"+
		"$\3\3\2\2\2%&\7\25\2\2&\'\7\34\2\2\'(\5\6\4\2()\b\3\1\2)\5\3\2\2\2*.\7"+
		"\b\2\2+-\5\b\5\2,+\3\2\2\2-\60\3\2\2\2.,\3\2\2\2./\3\2\2\2/\61\3\2\2\2"+
		"\60.\3\2\2\2\61\62\7\f\2\2\62\7\3\2\2\2\63\64\5\n\6\2\64\65\b\5\1\2\65"+
		"=\3\2\2\2\66\67\5\f\7\2\678\b\5\1\28=\3\2\2\29:\5\16\b\2:;\b\5\1\2;=\3"+
		"\2\2\2<\63\3\2\2\2<\66\3\2\2\2<9\3\2\2\2=\t\3\2\2\2>?\7\34\2\2?@\7\5\2"+
		"\2@A\7\33\2\2AB\7\31\2\2BC\b\6\1\2C\13\3\2\2\2DE\7\34\2\2EF\7\5\2\2FG"+
		"\7\33\2\2GH\7\31\2\2HI\7\21\2\2IJ\5\20\t\2JK\7\22\2\2KL\b\7\1\2L\r\3\2"+
		"\2\2MN\7\16\2\2NO\7\21\2\2OP\5\20\t\2PQ\7\22\2\2QR\5\6\4\2RS\b\b\1\2S"+
		"\17\3\2\2\2TU\5\22\n\2U\\\b\t\1\2VW\7\7\2\2WX\5\22\n\2XY\b\t\1\2Y[\3\2"+
		"\2\2ZV\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]\21\3\2\2\2^\\\3\2\2\2"+
		"_`\5\24\13\2`g\b\n\1\2ab\7\13\2\2bc\5\24\13\2cd\b\n\1\2df\3\2\2\2ea\3"+
		"\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2h\23\3\2\2\2ig\3\2\2\2jk\5\26\f\2"+
		"kr\b\13\1\2lm\t\2\2\2mn\5\26\f\2no\b\13\1\2oq\3\2\2\2pl\3\2\2\2qt\3\2"+
		"\2\2rp\3\2\2\2rs\3\2\2\2s\25\3\2\2\2tr\3\2\2\2uv\5\30\r\2v}\b\f\1\2wx"+
		"\t\3\2\2xy\5\30\r\2yz\b\f\1\2z|\3\2\2\2{w\3\2\2\2|\177\3\2\2\2}{\3\2\2"+
		"\2}~\3\2\2\2~\27\3\2\2\2\177}\3\2\2\2\u0080\u0081\5\32\16\2\u0081\u0088"+
		"\b\r\1\2\u0082\u0083\t\4\2\2\u0083\u0084\5\32\16\2\u0084\u0085\b\r\1\2"+
		"\u0085\u0087\3\2\2\2\u0086\u0082\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086"+
		"\3\2\2\2\u0088\u0089\3\2\2\2\u0089\31\3\2\2\2\u008a\u0088\3\2\2\2\u008b"+
		"\u008c\7\24\2\2\u008c\u008d\5\32\16\2\u008d\u008e\b\16\1\2\u008e\u009b"+
		"\3\2\2\2\u008f\u0090\7\26\2\2\u0090\u0091\5\32\16\2\u0091\u0092\b\16\1"+
		"\2\u0092\u009b\3\2\2\2\u0093\u0094\7\17\2\2\u0094\u0095\5\32\16\2\u0095"+
		"\u0096\b\16\1\2\u0096\u009b\3\2\2\2\u0097\u0098\5\34\17\2\u0098\u0099"+
		"\b\16\1\2\u0099\u009b\3\2\2\2\u009a\u008b\3\2\2\2\u009a\u008f\3\2\2\2"+
		"\u009a\u0093\3\2\2\2\u009a\u0097\3\2\2\2\u009b\33\3\2\2\2\u009c\u009d"+
		"\5\36\20\2\u009d\u009e\b\17\1\2\u009e\u00a3\3\2\2\2\u009f\u00a0\5 \21"+
		"\2\u00a0\u00a1\b\17\1\2\u00a1\u00a3\3\2\2\2\u00a2\u009c\3\2\2\2\u00a2"+
		"\u009f\3\2\2\2\u00a3\35\3\2\2\2\u00a4\u00a5\7\32\2\2\u00a5\u00a6\b\20"+
		"\1\2\u00a6\37\3\2\2\2\u00a7\u00a8\7\34\2\2\u00a8\u00a9\b\21\1\2\u00a9"+
		"!\3\2\2\2\13.<\\gr}\u0088\u009a\u00a2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}