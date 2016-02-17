// Generated from QL.g4 by ANTLR 4.5.2

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
	static { RuntimeMetaData.checkVersion("4.5.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, WHITESPACE=21, COMMENT=22, BOOLEAN=23, INTEGER=24, 
		STRING=25, BOOL=26, INT=27, STR=28, ID=29;
	public static final int
		RULE_file = 0, RULE_form = 1, RULE_body = 2, RULE_statement = 3, RULE_question = 4, 
		RULE_assignmentQuestion = 5, RULE_ifStatement = 6, RULE_variable = 7, 
		RULE_variableType = 8, RULE_orExpression = 9, RULE_andExpression = 10, 
		RULE_relExpression = 11, RULE_addExpression = 12, RULE_mulExpression = 13, 
		RULE_unExpression = 14, RULE_literal = 15, RULE_intLiteral = 16, RULE_boolLiteral = 17, 
		RULE_stringLiteral = 18, RULE_variableExpression = 19;
	public static final String[] ruleNames = {
		"file", "form", "body", "statement", "question", "assignmentQuestion", 
		"ifStatement", "variable", "variableType", "orExpression", "andExpression", 
		"relExpression", "addExpression", "mulExpression", "unExpression", "literal", 
		"intLiteral", "boolLiteral", "stringLiteral", "variableExpression"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'('", "')'", "'if'", "':'", "'||'", "'&&'", 
		"'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'+'", "'-'", "'*'", "'/'", 
		"'!'", null, null, "'boolean'", "'int'", "'string'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, "WHITESPACE", "COMMENT", 
		"BOOLEAN", "INTEGER", "STRING", "BOOL", "INT", "STR", "ID"
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
	public static class FileContext extends ParserRuleContext {
		public FormContext form() {
			return getRuleContext(FormContext.class,0);
		}
		public TerminalNode EOF() { return getToken(QLParser.EOF, 0); }
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
			setState(40);
			form();
			setState(41);
			match(EOF);
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
			setState(43);
			match(T__0);
			setState(44);
			((FormContext)_localctx).ID = match(ID);
			setState(45);
			((FormContext)_localctx).body = body();
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
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
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
			setState(48);
			match(T__1);
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5 || _la==ID) {
				{
				{
				setState(49);
				statement(_localctx.result);
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(55);
			match(T__2);
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
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public AssignmentQuestionContext assignmentQuestion() {
			return getRuleContext(AssignmentQuestionContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
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
			setState(66);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				((StatementContext)_localctx).question = question();
				 _localctx.result.add(new Statement(((StatementContext)_localctx).question.result)); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				((StatementContext)_localctx).assignmentQuestion = assignmentQuestion();
				 _localctx.result.add(new Statement(((StatementContext)_localctx).assignmentQuestion.result)); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(63);
				((StatementContext)_localctx).ifStatement = ifStatement();
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
		public VariableContext variable;
		public Token STR;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode STR() { return getToken(QLParser.STR, 0); }
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
			setState(68);
			((QuestionContext)_localctx).variable = variable();
			setState(69);
			((QuestionContext)_localctx).STR = match(STR);
			 ((QuestionContext)_localctx).result =  new Question(((QuestionContext)_localctx).variable.result, (((QuestionContext)_localctx).STR!=null?((QuestionContext)_localctx).STR.getText():null)); 
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
		public VariableContext variable;
		public Token STR;
		public OrExpressionContext orExpression;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode STR() { return getToken(QLParser.STR, 0); }
		public OrExpressionContext orExpression() {
			return getRuleContext(OrExpressionContext.class,0);
		}
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
			setState(72);
			((AssignmentQuestionContext)_localctx).variable = variable();
			setState(73);
			((AssignmentQuestionContext)_localctx).STR = match(STR);
			setState(74);
			match(T__3);
			setState(75);
			((AssignmentQuestionContext)_localctx).orExpression = orExpression();
			setState(76);
			match(T__4);
			 ((AssignmentQuestionContext)_localctx).result =  new AssignmentQuestion(((AssignmentQuestionContext)_localctx).variable.result, (((AssignmentQuestionContext)_localctx).STR!=null?((AssignmentQuestionContext)_localctx).STR.getText():null), ((AssignmentQuestionContext)_localctx).orExpression.result); 
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
			setState(79);
			match(T__5);
			setState(80);
			match(T__3);
			setState(81);
			((IfStatementContext)_localctx).orExpression = orExpression();
			setState(82);
			match(T__4);
			setState(83);
			((IfStatementContext)_localctx).body = body();
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
		enterRule(_localctx, 14, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			((VariableContext)_localctx).ID = match(ID);
			setState(87);
			match(T__6);
			setState(88);
			((VariableContext)_localctx).variableType = variableType();
			 ((VariableContext)_localctx).result =  new Variable((((VariableContext)_localctx).ID!=null?((VariableContext)_localctx).ID.getText():null), ((VariableContext)_localctx).variableType.result); 
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
		public TerminalNode BOOLEAN() { return getToken(QLParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(QLParser.INTEGER, 0); }
		public TerminalNode STRING() { return getToken(QLParser.STRING, 0); }
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
		enterRule(_localctx, 16, RULE_variableType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			((VariableTypeContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << INTEGER) | (1L << STRING))) != 0)) ) {
				((VariableTypeContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			 ((VariableTypeContext)_localctx).result =  new VariableType((((VariableTypeContext)_localctx).type!=null?((VariableTypeContext)_localctx).type.getText():null)); 
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
		enterRule(_localctx, 18, RULE_orExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			((OrExpressionContext)_localctx).lhs = andExpression();
			 ((OrExpressionContext)_localctx).result =  ((OrExpressionContext)_localctx).lhs.result; 
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(96);
				match(T__7);
				setState(97);
				((OrExpressionContext)_localctx).rhs = andExpression();
				 ((OrExpressionContext)_localctx).result =  new OrExpression(_localctx.result, ((OrExpressionContext)_localctx).rhs.result); 
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

	public static class AndExpressionContext extends ParserRuleContext {
		public Expression result;
		public RelExpressionContext lhs;
		public RelExpressionContext rhs;
		public List<RelExpressionContext> relExpression() {
			return getRuleContexts(RelExpressionContext.class);
		}
		public RelExpressionContext relExpression(int i) {
			return getRuleContext(RelExpressionContext.class,i);
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
		enterRule(_localctx, 20, RULE_andExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			((AndExpressionContext)_localctx).lhs = relExpression();
			 ((AndExpressionContext)_localctx).result =  ((AndExpressionContext)_localctx).lhs.result; 
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(107);
				match(T__8);
				setState(108);
				((AndExpressionContext)_localctx).rhs = relExpression();
				 ((AndExpressionContext)_localctx).result =  new AndExpression(_localctx.result, ((AndExpressionContext)_localctx).rhs.result); 
				}
				}
				setState(115);
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
		enterRule(_localctx, 22, RULE_relExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			((RelExpressionContext)_localctx).lhs = addExpression();
			 ((RelExpressionContext)_localctx).result =  ((RelExpressionContext)_localctx).lhs.result; 
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14))) != 0)) {
				{
				{
				setState(118);
				((RelExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14))) != 0)) ) {
					((RelExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(119);
				((RelExpressionContext)_localctx).rhs = addExpression();

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
		enterRule(_localctx, 24, RULE_addExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			((AddExpressionContext)_localctx).lhs = mulExpression();
			 ((AddExpressionContext)_localctx).result =  ((AddExpressionContext)_localctx).lhs.result; 
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__15 || _la==T__16) {
				{
				{
				setState(129);
				((AddExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
					((AddExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(130);
				((AddExpressionContext)_localctx).rhs = mulExpression();

							if((((AddExpressionContext)_localctx).op!=null?((AddExpressionContext)_localctx).op.getText():null).equals("+")){
								((AddExpressionContext)_localctx).result =  new Add(_localctx.result, ((AddExpressionContext)_localctx).rhs.result);
							}
							if((((AddExpressionContext)_localctx).op!=null?((AddExpressionContext)_localctx).op.getText():null).equals("-")){
								((AddExpressionContext)_localctx).result =  new Sub(_localctx.result, ((AddExpressionContext)_localctx).rhs.result);
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

	public static class MulExpressionContext extends ParserRuleContext {
		public Expression result;
		public UnExpressionContext lhs;
		public Token op;
		public UnExpressionContext rhs;
		public List<UnExpressionContext> unExpression() {
			return getRuleContexts(UnExpressionContext.class);
		}
		public UnExpressionContext unExpression(int i) {
			return getRuleContext(UnExpressionContext.class,i);
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
		enterRule(_localctx, 26, RULE_mulExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			((MulExpressionContext)_localctx).lhs = unExpression();
			 ((MulExpressionContext)_localctx).result =  ((MulExpressionContext)_localctx).lhs.result; 
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__17 || _la==T__18) {
				{
				{
				setState(140);
				((MulExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__17 || _la==T__18) ) {
					((MulExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(141);
				((MulExpressionContext)_localctx).rhs = unExpression();

							if((((MulExpressionContext)_localctx).op!=null?((MulExpressionContext)_localctx).op.getText():null).equals("*")){
								((MulExpressionContext)_localctx).result =  new Mul(_localctx.result, ((MulExpressionContext)_localctx).rhs.result);
							}
							if((((MulExpressionContext)_localctx).op!=null?((MulExpressionContext)_localctx).op.getText():null).equals("/")){
								((MulExpressionContext)_localctx).result =  new Div(_localctx.result, ((MulExpressionContext)_localctx).rhs.result);
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

	public static class UnExpressionContext extends ParserRuleContext {
		public Expression result;
		public UnExpressionContext x;
		public LiteralContext y;
		public UnExpressionContext unExpression() {
			return getRuleContext(UnExpressionContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
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
		enterRule(_localctx, 28, RULE_unExpression);
		try {
			setState(164);
			switch (_input.LA(1)) {
			case T__15:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				match(T__15);
				setState(150);
				((UnExpressionContext)_localctx).x = unExpression();
				 ((UnExpressionContext)_localctx).result =  new Pos(((UnExpressionContext)_localctx).x.result); 
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				match(T__16);
				setState(154);
				((UnExpressionContext)_localctx).x = unExpression();
				 ((UnExpressionContext)_localctx).result =  new Neg(((UnExpressionContext)_localctx).x.result); 
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 3);
				{
				setState(157);
				match(T__19);
				setState(158);
				((UnExpressionContext)_localctx).x = unExpression();
				 ((UnExpressionContext)_localctx).result =  new Not(((UnExpressionContext)_localctx).x.result); 
				}
				break;
			case BOOL:
			case INT:
			case STR:
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(161);
				((UnExpressionContext)_localctx).y = literal();
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
		public BoolLiteralContext boolLiteral() {
			return getRuleContext(BoolLiteralContext.class,0);
		}
		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class,0);
		}
		public VariableExpressionContext variableExpression() {
			return getRuleContext(VariableExpressionContext.class,0);
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
		enterRule(_localctx, 30, RULE_literal);
		try {
			setState(178);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(166);
				((LiteralContext)_localctx).intLiteral = intLiteral();
				 ((LiteralContext)_localctx).result =  new Literal(((LiteralContext)_localctx).intLiteral.result); 
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(169);
				((LiteralContext)_localctx).boolLiteral = boolLiteral();
				 ((LiteralContext)_localctx).result =  new Literal(((LiteralContext)_localctx).boolLiteral.result); 
				}
				break;
			case STR:
				enterOuterAlt(_localctx, 3);
				{
				setState(172);
				((LiteralContext)_localctx).stringLiteral = stringLiteral();
				 ((LiteralContext)_localctx).result =  new Literal(((LiteralContext)_localctx).stringLiteral.result); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(175);
				((LiteralContext)_localctx).variableExpression = variableExpression();
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
		enterRule(_localctx, 32, RULE_intLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			((IntLiteralContext)_localctx).INT = match(INT);
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
		enterRule(_localctx, 34, RULE_boolLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			((BoolLiteralContext)_localctx).BOOL = match(BOOL);
			 ((BoolLiteralContext)_localctx).result =  new BoolLiteral(Boolean.valueOf((((BoolLiteralContext)_localctx).BOOL!=null?((BoolLiteralContext)_localctx).BOOL.getText():null))); 
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
		enterRule(_localctx, 36, RULE_stringLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			((StringLiteralContext)_localctx).STR = match(STR);
			 ((StringLiteralContext)_localctx).result =  new StringLiteral((((StringLiteralContext)_localctx).STR!=null?((StringLiteralContext)_localctx).STR.getText():null)); 
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
		enterRule(_localctx, 38, RULE_variableExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			((VariableExpressionContext)_localctx).ID = match(ID);
			 ((VariableExpressionContext)_localctx).result =  new VariableExpression((((VariableExpressionContext)_localctx).ID!=null?((VariableExpressionContext)_localctx).ID.getText():null)); 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\37\u00c3\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\7\4\65\n\4\f\4\16\48\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\5\5E\n\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\7\13g\n\13\f\13\16\13j\13\13\3\f\3\f\3\f\3\f\3\f\3\f\7\fr\n\f\f"+
		"\f\16\fu\13\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r}\n\r\f\r\16\r\u0080\13\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\7\16\u0088\n\16\f\16\16\16\u008b\13\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\7\17\u0093\n\17\f\17\16\17\u0096\13\17\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\5\20\u00a7\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\5\21\u00b5\n\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\2\2\26\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2"+
		"\6\3\2\31\33\3\2\f\21\3\2\22\23\3\2\24\25\u00bc\2*\3\2\2\2\4-\3\2\2\2"+
		"\6\62\3\2\2\2\bD\3\2\2\2\nF\3\2\2\2\fJ\3\2\2\2\16Q\3\2\2\2\20X\3\2\2\2"+
		"\22]\3\2\2\2\24`\3\2\2\2\26k\3\2\2\2\30v\3\2\2\2\32\u0081\3\2\2\2\34\u008c"+
		"\3\2\2\2\36\u00a6\3\2\2\2 \u00b4\3\2\2\2\"\u00b6\3\2\2\2$\u00b9\3\2\2"+
		"\2&\u00bc\3\2\2\2(\u00bf\3\2\2\2*+\5\4\3\2+,\7\2\2\3,\3\3\2\2\2-.\7\3"+
		"\2\2./\7\37\2\2/\60\5\6\4\2\60\61\b\3\1\2\61\5\3\2\2\2\62\66\7\4\2\2\63"+
		"\65\5\b\5\2\64\63\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\679"+
		"\3\2\2\28\66\3\2\2\29:\7\5\2\2:\7\3\2\2\2;<\5\n\6\2<=\b\5\1\2=E\3\2\2"+
		"\2>?\5\f\7\2?@\b\5\1\2@E\3\2\2\2AB\5\16\b\2BC\b\5\1\2CE\3\2\2\2D;\3\2"+
		"\2\2D>\3\2\2\2DA\3\2\2\2E\t\3\2\2\2FG\5\20\t\2GH\7\36\2\2HI\b\6\1\2I\13"+
		"\3\2\2\2JK\5\20\t\2KL\7\36\2\2LM\7\6\2\2MN\5\24\13\2NO\7\7\2\2OP\b\7\1"+
		"\2P\r\3\2\2\2QR\7\b\2\2RS\7\6\2\2ST\5\24\13\2TU\7\7\2\2UV\5\6\4\2VW\b"+
		"\b\1\2W\17\3\2\2\2XY\7\37\2\2YZ\7\t\2\2Z[\5\22\n\2[\\\b\t\1\2\\\21\3\2"+
		"\2\2]^\t\2\2\2^_\b\n\1\2_\23\3\2\2\2`a\5\26\f\2ah\b\13\1\2bc\7\n\2\2c"+
		"d\5\26\f\2de\b\13\1\2eg\3\2\2\2fb\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2"+
		"\2i\25\3\2\2\2jh\3\2\2\2kl\5\30\r\2ls\b\f\1\2mn\7\13\2\2no\5\30\r\2op"+
		"\b\f\1\2pr\3\2\2\2qm\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2t\27\3\2\2\2"+
		"us\3\2\2\2vw\5\32\16\2w~\b\r\1\2xy\t\3\2\2yz\5\32\16\2z{\b\r\1\2{}\3\2"+
		"\2\2|x\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\31\3\2\2\2\u0080"+
		"~\3\2\2\2\u0081\u0082\5\34\17\2\u0082\u0089\b\16\1\2\u0083\u0084\t\4\2"+
		"\2\u0084\u0085\5\34\17\2\u0085\u0086\b\16\1\2\u0086\u0088\3\2\2\2\u0087"+
		"\u0083\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2"+
		"\2\2\u008a\33\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u008d\5\36\20\2\u008d"+
		"\u0094\b\17\1\2\u008e\u008f\t\5\2\2\u008f\u0090\5\36\20\2\u0090\u0091"+
		"\b\17\1\2\u0091\u0093\3\2\2\2\u0092\u008e\3\2\2\2\u0093\u0096\3\2\2\2"+
		"\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\35\3\2\2\2\u0096\u0094"+
		"\3\2\2\2\u0097\u0098\7\22\2\2\u0098\u0099\5\36\20\2\u0099\u009a\b\20\1"+
		"\2\u009a\u00a7\3\2\2\2\u009b\u009c\7\23\2\2\u009c\u009d\5\36\20\2\u009d"+
		"\u009e\b\20\1\2\u009e\u00a7\3\2\2\2\u009f\u00a0\7\26\2\2\u00a0\u00a1\5"+
		"\36\20\2\u00a1\u00a2\b\20\1\2\u00a2\u00a7\3\2\2\2\u00a3\u00a4\5 \21\2"+
		"\u00a4\u00a5\b\20\1\2\u00a5\u00a7\3\2\2\2\u00a6\u0097\3\2\2\2\u00a6\u009b"+
		"\3\2\2\2\u00a6\u009f\3\2\2\2\u00a6\u00a3\3\2\2\2\u00a7\37\3\2\2\2\u00a8"+
		"\u00a9\5\"\22\2\u00a9\u00aa\b\21\1\2\u00aa\u00b5\3\2\2\2\u00ab\u00ac\5"+
		"$\23\2\u00ac\u00ad\b\21\1\2\u00ad\u00b5\3\2\2\2\u00ae\u00af\5&\24\2\u00af"+
		"\u00b0\b\21\1\2\u00b0\u00b5\3\2\2\2\u00b1\u00b2\5(\25\2\u00b2\u00b3\b"+
		"\21\1\2\u00b3\u00b5\3\2\2\2\u00b4\u00a8\3\2\2\2\u00b4\u00ab\3\2\2\2\u00b4"+
		"\u00ae\3\2\2\2\u00b4\u00b1\3\2\2\2\u00b5!\3\2\2\2\u00b6\u00b7\7\35\2\2"+
		"\u00b7\u00b8\b\22\1\2\u00b8#\3\2\2\2\u00b9\u00ba\7\34\2\2\u00ba\u00bb"+
		"\b\23\1\2\u00bb%\3\2\2\2\u00bc\u00bd\7\36\2\2\u00bd\u00be\b\24\1\2\u00be"+
		"\'\3\2\2\2\u00bf\u00c0\7\37\2\2\u00c0\u00c1\b\25\1\2\u00c1)\3\2\2\2\13"+
		"\66Dhs~\u0089\u0094\u00a6\u00b4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}