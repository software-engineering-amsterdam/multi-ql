// Generated from QL.g4 by ANTLR 4.5.2

	package ql.antlr;
	import java.util.List;

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import ql.ast.expression.Add;
import ql.ast.expression.AndExpression;
import ql.ast.expression.Div;
import ql.ast.expression.Eq;
import ql.ast.expression.Expression;
import ql.ast.expression.GEq;
import ql.ast.expression.GT;
import ql.ast.expression.LEq;
import ql.ast.expression.LT;
import ql.ast.expression.Mul;
import ql.ast.expression.NEq;
import ql.ast.expression.Neg;
import ql.ast.expression.Not;
import ql.ast.expression.OrExpression;
import ql.ast.expression.Pos;
import ql.ast.expression.Sub;
import ql.ast.expression.VariableExpression;
import ql.ast.form.Body;
import ql.ast.form.Form;
import ql.ast.literal.BoolLiteral;
import ql.ast.literal.IntLiteral;
import ql.ast.literal.Literal;
import ql.ast.literal.StringLiteral;
import ql.ast.literal.Variable;
import ql.ast.literal.VariableType;
import ql.ast.statement.IfStatement;
import ql.ast.statement.Statement;
import ql.ast.statement.question.ComputedQuestion;
import ql.ast.statement.question.InputQuestion;
import ql.ast.statement.question.Question;

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
		RULE_ifStatement = 5, RULE_variable = 6, RULE_variableType = 7, RULE_orExpression = 8, 
		RULE_andExpression = 9, RULE_relExpression = 10, RULE_addExpression = 11, 
		RULE_mulExpression = 12, RULE_unExpression = 13, RULE_literal = 14;
	public static final String[] ruleNames = {
		"file", "form", "body", "statement", "question", "ifStatement", "variable", 
		"variableType", "orExpression", "andExpression", "relExpression", "addExpression", 
		"mulExpression", "unExpression", "literal"
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
			setState(30);
			form();
			setState(31);
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
			setState(33);
			match(T__0);
			setState(34);
			((FormContext)_localctx).ID = match(ID);
			setState(35);
			((FormContext)_localctx).body = body();
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
			setState(38);
			match(T__1);
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5 || _la==ID) {
				{
				{
				setState(39);
				statement(_localctx.result);
				}
				}
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(45);
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
		public IfStatementContext ifStatement;
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
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
			setState(53);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(47);
				((StatementContext)_localctx).question = question();
				 _localctx.result.add(new Statement(((StatementContext)_localctx).question.result)); 
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(50);
				((StatementContext)_localctx).ifStatement = ifStatement();
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
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode STR() { return getToken(QLParser.STR, 0); }
		public OrExpressionContext orExpression() {
			return getRuleContext(OrExpressionContext.class,0);
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
			setState(66);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				((QuestionContext)_localctx).variable = variable();
				setState(56);
				((QuestionContext)_localctx).STR = match(STR);
				 ((QuestionContext)_localctx).result =  new InputQuestion((((QuestionContext)_localctx).variable!=null?(((QuestionContext)_localctx).variable.start):null).getLine(), ((QuestionContext)_localctx).variable.result, (((QuestionContext)_localctx).STR!=null?((QuestionContext)_localctx).STR.getText():null)); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				((QuestionContext)_localctx).variable = variable();
				setState(60);
				((QuestionContext)_localctx).STR = match(STR);
				setState(61);
				match(T__3);
				setState(62);
				((QuestionContext)_localctx).orExpression = orExpression();
				setState(63);
				match(T__4);
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
			setState(68);
			match(T__5);
			setState(69);
			match(T__3);
			setState(70);
			((IfStatementContext)_localctx).orExpression = orExpression();
			setState(71);
			match(T__4);
			setState(72);
			((IfStatementContext)_localctx).body = body();
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
			setState(75);
			((VariableContext)_localctx).ID = match(ID);
			setState(76);
			match(T__6);
			setState(77);
			((VariableContext)_localctx).variableType = variableType();
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
		enterRule(_localctx, 14, RULE_variableType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			((VariableTypeContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << INTEGER) | (1L << STRING))) != 0)) ) {
				((VariableTypeContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
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
			setState(83);
			((OrExpressionContext)_localctx).lhs = andExpression();
			 ((OrExpressionContext)_localctx).result =  ((OrExpressionContext)_localctx).lhs.result; 
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(85);
				match(T__7);
				setState(86);
				((OrExpressionContext)_localctx).rhs = andExpression();
				 ((OrExpressionContext)_localctx).result =  new OrExpression((((OrExpressionContext)_localctx).lhs!=null?(((OrExpressionContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((OrExpressionContext)_localctx).rhs.result); 
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
		enterRule(_localctx, 18, RULE_andExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			((AndExpressionContext)_localctx).lhs = relExpression();
			 ((AndExpressionContext)_localctx).result =  ((AndExpressionContext)_localctx).lhs.result; 
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(96);
				match(T__8);
				setState(97);
				((AndExpressionContext)_localctx).rhs = relExpression();
				 ((AndExpressionContext)_localctx).result =  new AndExpression((((AndExpressionContext)_localctx).lhs!=null?(((AndExpressionContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((AndExpressionContext)_localctx).rhs.result); 
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
		enterRule(_localctx, 20, RULE_relExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			((RelExpressionContext)_localctx).lhs = addExpression();
			 ((RelExpressionContext)_localctx).result =  ((RelExpressionContext)_localctx).lhs.result; 
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14))) != 0)) {
				{
				{
				setState(107);
				((RelExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14))) != 0)) ) {
					((RelExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(108);
				((RelExpressionContext)_localctx).rhs = addExpression();

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
			setState(116);
			((AddExpressionContext)_localctx).lhs = mulExpression();
			 ((AddExpressionContext)_localctx).result =  ((AddExpressionContext)_localctx).lhs.result; 
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__15 || _la==T__16) {
				{
				{
				setState(118);
				((AddExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
					((AddExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(119);
				((AddExpressionContext)_localctx).rhs = mulExpression();

							if((((AddExpressionContext)_localctx).op!=null?((AddExpressionContext)_localctx).op.getText():null).equals("+")){
								((AddExpressionContext)_localctx).result =  new Add((((AddExpressionContext)_localctx).lhs!=null?(((AddExpressionContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((AddExpressionContext)_localctx).rhs.result);
							}
							if((((AddExpressionContext)_localctx).op!=null?((AddExpressionContext)_localctx).op.getText():null).equals("-")){
								((AddExpressionContext)_localctx).result =  new Sub((((AddExpressionContext)_localctx).lhs!=null?(((AddExpressionContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((AddExpressionContext)_localctx).rhs.result);
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
		enterRule(_localctx, 24, RULE_mulExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			((MulExpressionContext)_localctx).lhs = unExpression();
			 ((MulExpressionContext)_localctx).result =  ((MulExpressionContext)_localctx).lhs.result; 
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__17 || _la==T__18) {
				{
				{
				setState(129);
				((MulExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__17 || _la==T__18) ) {
					((MulExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(130);
				((MulExpressionContext)_localctx).rhs = unExpression();

							if((((MulExpressionContext)_localctx).op!=null?((MulExpressionContext)_localctx).op.getText():null).equals("*")){
								((MulExpressionContext)_localctx).result =  new Mul((((MulExpressionContext)_localctx).lhs!=null?(((MulExpressionContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((MulExpressionContext)_localctx).rhs.result);
							}
							if((((MulExpressionContext)_localctx).op!=null?((MulExpressionContext)_localctx).op.getText():null).equals("/")){
								((MulExpressionContext)_localctx).result =  new Div((((MulExpressionContext)_localctx).lhs!=null?(((MulExpressionContext)_localctx).lhs.start):null).getLine(), _localctx.result, ((MulExpressionContext)_localctx).rhs.result);
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
		enterRule(_localctx, 26, RULE_unExpression);
		try {
			setState(153);
			switch (_input.LA(1)) {
			case T__15:
				enterOuterAlt(_localctx, 1);
				{
				setState(138);
				match(T__15);
				setState(139);
				((UnExpressionContext)_localctx).x = unExpression();
				 ((UnExpressionContext)_localctx).result =  new Pos(((UnExpressionContext)_localctx).x.result); 
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				match(T__16);
				setState(143);
				((UnExpressionContext)_localctx).x = unExpression();
				 ((UnExpressionContext)_localctx).result =  new Neg(((UnExpressionContext)_localctx).x.result); 
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 3);
				{
				setState(146);
				match(T__19);
				setState(147);
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
				setState(150);
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
		public Token INT;
		public Token BOOL;
		public Token STR;
		public Token ID;
		public TerminalNode INT() { return getToken(QLParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(QLParser.BOOL, 0); }
		public TerminalNode STR() { return getToken(QLParser.STR, 0); }
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
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
			setState(163);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(155);
				((LiteralContext)_localctx).INT = match(INT);
				 ((LiteralContext)_localctx).result =  new IntLiteral(Integer.valueOf((((LiteralContext)_localctx).INT!=null?((LiteralContext)_localctx).INT.getText():null)), ((LiteralContext)_localctx).INT.getLine()); 
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(157);
				((LiteralContext)_localctx).BOOL = match(BOOL);
				 ((LiteralContext)_localctx).result =  new BoolLiteral(Boolean.valueOf((((LiteralContext)_localctx).BOOL!=null?((LiteralContext)_localctx).BOOL.getText():null)), ((LiteralContext)_localctx).BOOL.getLine()); 
				}
				break;
			case STR:
				enterOuterAlt(_localctx, 3);
				{
				setState(159);
				((LiteralContext)_localctx).STR = match(STR);
				 ((LiteralContext)_localctx).result =  new StringLiteral((((LiteralContext)_localctx).STR!=null?((LiteralContext)_localctx).STR.getText():null), ((LiteralContext)_localctx).STR.getLine()); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(161);
				((LiteralContext)_localctx).ID = match(ID);
				 ((LiteralContext)_localctx).result =  new VariableExpression((((LiteralContext)_localctx).ID!=null?((LiteralContext)_localctx).ID.getText():null), ((LiteralContext)_localctx).ID.getLine()); 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\37\u00a8\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\3\3"+
		"\3\3\3\3\3\3\3\3\4\3\4\7\4+\n\4\f\4\16\4.\13\4\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\5\58\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6E\n\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\7\n\\\n\n\f\n\16\n_\13\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\7\13g\n\13\f\13\16\13j\13\13\3\f\3\f\3\f\3\f\3\f\3\f\7\fr\n\f\f\f\16"+
		"\fu\13\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r}\n\r\f\r\16\r\u0080\13\r\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\7\16\u0088\n\16\f\16\16\16\u008b\13\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17"+
		"\u009c\n\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00a6\n\20\3"+
		"\20\2\2\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\6\3\2\31\33\3\2\f"+
		"\21\3\2\22\23\3\2\24\25\u00a6\2 \3\2\2\2\4#\3\2\2\2\6(\3\2\2\2\b\67\3"+
		"\2\2\2\nD\3\2\2\2\fF\3\2\2\2\16M\3\2\2\2\20R\3\2\2\2\22U\3\2\2\2\24`\3"+
		"\2\2\2\26k\3\2\2\2\30v\3\2\2\2\32\u0081\3\2\2\2\34\u009b\3\2\2\2\36\u00a5"+
		"\3\2\2\2 !\5\4\3\2!\"\7\2\2\3\"\3\3\2\2\2#$\7\3\2\2$%\7\37\2\2%&\5\6\4"+
		"\2&\'\b\3\1\2\'\5\3\2\2\2(,\7\4\2\2)+\5\b\5\2*)\3\2\2\2+.\3\2\2\2,*\3"+
		"\2\2\2,-\3\2\2\2-/\3\2\2\2.,\3\2\2\2/\60\7\5\2\2\60\7\3\2\2\2\61\62\5"+
		"\n\6\2\62\63\b\5\1\2\638\3\2\2\2\64\65\5\f\7\2\65\66\b\5\1\2\668\3\2\2"+
		"\2\67\61\3\2\2\2\67\64\3\2\2\28\t\3\2\2\29:\5\16\b\2:;\7\36\2\2;<\b\6"+
		"\1\2<E\3\2\2\2=>\5\16\b\2>?\7\36\2\2?@\7\6\2\2@A\5\22\n\2AB\7\7\2\2BC"+
		"\b\6\1\2CE\3\2\2\2D9\3\2\2\2D=\3\2\2\2E\13\3\2\2\2FG\7\b\2\2GH\7\6\2\2"+
		"HI\5\22\n\2IJ\7\7\2\2JK\5\6\4\2KL\b\7\1\2L\r\3\2\2\2MN\7\37\2\2NO\7\t"+
		"\2\2OP\5\20\t\2PQ\b\b\1\2Q\17\3\2\2\2RS\t\2\2\2ST\b\t\1\2T\21\3\2\2\2"+
		"UV\5\24\13\2V]\b\n\1\2WX\7\n\2\2XY\5\24\13\2YZ\b\n\1\2Z\\\3\2\2\2[W\3"+
		"\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2\2^\23\3\2\2\2_]\3\2\2\2`a\5\26\f\2"+
		"ah\b\13\1\2bc\7\13\2\2cd\5\26\f\2de\b\13\1\2eg\3\2\2\2fb\3\2\2\2gj\3\2"+
		"\2\2hf\3\2\2\2hi\3\2\2\2i\25\3\2\2\2jh\3\2\2\2kl\5\30\r\2ls\b\f\1\2mn"+
		"\t\3\2\2no\5\30\r\2op\b\f\1\2pr\3\2\2\2qm\3\2\2\2ru\3\2\2\2sq\3\2\2\2"+
		"st\3\2\2\2t\27\3\2\2\2us\3\2\2\2vw\5\32\16\2w~\b\r\1\2xy\t\4\2\2yz\5\32"+
		"\16\2z{\b\r\1\2{}\3\2\2\2|x\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2"+
		"\2\2\177\31\3\2\2\2\u0080~\3\2\2\2\u0081\u0082\5\34\17\2\u0082\u0089\b"+
		"\16\1\2\u0083\u0084\t\5\2\2\u0084\u0085\5\34\17\2\u0085\u0086\b\16\1\2"+
		"\u0086\u0088\3\2\2\2\u0087\u0083\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087"+
		"\3\2\2\2\u0089\u008a\3\2\2\2\u008a\33\3\2\2\2\u008b\u0089\3\2\2\2\u008c"+
		"\u008d\7\22\2\2\u008d\u008e\5\34\17\2\u008e\u008f\b\17\1\2\u008f\u009c"+
		"\3\2\2\2\u0090\u0091\7\23\2\2\u0091\u0092\5\34\17\2\u0092\u0093\b\17\1"+
		"\2\u0093\u009c\3\2\2\2\u0094\u0095\7\26\2\2\u0095\u0096\5\34\17\2\u0096"+
		"\u0097\b\17\1\2\u0097\u009c\3\2\2\2\u0098\u0099\5\36\20\2\u0099\u009a"+
		"\b\17\1\2\u009a\u009c\3\2\2\2\u009b\u008c\3\2\2\2\u009b\u0090\3\2\2\2"+
		"\u009b\u0094\3\2\2\2\u009b\u0098\3\2\2\2\u009c\35\3\2\2\2\u009d\u009e"+
		"\7\35\2\2\u009e\u00a6\b\20\1\2\u009f\u00a0\7\34\2\2\u00a0\u00a6\b\20\1"+
		"\2\u00a1\u00a2\7\36\2\2\u00a2\u00a6\b\20\1\2\u00a3\u00a4\7\37\2\2\u00a4"+
		"\u00a6\b\20\1\2\u00a5\u009d\3\2\2\2\u00a5\u009f\3\2\2\2\u00a5\u00a1\3"+
		"\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\37\3\2\2\2\f,\67D]hs~\u0089\u009b\u00a5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}