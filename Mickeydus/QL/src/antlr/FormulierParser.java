// Generated from /Users/Dominique/NetBeansProjects/multi-ql/Mickeydus/QL/src/antlr/Formulier.g4 by ANTLR 4.5.2

package antlr;
import AST.expressions.*;
import AST.types.*;
import ql.*;
import AST.form.*;
import AST.literals.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FormulierParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, BOOL_TYPE=8, MONEY_TYPE=9, 
		STRING_TYPE=10, INTEGER_TYPE=11, WS=12, DIGIT=13, SMALLERTHAN=14, BIGGERTHAN=15, 
		SMALLER_EQUAL=16, BIGGER_EQUAL=17, EQUAL=18, NOT_EQUAL=19, AND=20, OR=21, 
		NOT=22, IF=23, ELSE=24, FORM=25, ASSIGN=26, MINUS=27, ADD=28, MULTIPLY=29, 
		DIVIDE=30, BOOLEAN=31, STR=32, INT=33, FLOAT=34, CURRENCYSYMBOL=35, MONEY=36, 
		DATE=37, ID=38, COMMA=39, COMMENT=40;
	public static final int
		RULE_form = 0, RULE_formName = 1, RULE_block = 2, RULE_statement = 3, 
		RULE_ifstatement = 4, RULE_ifelsestatement = 5, RULE_question = 6, RULE_normalquestion = 7, 
		RULE_computedquestion = 8, RULE_variable = 9, RULE_label = 10, RULE_questiontype = 11, 
		RULE_primary = 12, RULE_unExpr = 13, RULE_bool = 14, RULE_mulExpr = 15, 
		RULE_addExpr = 16, RULE_relExpr = 17, RULE_andExpr = 18, RULE_orExpr = 19;
	public static final String[] ruleNames = {
		"form", "formName", "block", "statement", "ifstatement", "ifelsestatement", 
		"question", "normalquestion", "computedquestion", "variable", "label", 
		"questiontype", "primary", "unExpr", "bool", "mulExpr", "addExpr", "relExpr", 
		"andExpr", "orExpr"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "'}'", "'('", "')'", "':'", "'true'", "'false'", "'boolean'", 
		"'money'", "'string'", "'integer'", null, null, "'<'", "'>'", "'<='", 
		"'>='", "'=='", "'!='", "'&&'", "'||'", "'!'", "'if'", "'else'", "'form'", 
		"'='", "'-'", "'+'", "'*'", "'/'", null, null, null, null, "'€'", null, 
		"'date'", null, "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "BOOL_TYPE", "MONEY_TYPE", 
		"STRING_TYPE", "INTEGER_TYPE", "WS", "DIGIT", "SMALLERTHAN", "BIGGERTHAN", 
		"SMALLER_EQUAL", "BIGGER_EQUAL", "EQUAL", "NOT_EQUAL", "AND", "OR", "NOT", 
		"IF", "ELSE", "FORM", "ASSIGN", "MINUS", "ADD", "MULTIPLY", "DIVIDE", 
		"BOOLEAN", "STR", "INT", "FLOAT", "CURRENCYSYMBOL", "MONEY", "DATE", "ID", 
		"COMMA", "COMMENT"
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
	public String getGrammarFileName() { return "Formulier.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FormulierParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FormContext extends ParserRuleContext {
		public Form result;
		public FormNameContext formName;
		public BlockContext forminhoud;
		public TerminalNode FORM() { return getToken(FormulierParser.FORM, 0); }
		public FormNameContext formName() {
			return getRuleContext(FormNameContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterForm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitForm(this);
		}
	}

	public final FormContext form() throws RecognitionException {
		FormContext _localctx = new FormContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_form);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(FORM);
			setState(41);
			((FormContext)_localctx).formName = formName();
			setState(42);
			((FormContext)_localctx).forminhoud = block();

					((FormContext)_localctx).result =  new Form((((FormContext)_localctx).formName!=null?_input.getText(((FormContext)_localctx).formName.start,((FormContext)_localctx).formName.stop):null), ((FormContext)_localctx).forminhoud.result);		
				
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

	public static class FormNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(FormulierParser.ID, 0); }
		public FormNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterFormName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitFormName(this);
		}
	}

	public final FormNameContext formName() throws RecognitionException {
		FormNameContext _localctx = new FormNameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_formName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(ID);
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
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_block);

		                List<Statement> statementList = new ArrayList<Statement>();
		                List<Question> questionList = new ArrayList<Question>();
		        
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(T__0);
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IF || _la==ID) {
				{
				setState(54);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(48);
					((BlockContext)_localctx).question = question();
					questionList.add(((BlockContext)_localctx).question.result); 
					                        
					}
					break;
				case IF:
					{
					setState(51);
					((BlockContext)_localctx).statement = statement();
					statementList.add(((BlockContext)_localctx).statement.result); 
					                    
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(59);
			match(T__1);

			        ((BlockContext)_localctx).result =  new Block(statementList, questionList);
			        
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
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_statement);
		try {
			setState(68);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(62);
				((StatementContext)_localctx).ifstatement = ifstatement();
				 
				        ((StatementContext)_localctx).result =  ((StatementContext)_localctx).ifstatement.result; 
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
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
		public IfStatement result;
		public OrExprContext condition;
		public OrExprContext orExpr;
		public BlockContext thenstatement;
		public OrExprContext orExpr() {
			return getRuleContext(OrExprContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IfstatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifstatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterIfstatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitIfstatement(this);
		}
	}

	public final IfstatementContext ifstatement() throws RecognitionException {
		IfstatementContext _localctx = new IfstatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ifstatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(IF);
			setState(71);
			match(T__2);
			setState(72);
			((IfstatementContext)_localctx).condition = ((IfstatementContext)_localctx).orExpr = orExpr();
			setState(73);
			match(T__3);
			setState(74);
			((IfstatementContext)_localctx).thenstatement = block();
			 
			        ((IfstatementContext)_localctx).result =  new IfStatement(((IfstatementContext)_localctx).orExpr.result, ((IfstatementContext)_localctx).thenstatement.result); 
			        
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
		public IfElseStatement result;
		public OrExprContext condition;
		public OrExprContext orExpr;
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
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterIfelsestatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitIfelsestatement(this);
		}
	}

	public final IfelsestatementContext ifelsestatement() throws RecognitionException {
		IfelsestatementContext _localctx = new IfelsestatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_ifelsestatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(IF);
			setState(78);
			match(T__2);
			setState(79);
			((IfelsestatementContext)_localctx).condition = ((IfelsestatementContext)_localctx).orExpr = orExpr();
			setState(80);
			match(T__3);
			setState(81);
			((IfelsestatementContext)_localctx).thenstatement = block();
			setState(82);
			match(ELSE);
			setState(83);
			((IfelsestatementContext)_localctx).elsestatement = block();
			 
			        ((IfelsestatementContext)_localctx).result =  new IfElseStatement(((IfelsestatementContext)_localctx).orExpr.result, ((IfelsestatementContext)_localctx).thenstatement.result, ((IfelsestatementContext)_localctx).elsestatement.result); 
			         
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
		public NormalquestionContext normalquestion;
		public ComputedquestionContext computedquestion;
		public NormalquestionContext normalquestion() {
			return getRuleContext(NormalquestionContext.class,0);
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
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitQuestion(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_question);
		try {
			setState(92);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(86);
				((QuestionContext)_localctx).normalquestion = normalquestion();
				 
				        ((QuestionContext)_localctx).result =  ((QuestionContext)_localctx).normalquestion.result; 
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
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

	public static class NormalquestionContext extends ParserRuleContext {
		public Question result;
		public Token questionid;
		public LabelContext nqlabel;
		public QuestiontypeContext type;
		public TerminalNode ID() { return getToken(FormulierParser.ID, 0); }
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public QuestiontypeContext questiontype() {
			return getRuleContext(QuestiontypeContext.class,0);
		}
		public NormalquestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_normalquestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterNormalquestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitNormalquestion(this);
		}
	}

	public final NormalquestionContext normalquestion() throws RecognitionException {
		NormalquestionContext _localctx = new NormalquestionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_normalquestion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			((NormalquestionContext)_localctx).questionid = match(ID);
			setState(95);
			match(T__4);
			setState(96);
			((NormalquestionContext)_localctx).nqlabel = label();
			setState(97);
			((NormalquestionContext)_localctx).type = questiontype();

			         ((NormalquestionContext)_localctx).result =  new NormalQuestion(new Ident((((NormalquestionContext)_localctx).questionid!=null?((NormalquestionContext)_localctx).questionid.getText():null)), new Label((((NormalquestionContext)_localctx).nqlabel!=null?_input.getText(((NormalquestionContext)_localctx).nqlabel.start,((NormalquestionContext)_localctx).nqlabel.stop):null)), ((NormalquestionContext)_localctx).type.result); 
			        
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
		public NormalquestionContext normalquestion;
		public OrExprContext orExpr;
		public NormalquestionContext normalquestion() {
			return getRuleContext(NormalquestionContext.class,0);
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
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterComputedquestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitComputedquestion(this);
		}
	}

	public final ComputedquestionContext computedquestion() throws RecognitionException {
		ComputedquestionContext _localctx = new ComputedquestionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_computedquestion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			((ComputedquestionContext)_localctx).normalquestion = normalquestion();
			setState(101);
			match(T__2);
			setState(102);
			((ComputedquestionContext)_localctx).orExpr = orExpr();
			setState(103);
			match(T__3);
			 
			    ((ComputedquestionContext)_localctx).result =  new ComputedQuestion(((ComputedquestionContext)_localctx).normalquestion.result.getId(), ((ComputedquestionContext)_localctx).normalquestion.result.getLabel(), ((ComputedquestionContext)_localctx).normalquestion.result.getType(), ((ComputedquestionContext)_localctx).orExpr.result);
			    
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
		public Expr result;
		public Token ID;
		public TerminalNode ID() { return getToken(FormulierParser.ID, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			((VariableContext)_localctx).ID = match(ID);

			        ((VariableContext)_localctx).result =  new Ident((((VariableContext)_localctx).ID!=null?((VariableContext)_localctx).ID.getText():null)); 
			        
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
		public Token STR;
		public TerminalNode STR() { return getToken(FormulierParser.STR, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitLabel(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			((LabelContext)_localctx).STR = match(STR);
			 
			        ((LabelContext)_localctx).result =  new Label((((LabelContext)_localctx).STR!=null?((LabelContext)_localctx).STR.getText():null)); 
			        
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
		public Type result;
		public TerminalNode BOOL_TYPE() { return getToken(FormulierParser.BOOL_TYPE, 0); }
		public TerminalNode MONEY_TYPE() { return getToken(FormulierParser.MONEY_TYPE, 0); }
		public TerminalNode STRING_TYPE() { return getToken(FormulierParser.STRING_TYPE, 0); }
		public TerminalNode INTEGER_TYPE() { return getToken(FormulierParser.INTEGER_TYPE, 0); }
		public QuestiontypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questiontype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterQuestiontype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitQuestiontype(this);
		}
	}

	public final QuestiontypeContext questiontype() throws RecognitionException {
		QuestiontypeContext _localctx = new QuestiontypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_questiontype);
		try {
			setState(120);
			switch (_input.LA(1)) {
			case BOOL_TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				match(BOOL_TYPE);

				        ((QuestiontypeContext)_localctx).result =  new Bool();
				        
				}
				break;
			case MONEY_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
				match(MONEY_TYPE);

				        ((QuestiontypeContext)_localctx).result =  new Money();
				        
				}
				break;
			case STRING_TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(116);
				match(STRING_TYPE);

				        ((QuestiontypeContext)_localctx).result =  new Str();
				        
				}
				break;
			case INTEGER_TYPE:
				enterOuterAlt(_localctx, 4);
				{
				setState(118);
				match(INTEGER_TYPE);

				        ((QuestiontypeContext)_localctx).result =  new Int();
				        
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

	public static class PrimaryContext extends ParserRuleContext {
		public Expr result;
		public Token DIGIT;
		public Token ID;
		public Token STR;
		public BoolContext bool;
		public OrExprContext x;
		public TerminalNode DIGIT() { return getToken(FormulierParser.DIGIT, 0); }
		public TerminalNode ID() { return getToken(FormulierParser.ID, 0); }
		public TerminalNode STR() { return getToken(FormulierParser.STR, 0); }
		public TerminalNode CURRENCYSYMBOL() { return getToken(FormulierParser.CURRENCYSYMBOL, 0); }
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
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitPrimary(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_primary);
		try {
			setState(139);
			switch (_input.LA(1)) {
			case DIGIT:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				((PrimaryContext)_localctx).DIGIT = match(DIGIT);
				 
				        ((PrimaryContext)_localctx).result =  new IntLiteral(Integer.parseInt((((PrimaryContext)_localctx).DIGIT!=null?((PrimaryContext)_localctx).DIGIT.getText():null))); 
				        _localctx.result.setLiteral();
				        
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(124);
				((PrimaryContext)_localctx).ID = match(ID);
				 
				        ((PrimaryContext)_localctx).result =  new Ident((((PrimaryContext)_localctx).ID!=null?((PrimaryContext)_localctx).ID.getText():null));
				        
				}
				break;
			case STR:
				enterOuterAlt(_localctx, 3);
				{
				setState(126);
				((PrimaryContext)_localctx).STR = match(STR);
				 
				        ((PrimaryContext)_localctx).result =  new StrLiteral((((PrimaryContext)_localctx).STR!=null?((PrimaryContext)_localctx).STR.getText():null)); 
				        _localctx.result.setLiteral();
				        
				}
				break;
			case CURRENCYSYMBOL:
				enterOuterAlt(_localctx, 4);
				{
				setState(128);
				match(CURRENCYSYMBOL);
				setState(129);
				((PrimaryContext)_localctx).DIGIT = match(DIGIT);
				 
				        ((PrimaryContext)_localctx).result =  new MoneyLiteral(Integer.parseInt((((PrimaryContext)_localctx).DIGIT!=null?((PrimaryContext)_localctx).DIGIT.getText():null))); 
				        _localctx.result.setLiteral();
				        
				}
				break;
			case T__5:
			case T__6:
				enterOuterAlt(_localctx, 5);
				{
				setState(131);
				((PrimaryContext)_localctx).bool = bool();
				 
				        ((PrimaryContext)_localctx).result =  ((PrimaryContext)_localctx).bool.result; 
				        _localctx.result.setLiteral();
				        
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 6);
				{
				setState(134);
				match(T__2);
				setState(135);
				((PrimaryContext)_localctx).x = orExpr();
				setState(136);
				match(T__3);
				 
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
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterUnExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitUnExpr(this);
		}
	}

	public final UnExprContext unExpr() throws RecognitionException {
		UnExprContext _localctx = new UnExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_unExpr);
		try {
			setState(156);
			switch (_input.LA(1)) {
			case ADD:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				match(ADD);
				setState(142);
				((UnExprContext)_localctx).x = unExpr();
				 
				        ((UnExprContext)_localctx).result =  new Pos(((UnExprContext)_localctx).x.result); 
				        
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				match(MINUS);
				setState(146);
				((UnExprContext)_localctx).x = unExpr();
				 
				        ((UnExprContext)_localctx).result =  new Neg(((UnExprContext)_localctx).x.result); 
				        
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(149);
				match(NOT);
				setState(150);
				((UnExprContext)_localctx).x = unExpr();
				 
				        ((UnExprContext)_localctx).result =  new Not(((UnExprContext)_localctx).x.result); 
				        
				}
				break;
			case T__2:
			case T__5:
			case T__6:
			case DIGIT:
			case STR:
			case CURRENCYSYMBOL:
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(153);
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

	public static class BoolContext extends ParserRuleContext {
		public Expr result;
		public Token booltrue;
		public Token boolfalse;
		public BoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitBool(this);
		}
	}

	public final BoolContext bool() throws RecognitionException {
		BoolContext _localctx = new BoolContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_bool);
		try {
			setState(162);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				((BoolContext)_localctx).booltrue = match(T__5);
				 
				        ((BoolContext)_localctx).result =  new BoolLiteral(true); 
				        
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(160);
				((BoolContext)_localctx).boolfalse = match(T__6);
				 
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
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterMulExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitMulExpr(this);
		}
	}

	public final MulExprContext mulExpr() throws RecognitionException {
		MulExprContext _localctx = new MulExprContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_mulExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			((MulExprContext)_localctx).lhs = unExpr();
			   ((MulExprContext)_localctx).result = ((MulExprContext)_localctx).lhs.result; 
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MULTIPLY || _la==DIVIDE) {
				{
				{
				setState(166);
				((MulExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==MULTIPLY || _la==DIVIDE) ) {
					((MulExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(167);
				((MulExprContext)_localctx).rhs = unExpr();
				 
				        if ((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getText():null).equals("*")) {
				        ((MulExprContext)_localctx).result =  new Mul(_localctx.result, ((MulExprContext)_localctx).rhs.result);
				        }
				        if ((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getText():null).equals("<=")) {
				        ((MulExprContext)_localctx).result =  new Div(_localctx.result, ((MulExprContext)_localctx).rhs.result);      
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
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterAddExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitAddExpr(this);
		}
	}

	public final AddExprContext addExpr() throws RecognitionException {
		AddExprContext _localctx = new AddExprContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_addExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			((AddExprContext)_localctx).lhs = mulExpr();
			 ((AddExprContext)_localctx).result = ((AddExprContext)_localctx).lhs.result; 
			setState(183);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MINUS || _la==ADD) {
				{
				{
				setState(177);
				((AddExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==MINUS || _la==ADD) ) {
					((AddExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(178);
				((AddExprContext)_localctx).rhs = mulExpr();
				 
				      if ((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getText():null).equals("+")) {
				        ((AddExprContext)_localctx).result =  new Add(_localctx.result, ((AddExprContext)_localctx).rhs.result);
				      }
				      if ((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getText():null).equals("-")) {
				        ((AddExprContext)_localctx).result =  new Sub(_localctx.result, ((AddExprContext)_localctx).rhs.result);      
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
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterRelExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitRelExpr(this);
		}
	}

	public final RelExprContext relExpr() throws RecognitionException {
		RelExprContext _localctx = new RelExprContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_relExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			((RelExprContext)_localctx).lhs = addExpr();
			 ((RelExprContext)_localctx).result = ((RelExprContext)_localctx).lhs.result; 
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SMALLERTHAN) | (1L << BIGGERTHAN) | (1L << SMALLER_EQUAL) | (1L << BIGGER_EQUAL) | (1L << EQUAL) | (1L << NOT_EQUAL))) != 0)) {
				{
				{
				setState(188);
				((RelExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SMALLERTHAN) | (1L << BIGGERTHAN) | (1L << SMALLER_EQUAL) | (1L << BIGGER_EQUAL) | (1L << EQUAL) | (1L << NOT_EQUAL))) != 0)) ) {
					((RelExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(189);
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
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitAndExpr(this);
		}
	}

	public final AndExprContext andExpr() throws RecognitionException {
		AndExprContext _localctx = new AndExprContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_andExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			((AndExprContext)_localctx).lhs = relExpr();
			 ((AndExprContext)_localctx).result = ((AndExprContext)_localctx).lhs.result; 
			setState(205);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(199);
				match(AND);
				setState(200);
				((AndExprContext)_localctx).rhs = relExpr();
				 ((AndExprContext)_localctx).result =  new And(_localctx.result, ((AndExprContext)_localctx).rhs.result); 
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
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulierListener ) ((FormulierListener)listener).exitOrExpr(this);
		}
	}

	public final OrExprContext orExpr() throws RecognitionException {
		OrExprContext _localctx = new OrExprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_orExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			((OrExprContext)_localctx).lhs = andExpr();
			 ((OrExprContext)_localctx).result =  ((OrExprContext)_localctx).lhs.result; 
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(210);
				match(OR);
				setState(211);
				((OrExprContext)_localctx).rhs = andExpr();
				 ((OrExprContext)_localctx).result =  new Or(_localctx.result, ((OrExprContext)_localctx).rhs.result); 
				}
				}
				setState(218);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3*\u00de\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\7\49\n\4\f\4\16\4<\13\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\5\5G\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\5\b_\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\5\r{\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u008e\n\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u009f\n\17"+
		"\3\20\3\20\3\20\3\20\5\20\u00a5\n\20\3\21\3\21\3\21\3\21\3\21\3\21\7\21"+
		"\u00ad\n\21\f\21\16\21\u00b0\13\21\3\22\3\22\3\22\3\22\3\22\3\22\7\22"+
		"\u00b8\n\22\f\22\16\22\u00bb\13\22\3\23\3\23\3\23\3\23\3\23\3\23\7\23"+
		"\u00c3\n\23\f\23\16\23\u00c6\13\23\3\24\3\24\3\24\3\24\3\24\3\24\7\24"+
		"\u00ce\n\24\f\24\16\24\u00d1\13\24\3\25\3\25\3\25\3\25\3\25\3\25\7\25"+
		"\u00d9\n\25\f\25\16\25\u00dc\13\25\3\25\2\2\26\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(\2\5\3\2\37 \3\2\35\36\3\2\20\25\u00de\2*\3\2\2\2"+
		"\4/\3\2\2\2\6\61\3\2\2\2\bF\3\2\2\2\nH\3\2\2\2\fO\3\2\2\2\16^\3\2\2\2"+
		"\20`\3\2\2\2\22f\3\2\2\2\24l\3\2\2\2\26o\3\2\2\2\30z\3\2\2\2\32\u008d"+
		"\3\2\2\2\34\u009e\3\2\2\2\36\u00a4\3\2\2\2 \u00a6\3\2\2\2\"\u00b1\3\2"+
		"\2\2$\u00bc\3\2\2\2&\u00c7\3\2\2\2(\u00d2\3\2\2\2*+\7\33\2\2+,\5\4\3\2"+
		",-\5\6\4\2-.\b\2\1\2.\3\3\2\2\2/\60\7(\2\2\60\5\3\2\2\2\61:\7\3\2\2\62"+
		"\63\5\16\b\2\63\64\b\4\1\2\649\3\2\2\2\65\66\5\b\5\2\66\67\b\4\1\2\67"+
		"9\3\2\2\28\62\3\2\2\28\65\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;=\3\2"+
		"\2\2<:\3\2\2\2=>\7\4\2\2>?\b\4\1\2?\7\3\2\2\2@A\5\n\6\2AB\b\5\1\2BG\3"+
		"\2\2\2CD\5\f\7\2DE\b\5\1\2EG\3\2\2\2F@\3\2\2\2FC\3\2\2\2G\t\3\2\2\2HI"+
		"\7\31\2\2IJ\7\5\2\2JK\5(\25\2KL\7\6\2\2LM\5\6\4\2MN\b\6\1\2N\13\3\2\2"+
		"\2OP\7\31\2\2PQ\7\5\2\2QR\5(\25\2RS\7\6\2\2ST\5\6\4\2TU\7\32\2\2UV\5\6"+
		"\4\2VW\b\7\1\2W\r\3\2\2\2XY\5\20\t\2YZ\b\b\1\2Z_\3\2\2\2[\\\5\22\n\2\\"+
		"]\b\b\1\2]_\3\2\2\2^X\3\2\2\2^[\3\2\2\2_\17\3\2\2\2`a\7(\2\2ab\7\7\2\2"+
		"bc\5\26\f\2cd\5\30\r\2de\b\t\1\2e\21\3\2\2\2fg\5\20\t\2gh\7\5\2\2hi\5"+
		"(\25\2ij\7\6\2\2jk\b\n\1\2k\23\3\2\2\2lm\7(\2\2mn\b\13\1\2n\25\3\2\2\2"+
		"op\7\"\2\2pq\b\f\1\2q\27\3\2\2\2rs\7\n\2\2s{\b\r\1\2tu\7\13\2\2u{\b\r"+
		"\1\2vw\7\f\2\2w{\b\r\1\2xy\7\r\2\2y{\b\r\1\2zr\3\2\2\2zt\3\2\2\2zv\3\2"+
		"\2\2zx\3\2\2\2{\31\3\2\2\2|}\7\17\2\2}\u008e\b\16\1\2~\177\7(\2\2\177"+
		"\u008e\b\16\1\2\u0080\u0081\7\"\2\2\u0081\u008e\b\16\1\2\u0082\u0083\7"+
		"%\2\2\u0083\u0084\7\17\2\2\u0084\u008e\b\16\1\2\u0085\u0086\5\36\20\2"+
		"\u0086\u0087\b\16\1\2\u0087\u008e\3\2\2\2\u0088\u0089\7\5\2\2\u0089\u008a"+
		"\5(\25\2\u008a\u008b\7\6\2\2\u008b\u008c\b\16\1\2\u008c\u008e\3\2\2\2"+
		"\u008d|\3\2\2\2\u008d~\3\2\2\2\u008d\u0080\3\2\2\2\u008d\u0082\3\2\2\2"+
		"\u008d\u0085\3\2\2\2\u008d\u0088\3\2\2\2\u008e\33\3\2\2\2\u008f\u0090"+
		"\7\36\2\2\u0090\u0091\5\34\17\2\u0091\u0092\b\17\1\2\u0092\u009f\3\2\2"+
		"\2\u0093\u0094\7\35\2\2\u0094\u0095\5\34\17\2\u0095\u0096\b\17\1\2\u0096"+
		"\u009f\3\2\2\2\u0097\u0098\7\30\2\2\u0098\u0099\5\34\17\2\u0099\u009a"+
		"\b\17\1\2\u009a\u009f\3\2\2\2\u009b\u009c\5\32\16\2\u009c\u009d\b\17\1"+
		"\2\u009d\u009f\3\2\2\2\u009e\u008f\3\2\2\2\u009e\u0093\3\2\2\2\u009e\u0097"+
		"\3\2\2\2\u009e\u009b\3\2\2\2\u009f\35\3\2\2\2\u00a0\u00a1\7\b\2\2\u00a1"+
		"\u00a5\b\20\1\2\u00a2\u00a3\7\t\2\2\u00a3\u00a5\b\20\1\2\u00a4\u00a0\3"+
		"\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\37\3\2\2\2\u00a6\u00a7\5\34\17\2\u00a7"+
		"\u00ae\b\21\1\2\u00a8\u00a9\t\2\2\2\u00a9\u00aa\5\34\17\2\u00aa\u00ab"+
		"\b\21\1\2\u00ab\u00ad\3\2\2\2\u00ac\u00a8\3\2\2\2\u00ad\u00b0\3\2\2\2"+
		"\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af!\3\2\2\2\u00b0\u00ae\3"+
		"\2\2\2\u00b1\u00b2\5 \21\2\u00b2\u00b9\b\22\1\2\u00b3\u00b4\t\3\2\2\u00b4"+
		"\u00b5\5 \21\2\u00b5\u00b6\b\22\1\2\u00b6\u00b8\3\2\2\2\u00b7\u00b3\3"+
		"\2\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba"+
		"#\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc\u00bd\5\"\22\2\u00bd\u00c4\b\23\1"+
		"\2\u00be\u00bf\t\4\2\2\u00bf\u00c0\5\"\22\2\u00c0\u00c1\b\23\1\2\u00c1"+
		"\u00c3\3\2\2\2\u00c2\u00be\3\2\2\2\u00c3\u00c6\3\2\2\2\u00c4\u00c2\3\2"+
		"\2\2\u00c4\u00c5\3\2\2\2\u00c5%\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00c8"+
		"\5$\23\2\u00c8\u00cf\b\24\1\2\u00c9\u00ca\7\26\2\2\u00ca\u00cb\5$\23\2"+
		"\u00cb\u00cc\b\24\1\2\u00cc\u00ce\3\2\2\2\u00cd\u00c9\3\2\2\2\u00ce\u00d1"+
		"\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\'\3\2\2\2\u00d1"+
		"\u00cf\3\2\2\2\u00d2\u00d3\5&\24\2\u00d3\u00da\b\25\1\2\u00d4\u00d5\7"+
		"\27\2\2\u00d5\u00d6\5&\24\2\u00d6\u00d7\b\25\1\2\u00d7\u00d9\3\2\2\2\u00d8"+
		"\u00d4\3\2\2\2\u00d9\u00dc\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2"+
		"\2\2\u00db)\3\2\2\2\u00dc\u00da\3\2\2\2\178:F^z\u008d\u009e\u00a4\u00ae"+
		"\u00b9\u00c4\u00cf\u00da";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}