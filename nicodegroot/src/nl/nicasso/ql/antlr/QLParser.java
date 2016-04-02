// Generated from QL.g4 by ANTLR 4.5

package nl.nicasso.ql.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast" })
public class QLParser extends Parser {
	static {
		RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
	public static final int T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
			T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, T__15 = 16, T__16 = 17, T__17 = 18,
			T__18 = 19, T__19 = 20, T__20 = 21, T__21 = 22, T__22 = 23, T__23 = 24, T__24 = 25, T__25 = 26,
			WHITESPACE = 27, COMMENT = 28, LINE_COMMENT = 29, BOOLEAN = 30, IDENTIFIER = 31, INTEGER = 32, MONEY = 33,
			STRING = 34;
	public static final int RULE_form = 0, RULE_block = 1, RULE_statement = 2, RULE_expression = 3, RULE_literal = 4,
			RULE_questionType = 5;
	public static final String[] ruleNames = { "form", "block", "statement", "expression", "literal", "questionType" };

	private static final String[] _LITERAL_NAMES = { null, "'form'", "'{'", "'}'", "':'", "'='", "'('", "')'", "'if'",
			"'else'", "'!'", "'*'", "'/'", "'+'", "'-'", "'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'&&'", "'||'",
			"'integer'", "'string'", "'boolean'", "'money'" };
	private static final String[] _SYMBOLIC_NAMES = { null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			"WHITESPACE", "COMMENT", "LINE_COMMENT", "BOOLEAN", "IDENTIFIER", "INTEGER", "MONEY", "STRING" };
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
	public String getGrammarFileName() {
		return "QL.g4";
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	@Override
	public String getSerializedATN() {
		return _serializedATN;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	public QLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	public static class FormContext extends ParserRuleContext {
		public Token identifier;

		public BlockContext block() {
			return getRuleContext(BlockContext.class, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(QLParser.IDENTIFIER, 0);
		}

		public FormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_form;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterForm(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitForm(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitForm(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final FormContext form() throws RecognitionException {
		FormContext _localctx = new FormContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_form);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(12);
				match(T__0);
				setState(13);
				((FormContext) _localctx).identifier = match(IDENTIFIER);
				setState(14);
				block();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}

		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class, i);
		}

		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_block;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterBlock(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitBlock(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitBlock(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(16);
				match(T__1);
				setState(20);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__7 || _la == STRING) {
					{
						{
							setState(17);
							statement();
						}
					}
					setState(22);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(23);
				match(T__2);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_statement;
		}

		public StatementContext() {
		}

		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class IfElseStatementContext extends StatementContext {
		public ExpressionContext expr;
		public BlockContext ifBody;
		public BlockContext elseBody;

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}

		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class, i);
		}

		public IfElseStatementContext(StatementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterIfElseStatement(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitIfElseStatement(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitIfElseStatement(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ComputedQuestionStatementContext extends StatementContext {
		public Token label;
		public Token identifier;
		public QuestionTypeContext type;
		public ExpressionContext expr;

		public TerminalNode STRING() {
			return getToken(QLParser.STRING, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(QLParser.IDENTIFIER, 0);
		}

		public QuestionTypeContext questionType() {
			return getRuleContext(QuestionTypeContext.class, 0);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public ComputedQuestionStatementContext(StatementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterComputedQuestionStatement(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitComputedQuestionStatement(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitComputedQuestionStatement(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class IfStatementContext extends StatementContext {
		public ExpressionContext expr;
		public BlockContext ifBody;

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public BlockContext block() {
			return getRuleContext(BlockContext.class, 0);
		}

		public IfStatementContext(StatementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterIfStatement(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitIfStatement(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitIfStatement(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class QuestionStatementContext extends StatementContext {
		public Token label;
		public Token identifier;
		public QuestionTypeContext type;

		public TerminalNode STRING() {
			return getToken(QLParser.STRING, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(QLParser.IDENTIFIER, 0);
		}

		public QuestionTypeContext questionType() {
			return getRuleContext(QuestionTypeContext.class, 0);
		}

		public QuestionStatementContext(StatementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterQuestionStatement(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitQuestionStatement(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitQuestionStatement(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(52);
			switch (getInterpreter().adaptivePredict(_input, 1, _ctx)) {
			case 1:
				_localctx = new QuestionStatementContext(_localctx);
				enterOuterAlt(_localctx, 1); {
				setState(25);
				((QuestionStatementContext) _localctx).label = match(STRING);
				setState(26);
				((QuestionStatementContext) _localctx).identifier = match(IDENTIFIER);
				setState(27);
				match(T__3);
				setState(28);
				((QuestionStatementContext) _localctx).type = questionType();
			}
				break;
			case 2:
				_localctx = new ComputedQuestionStatementContext(_localctx);
				enterOuterAlt(_localctx, 2); {
				setState(29);
				((ComputedQuestionStatementContext) _localctx).label = match(STRING);
				setState(30);
				((ComputedQuestionStatementContext) _localctx).identifier = match(IDENTIFIER);
				setState(31);
				match(T__3);
				setState(32);
				((ComputedQuestionStatementContext) _localctx).type = questionType();
				setState(33);
				match(T__4);
				setState(34);
				match(T__5);
				setState(35);
				((ComputedQuestionStatementContext) _localctx).expr = expression(0);
				setState(36);
				match(T__6);
			}
				break;
			case 3:
				_localctx = new IfStatementContext(_localctx);
				enterOuterAlt(_localctx, 3); {
				setState(38);
				match(T__7);
				setState(39);
				match(T__5);
				setState(40);
				((IfStatementContext) _localctx).expr = expression(0);
				setState(41);
				match(T__6);
				setState(42);
				((IfStatementContext) _localctx).ifBody = block();
			}
				break;
			case 4:
				_localctx = new IfElseStatementContext(_localctx);
				enterOuterAlt(_localctx, 4); {
				setState(44);
				match(T__7);
				setState(45);
				match(T__5);
				setState(46);
				((IfElseStatementContext) _localctx).expr = expression(0);
				setState(47);
				match(T__6);
				setState(48);
				((IfElseStatementContext) _localctx).ifBody = block();
				setState(49);
				match(T__8);
				setState(50);
				((IfElseStatementContext) _localctx).elseBody = block();
			}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_expression;
		}

		public ExpressionContext() {
		}

		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class EqualityExpressionsContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;

		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public EqualityExpressionsContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterEqualityExpressions(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitEqualityExpressions(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitEqualityExpressions(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class MultiplicativeExpressionsContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;

		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public MultiplicativeExpressionsContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterMultiplicativeExpressions(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitMultiplicativeExpressions(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitMultiplicativeExpressions(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class AdditiveExpressionsContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;

		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public AdditiveExpressionsContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterAdditiveExpressions(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitAdditiveExpressions(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitAdditiveExpressions(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class RelationalExpressionsContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;

		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public RelationalExpressionsContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterRelationalExpressions(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitRelationalExpressions(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitRelationalExpressions(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class IdentifierExpressionContext extends ExpressionContext {
		public Token identifier;

		public TerminalNode IDENTIFIER() {
			return getToken(QLParser.IDENTIFIER, 0);
		}

		public IdentifierExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterIdentifierExpression(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitIdentifierExpression(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitIdentifierExpression(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ParenthesisExpressionContext extends ExpressionContext {
		public ExpressionContext expr;

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public ParenthesisExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterParenthesisExpression(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitParenthesisExpression(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitParenthesisExpression(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class NotExpressionContext extends ExpressionContext {
		public ExpressionContext expr;

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public NotExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterNotExpression(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitNotExpression(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitNotExpression(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class LiteralExpressionContext extends ExpressionContext {
		public LiteralContext literalValue;

		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class, 0);
		}

		public LiteralExpressionContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterLiteralExpression(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitLiteralExpression(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitLiteralExpression(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class ConditionalExpressionsContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;

		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public ConditionalExpressionsContext(ExpressionContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterConditionalExpressions(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitConditionalExpressions(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitConditionalExpressions(this);
			else
				return visitor.visitChildren(this);
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
				setState(63);
				switch (_input.LA(1)) {
				case T__9: {
					_localctx = new NotExpressionContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;

					setState(55);
					match(T__9);
					setState(56);
					((NotExpressionContext) _localctx).expr = expression(9);
				}
					break;
				case T__5: {
					_localctx = new ParenthesisExpressionContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(57);
					match(T__5);
					setState(58);
					((ParenthesisExpressionContext) _localctx).expr = expression(0);
					setState(59);
					match(T__6);
				}
					break;
				case BOOLEAN:
				case INTEGER:
				case MONEY:
				case STRING: {
					_localctx = new LiteralExpressionContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(61);
					((LiteralExpressionContext) _localctx).literalValue = literal();
				}
					break;
				case IDENTIFIER: {
					_localctx = new IdentifierExpressionContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(62);
					((IdentifierExpressionContext) _localctx).identifier = match(IDENTIFIER);
				}
					break;
				default:
					throw new NoViableAltException(this);
				}
				_ctx.stop = _input.LT(-1);
				setState(82);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 4, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null)
							triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(80);
							switch (getInterpreter().adaptivePredict(_input, 3, _ctx)) {
							case 1: {
								_localctx = new MultiplicativeExpressionsContext(
										new ExpressionContext(_parentctx, _parentState));
								((MultiplicativeExpressionsContext) _localctx).left = _prevctx;
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(65);
								if (!(precpred(_ctx, 7)))
									throw new FailedPredicateException(this, "precpred(_ctx, 7)");
								setState(66);
								((MultiplicativeExpressionsContext) _localctx).op = _input.LT(1);
								_la = _input.LA(1);
								if (!(_la == T__10 || _la == T__11)) {
									((MultiplicativeExpressionsContext) _localctx).op = (Token) _errHandler
											.recoverInline(this);
								} else {
									consume();
								}
								setState(67);
								((MultiplicativeExpressionsContext) _localctx).right = expression(8);
							}
								break;
							case 2: {
								_localctx = new AdditiveExpressionsContext(
										new ExpressionContext(_parentctx, _parentState));
								((AdditiveExpressionsContext) _localctx).left = _prevctx;
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(68);
								if (!(precpred(_ctx, 6)))
									throw new FailedPredicateException(this, "precpred(_ctx, 6)");
								setState(69);
								((AdditiveExpressionsContext) _localctx).op = _input.LT(1);
								_la = _input.LA(1);
								if (!(_la == T__12 || _la == T__13)) {
									((AdditiveExpressionsContext) _localctx).op = (Token) _errHandler
											.recoverInline(this);
								} else {
									consume();
								}
								setState(70);
								((AdditiveExpressionsContext) _localctx).right = expression(7);
							}
								break;
							case 3: {
								_localctx = new RelationalExpressionsContext(
										new ExpressionContext(_parentctx, _parentState));
								((RelationalExpressionsContext) _localctx).left = _prevctx;
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(71);
								if (!(precpred(_ctx, 5)))
									throw new FailedPredicateException(this, "precpred(_ctx, 5)");
								setState(72);
								((RelationalExpressionsContext) _localctx).op = _input.LT(1);
								_la = _input.LA(1);
								if (!((((_la) & ~0x3f) == 0 && ((1L << _la)
										& ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17))) != 0))) {
									((RelationalExpressionsContext) _localctx).op = (Token) _errHandler
											.recoverInline(this);
								} else {
									consume();
								}
								setState(73);
								((RelationalExpressionsContext) _localctx).right = expression(6);
							}
								break;
							case 4: {
								_localctx = new EqualityExpressionsContext(
										new ExpressionContext(_parentctx, _parentState));
								((EqualityExpressionsContext) _localctx).left = _prevctx;
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(74);
								if (!(precpred(_ctx, 4)))
									throw new FailedPredicateException(this, "precpred(_ctx, 4)");
								setState(75);
								((EqualityExpressionsContext) _localctx).op = _input.LT(1);
								_la = _input.LA(1);
								if (!(_la == T__18 || _la == T__19)) {
									((EqualityExpressionsContext) _localctx).op = (Token) _errHandler
											.recoverInline(this);
								} else {
									consume();
								}
								setState(76);
								((EqualityExpressionsContext) _localctx).right = expression(5);
							}
								break;
							case 5: {
								_localctx = new ConditionalExpressionsContext(
										new ExpressionContext(_parentctx, _parentState));
								((ConditionalExpressionsContext) _localctx).left = _prevctx;
								pushNewRecursionContext(_localctx, _startState, RULE_expression);
								setState(77);
								if (!(precpred(_ctx, 3)))
									throw new FailedPredicateException(this, "precpred(_ctx, 3)");
								setState(78);
								((ConditionalExpressionsContext) _localctx).op = _input.LT(1);
								_la = _input.LA(1);
								if (!(_la == T__20 || _la == T__21)) {
									((ConditionalExpressionsContext) _localctx).op = (Token) _errHandler
											.recoverInline(this);
								} else {
									consume();
								}
								setState(79);
								((ConditionalExpressionsContext) _localctx).right = expression(4);
							}
								break;
							}
						}
					}
					setState(84);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 4, _ctx);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_literal;
		}

		public LiteralContext() {
		}

		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class BooleanliteralContext extends LiteralContext {
		public TerminalNode BOOLEAN() {
			return getToken(QLParser.BOOLEAN, 0);
		}

		public BooleanliteralContext(LiteralContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterBooleanliteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitBooleanliteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitBooleanliteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class StringLiteralContext extends LiteralContext {
		public TerminalNode STRING() {
			return getToken(QLParser.STRING, 0);
		}

		public StringLiteralContext(LiteralContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterStringLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitStringLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitStringLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class IntegerLiteralContext extends LiteralContext {
		public TerminalNode INTEGER() {
			return getToken(QLParser.INTEGER, 0);
		}

		public IntegerLiteralContext(LiteralContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterIntegerLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitIntegerLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitIntegerLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class MoneyLiteralContext extends LiteralContext {
		public TerminalNode MONEY() {
			return getToken(QLParser.MONEY, 0);
		}

		public MoneyLiteralContext(LiteralContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterMoneyLiteral(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitMoneyLiteral(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitMoneyLiteral(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_literal);
		try {
			setState(89);
			switch (_input.LA(1)) {
			case MONEY:
				_localctx = new MoneyLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1); {
				setState(85);
				match(MONEY);
			}
				break;
			case INTEGER:
				_localctx = new IntegerLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2); {
				setState(86);
				match(INTEGER);
			}
				break;
			case BOOLEAN:
				_localctx = new BooleanliteralContext(_localctx);
				enterOuterAlt(_localctx, 3); {
				setState(87);
				match(BOOLEAN);
			}
				break;
			case STRING:
				_localctx = new StringLiteralContext(_localctx);
				enterOuterAlt(_localctx, 4); {
				setState(88);
				match(STRING);
			}
				break;
			default:
				throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuestionTypeContext extends ParserRuleContext {
		public QuestionTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_questionType;
		}

		public QuestionTypeContext() {
		}

		public void copyFrom(QuestionTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class BooleanTypeContext extends QuestionTypeContext {
		public BooleanTypeContext(QuestionTypeContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterBooleanType(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitBooleanType(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitBooleanType(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class IntegerTypeContext extends QuestionTypeContext {
		public IntegerTypeContext(QuestionTypeContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterIntegerType(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitIntegerType(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitIntegerType(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class StringTypeContext extends QuestionTypeContext {
		public StringTypeContext(QuestionTypeContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterStringType(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitStringType(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitStringType(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public static class MoneyTypeContext extends QuestionTypeContext {
		public MoneyTypeContext(QuestionTypeContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).enterMoneyType(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof QLListener)
				((QLListener) listener).exitMoneyType(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof QLVisitor)
				return ((QLVisitor<? extends T>) visitor).visitMoneyType(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final QuestionTypeContext questionType() throws RecognitionException {
		QuestionTypeContext _localctx = new QuestionTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_questionType);
		try {
			setState(95);
			switch (_input.LA(1)) {
			case T__22:
				_localctx = new IntegerTypeContext(_localctx);
				enterOuterAlt(_localctx, 1); {
				setState(91);
				match(T__22);
			}
				break;
			case T__23:
				_localctx = new StringTypeContext(_localctx);
				enterOuterAlt(_localctx, 2); {
				setState(92);
				match(T__23);
			}
				break;
			case T__24:
				_localctx = new BooleanTypeContext(_localctx);
				enterOuterAlt(_localctx, 3); {
				setState(93);
				match(T__24);
			}
				break;
			case T__25:
				_localctx = new MoneyTypeContext(_localctx);
				enterOuterAlt(_localctx, 4); {
				setState(94);
				match(T__25);
			}
				break;
			default:
				throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return expression_sempred((ExpressionContext) _localctx, predIndex);
		}
		return true;
	}

	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN = "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3$d\4\2\t\2\4\3\t\3"
			+ "\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\2\3\2\3\3\3\3\7\3\25\n\3\f"
			+ "\3\16\3\30\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"
			+ "\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\67"
			+ "\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5B\n\5\3\5\3\5\3\5\3\5\3\5"
			+ "\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5S\n\5\f\5\16\5V\13\5\3\6\3"
			+ "\6\3\6\3\6\5\6\\\n\6\3\7\3\7\3\7\3\7\5\7b\n\7\3\7\2\3\b\b\2\4\6\b\n\f"
			+ "\2\7\3\2\r\16\3\2\17\20\3\2\21\24\3\2\25\26\3\2\27\30o\2\16\3\2\2\2\4"
			+ "\22\3\2\2\2\6\66\3\2\2\2\bA\3\2\2\2\n[\3\2\2\2\fa\3\2\2\2\16\17\7\3\2"
			+ "\2\17\20\7!\2\2\20\21\5\4\3\2\21\3\3\2\2\2\22\26\7\4\2\2\23\25\5\6\4\2"
			+ "\24\23\3\2\2\2\25\30\3\2\2\2\26\24\3\2\2\2\26\27\3\2\2\2\27\31\3\2\2\2"
			+ "\30\26\3\2\2\2\31\32\7\5\2\2\32\5\3\2\2\2\33\34\7$\2\2\34\35\7!\2\2\35"
			+ "\36\7\6\2\2\36\67\5\f\7\2\37 \7$\2\2 !\7!\2\2!\"\7\6\2\2\"#\5\f\7\2#$"
			+ "\7\7\2\2$%\7\b\2\2%&\5\b\5\2&\'\7\t\2\2\'\67\3\2\2\2()\7\n\2\2)*\7\b\2"
			+ "\2*+\5\b\5\2+,\7\t\2\2,-\5\4\3\2-\67\3\2\2\2./\7\n\2\2/\60\7\b\2\2\60"
			+ "\61\5\b\5\2\61\62\7\t\2\2\62\63\5\4\3\2\63\64\7\13\2\2\64\65\5\4\3\2\65"
			+ "\67\3\2\2\2\66\33\3\2\2\2\66\37\3\2\2\2\66(\3\2\2\2\66.\3\2\2\2\67\7\3"
			+ "\2\2\289\b\5\1\29:\7\f\2\2:B\5\b\5\13;<\7\b\2\2<=\5\b\5\2=>\7\t\2\2>B"
			+ "\3\2\2\2?B\5\n\6\2@B\7!\2\2A8\3\2\2\2A;\3\2\2\2A?\3\2\2\2A@\3\2\2\2BT"
			+ "\3\2\2\2CD\f\t\2\2DE\t\2\2\2ES\5\b\5\nFG\f\b\2\2GH\t\3\2\2HS\5\b\5\tI"
			+ "J\f\7\2\2JK\t\4\2\2KS\5\b\5\bLM\f\6\2\2MN\t\5\2\2NS\5\b\5\7OP\f\5\2\2"
			+ "PQ\t\6\2\2QS\5\b\5\6RC\3\2\2\2RF\3\2\2\2RI\3\2\2\2RL\3\2\2\2RO\3\2\2\2"
			+ "SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2U\t\3\2\2\2VT\3\2\2\2W\\\7#\2\2X\\\7\"\2"
			+ "\2Y\\\7 \2\2Z\\\7$\2\2[W\3\2\2\2[X\3\2\2\2[Y\3\2\2\2[Z\3\2\2\2\\\13\3"
			+ "\2\2\2]b\7\31\2\2^b\7\32\2\2_b\7\33\2\2`b\7\34\2\2a]\3\2\2\2a^\3\2\2\2"
			+ "a_\3\2\2\2a`\3\2\2\2b\r\3\2\2\2\t\26\66ART[a";
	public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());

	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}