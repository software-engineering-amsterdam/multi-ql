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
		public TerminalNode EOF() { return getToken(QLParser.EOF, 0); }
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
			setState(39);
			match(EOF);
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
			setState(42);
			match(T__1);
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3 || _la==Ident) {
				{
				setState(49);
				switch (_input.LA(1)) {
				case Ident:
					{
					setState(43);
					((BlockContext)_localctx).question = question();
					 questions.add(((BlockContext)_localctx).question.result); 
					}
					break;
				case T__3:
					{
					setState(46);
					((BlockContext)_localctx).statement = statement();
					 statements.add(((BlockContext)_localctx).statement.result); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
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
			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				((StatementContext)_localctx).ifstatement = ifstatement();
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).ifstatement.result; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
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
			setState(65);
			match(T__3);
			setState(66);
			match(T__4);
			setState(67);
			((IfstatementContext)_localctx).condition = orExpr();
			setState(68);
			match(T__5);
			setState(69);
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
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public OrExprContext orExpr() {
			return getRuleContext(OrExprContext.class,0);
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
			setState(72);
			match(T__3);
			setState(73);
			match(T__4);
			setState(74);
			((IfelsestatementContext)_localctx).condition = orExpr();
			setState(75);
			match(T__5);
			setState(76);
			((IfelsestatementContext)_localctx).thenstatement = block();
			setState(77);
			match(T__6);
			setState(78);
			block();
			 ((IfelsestatementContext)_localctx).result =  new IfElseStatement(((IfelsestatementContext)_localctx).condition.result, ((IfelsestatementContext)_localctx).thenstatement.result); 
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
			setState(87);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				((QuestionContext)_localctx).simplequestion = simplequestion();
				 ((QuestionContext)_localctx).result =  ((QuestionContext)_localctx).simplequestion.result;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
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
			setState(89);
			((SimplequestionContext)_localctx).questionid = match(Ident);
			setState(90);
			match(T__7);
			setState(91);
			((SimplequestionContext)_localctx).questionlabel = label();
			setState(92);
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
			setState(95);
			((ComputedquestionContext)_localctx).simplequestion = simplequestion();
			setState(96);
			match(T__4);
			setState(97);
			((ComputedquestionContext)_localctx).orExpr = orExpr();
			setState(98);
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
			setState(109);
			switch (_input.LA(1)) {
			case STRING_TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(101);
				match(STRING_TYPE);
				 ((QuestiontypeContext)_localctx).result =  new StringType(); 
				}
				break;
			case INTEGER_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				match(INTEGER_TYPE);
				 ((QuestiontypeContext)_localctx).result =  new IntType(); 
				}
				break;
			case MONEY_TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(105);
				match(MONEY_TYPE);
				 ((QuestiontypeContext)_localctx).result =  new IntType(); 
				}
				break;
			case BOOLEAN_TYPE:
				enterOuterAlt(_localctx, 4);
				{
				setState(107);
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
			setState(111);
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
			setState(128);
			switch (_input.LA(1)) {
			case Int:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				((PrimaryContext)_localctx).Int = match(Int);
				 ((PrimaryContext)_localctx).result =  new IntegerLiteral(Integer.parseInt((((PrimaryContext)_localctx).Int!=null?((PrimaryContext)_localctx).Int.getText():null))); 
				}
				break;
			case Ident:
				enterOuterAlt(_localctx, 2);
				{
				setState(116);
				((PrimaryContext)_localctx).Ident = match(Ident);
				 ((PrimaryContext)_localctx).result =  new Ident((((PrimaryContext)_localctx).Ident!=null?((PrimaryContext)_localctx).Ident.getText():null)); 
				}
				break;
			case Str:
				enterOuterAlt(_localctx, 3);
				{
				setState(118);
				((PrimaryContext)_localctx).Str = match(Str);
				 ((PrimaryContext)_localctx).result =  new StringLiteral((((PrimaryContext)_localctx).Str!=null?((PrimaryContext)_localctx).Str.getText():null)); 
				}
				break;
			case T__8:
			case T__9:
				enterOuterAlt(_localctx, 4);
				{
				setState(120);
				((PrimaryContext)_localctx).bool = bool();
				 ((PrimaryContext)_localctx).result =  ((PrimaryContext)_localctx).bool.result; 
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 5);
				{
				setState(123);
				match(T__4);
				setState(124);
				((PrimaryContext)_localctx).x = orExpr();
				setState(125);
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
			setState(134);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				((BoolContext)_localctx).t = match(T__8);
				 ((BoolContext)_localctx).result =  new BoolLiteral(true); 
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
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
			setState(151);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				match(T__10);
				setState(137);
				((UnaryExprContext)_localctx).x = unaryExpr();
				 ((UnaryExprContext)_localctx).result =  new Pos(((UnaryExprContext)_localctx).x.result); 
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(140);
				match(T__11);
				setState(141);
				((UnaryExprContext)_localctx).x = unaryExpr();
				 ((UnaryExprContext)_localctx).result =  new Neg(((UnaryExprContext)_localctx).x.result); 
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 3);
				{
				setState(144);
				match(T__12);
				setState(145);
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
				setState(148);
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
			setState(153);
			((MulExprContext)_localctx).lhs = unaryExpr();
			 ((MulExprContext)_localctx).result = ((MulExprContext)_localctx).lhs.result; 
			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13 || _la==T__14) {
				{
				{
				setState(155);
				((MulExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__13 || _la==T__14) ) {
					((MulExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(156);
				((MulExprContext)_localctx).rhs = unaryExpr();
				 
				      if ((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getText():null).equals("*")) {
				        ((MulExprContext)_localctx).result =  new Mul(_localctx.result, ((MulExprContext)_localctx).rhs.result);
				      }
				      if ((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getText():null).equals("<=")) {
				        ((MulExprContext)_localctx).result =  new Div(_localctx.result, ((MulExprContext)_localctx).rhs.result);      
				      }
				    
				}
				}
				setState(163);
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
			setState(164);
			((AddExprContext)_localctx).lhs = mulExpr();
			 ((AddExprContext)_localctx).result = ((AddExprContext)_localctx).lhs.result; 
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10 || _la==T__11) {
				{
				{
				setState(166);
				((AddExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__10 || _la==T__11) ) {
					((AddExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(167);
				((AddExprContext)_localctx).rhs = mulExpr();
				 
				      if ((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getText():null).equals("+")) {
				        ((AddExprContext)_localctx).result =  new Add(_localctx.result, ((AddExprContext)_localctx).rhs.result);
				      }
				      if ((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getText():null).equals("-")) {
				        ((AddExprContext)_localctx).result =  new Sub(_localctx.result, ((AddExprContext)_localctx).rhs.result);      
				      }
				    
				}
				}
				setState(174);
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
			setState(175);
			((RelExprContext)_localctx).lhs = addExpr();
			 ((RelExprContext)_localctx).result = ((RelExprContext)_localctx).lhs.result; 
			setState(183);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20))) != 0)) {
				{
				{
				setState(177);
				((RelExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20))) != 0)) ) {
					((RelExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(178);
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
				setState(185);
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
			setState(186);
			((AndExprContext)_localctx).lhs = relExpr();
			 ((AndExprContext)_localctx).result = ((AndExprContext)_localctx).lhs.result; 
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__21) {
				{
				{
				setState(188);
				match(T__21);
				setState(189);
				((AndExprContext)_localctx).rhs = relExpr();
				 ((AndExprContext)_localctx).result =  new And(_localctx.result, ((AndExprContext)_localctx).rhs.result); 
				}
				}
				setState(196);
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
			setState(197);
			((OrExprContext)_localctx).lhs = andExpr();
			 ((OrExprContext)_localctx).result =  ((OrExprContext)_localctx).lhs.result; 
			setState(205);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(199);
				match(T__22);
				setState(200);
				((OrExprContext)_localctx).rhs = andExpr();
				 ((OrExprContext)_localctx).result =  new Or(_localctx.result, ((OrExprContext)_localctx).rhs.result); 
				}
				}
				setState(207);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\"\u00d3\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\64"+
		"\n\3\f\3\16\3\67\13\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4B\n\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\5\7Z\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\np\n\n\3\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0083\n\f\3\r\3\r\3\r"+
		"\3\r\5\r\u0089\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\5\16\u009a\n\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\7\17\u00a2\n\17\f\17\16\17\u00a5\13\17\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\7\20\u00ad\n\20\f\20\16\20\u00b0\13\20\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\7\21\u00b8\n\21\f\21\16\21\u00bb\13\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\7\22\u00c3\n\22\f\22\16\22\u00c6\13\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\7\23\u00ce\n\23\f\23\16\23\u00d1\13\23\3\23\2\2\24\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$\2\5\3\2\20\21\3\2\r\16\3\2\22\27\u00d4\2&\3"+
		"\2\2\2\4,\3\2\2\2\6A\3\2\2\2\bC\3\2\2\2\nJ\3\2\2\2\fY\3\2\2\2\16[\3\2"+
		"\2\2\20a\3\2\2\2\22o\3\2\2\2\24q\3\2\2\2\26\u0082\3\2\2\2\30\u0088\3\2"+
		"\2\2\32\u0099\3\2\2\2\34\u009b\3\2\2\2\36\u00a6\3\2\2\2 \u00b1\3\2\2\2"+
		"\"\u00bc\3\2\2\2$\u00c7\3\2\2\2&\'\7\3\2\2\'(\7 \2\2()\5\4\3\2)*\7\2\2"+
		"\3*+\b\2\1\2+\3\3\2\2\2,\65\7\4\2\2-.\5\f\7\2./\b\3\1\2/\64\3\2\2\2\60"+
		"\61\5\6\4\2\61\62\b\3\1\2\62\64\3\2\2\2\63-\3\2\2\2\63\60\3\2\2\2\64\67"+
		"\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\668\3\2\2\2\67\65\3\2\2\289\7\5\2"+
		"\29:\b\3\1\2:\5\3\2\2\2;<\5\b\5\2<=\b\4\1\2=B\3\2\2\2>?\5\n\6\2?@\b\4"+
		"\1\2@B\3\2\2\2A;\3\2\2\2A>\3\2\2\2B\7\3\2\2\2CD\7\6\2\2DE\7\7\2\2EF\5"+
		"$\23\2FG\7\b\2\2GH\5\4\3\2HI\b\5\1\2I\t\3\2\2\2JK\7\6\2\2KL\7\7\2\2LM"+
		"\5$\23\2MN\7\b\2\2NO\5\4\3\2OP\7\t\2\2PQ\5\4\3\2QR\b\6\1\2R\13\3\2\2\2"+
		"ST\5\16\b\2TU\b\7\1\2UZ\3\2\2\2VW\5\20\t\2WX\b\7\1\2XZ\3\2\2\2YS\3\2\2"+
		"\2YV\3\2\2\2Z\r\3\2\2\2[\\\7 \2\2\\]\7\n\2\2]^\5\24\13\2^_\5\22\n\2_`"+
		"\b\b\1\2`\17\3\2\2\2ab\5\16\b\2bc\7\7\2\2cd\5$\23\2de\7\b\2\2ef\b\t\1"+
		"\2f\21\3\2\2\2gh\7\35\2\2hp\b\n\1\2ij\7\34\2\2jp\b\n\1\2kl\7\33\2\2lp"+
		"\b\n\1\2mn\7\32\2\2np\b\n\1\2og\3\2\2\2oi\3\2\2\2ok\3\2\2\2om\3\2\2\2"+
		"p\23\3\2\2\2qr\7\"\2\2rs\b\13\1\2s\25\3\2\2\2tu\7!\2\2u\u0083\b\f\1\2"+
		"vw\7 \2\2w\u0083\b\f\1\2xy\7\"\2\2y\u0083\b\f\1\2z{\5\30\r\2{|\b\f\1\2"+
		"|\u0083\3\2\2\2}~\7\7\2\2~\177\5$\23\2\177\u0080\7\b\2\2\u0080\u0081\b"+
		"\f\1\2\u0081\u0083\3\2\2\2\u0082t\3\2\2\2\u0082v\3\2\2\2\u0082x\3\2\2"+
		"\2\u0082z\3\2\2\2\u0082}\3\2\2\2\u0083\27\3\2\2\2\u0084\u0085\7\13\2\2"+
		"\u0085\u0089\b\r\1\2\u0086\u0087\7\f\2\2\u0087\u0089\b\r\1\2\u0088\u0084"+
		"\3\2\2\2\u0088\u0086\3\2\2\2\u0089\31\3\2\2\2\u008a\u008b\7\r\2\2\u008b"+
		"\u008c\5\32\16\2\u008c\u008d\b\16\1\2\u008d\u009a\3\2\2\2\u008e\u008f"+
		"\7\16\2\2\u008f\u0090\5\32\16\2\u0090\u0091\b\16\1\2\u0091\u009a\3\2\2"+
		"\2\u0092\u0093\7\17\2\2\u0093\u0094\5\32\16\2\u0094\u0095\b\16\1\2\u0095"+
		"\u009a\3\2\2\2\u0096\u0097\5\26\f\2\u0097\u0098\b\16\1\2\u0098\u009a\3"+
		"\2\2\2\u0099\u008a\3\2\2\2\u0099\u008e\3\2\2\2\u0099\u0092\3\2\2\2\u0099"+
		"\u0096\3\2\2\2\u009a\33\3\2\2\2\u009b\u009c\5\32\16\2\u009c\u00a3\b\17"+
		"\1\2\u009d\u009e\t\2\2\2\u009e\u009f\5\32\16\2\u009f\u00a0\b\17\1\2\u00a0"+
		"\u00a2\3\2\2\2\u00a1\u009d\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2"+
		"\2\2\u00a3\u00a4\3\2\2\2\u00a4\35\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a7"+
		"\5\34\17\2\u00a7\u00ae\b\20\1\2\u00a8\u00a9\t\3\2\2\u00a9\u00aa\5\34\17"+
		"\2\u00aa\u00ab\b\20\1\2\u00ab\u00ad\3\2\2\2\u00ac\u00a8\3\2\2\2\u00ad"+
		"\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\37\3\2\2"+
		"\2\u00b0\u00ae\3\2\2\2\u00b1\u00b2\5\36\20\2\u00b2\u00b9\b\21\1\2\u00b3"+
		"\u00b4\t\4\2\2\u00b4\u00b5\5\36\20\2\u00b5\u00b6\b\21\1\2\u00b6\u00b8"+
		"\3\2\2\2\u00b7\u00b3\3\2\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9"+
		"\u00ba\3\2\2\2\u00ba!\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc\u00bd\5 \21\2"+
		"\u00bd\u00c4\b\22\1\2\u00be\u00bf\7\30\2\2\u00bf\u00c0\5 \21\2\u00c0\u00c1"+
		"\b\22\1\2\u00c1\u00c3\3\2\2\2\u00c2\u00be\3\2\2\2\u00c3\u00c6\3\2\2\2"+
		"\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5#\3\2\2\2\u00c6\u00c4\3"+
		"\2\2\2\u00c7\u00c8\5\"\22\2\u00c8\u00cf\b\23\1\2\u00c9\u00ca\7\31\2\2"+
		"\u00ca\u00cb\5\"\22\2\u00cb\u00cc\b\23\1\2\u00cc\u00ce\3\2\2\2\u00cd\u00c9"+
		"\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0"+
		"%\3\2\2\2\u00d1\u00cf\3\2\2\2\17\63\65AYo\u0082\u0088\u0099\u00a3\u00ae"+
		"\u00b9\u00c4\u00cf";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}