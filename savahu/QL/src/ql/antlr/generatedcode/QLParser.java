// Generated from A:\Users\sander\Documents\NetBeansProjects\multi-ql\savahu\QL\src\ql\antlr\QL.g4 by ANTLR 4.5.2

    package ql.antlr.generatedcode;
    import ql.ast.*;
    import ql.ast.expression.*;
    import ql.ast.type.*;
    import ql.ast.form.*;
    import ql.ast.literal.*;
    import ql.ast.statement.*;
    import ql.ast.question.*;

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
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, BOOLEAN_TYPE=24, 
		MONEY_TYPE=25, INTEGER_TYPE=26, STRING_TYPE=27, WS=28, COMMENT=29, Ident=30, 
		Int=31, Str=32;
	public static final int
		RULE_form = 0, RULE_block = 1, RULE_statement = 2, RULE_ifstatement = 3, 
		RULE_ifelsestatement = 4, RULE_question = 5, RULE_simplequestion = 6, 
		RULE_computedquestion = 7, RULE_questiontype = 8, RULE_label = 9, RULE_primary = 10, 
		RULE_bool = 11, RULE_unaryExpr = 12, RULE_mulExpr = 13, RULE_addExpr = 14, 
		RULE_relExpr = 15, RULE_andExpr = 16, RULE_orExpr = 17;
	public static final String[] ruleNames = {
		"form", "block", "statement", "ifstatement", "ifelsestatement", "question", 
		"simplequestion", "computedquestion", "questiontype", "label", "primary", 
		"bool", "unaryExpr", "mulExpr", "addExpr", "relExpr", "andExpr", "orExpr"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'if'", "'('", "')'", "'else'", "':'", "'true'", 
		"'false'", "'+'", "'-'", "'!'", "'*'", "'/'", "'<'", "'<='", "'>'", "'>='", 
		"'=='", "'!='", "'&&'", "'||'", "'boolean'", "'money'", "'integer'", "'string'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"BOOLEAN_TYPE", "MONEY_TYPE", "INTEGER_TYPE", "STRING_TYPE", "WS", "COMMENT", 
		"Ident", "Int", "Str"
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
		public Token Ident;
		public BlockContext body;
		public TerminalNode Ident() { return getToken(QLParser.Ident, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitForm(this);
			else return visitor.visitChildren(this);
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
			((FormContext)_localctx).Ident = match(Ident);
			setState(38);
			((FormContext)_localctx).body = block();
			 ((FormContext)_localctx).result =  new Form(new Ident((((FormContext)_localctx).Ident!=null?((FormContext)_localctx).Ident.getText():null)), ((FormContext)_localctx).body.result); 
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

	public static class BlockContext extends ParserRuleContext {
		public Block result;
		public QuestionContext question;
		public StatementContext statement;
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		 
		          List<Statement> statements = new ArrayList<Statement>(); 
		          List<Question> questions = new ArrayList<Question>(); 
		        
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(T__1);
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3 || _la==Ident) {
				{
				setState(48);
				switch (_input.LA(1)) {
				case Ident:
					{
					setState(42);
					((BlockContext)_localctx).question = question();
					 questions.add(((BlockContext)_localctx).question.result); 
					}
					break;
				case T__3:
					{
					setState(45);
					((BlockContext)_localctx).statement = statement();
					 statements.add(((BlockContext)_localctx).statement.result); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53);
			match(T__2);
			 
			       ((BlockContext)_localctx).result =  new Block(statements, questions); 
			     
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
		public IfstatementContext ifstatement;
		public IfelsestatementContext ifelsestatement;
		public IfstatementContext ifstatement() {
			return getRuleContext(IfstatementContext.class,0);
		}
		public IfelsestatementContext ifelsestatement() {
			return getRuleContext(IfelsestatementContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(62);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				((StatementContext)_localctx).ifstatement = ifstatement();
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).ifstatement.result; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				((StatementContext)_localctx).ifelsestatement = ifelsestatement();
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).ifelsestatement.result; 
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

	public static class IfstatementContext extends ParserRuleContext {
		public Statement result;
		public OrExprContext condition;
		public BlockContext block;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public OrExprContext orExpr() {
			return getRuleContext(OrExprContext.class,0);
		}
		public IfstatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifstatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterIfstatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitIfstatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIfstatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfstatementContext ifstatement() throws RecognitionException {
		IfstatementContext _localctx = new IfstatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ifstatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__3);
			setState(65);
			match(T__4);
			setState(66);
			((IfstatementContext)_localctx).condition = orExpr();
			setState(67);
			match(T__5);
			setState(68);
			((IfstatementContext)_localctx).block = block();
			 ((IfstatementContext)_localctx).result =  new IfStatement(((IfstatementContext)_localctx).condition.result, ((IfstatementContext)_localctx).block.result); 
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

	public static class IfelsestatementContext extends ParserRuleContext {
		public Statement result;
		public OrExprContext condition;
		public BlockContext thenstatement;
		public BlockContext elsestatement;
		public OrExprContext orExpr() {
			return getRuleContext(OrExprContext.class,0);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public IfelsestatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifelsestatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterIfelsestatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitIfelsestatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIfelsestatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfelsestatementContext ifelsestatement() throws RecognitionException {
		IfelsestatementContext _localctx = new IfelsestatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ifelsestatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(T__3);
			setState(72);
			match(T__4);
			setState(73);
			((IfelsestatementContext)_localctx).condition = orExpr();
			setState(74);
			match(T__5);
			setState(75);
			((IfelsestatementContext)_localctx).thenstatement = block();
			setState(76);
			match(T__6);
			setState(77);
			((IfelsestatementContext)_localctx).elsestatement = block();
			 ((IfelsestatementContext)_localctx).result =  new IfElseStatement(((IfelsestatementContext)_localctx).condition.result, ((IfelsestatementContext)_localctx).thenstatement.result, ((IfelsestatementContext)_localctx).elsestatement.result); 
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
		public SimplequestionContext simplequestion;
		public ComputedquestionContext computedquestion;
		public SimplequestionContext simplequestion() {
			return getRuleContext(SimplequestionContext.class,0);
		}
		public ComputedquestionContext computedquestion() {
			return getRuleContext(ComputedquestionContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_question);
		try {
			setState(86);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				((QuestionContext)_localctx).simplequestion = simplequestion();
				 ((QuestionContext)_localctx).result =  ((QuestionContext)_localctx).simplequestion.result;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				((QuestionContext)_localctx).computedquestion = computedquestion();
				 ((QuestionContext)_localctx).result =  ((QuestionContext)_localctx).computedquestion.result;
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

	public static class SimplequestionContext extends ParserRuleContext {
		public Question result;
		public Token questionid;
		public LabelContext questionlabel;
		public QuestiontypeContext questiontype;
		public QuestiontypeContext questiontype() {
			return getRuleContext(QuestiontypeContext.class,0);
		}
		public TerminalNode Ident() { return getToken(QLParser.Ident, 0); }
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public SimplequestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simplequestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterSimplequestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitSimplequestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitSimplequestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimplequestionContext simplequestion() throws RecognitionException {
		SimplequestionContext _localctx = new SimplequestionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_simplequestion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			((SimplequestionContext)_localctx).questionid = match(Ident);
			setState(89);
			match(T__7);
			setState(90);
			((SimplequestionContext)_localctx).questionlabel = label();
			setState(91);
			((SimplequestionContext)_localctx).questiontype = questiontype();
			 
			    ((SimplequestionContext)_localctx).result =  new SimpleQuestion(new Ident((((SimplequestionContext)_localctx).questionid!=null?((SimplequestionContext)_localctx).questionid.getText():null)), new Label((((SimplequestionContext)_localctx).questionlabel!=null?_input.getText(((SimplequestionContext)_localctx).questionlabel.start,((SimplequestionContext)_localctx).questionlabel.stop):null)), ((SimplequestionContext)_localctx).questiontype.result); 
			    
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

	public static class ComputedquestionContext extends ParserRuleContext {
		public Question result;
		public SimplequestionContext simplequestion;
		public OrExprContext orExpr;
		public SimplequestionContext simplequestion() {
			return getRuleContext(SimplequestionContext.class,0);
		}
		public OrExprContext orExpr() {
			return getRuleContext(OrExprContext.class,0);
		}
		public ComputedquestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_computedquestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterComputedquestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitComputedquestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitComputedquestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComputedquestionContext computedquestion() throws RecognitionException {
		ComputedquestionContext _localctx = new ComputedquestionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_computedquestion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			((ComputedquestionContext)_localctx).simplequestion = simplequestion();
			setState(95);
			match(T__4);
			setState(96);
			((ComputedquestionContext)_localctx).orExpr = orExpr();
			setState(97);
			match(T__5);
			 
			    ((ComputedquestionContext)_localctx).result =  new ComputedQuestion(((ComputedquestionContext)_localctx).simplequestion.result.getId(), ((ComputedquestionContext)_localctx).simplequestion.result.getLabel(), ((ComputedquestionContext)_localctx).simplequestion.result.getType(), ((ComputedquestionContext)_localctx).orExpr.result);
			    
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

	public static class QuestiontypeContext extends ParserRuleContext {
		public QuestionType result;
		public TerminalNode STRING_TYPE() { return getToken(QLParser.STRING_TYPE, 0); }
		public TerminalNode INTEGER_TYPE() { return getToken(QLParser.INTEGER_TYPE, 0); }
		public TerminalNode MONEY_TYPE() { return getToken(QLParser.MONEY_TYPE, 0); }
		public TerminalNode BOOLEAN_TYPE() { return getToken(QLParser.BOOLEAN_TYPE, 0); }
		public QuestiontypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questiontype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterQuestiontype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitQuestiontype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitQuestiontype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestiontypeContext questiontype() throws RecognitionException {
		QuestiontypeContext _localctx = new QuestiontypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_questiontype);
		try {
			setState(108);
			switch (_input.LA(1)) {
			case STRING_TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(100);
				match(STRING_TYPE);
				 ((QuestiontypeContext)_localctx).result =  new StringType(); 
				}
				break;
			case INTEGER_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(102);
				match(INTEGER_TYPE);
				 ((QuestiontypeContext)_localctx).result =  new IntType(); 
				}
				break;
			case MONEY_TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(104);
				match(MONEY_TYPE);
				 ((QuestiontypeContext)_localctx).result =  new IntType(); 
				}
				break;
			case BOOLEAN_TYPE:
				enterOuterAlt(_localctx, 4);
				{
				setState(106);
				match(BOOLEAN_TYPE);
				 ((QuestiontypeContext)_localctx).result =  new BoolType(); 
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

	public static class LabelContext extends ParserRuleContext {
		public Label result;
		public Token Str;
		public TerminalNode Str() { return getToken(QLParser.Str, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			((LabelContext)_localctx).Str = match(Str);
			 ((LabelContext)_localctx).result =  new Label((((LabelContext)_localctx).Str!=null?((LabelContext)_localctx).Str.getText():null)); 
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
		public Token Int;
		public Token Ident;
		public Token Str;
		public BoolContext bool;
		public OrExprContext x;
		public TerminalNode Int() { return getToken(QLParser.Int, 0); }
		public TerminalNode Ident() { return getToken(QLParser.Ident, 0); }
		public TerminalNode Str() { return getToken(QLParser.Str, 0); }
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public OrExprContext orExpr() {
			return getRuleContext(OrExprContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_primary);
		try {
			setState(127);
			switch (_input.LA(1)) {
			case Int:
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				((PrimaryContext)_localctx).Int = match(Int);
				 ((PrimaryContext)_localctx).result =  new IntegerLiteral(Integer.parseInt((((PrimaryContext)_localctx).Int!=null?((PrimaryContext)_localctx).Int.getText():null))); 
				}
				break;
			case Ident:
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				((PrimaryContext)_localctx).Ident = match(Ident);
				 ((PrimaryContext)_localctx).result =  new Ident((((PrimaryContext)_localctx).Ident!=null?((PrimaryContext)_localctx).Ident.getText():null)); 
				}
				break;
			case Str:
				enterOuterAlt(_localctx, 3);
				{
				setState(117);
				((PrimaryContext)_localctx).Str = match(Str);
				 ((PrimaryContext)_localctx).result =  new StringLiteral((((PrimaryContext)_localctx).Str!=null?((PrimaryContext)_localctx).Str.getText():null)); 
				}
				break;
			case T__8:
			case T__9:
				enterOuterAlt(_localctx, 4);
				{
				setState(119);
				((PrimaryContext)_localctx).bool = bool();
				 ((PrimaryContext)_localctx).result =  ((PrimaryContext)_localctx).bool.result; 
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 5);
				{
				setState(122);
				match(T__4);
				setState(123);
				((PrimaryContext)_localctx).x = orExpr();
				setState(124);
				match(T__5);
				 ((PrimaryContext)_localctx).result =  ((PrimaryContext)_localctx).x.result; 
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
		public Expr result;
		public Token t;
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolContext bool() throws RecognitionException {
		BoolContext _localctx = new BoolContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_bool);
		try {
			setState(133);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(129);
				((BoolContext)_localctx).t = match(T__8);
				 ((BoolContext)_localctx).result =  new BoolLiteral(true); 
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(131);
				((BoolContext)_localctx).t = match(T__9);
				 ((BoolContext)_localctx).result =  new BoolLiteral(false); 
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

	public static class UnaryExprContext extends ParserRuleContext {
		public Expr result;
		public UnaryExprContext x;
		public PrimaryContext p;
		public UnaryExprContext unaryExpr() {
			return getRuleContext(UnaryExprContext.class,0);
		}
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public UnaryExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitUnaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExprContext unaryExpr() throws RecognitionException {
		UnaryExprContext _localctx = new UnaryExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_unaryExpr);
		try {
			setState(150);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(135);
				match(T__10);
				setState(136);
				((UnaryExprContext)_localctx).x = unaryExpr();
				 ((UnaryExprContext)_localctx).result =  new Pos(((UnaryExprContext)_localctx).x.result); 
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(139);
				match(T__11);
				setState(140);
				((UnaryExprContext)_localctx).x = unaryExpr();
				 ((UnaryExprContext)_localctx).result =  new Neg(((UnaryExprContext)_localctx).x.result); 
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 3);
				{
				setState(143);
				match(T__12);
				setState(144);
				((UnaryExprContext)_localctx).x = unaryExpr();
				 ((UnaryExprContext)_localctx).result =  new Not(((UnaryExprContext)_localctx).x.result); 
				}
				break;
			case T__4:
			case T__8:
			case T__9:
			case Ident:
			case Int:
			case Str:
				enterOuterAlt(_localctx, 4);
				{
				setState(147);
				((UnaryExprContext)_localctx).p = primary();
				 ((UnaryExprContext)_localctx).result =  ((UnaryExprContext)_localctx).p.result; 
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
		public UnaryExprContext lhs;
		public Token op;
		public UnaryExprContext rhs;
		public List<UnaryExprContext> unaryExpr() {
			return getRuleContexts(UnaryExprContext.class);
		}
		public UnaryExprContext unaryExpr(int i) {
			return getRuleContext(UnaryExprContext.class,i);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitMulExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MulExprContext mulExpr() throws RecognitionException {
		MulExprContext _localctx = new MulExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_mulExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			((MulExprContext)_localctx).lhs = unaryExpr();
			 ((MulExprContext)_localctx).result = ((MulExprContext)_localctx).lhs.result; 
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13 || _la==T__14) {
				{
				{
				setState(154);
				((MulExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__13 || _la==T__14) ) {
					((MulExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(155);
				((MulExprContext)_localctx).rhs = unaryExpr();
				 
				      if ((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getText():null).equals("*")) {
				        ((MulExprContext)_localctx).result =  new Mul(_localctx.result, ((MulExprContext)_localctx).rhs.result);
				      }
				      if ((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getText():null).equals("<=")) {
				        ((MulExprContext)_localctx).result =  new Div(_localctx.result, ((MulExprContext)_localctx).rhs.result);      
				      }
				    
				}
				}
				setState(162);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitAddExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddExprContext addExpr() throws RecognitionException {
		AddExprContext _localctx = new AddExprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_addExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			((AddExprContext)_localctx).lhs = mulExpr();
			 ((AddExprContext)_localctx).result = ((AddExprContext)_localctx).lhs.result; 
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10 || _la==T__11) {
				{
				{
				setState(165);
				((AddExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__10 || _la==T__11) ) {
					((AddExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(166);
				((AddExprContext)_localctx).rhs = mulExpr();
				 
				      if ((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getText():null).equals("+")) {
				        ((AddExprContext)_localctx).result =  new Add(_localctx.result, ((AddExprContext)_localctx).rhs.result);
				      }
				      if ((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getText():null).equals("-")) {
				        ((AddExprContext)_localctx).result =  new Sub(_localctx.result, ((AddExprContext)_localctx).rhs.result);      
				      }
				    
				}
				}
				setState(173);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitRelExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelExprContext relExpr() throws RecognitionException {
		RelExprContext _localctx = new RelExprContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_relExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			((RelExprContext)_localctx).lhs = addExpr();
			 ((RelExprContext)_localctx).result = ((RelExprContext)_localctx).lhs.result; 
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20))) != 0)) {
				{
				{
				setState(176);
				((RelExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20))) != 0)) ) {
					((RelExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(177);
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
				setState(184);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndExprContext andExpr() throws RecognitionException {
		AndExprContext _localctx = new AndExprContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_andExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			((AndExprContext)_localctx).lhs = relExpr();
			 ((AndExprContext)_localctx).result = ((AndExprContext)_localctx).lhs.result; 
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__21) {
				{
				{
				setState(187);
				match(T__21);
				setState(188);
				((AndExprContext)_localctx).rhs = relExpr();
				 ((AndExprContext)_localctx).result =  new And(_localctx.result, ((AndExprContext)_localctx).rhs.result); 
				}
				}
				setState(195);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrExprContext orExpr() throws RecognitionException {
		OrExprContext _localctx = new OrExprContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_orExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			((OrExprContext)_localctx).lhs = andExpr();
			 ((OrExprContext)_localctx).result =  ((OrExprContext)_localctx).lhs.result; 
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(198);
				match(T__22);
				setState(199);
				((OrExprContext)_localctx).rhs = andExpr();
				 ((OrExprContext)_localctx).result =  new Or(_localctx.result, ((OrExprContext)_localctx).rhs.result); 
				}
				}
				setState(206);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\"\u00d2\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\63\n\3"+
		"\f\3\16\3\66\13\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4A\n\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\5\7Y\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\no\n\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0082\n\f\3\r\3\r\3\r\3\r"+
		"\5\r\u0088\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\5\16\u0099\n\16\3\17\3\17\3\17\3\17\3\17\3\17\7\17"+
		"\u00a1\n\17\f\17\16\17\u00a4\13\17\3\20\3\20\3\20\3\20\3\20\3\20\7\20"+
		"\u00ac\n\20\f\20\16\20\u00af\13\20\3\21\3\21\3\21\3\21\3\21\3\21\7\21"+
		"\u00b7\n\21\f\21\16\21\u00ba\13\21\3\22\3\22\3\22\3\22\3\22\3\22\7\22"+
		"\u00c2\n\22\f\22\16\22\u00c5\13\22\3\23\3\23\3\23\3\23\3\23\3\23\7\23"+
		"\u00cd\n\23\f\23\16\23\u00d0\13\23\3\23\2\2\24\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$\2\5\3\2\20\21\3\2\r\16\3\2\22\27\u00d3\2&\3\2\2\2"+
		"\4+\3\2\2\2\6@\3\2\2\2\bB\3\2\2\2\nI\3\2\2\2\fX\3\2\2\2\16Z\3\2\2\2\20"+
		"`\3\2\2\2\22n\3\2\2\2\24p\3\2\2\2\26\u0081\3\2\2\2\30\u0087\3\2\2\2\32"+
		"\u0098\3\2\2\2\34\u009a\3\2\2\2\36\u00a5\3\2\2\2 \u00b0\3\2\2\2\"\u00bb"+
		"\3\2\2\2$\u00c6\3\2\2\2&\'\7\3\2\2\'(\7 \2\2()\5\4\3\2)*\b\2\1\2*\3\3"+
		"\2\2\2+\64\7\4\2\2,-\5\f\7\2-.\b\3\1\2.\63\3\2\2\2/\60\5\6\4\2\60\61\b"+
		"\3\1\2\61\63\3\2\2\2\62,\3\2\2\2\62/\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2"+
		"\2\64\65\3\2\2\2\65\67\3\2\2\2\66\64\3\2\2\2\678\7\5\2\289\b\3\1\29\5"+
		"\3\2\2\2:;\5\b\5\2;<\b\4\1\2<A\3\2\2\2=>\5\n\6\2>?\b\4\1\2?A\3\2\2\2@"+
		":\3\2\2\2@=\3\2\2\2A\7\3\2\2\2BC\7\6\2\2CD\7\7\2\2DE\5$\23\2EF\7\b\2\2"+
		"FG\5\4\3\2GH\b\5\1\2H\t\3\2\2\2IJ\7\6\2\2JK\7\7\2\2KL\5$\23\2LM\7\b\2"+
		"\2MN\5\4\3\2NO\7\t\2\2OP\5\4\3\2PQ\b\6\1\2Q\13\3\2\2\2RS\5\16\b\2ST\b"+
		"\7\1\2TY\3\2\2\2UV\5\20\t\2VW\b\7\1\2WY\3\2\2\2XR\3\2\2\2XU\3\2\2\2Y\r"+
		"\3\2\2\2Z[\7 \2\2[\\\7\n\2\2\\]\5\24\13\2]^\5\22\n\2^_\b\b\1\2_\17\3\2"+
		"\2\2`a\5\16\b\2ab\7\7\2\2bc\5$\23\2cd\7\b\2\2de\b\t\1\2e\21\3\2\2\2fg"+
		"\7\35\2\2go\b\n\1\2hi\7\34\2\2io\b\n\1\2jk\7\33\2\2ko\b\n\1\2lm\7\32\2"+
		"\2mo\b\n\1\2nf\3\2\2\2nh\3\2\2\2nj\3\2\2\2nl\3\2\2\2o\23\3\2\2\2pq\7\""+
		"\2\2qr\b\13\1\2r\25\3\2\2\2st\7!\2\2t\u0082\b\f\1\2uv\7 \2\2v\u0082\b"+
		"\f\1\2wx\7\"\2\2x\u0082\b\f\1\2yz\5\30\r\2z{\b\f\1\2{\u0082\3\2\2\2|}"+
		"\7\7\2\2}~\5$\23\2~\177\7\b\2\2\177\u0080\b\f\1\2\u0080\u0082\3\2\2\2"+
		"\u0081s\3\2\2\2\u0081u\3\2\2\2\u0081w\3\2\2\2\u0081y\3\2\2\2\u0081|\3"+
		"\2\2\2\u0082\27\3\2\2\2\u0083\u0084\7\13\2\2\u0084\u0088\b\r\1\2\u0085"+
		"\u0086\7\f\2\2\u0086\u0088\b\r\1\2\u0087\u0083\3\2\2\2\u0087\u0085\3\2"+
		"\2\2\u0088\31\3\2\2\2\u0089\u008a\7\r\2\2\u008a\u008b\5\32\16\2\u008b"+
		"\u008c\b\16\1\2\u008c\u0099\3\2\2\2\u008d\u008e\7\16\2\2\u008e\u008f\5"+
		"\32\16\2\u008f\u0090\b\16\1\2\u0090\u0099\3\2\2\2\u0091\u0092\7\17\2\2"+
		"\u0092\u0093\5\32\16\2\u0093\u0094\b\16\1\2\u0094\u0099\3\2\2\2\u0095"+
		"\u0096\5\26\f\2\u0096\u0097\b\16\1\2\u0097\u0099\3\2\2\2\u0098\u0089\3"+
		"\2\2\2\u0098\u008d\3\2\2\2\u0098\u0091\3\2\2\2\u0098\u0095\3\2\2\2\u0099"+
		"\33\3\2\2\2\u009a\u009b\5\32\16\2\u009b\u00a2\b\17\1\2\u009c\u009d\t\2"+
		"\2\2\u009d\u009e\5\32\16\2\u009e\u009f\b\17\1\2\u009f\u00a1\3\2\2\2\u00a0"+
		"\u009c\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2"+
		"\2\2\u00a3\35\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a6\5\34\17\2\u00a6"+
		"\u00ad\b\20\1\2\u00a7\u00a8\t\3\2\2\u00a8\u00a9\5\34\17\2\u00a9\u00aa"+
		"\b\20\1\2\u00aa\u00ac\3\2\2\2\u00ab\u00a7\3\2\2\2\u00ac\u00af\3\2\2\2"+
		"\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\37\3\2\2\2\u00af\u00ad"+
		"\3\2\2\2\u00b0\u00b1\5\36\20\2\u00b1\u00b8\b\21\1\2\u00b2\u00b3\t\4\2"+
		"\2\u00b3\u00b4\5\36\20\2\u00b4\u00b5\b\21\1\2\u00b5\u00b7\3\2\2\2\u00b6"+
		"\u00b2\3\2\2\2\u00b7\u00ba\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2"+
		"\2\2\u00b9!\3\2\2\2\u00ba\u00b8\3\2\2\2\u00bb\u00bc\5 \21\2\u00bc\u00c3"+
		"\b\22\1\2\u00bd\u00be\7\30\2\2\u00be\u00bf\5 \21\2\u00bf\u00c0\b\22\1"+
		"\2\u00c0\u00c2\3\2\2\2\u00c1\u00bd\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1"+
		"\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4#\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6"+
		"\u00c7\5\"\22\2\u00c7\u00ce\b\23\1\2\u00c8\u00c9\7\31\2\2\u00c9\u00ca"+
		"\5\"\22\2\u00ca\u00cb\b\23\1\2\u00cb\u00cd\3\2\2\2\u00cc\u00c8\3\2\2\2"+
		"\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf%\3"+
		"\2\2\2\u00d0\u00ce\3\2\2\2\17\62\64@Xn\u0081\u0087\u0098\u00a2\u00ad\u00b8"+
		"\u00c3\u00ce";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}