// Generated from QL.g4 by ANTLR 4.4
package grammer;
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
		T__23=1, T__22=2, T__21=3, T__20=4, T__19=5, T__18=6, T__17=7, T__16=8, 
		T__15=9, T__14=10, T__13=11, T__12=12, T__11=13, T__10=14, T__9=15, T__8=16, 
		T__7=17, T__6=18, T__5=19, T__4=20, T__3=21, T__2=22, T__1=23, T__0=24, 
		STRINGLITERAL=25, BOOLEANLITERAL=26, INT=27, IDENTIFIER=28, LOWERCASE=29, 
		UPPERCASE=30, DIGIT=31, WS=32, ESC=33, COMMENT=34;
	public static final String[] tokenNames = {
		"<INVALID>", "'/'", "'boolean'", "'integer'", "'!='", "':'", "'||'", "'>='", 
		"'{'", "'&&'", "'=='", "'<'", "'}'", "'>'", "'if'", "'<='", "'!'", "'string'", 
		"'else'", "'('", "')'", "'*'", "'+'", "'form'", "'-'", "STRINGLITERAL", 
		"BOOLEANLITERAL", "INT", "IDENTIFIER", "LOWERCASE", "UPPERCASE", "DIGIT", 
		"WS", "ESC", "COMMENT"
	};
	public static final int
		RULE_questionnaire = 0, RULE_form = 1, RULE_question = 2, RULE_expression = 3, 
		RULE_type = 4;
	public static final String[] ruleNames = {
		"questionnaire", "form", "question", "expression", "type"
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
	public static class QuestionnaireContext extends ParserRuleContext {
		public FormContext form() {
			return getRuleContext(FormContext.class,0);
		}
		public QuestionnaireContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionnaire; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitQuestionnaire(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionnaireContext questionnaire() throws RecognitionException {
		QuestionnaireContext _localctx = new QuestionnaireContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_questionnaire);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10); form();
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
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public TerminalNode IDENTIFIER() { return getToken(QLParser.IDENTIFIER, 0); }
		public FormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitForm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormContext form() throws RecognitionException {
		FormContext _localctx = new FormContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_form);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12); match(T__1);
			setState(13); match(IDENTIFIER);
			setState(14); match(T__16);
			setState(16); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(15); question();
				}
				}
				setState(18); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__10 || _la==IDENTIFIER );
			setState(20); match(T__12);
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
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
	 
		public QuestionContext() { }
		public void copyFrom(QuestionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CalculatedQuestionContext extends QuestionContext {
		public TerminalNode STRINGLITERAL() { return getToken(QLParser.STRINGLITERAL, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(QLParser.IDENTIFIER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CalculatedQuestionContext(QuestionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitCalculatedQuestion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NormalQuestionContext extends QuestionContext {
		public TerminalNode STRINGLITERAL() { return getToken(QLParser.STRINGLITERAL, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(QLParser.IDENTIFIER, 0); }
		public NormalQuestionContext(QuestionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitNormalQuestion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfQuestionContext extends QuestionContext {
		public QuestionContext then;
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IfQuestionContext(QuestionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIfQuestion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfElseQuestionContext extends QuestionContext {
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public IfElseQuestionContext(QuestionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIfElseQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_question);
		int _la;
		try {
			setState(59);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new NormalQuestionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(22); match(IDENTIFIER);
				setState(23); match(T__19);
				setState(24); match(STRINGLITERAL);
				setState(25); type();
				}
				break;
			case 2:
				_localctx = new CalculatedQuestionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(26); match(IDENTIFIER);
				setState(27); match(T__19);
				setState(28); match(STRINGLITERAL);
				setState(29); type();
				setState(30); match(T__5);
				setState(31); expression(0);
				setState(32); match(T__4);
				}
				break;
			case 3:
				_localctx = new IfQuestionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(34); match(T__10);
				setState(35); match(T__5);
				setState(36); expression(0);
				setState(37); match(T__4);
				setState(38); match(T__16);
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__10 || _la==IDENTIFIER) {
					{
					{
					setState(39); ((IfQuestionContext)_localctx).then = question();
					}
					}
					setState(44);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(45); match(T__12);
				}
				break;
			case 4:
				_localctx = new IfElseQuestionContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(47); match(T__10);
				setState(57);
				_la = _input.LA(1);
				if (_la==T__6) {
					{
					setState(48); match(T__6);
					setState(49); match(T__16);
					setState(53);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__10 || _la==IDENTIFIER) {
						{
						{
						setState(50); question();
						}
						}
						setState(55);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(56); match(T__12);
					}
				}

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

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PARContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PARContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitPAR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndExprContext extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public AndExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentifierContext extends ExpressionContext {
		public TerminalNode IDENTIFIER() { return getToken(QLParser.IDENTIFIER, 0); }
		public IdentifierContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TimeDivExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TimeDivExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitTimeDivExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UnaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddSubExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public AddSubExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitAddSubExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EQUALExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public EQUALExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitEQUALExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerliteralContext extends ExpressionContext {
		public TerminalNode INT() { return getToken(QLParser.INT, 0); }
		public IntegerliteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIntegerliteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ORExprContext extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ORExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitORExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class COMPExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public COMPExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitCOMPExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanliteralContext extends ExpressionContext {
		public TerminalNode BOOLEANLITERAL() { return getToken(QLParser.BOOLEANLITERAL, 0); }
		public BooleanliteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBooleanliteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringliteralContext extends ExpressionContext {
		public TerminalNode STRINGLITERAL() { return getToken(QLParser.STRINGLITERAL, 0); }
		public StringliteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitStringliteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			switch (_input.LA(1)) {
			case T__8:
			case T__2:
			case T__0:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(62);
				((UnaryExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__2) | (1L << T__0))) != 0)) ) {
					((UnaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(63); expression(12);
				}
				break;
			case T__5:
				{
				_localctx = new PARContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				{
				setState(64); match(T__5);
				setState(65); expression(0);
				setState(66); match(T__4);
				}
				}
				break;
			case BOOLEANLITERAL:
				{
				_localctx = new BooleanliteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(68); match(BOOLEANLITERAL);
				}
				break;
			case STRINGLITERAL:
				{
				_localctx = new StringliteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(69); match(STRINGLITERAL);
				}
				break;
			case INT:
				{
				_localctx = new IntegerliteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(70); match(INT);
				}
				break;
			case IDENTIFIER:
				{
				_localctx = new IdentifierContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(71); match(IDENTIFIER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(94);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(92);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new TimeDivExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(74);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(75);
						((TimeDivExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__23 || _la==T__3) ) {
							((TimeDivExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(76); expression(12);
						}
						break;
					case 2:
						{
						_localctx = new AddSubExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(77);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(78);
						((AddSubExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__2 || _la==T__0) ) {
							((AddSubExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(79); expression(11);
						}
						break;
					case 3:
						{
						_localctx = new COMPExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(80);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(81);
						((COMPExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__13) | (1L << T__11) | (1L << T__9))) != 0)) ) {
							((COMPExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(82); expression(10);
						}
						break;
					case 4:
						{
						_localctx = new EQUALExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(83);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(84);
						((EQUALExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__20 || _la==T__14) ) {
							((EQUALExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(85); expression(9);
						}
						break;
					case 5:
						{
						_localctx = new AndExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(86);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(87); match(T__15);
						setState(88); expression(8);
						}
						break;
					case 6:
						{
						_localctx = new ORExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(89);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(90); match(T__18);
						setState(91); expression(7);
						}
						break;
					}
					} 
				}
				setState(96);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StringQL_typeContext extends TypeContext {
		public StringQL_typeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitStringQL_type(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanQL_typeContext extends TypeContext {
		public BooleanQL_typeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBooleanQL_type(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerQL_typeContext extends TypeContext {
		public IntegerQL_typeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIntegerQL_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type);
		try {
			setState(100);
			switch (_input.LA(1)) {
			case T__22:
				_localctx = new BooleanQL_typeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(97); match(T__22);
				}
				break;
			case T__7:
				_localctx = new StringQL_typeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(98); match(T__7);
				}
				break;
			case T__21:
				_localctx = new IntegerQL_typeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(99); match(T__21);
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
		case 3: return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 11);
		case 1: return precpred(_ctx, 10);
		case 2: return precpred(_ctx, 9);
		case 3: return precpred(_ctx, 8);
		case 4: return precpred(_ctx, 7);
		case 5: return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3$i\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\3\3\3\3\3\3\3\6\3\23\n\3\r\3\16\3\24"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\7\4+\n\4\f\4\16\4.\13\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\66\n\4"+
		"\f\4\16\49\13\4\3\4\5\4<\n\4\5\4>\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\5\5K\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\7\5_\n\5\f\5\16\5b\13\5\3\6\3\6\3\6\5\6g\n\6"+
		"\3\6\2\3\b\7\2\4\6\b\n\2\7\5\2\22\22\30\30\32\32\4\2\3\3\27\27\4\2\30"+
		"\30\32\32\6\2\t\t\r\r\17\17\21\21\4\2\6\6\f\fw\2\f\3\2\2\2\4\16\3\2\2"+
		"\2\6=\3\2\2\2\bJ\3\2\2\2\nf\3\2\2\2\f\r\5\4\3\2\r\3\3\2\2\2\16\17\7\31"+
		"\2\2\17\20\7\36\2\2\20\22\7\n\2\2\21\23\5\6\4\2\22\21\3\2\2\2\23\24\3"+
		"\2\2\2\24\22\3\2\2\2\24\25\3\2\2\2\25\26\3\2\2\2\26\27\7\16\2\2\27\5\3"+
		"\2\2\2\30\31\7\36\2\2\31\32\7\7\2\2\32\33\7\33\2\2\33>\5\n\6\2\34\35\7"+
		"\36\2\2\35\36\7\7\2\2\36\37\7\33\2\2\37 \5\n\6\2 !\7\25\2\2!\"\5\b\5\2"+
		"\"#\7\26\2\2#>\3\2\2\2$%\7\20\2\2%&\7\25\2\2&\'\5\b\5\2\'(\7\26\2\2(,"+
		"\7\n\2\2)+\5\6\4\2*)\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-/\3\2\2\2."+
		",\3\2\2\2/\60\7\16\2\2\60>\3\2\2\2\61;\7\20\2\2\62\63\7\24\2\2\63\67\7"+
		"\n\2\2\64\66\5\6\4\2\65\64\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2"+
		"\28:\3\2\2\29\67\3\2\2\2:<\7\16\2\2;\62\3\2\2\2;<\3\2\2\2<>\3\2\2\2=\30"+
		"\3\2\2\2=\34\3\2\2\2=$\3\2\2\2=\61\3\2\2\2>\7\3\2\2\2?@\b\5\1\2@A\t\2"+
		"\2\2AK\5\b\5\16BC\7\25\2\2CD\5\b\5\2DE\7\26\2\2EK\3\2\2\2FK\7\34\2\2G"+
		"K\7\33\2\2HK\7\35\2\2IK\7\36\2\2J?\3\2\2\2JB\3\2\2\2JF\3\2\2\2JG\3\2\2"+
		"\2JH\3\2\2\2JI\3\2\2\2K`\3\2\2\2LM\f\r\2\2MN\t\3\2\2N_\5\b\5\16OP\f\f"+
		"\2\2PQ\t\4\2\2Q_\5\b\5\rRS\f\13\2\2ST\t\5\2\2T_\5\b\5\fUV\f\n\2\2VW\t"+
		"\6\2\2W_\5\b\5\13XY\f\t\2\2YZ\7\13\2\2Z_\5\b\5\n[\\\f\b\2\2\\]\7\b\2\2"+
		"]_\5\b\5\t^L\3\2\2\2^O\3\2\2\2^R\3\2\2\2^U\3\2\2\2^X\3\2\2\2^[\3\2\2\2"+
		"_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2a\t\3\2\2\2b`\3\2\2\2cg\7\4\2\2dg\7\23\2"+
		"\2eg\7\5\2\2fc\3\2\2\2fd\3\2\2\2fe\3\2\2\2g\13\3\2\2\2\13\24,\67;=J^`"+
		"f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}