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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, BOOLEAN_TYPE=9, 
		MONEY_TYPE=10, INTEGER_TYPE=11, STRING_TYPE=12, WS=13, COMMENT=14, Ident=15, 
		Int=16, Str=17, OrSymbol=18, AndSymbol=19, RelSymbol=20, AddSymbol=21, 
		PlusSymbol=22, MinSymbol=23, MulSymbol=24, True=25, False=26, ExclamationMark=27;
	public static final int
		RULE_form = 0, RULE_block = 1, RULE_statement = 2, RULE_ifstatement = 3, 
		RULE_ifelsestatement = 4, RULE_question = 5, RULE_simplequestion = 6, 
		RULE_computedquestion = 7, RULE_type = 8, RULE_label = 9, RULE_primary = 10, 
		RULE_bool = 11, RULE_unaryExpr = 12, RULE_mulExpr = 13, RULE_addExpr = 14, 
		RULE_relExpr = 15, RULE_andExpr = 16, RULE_orExpr = 17;
	public static final String[] ruleNames = {
		"form", "block", "statement", "ifstatement", "ifelsestatement", "question", 
		"simplequestion", "computedquestion", "type", "label", "primary", "bool", 
		"unaryExpr", "mulExpr", "addExpr", "relExpr", "andExpr", "orExpr"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'if'", "'('", "')'", "'else'", "':'", "'boolean'", 
		"'money'", "'integer'", "'string'", null, null, null, null, null, "'||'", 
		"'&&'", null, null, "'+'", "'-'", null, "'true'", "'false'", "'!'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, "BOOLEAN_TYPE", 
		"MONEY_TYPE", "INTEGER_TYPE", "STRING_TYPE", "WS", "COMMENT", "Ident", 
		"Int", "Str", "OrSymbol", "AndSymbol", "RelSymbol", "AddSymbol", "PlusSymbol", 
		"MinSymbol", "MulSymbol", "True", "False", "ExclamationMark"
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
			match(T__1);
			setState(39);
			((FormContext)_localctx).body = block();
			setState(40);
			match(T__2);
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
			match(T__1);
			setState(69);
			((IfstatementContext)_localctx).block = block();
			setState(70);
			match(T__2);
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
			setState(73);
			match(T__3);
			setState(74);
			match(T__4);
			setState(75);
			((IfelsestatementContext)_localctx).condition = orExpr();
			setState(76);
			match(T__5);
			setState(77);
			match(T__1);
			setState(78);
			((IfelsestatementContext)_localctx).thenstatement = block();
			setState(79);
			match(T__2);
			setState(80);
			match(T__6);
			setState(81);
			match(T__1);
			setState(82);
			((IfelsestatementContext)_localctx).elsestatement = block();
			setState(83);
			match(T__2);
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
			setState(92);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(86);
				((QuestionContext)_localctx).simplequestion = simplequestion();
				 ((QuestionContext)_localctx).result =  ((QuestionContext)_localctx).simplequestion.result;
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

	public static class SimplequestionContext extends ParserRuleContext {
		public Question result;
		public Token questionid;
		public LabelContext questionlabel;
		public TypeContext type;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
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
			setState(94);
			((SimplequestionContext)_localctx).questionid = match(Ident);
			setState(95);
			match(T__7);
			setState(96);
			((SimplequestionContext)_localctx).questionlabel = label();
			setState(97);
			((SimplequestionContext)_localctx).type = type();
			 
			    ((SimplequestionContext)_localctx).result =  new SimpleQuestion(new Ident((((SimplequestionContext)_localctx).questionid!=null?((SimplequestionContext)_localctx).questionid.getText():null)), new Label((((SimplequestionContext)_localctx).questionlabel!=null?_input.getText(((SimplequestionContext)_localctx).questionlabel.start,((SimplequestionContext)_localctx).questionlabel.stop):null)), ((SimplequestionContext)_localctx).type.result); 
			    
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
			setState(100);
			((ComputedquestionContext)_localctx).simplequestion = simplequestion();
			setState(101);
			match(T__4);
			setState(102);
			((ComputedquestionContext)_localctx).orExpr = orExpr();
			setState(103);
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

	public static class TypeContext extends ParserRuleContext {
		public Type result;
		public TerminalNode STRING_TYPE() { return getToken(QLParser.STRING_TYPE, 0); }
		public TerminalNode INTEGER_TYPE() { return getToken(QLParser.INTEGER_TYPE, 0); }
		public TerminalNode MONEY_TYPE() { return getToken(QLParser.MONEY_TYPE, 0); }
		public TerminalNode BOOLEAN_TYPE() { return getToken(QLParser.BOOLEAN_TYPE, 0); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_type);
		try {
			setState(114);
			switch (_input.LA(1)) {
			case STRING_TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				match(STRING_TYPE);
				 ((TypeContext)_localctx).result =  new StringType(); 
				}
				break;
			case INTEGER_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				match(INTEGER_TYPE);
				 ((TypeContext)_localctx).result =  new IntType(); 
				}
				break;
			case MONEY_TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(110);
				match(MONEY_TYPE);
				 ((TypeContext)_localctx).result =  new IntType(); 
				}
				break;
			case BOOLEAN_TYPE:
				enterOuterAlt(_localctx, 4);
				{
				setState(112);
				match(BOOLEAN_TYPE);
				 ((TypeContext)_localctx).result =  new BoolType(); 
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
			setState(116);
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
			setState(133);
			switch (_input.LA(1)) {
			case Int:
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				((PrimaryContext)_localctx).Int = match(Int);
				 ((PrimaryContext)_localctx).result =  new IntegerLiteral(Integer.parseInt((((PrimaryContext)_localctx).Int!=null?((PrimaryContext)_localctx).Int.getText():null))); 
				            _localctx.result.setLiteral(); 
				}
				break;
			case Ident:
				enterOuterAlt(_localctx, 2);
				{
				setState(121);
				((PrimaryContext)_localctx).Ident = match(Ident);
				 ((PrimaryContext)_localctx).result =  new Ident((((PrimaryContext)_localctx).Ident!=null?((PrimaryContext)_localctx).Ident.getText():null)); 
				}
				break;
			case Str:
				enterOuterAlt(_localctx, 3);
				{
				setState(123);
				((PrimaryContext)_localctx).Str = match(Str);
				 ((PrimaryContext)_localctx).result =  new StringLiteral((((PrimaryContext)_localctx).Str!=null?((PrimaryContext)_localctx).Str.getText():null)); 
				            _localctx.result.setLiteral(); 
				}
				break;
			case True:
			case False:
				enterOuterAlt(_localctx, 4);
				{
				setState(125);
				((PrimaryContext)_localctx).bool = bool();
				 ((PrimaryContext)_localctx).result =  ((PrimaryContext)_localctx).bool.result; 
				            _localctx.result.setLiteral(); 
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 5);
				{
				setState(128);
				match(T__4);
				setState(129);
				((PrimaryContext)_localctx).x = orExpr();
				setState(130);
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
			setState(139);
			switch (_input.LA(1)) {
			case True:
				enterOuterAlt(_localctx, 1);
				{
				setState(135);
				((BoolContext)_localctx).t = match(True);
				 ((BoolContext)_localctx).result =  new BoolLiteral(true); 
				}
				break;
			case False:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				((BoolContext)_localctx).t = match(False);
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
		public TerminalNode PlusSymbol() { return getToken(QLParser.PlusSymbol, 0); }
		public UnaryExprContext unaryExpr() {
			return getRuleContext(UnaryExprContext.class,0);
		}
		public TerminalNode MinSymbol() { return getToken(QLParser.MinSymbol, 0); }
		public TerminalNode ExclamationMark() { return getToken(QLParser.ExclamationMark, 0); }
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
			setState(156);
			switch (_input.LA(1)) {
			case PlusSymbol:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				match(PlusSymbol);
				setState(142);
				((UnaryExprContext)_localctx).x = unaryExpr();
				 ((UnaryExprContext)_localctx).result =  new Pos(((UnaryExprContext)_localctx).x.result); 
				}
				break;
			case MinSymbol:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				match(MinSymbol);
				setState(146);
				((UnaryExprContext)_localctx).x = unaryExpr();
				 ((UnaryExprContext)_localctx).result =  new Neg(((UnaryExprContext)_localctx).x.result); 
				}
				break;
			case ExclamationMark:
				enterOuterAlt(_localctx, 3);
				{
				setState(149);
				match(ExclamationMark);
				setState(150);
				((UnaryExprContext)_localctx).x = unaryExpr();
				 ((UnaryExprContext)_localctx).result =  new Not(((UnaryExprContext)_localctx).x.result); 
				}
				break;
			case T__4:
			case Ident:
			case Int:
			case Str:
			case True:
			case False:
				enterOuterAlt(_localctx, 4);
				{
				setState(153);
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
		public List<TerminalNode> MulSymbol() { return getTokens(QLParser.MulSymbol); }
		public TerminalNode MulSymbol(int i) {
			return getToken(QLParser.MulSymbol, i);
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
			setState(158);
			((MulExprContext)_localctx).lhs = unaryExpr();
			 ((MulExprContext)_localctx).result = ((MulExprContext)_localctx).lhs.result; 
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MulSymbol) {
				{
				{
				setState(160);
				((MulExprContext)_localctx).op = match(MulSymbol);
				setState(161);
				((MulExprContext)_localctx).rhs = unaryExpr();
				 
				      if ((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getText():null).equals("*")) {
				        ((MulExprContext)_localctx).result =  new Mul(_localctx.result, ((MulExprContext)_localctx).rhs.result);
				      }
				      if ((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getText():null).equals("<=")) {
				        ((MulExprContext)_localctx).result =  new Div(_localctx.result, ((MulExprContext)_localctx).rhs.result);      
				      }
				    
				}
				}
				setState(168);
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
		public List<TerminalNode> AddSymbol() { return getTokens(QLParser.AddSymbol); }
		public TerminalNode AddSymbol(int i) {
			return getToken(QLParser.AddSymbol, i);
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
			setState(169);
			((AddExprContext)_localctx).lhs = mulExpr();
			 ((AddExprContext)_localctx).result = ((AddExprContext)_localctx).lhs.result; 
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AddSymbol) {
				{
				{
				setState(171);
				((AddExprContext)_localctx).op = match(AddSymbol);
				setState(172);
				((AddExprContext)_localctx).rhs = mulExpr();
				 
				      if ((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getText():null).equals("+")) {
				        ((AddExprContext)_localctx).result =  new Add(_localctx.result, ((AddExprContext)_localctx).rhs.result);
				      }
				      if ((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getText():null).equals("-")) {
				        ((AddExprContext)_localctx).result =  new Sub(_localctx.result, ((AddExprContext)_localctx).rhs.result);      
				      }
				    
				}
				}
				setState(179);
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
		public List<TerminalNode> RelSymbol() { return getTokens(QLParser.RelSymbol); }
		public TerminalNode RelSymbol(int i) {
			return getToken(QLParser.RelSymbol, i);
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
			setState(180);
			((RelExprContext)_localctx).lhs = addExpr();
			 ((RelExprContext)_localctx).result = ((RelExprContext)_localctx).lhs.result; 
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==RelSymbol) {
				{
				{
				setState(182);
				((RelExprContext)_localctx).op = match(RelSymbol);
				setState(183);
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
				setState(190);
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
			setState(191);
			((AndExprContext)_localctx).lhs = relExpr();
			 ((AndExprContext)_localctx).result = ((AndExprContext)_localctx).lhs.result; 
			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AndSymbol) {
				{
				{
				setState(193);
				match(AndSymbol);
				setState(194);
				((AndExprContext)_localctx).rhs = relExpr();
				 ((AndExprContext)_localctx).result =  new And(_localctx.result, ((AndExprContext)_localctx).rhs.result); 
				}
				}
				setState(201);
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
		public List<TerminalNode> OrSymbol() { return getTokens(QLParser.OrSymbol); }
		public TerminalNode OrSymbol(int i) {
			return getToken(QLParser.OrSymbol, i);
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
			setState(202);
			((OrExprContext)_localctx).lhs = andExpr();
			 ((OrExprContext)_localctx).result =  ((OrExprContext)_localctx).lhs.result; 
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OrSymbol) {
				{
				{
				setState(204);
				match(OrSymbol);
				setState(205);
				((OrExprContext)_localctx).rhs = andExpr();
				 ((OrExprContext)_localctx).result =  new Or(_localctx.result, ((OrExprContext)_localctx).rhs.result); 
				}
				}
				setState(212);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\35\u00d8\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3\64"+
		"\n\3\f\3\16\3\67\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4A\n\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7_\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\nu\n\n\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0088"+
		"\n\f\3\r\3\r\3\r\3\r\5\r\u008e\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u009f\n\16\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\7\17\u00a7\n\17\f\17\16\17\u00aa\13\17\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\7\20\u00b2\n\20\f\20\16\20\u00b5\13\20\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\7\21\u00bd\n\21\f\21\16\21\u00c0\13\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\7\22\u00c8\n\22\f\22\16\22\u00cb\13\22\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\7\23\u00d3\n\23\f\23\16\23\u00d6\13\23\3\23\2\2\24\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$\2\2\u00d9\2&\3\2\2\2\4\65\3\2"+
		"\2\2\6@\3\2\2\2\bB\3\2\2\2\nK\3\2\2\2\f^\3\2\2\2\16`\3\2\2\2\20f\3\2\2"+
		"\2\22t\3\2\2\2\24v\3\2\2\2\26\u0087\3\2\2\2\30\u008d\3\2\2\2\32\u009e"+
		"\3\2\2\2\34\u00a0\3\2\2\2\36\u00ab\3\2\2\2 \u00b6\3\2\2\2\"\u00c1\3\2"+
		"\2\2$\u00cc\3\2\2\2&\'\7\3\2\2\'(\7\21\2\2()\7\4\2\2)*\5\4\3\2*+\7\5\2"+
		"\2+,\b\2\1\2,\3\3\2\2\2-.\5\f\7\2./\b\3\1\2/\64\3\2\2\2\60\61\5\6\4\2"+
		"\61\62\b\3\1\2\62\64\3\2\2\2\63-\3\2\2\2\63\60\3\2\2\2\64\67\3\2\2\2\65"+
		"\63\3\2\2\2\65\66\3\2\2\2\668\3\2\2\2\67\65\3\2\2\289\b\3\1\29\5\3\2\2"+
		"\2:;\5\b\5\2;<\b\4\1\2<A\3\2\2\2=>\5\n\6\2>?\b\4\1\2?A\3\2\2\2@:\3\2\2"+
		"\2@=\3\2\2\2A\7\3\2\2\2BC\7\6\2\2CD\7\7\2\2DE\5$\23\2EF\7\b\2\2FG\7\4"+
		"\2\2GH\5\4\3\2HI\7\5\2\2IJ\b\5\1\2J\t\3\2\2\2KL\7\6\2\2LM\7\7\2\2MN\5"+
		"$\23\2NO\7\b\2\2OP\7\4\2\2PQ\5\4\3\2QR\7\5\2\2RS\7\t\2\2ST\7\4\2\2TU\5"+
		"\4\3\2UV\7\5\2\2VW\b\6\1\2W\13\3\2\2\2XY\5\16\b\2YZ\b\7\1\2Z_\3\2\2\2"+
		"[\\\5\20\t\2\\]\b\7\1\2]_\3\2\2\2^X\3\2\2\2^[\3\2\2\2_\r\3\2\2\2`a\7\21"+
		"\2\2ab\7\n\2\2bc\5\24\13\2cd\5\22\n\2de\b\b\1\2e\17\3\2\2\2fg\5\16\b\2"+
		"gh\7\7\2\2hi\5$\23\2ij\7\b\2\2jk\b\t\1\2k\21\3\2\2\2lm\7\16\2\2mu\b\n"+
		"\1\2no\7\r\2\2ou\b\n\1\2pq\7\f\2\2qu\b\n\1\2rs\7\13\2\2su\b\n\1\2tl\3"+
		"\2\2\2tn\3\2\2\2tp\3\2\2\2tr\3\2\2\2u\23\3\2\2\2vw\7\23\2\2wx\b\13\1\2"+
		"x\25\3\2\2\2yz\7\22\2\2z\u0088\b\f\1\2{|\7\21\2\2|\u0088\b\f\1\2}~\7\23"+
		"\2\2~\u0088\b\f\1\2\177\u0080\5\30\r\2\u0080\u0081\b\f\1\2\u0081\u0088"+
		"\3\2\2\2\u0082\u0083\7\7\2\2\u0083\u0084\5$\23\2\u0084\u0085\7\b\2\2\u0085"+
		"\u0086\b\f\1\2\u0086\u0088\3\2\2\2\u0087y\3\2\2\2\u0087{\3\2\2\2\u0087"+
		"}\3\2\2\2\u0087\177\3\2\2\2\u0087\u0082\3\2\2\2\u0088\27\3\2\2\2\u0089"+
		"\u008a\7\33\2\2\u008a\u008e\b\r\1\2\u008b\u008c\7\34\2\2\u008c\u008e\b"+
		"\r\1\2\u008d\u0089\3\2\2\2\u008d\u008b\3\2\2\2\u008e\31\3\2\2\2\u008f"+
		"\u0090\7\30\2\2\u0090\u0091\5\32\16\2\u0091\u0092\b\16\1\2\u0092\u009f"+
		"\3\2\2\2\u0093\u0094\7\31\2\2\u0094\u0095\5\32\16\2\u0095\u0096\b\16\1"+
		"\2\u0096\u009f\3\2\2\2\u0097\u0098\7\35\2\2\u0098\u0099\5\32\16\2\u0099"+
		"\u009a\b\16\1\2\u009a\u009f\3\2\2\2\u009b\u009c\5\26\f\2\u009c\u009d\b"+
		"\16\1\2\u009d\u009f\3\2\2\2\u009e\u008f\3\2\2\2\u009e\u0093\3\2\2\2\u009e"+
		"\u0097\3\2\2\2\u009e\u009b\3\2\2\2\u009f\33\3\2\2\2\u00a0\u00a1\5\32\16"+
		"\2\u00a1\u00a8\b\17\1\2\u00a2\u00a3\7\32\2\2\u00a3\u00a4\5\32\16\2\u00a4"+
		"\u00a5\b\17\1\2\u00a5\u00a7\3\2\2\2\u00a6\u00a2\3\2\2\2\u00a7\u00aa\3"+
		"\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\35\3\2\2\2\u00aa"+
		"\u00a8\3\2\2\2\u00ab\u00ac\5\34\17\2\u00ac\u00b3\b\20\1\2\u00ad\u00ae"+
		"\7\27\2\2\u00ae\u00af\5\34\17\2\u00af\u00b0\b\20\1\2\u00b0\u00b2\3\2\2"+
		"\2\u00b1\u00ad\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4"+
		"\3\2\2\2\u00b4\37\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b7\5\36\20\2\u00b7"+
		"\u00be\b\21\1\2\u00b8\u00b9\7\26\2\2\u00b9\u00ba\5\36\20\2\u00ba\u00bb"+
		"\b\21\1\2\u00bb\u00bd\3\2\2\2\u00bc\u00b8\3\2\2\2\u00bd\u00c0\3\2\2\2"+
		"\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf!\3\2\2\2\u00c0\u00be\3"+
		"\2\2\2\u00c1\u00c2\5 \21\2\u00c2\u00c9\b\22\1\2\u00c3\u00c4\7\25\2\2\u00c4"+
		"\u00c5\5 \21\2\u00c5\u00c6\b\22\1\2\u00c6\u00c8\3\2\2\2\u00c7\u00c3\3"+
		"\2\2\2\u00c8\u00cb\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca"+
		"#\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc\u00cd\5\"\22\2\u00cd\u00d4\b\23\1"+
		"\2\u00ce\u00cf\7\24\2\2\u00cf\u00d0\5\"\22\2\u00d0\u00d1\b\23\1\2\u00d1"+
		"\u00d3\3\2\2\2\u00d2\u00ce\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2"+
		"\2\2\u00d4\u00d5\3\2\2\2\u00d5%\3\2\2\2\u00d6\u00d4\3\2\2\2\17\63\65@"+
		"^t\u0087\u008d\u009e\u00a8\u00b3\u00be\u00c9\u00d4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}