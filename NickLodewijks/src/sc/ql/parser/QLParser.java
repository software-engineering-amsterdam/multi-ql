// Generated from QL.g4 by ANTLR 4.5.3
package sc.ql.parser;

import java.util.Map;
import java.util.HashMap;
import sc.ql.ast.*;
import static sc.ql.ast.Expression.*;
import static sc.ql.ast.Literal.*;
import static sc.ql.ast.Statement.*;
import static sc.ql.ast.ValueType.*;

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
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, WS=20, COMMENT=21, LINE_COMMENT=22, BOOLEAN=23, INTEGER=24, 
		STRING=25, BOOL=26, INT=27, STR=28, ID=29;
	public static final int
		RULE_form = 0, RULE_statement = 1, RULE_block = 2, RULE_ifStat = 3, RULE_question = 4, 
		RULE_variableType = 5, RULE_expr = 6, RULE_literal = 7;
	public static final String[] ruleNames = {
		"form", "statement", "block", "ifStat", "question", "variableType", "expr", 
		"literal"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'if'", "'('", "')'", "'+'", "'-'", "'<'", 
		"'<='", "'>'", "'>='", "'=='", "'!='", "'*'", "'/'", "'&&'", "'||'", "'!'", 
		null, null, null, "'bool'", "'int'", "'str'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, "WS", "COMMENT", "LINE_COMMENT", 
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


	    private <T extends ASTNode> T addSource(ParserRuleContext context, T node){
	        node.setSourceInfo(new SourceLocation(context));
	        return (T) node;
	    }
	    
	    private String unQuote(String text){
	        return text.substring(1, text.length()-1);
	    }

	public QLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FormContext extends ParserRuleContext {
		public Form result;
		public Token ID;
		public BlockContext block;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(QLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(QLParser.ID, i);
		}
		public FormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form; }
	}

	public final FormContext form() throws RecognitionException {
		FormContext _localctx = new FormContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_form);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(16);
				match(T__0);
				}
				}
				setState(19); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			setState(22); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(21);
				((FormContext)_localctx).ID = match(ID);
				}
				}
				setState(24); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(26);
			((FormContext)_localctx).block = block();
			 ((FormContext)_localctx).result =  addSource(_localctx, new Form((((FormContext)_localctx).ID!=null?((FormContext)_localctx).ID.getText():null), ((FormContext)_localctx).block.result)); 
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
		public IfStatContext ifStat;
		public QuestionContext question;
		public BlockContext block;
		public IfStatContext ifStat() {
			return getRuleContext(IfStatContext.class,0);
		}
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(38);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(29);
				((StatementContext)_localctx).ifStat = ifStat();
				 ((StatementContext)_localctx).result = ((StatementContext)_localctx).ifStat.result; 
				}
				break;
			case BOOLEAN:
			case INTEGER:
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(32);
				((StatementContext)_localctx).question = question();
				 ((StatementContext)_localctx).result = ((StatementContext)_localctx).question.result; 
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				setState(35);
				((StatementContext)_localctx).block = block();
				 ((StatementContext)_localctx).result = ((StatementContext)_localctx).block.result; 
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

	public static class BlockContext extends ParserRuleContext {
		public Block result;
		public List<Statement> statements =  new ArrayList<>();;
		public StatementContext statement;
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
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_block);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(41); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(40);
					match(T__1);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(43); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(48); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(45);
				((BlockContext)_localctx).statement = statement();
				 _localctx.statements.add(((BlockContext)_localctx).statement.result); 
				}
				}
				setState(50); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << BOOLEAN) | (1L << INTEGER) | (1L << STRING))) != 0) );
			setState(52);
			match(T__2);
			}
			_ctx.stop = _input.LT(-1);

			        ((BlockContext)_localctx).result =  addSource(_localctx, new Block(_localctx.statements));
			    
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

	public static class IfStatContext extends ParserRuleContext {
		public Statement result;
		public ExprContext expr;
		public BlockContext block;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IfStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStat; }
	}

	public final IfStatContext ifStat() throws RecognitionException {
		IfStatContext _localctx = new IfStatContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ifStat);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(55); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(54);
				match(T__3);
				}
				}
				setState(57); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 );
			setState(60); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(59);
					match(T__4);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(62); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(65); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(64);
				((IfStatContext)_localctx).expr = expr(0);
				}
				}
				setState(67); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__6) | (1L << T__7) | (1L << T__18) | (1L << BOOL) | (1L << INT) | (1L << STR) | (1L << ID))) != 0) );
			setState(70); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(69);
				match(T__5);
				}
				}
				setState(72); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__5 );
			setState(74);
			((IfStatContext)_localctx).block = block();
			 
			        ((IfStatContext)_localctx).result =  addSource(_localctx, new IfThen(((IfStatContext)_localctx).expr.result, ((IfStatContext)_localctx).block.result));
			    
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
		public VariableTypeContext variableType;
		public Token ID;
		public Token STR;
		public ExprContext expr;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<VariableTypeContext> variableType() {
			return getRuleContexts(VariableTypeContext.class);
		}
		public VariableTypeContext variableType(int i) {
			return getRuleContext(VariableTypeContext.class,i);
		}
		public List<TerminalNode> ID() { return getTokens(QLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(QLParser.ID, i);
		}
		public List<TerminalNode> STR() { return getTokens(QLParser.STR); }
		public TerminalNode STR(int i) {
			return getToken(QLParser.STR, i);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_question);
		int _la;
		try {
			int _alt;
			setState(108);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(78); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(77);
					((QuestionContext)_localctx).variableType = variableType();
					}
					}
					setState(80); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << INTEGER) | (1L << STRING))) != 0) );
				setState(83); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(82);
					((QuestionContext)_localctx).ID = match(ID);
					}
					}
					setState(85); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				setState(88); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(87);
						((QuestionContext)_localctx).STR = match(STR);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(90); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(92);
				((QuestionContext)_localctx).expr = expr(0);

				        ((QuestionContext)_localctx).result =  addSource(_localctx, new ComputedQuestion(((QuestionContext)_localctx).variableType.result, (((QuestionContext)_localctx).ID!=null?((QuestionContext)_localctx).ID.getText():null),  unQuote((((QuestionContext)_localctx).STR!=null?((QuestionContext)_localctx).STR.getText():null)), ((QuestionContext)_localctx).expr.result));
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(96); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(95);
					((QuestionContext)_localctx).variableType = variableType();
					}
					}
					setState(98); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << INTEGER) | (1L << STRING))) != 0) );
				setState(101); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(100);
					((QuestionContext)_localctx).ID = match(ID);
					}
					}
					setState(103); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				setState(105);
				((QuestionContext)_localctx).STR = match(STR);
				 
				        ((QuestionContext)_localctx).result =  addSource(_localctx, new NormalQuestion(((QuestionContext)_localctx).variableType.result, (((QuestionContext)_localctx).ID!=null?((QuestionContext)_localctx).ID.getText():null), unQuote((((QuestionContext)_localctx).STR!=null?((QuestionContext)_localctx).STR.getText():null))));
				    
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

	public static class VariableTypeContext extends ParserRuleContext {
		public ValueType result;
		public TerminalNode BOOLEAN() { return getToken(QLParser.BOOLEAN, 0); }
		public TerminalNode STRING() { return getToken(QLParser.STRING, 0); }
		public TerminalNode INTEGER() { return getToken(QLParser.INTEGER, 0); }
		public VariableTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableType; }
	}

	public final VariableTypeContext variableType() throws RecognitionException {
		VariableTypeContext _localctx = new VariableTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_variableType);
		try {
			setState(116);
			switch (_input.LA(1)) {
			case BOOLEAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				match(BOOLEAN);
				 ((VariableTypeContext)_localctx).result =  addSource(_localctx, new BooleanType()); 
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				match(STRING);
				 ((VariableTypeContext)_localctx).result =  addSource(_localctx, new StringType());  
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 3);
				{
				setState(114);
				match(INTEGER);
				 ((VariableTypeContext)_localctx).result =  addSource(_localctx, new IntegerType()); 
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

	public static class ExprContext extends ParserRuleContext {
		public Expression result;
		public ExprContext lhs;
		public Token op;
		public ExprContext exp;
		public LiteralContext literal;
		public Token ID;
		public ExprContext rhs;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			switch (_input.LA(1)) {
			case T__6:
			case T__7:
				{
				setState(119);
				((ExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__6 || _la==T__7) ) {
					((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(120);
				((ExprContext)_localctx).exp = expr(10);
				 
				      if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("+")) {
				        ((ExprContext)_localctx).result =  addSource(_localctx, new Positive(((ExprContext)_localctx).exp.result));
				      }
				      if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("-")) {
				        ((ExprContext)_localctx).result =  addSource(_localctx, new Negative(((ExprContext)_localctx).exp.result));
				      }
				    
				}
				break;
			case T__18:
				{
				setState(123);
				match(T__18);
				setState(124);
				((ExprContext)_localctx).exp = expr(4);
				 ((ExprContext)_localctx).result =  addSource(_localctx, new Not(((ExprContext)_localctx).exp.result)); 
				}
				break;
			case T__4:
				{
				setState(127);
				match(T__4);
				setState(128);
				((ExprContext)_localctx).lhs = expr(0);
				setState(129);
				match(T__5);
				 ((ExprContext)_localctx).result =  ((ExprContext)_localctx).lhs.result; 
				}
				break;
			case BOOL:
			case INT:
			case STR:
				{
				setState(132);
				((ExprContext)_localctx).literal = literal();
				 ((ExprContext)_localctx).result =  new LiteralExpr(((ExprContext)_localctx).literal.result); 
				}
				break;
			case ID:
				{
				setState(135);
				((ExprContext)_localctx).ID = match(ID);
				 ((ExprContext)_localctx).result =  addSource(_localctx, new VariableExpr((((ExprContext)_localctx).ID!=null?((ExprContext)_localctx).ID.getText():null))); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(166);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(164);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.lhs = _prevctx;
						_localctx.lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(139);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(140);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13))) != 0)) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(141);
						((ExprContext)_localctx).rhs = expr(10);
						 
						                if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("<")) {
						                  ((ExprContext)_localctx).result =  addSource(_localctx, new LessThan(((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result));
						                }
						                if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("<=")) {
						                  ((ExprContext)_localctx).result =  addSource(_localctx, new LessThanOrEqual(((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result));      
						                }
						                if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals(">")) {
						                  ((ExprContext)_localctx).result =  addSource(_localctx, new GreaterThan(((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result));
						                }
						                if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals(">=")) {
						                  ((ExprContext)_localctx).result =  addSource(_localctx, new GreaterThanOrEqual(((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result));      
						                }
						                if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("==")) {
						                  ((ExprContext)_localctx).result =  addSource(_localctx, new Equal(((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result));
						                }
						                if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("!=")) {
						                  ((ExprContext)_localctx).result =  addSource(_localctx, new NotEqual(((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result));
						                }
						              
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.lhs = _prevctx;
						_localctx.lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(144);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(145);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__14 || _la==T__15) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(146);
						((ExprContext)_localctx).rhs = expr(9);
						 
						                if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("*")) {
						                  ((ExprContext)_localctx).result =  addSource(_localctx, new Multiply(((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result));
						                }
						                if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("/")) {
						                  ((ExprContext)_localctx).result =  addSource(_localctx, new Divide(((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result));      
						                }
						              
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.lhs = _prevctx;
						_localctx.lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(149);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(150);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__6 || _la==T__7) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(151);
						((ExprContext)_localctx).rhs = expr(8);
						 
						                if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("+")) {
						                  ((ExprContext)_localctx).result =  addSource(_localctx, new Add(((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result));
						                }
						                if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("-")) {
						                  ((ExprContext)_localctx).result =  addSource(_localctx, new Subtract(((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result));      
						                }
						              
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.lhs = _prevctx;
						_localctx.lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(154);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(155);
						match(T__16);
						setState(156);
						((ExprContext)_localctx).rhs = expr(7);
						 
						                  ((ExprContext)_localctx).result =  addSource(_localctx, new And(((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result));
						              
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.lhs = _prevctx;
						_localctx.lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(159);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(160);
						match(T__17);
						setState(161);
						((ExprContext)_localctx).rhs = expr(6);
						 
						                  ((ExprContext)_localctx).result =  addSource(_localctx, new Or(((ExprContext)_localctx).lhs.result, ((ExprContext)_localctx).rhs.result));
						              
						}
						break;
					}
					} 
				}
				setState(168);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public Literal result;
		public Token INT;
		public Token STR;
		public Token BOOL;
		public TerminalNode INT() { return getToken(QLParser.INT, 0); }
		public TerminalNode STR() { return getToken(QLParser.STR, 0); }
		public TerminalNode BOOL() { return getToken(QLParser.BOOL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_literal);
		try {
			setState(175);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(169);
				((LiteralContext)_localctx).INT = match(INT);
				 ((LiteralContext)_localctx).result =  addSource(_localctx, new IntegerLiteral(Integer.valueOf((((LiteralContext)_localctx).INT!=null?((LiteralContext)_localctx).INT.getText():null)))); 
				}
				break;
			case STR:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				((LiteralContext)_localctx).STR = match(STR);
				 ((LiteralContext)_localctx).result =  addSource(_localctx, new StringLiteral(unQuote((((LiteralContext)_localctx).STR!=null?((LiteralContext)_localctx).STR.getText():null)))); 
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 3);
				{
				setState(173);
				((LiteralContext)_localctx).BOOL = match(BOOL);
				 ((LiteralContext)_localctx).result =  addSource(_localctx, new BooleanLiteral(Boolean.valueOf((((LiteralContext)_localctx).BOOL!=null?((LiteralContext)_localctx).BOOL.getText():null)))); 
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 6);
		case 4:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\37\u00b4\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\6\2\24\n"+
		"\2\r\2\16\2\25\3\2\6\2\31\n\2\r\2\16\2\32\3\2\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\5\3)\n\3\3\4\6\4,\n\4\r\4\16\4-\3\4\3\4\3\4\6\4\63"+
		"\n\4\r\4\16\4\64\3\4\3\4\3\5\6\5:\n\5\r\5\16\5;\3\5\6\5?\n\5\r\5\16\5"+
		"@\3\5\6\5D\n\5\r\5\16\5E\3\5\6\5I\n\5\r\5\16\5J\3\5\3\5\3\5\3\6\6\6Q\n"+
		"\6\r\6\16\6R\3\6\6\6V\n\6\r\6\16\6W\3\6\6\6[\n\6\r\6\16\6\\\3\6\3\6\3"+
		"\6\3\6\6\6c\n\6\r\6\16\6d\3\6\6\6h\n\6\r\6\16\6i\3\6\3\6\3\6\5\6o\n\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\5\7w\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u008c\n\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\7\b\u00a7\n\b\f\b\16\b\u00aa\13\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\5\t\u00b2\n\t\3\t\2\3\16\n\2\4\6\b\n\f\16\20\2\5\3\2\t\n\3\2\13\20"+
		"\3\2\21\22\u00c8\2\23\3\2\2\2\4(\3\2\2\2\6+\3\2\2\2\b9\3\2\2\2\nn\3\2"+
		"\2\2\fv\3\2\2\2\16\u008b\3\2\2\2\20\u00b1\3\2\2\2\22\24\7\3\2\2\23\22"+
		"\3\2\2\2\24\25\3\2\2\2\25\23\3\2\2\2\25\26\3\2\2\2\26\30\3\2\2\2\27\31"+
		"\7\37\2\2\30\27\3\2\2\2\31\32\3\2\2\2\32\30\3\2\2\2\32\33\3\2\2\2\33\34"+
		"\3\2\2\2\34\35\5\6\4\2\35\36\b\2\1\2\36\3\3\2\2\2\37 \5\b\5\2 !\b\3\1"+
		"\2!)\3\2\2\2\"#\5\n\6\2#$\b\3\1\2$)\3\2\2\2%&\5\6\4\2&\'\b\3\1\2\')\3"+
		"\2\2\2(\37\3\2\2\2(\"\3\2\2\2(%\3\2\2\2)\5\3\2\2\2*,\7\4\2\2+*\3\2\2\2"+
		",-\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\62\3\2\2\2/\60\5\4\3\2\60\61\b\4\1\2\61"+
		"\63\3\2\2\2\62/\3\2\2\2\63\64\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\66"+
		"\3\2\2\2\66\67\7\5\2\2\67\7\3\2\2\28:\7\6\2\298\3\2\2\2:;\3\2\2\2;9\3"+
		"\2\2\2;<\3\2\2\2<>\3\2\2\2=?\7\7\2\2>=\3\2\2\2?@\3\2\2\2@>\3\2\2\2@A\3"+
		"\2\2\2AC\3\2\2\2BD\5\16\b\2CB\3\2\2\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2FH"+
		"\3\2\2\2GI\7\b\2\2HG\3\2\2\2IJ\3\2\2\2JH\3\2\2\2JK\3\2\2\2KL\3\2\2\2L"+
		"M\5\6\4\2MN\b\5\1\2N\t\3\2\2\2OQ\5\f\7\2PO\3\2\2\2QR\3\2\2\2RP\3\2\2\2"+
		"RS\3\2\2\2SU\3\2\2\2TV\7\37\2\2UT\3\2\2\2VW\3\2\2\2WU\3\2\2\2WX\3\2\2"+
		"\2XZ\3\2\2\2Y[\7\36\2\2ZY\3\2\2\2[\\\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]^\3"+
		"\2\2\2^_\5\16\b\2_`\b\6\1\2`o\3\2\2\2ac\5\f\7\2ba\3\2\2\2cd\3\2\2\2db"+
		"\3\2\2\2de\3\2\2\2eg\3\2\2\2fh\7\37\2\2gf\3\2\2\2hi\3\2\2\2ig\3\2\2\2"+
		"ij\3\2\2\2jk\3\2\2\2kl\7\36\2\2lm\b\6\1\2mo\3\2\2\2nP\3\2\2\2nb\3\2\2"+
		"\2o\13\3\2\2\2pq\7\31\2\2qw\b\7\1\2rs\7\33\2\2sw\b\7\1\2tu\7\32\2\2uw"+
		"\b\7\1\2vp\3\2\2\2vr\3\2\2\2vt\3\2\2\2w\r\3\2\2\2xy\b\b\1\2yz\t\2\2\2"+
		"z{\5\16\b\f{|\b\b\1\2|\u008c\3\2\2\2}~\7\25\2\2~\177\5\16\b\6\177\u0080"+
		"\b\b\1\2\u0080\u008c\3\2\2\2\u0081\u0082\7\7\2\2\u0082\u0083\5\16\b\2"+
		"\u0083\u0084\7\b\2\2\u0084\u0085\b\b\1\2\u0085\u008c\3\2\2\2\u0086\u0087"+
		"\5\20\t\2\u0087\u0088\b\b\1\2\u0088\u008c\3\2\2\2\u0089\u008a\7\37\2\2"+
		"\u008a\u008c\b\b\1\2\u008bx\3\2\2\2\u008b}\3\2\2\2\u008b\u0081\3\2\2\2"+
		"\u008b\u0086\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u00a8\3\2\2\2\u008d\u008e"+
		"\f\13\2\2\u008e\u008f\t\3\2\2\u008f\u0090\5\16\b\f\u0090\u0091\b\b\1\2"+
		"\u0091\u00a7\3\2\2\2\u0092\u0093\f\n\2\2\u0093\u0094\t\4\2\2\u0094\u0095"+
		"\5\16\b\13\u0095\u0096\b\b\1\2\u0096\u00a7\3\2\2\2\u0097\u0098\f\t\2\2"+
		"\u0098\u0099\t\2\2\2\u0099\u009a\5\16\b\n\u009a\u009b\b\b\1\2\u009b\u00a7"+
		"\3\2\2\2\u009c\u009d\f\b\2\2\u009d\u009e\7\23\2\2\u009e\u009f\5\16\b\t"+
		"\u009f\u00a0\b\b\1\2\u00a0\u00a7\3\2\2\2\u00a1\u00a2\f\7\2\2\u00a2\u00a3"+
		"\7\24\2\2\u00a3\u00a4\5\16\b\b\u00a4\u00a5\b\b\1\2\u00a5\u00a7\3\2\2\2"+
		"\u00a6\u008d\3\2\2\2\u00a6\u0092\3\2\2\2\u00a6\u0097\3\2\2\2\u00a6\u009c"+
		"\3\2\2\2\u00a6\u00a1\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8"+
		"\u00a9\3\2\2\2\u00a9\17\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ac\7\35\2"+
		"\2\u00ac\u00b2\b\t\1\2\u00ad\u00ae\7\36\2\2\u00ae\u00b2\b\t\1\2\u00af"+
		"\u00b0\7\34\2\2\u00b0\u00b2\b\t\1\2\u00b1\u00ab\3\2\2\2\u00b1\u00ad\3"+
		"\2\2\2\u00b1\u00af\3\2\2\2\u00b2\21\3\2\2\2\26\25\32(-\64;@EJRW\\dinv"+
		"\u008b\u00a6\u00a8\u00b1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}