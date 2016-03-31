// Generated from QL.g4 by ANTLR 4.5

package nl.nicasso.ql.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLParser}.
 */
public interface QLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLParser#form}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterForm(QLParser.FormContext ctx);

	/**
	 * Exit a parse tree produced by {@link QLParser#form}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitForm(QLParser.FormContext ctx);

	/**
	 * Enter a parse tree produced by {@link QLParser#block}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterBlock(QLParser.BlockContext ctx);

	/**
	 * Exit a parse tree produced by {@link QLParser#block}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitBlock(QLParser.BlockContext ctx);

	/**
	 * Enter a parse tree produced by the {@code questionStatement} labeled
	 * alternative in {@link QLParser#statement}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterQuestionStatement(QLParser.QuestionStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code questionStatement} labeled
	 * alternative in {@link QLParser#statement}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitQuestionStatement(QLParser.QuestionStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code computedQuestionStatement}
	 * labeled alternative in {@link QLParser#statement}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterComputedQuestionStatement(QLParser.ComputedQuestionStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code computedQuestionStatement}
	 * labeled alternative in {@link QLParser#statement}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitComputedQuestionStatement(QLParser.ComputedQuestionStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ifStatement} labeled
	 * alternative in {@link QLParser#statement}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterIfStatement(QLParser.IfStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ifStatement} labeled alternative
	 * in {@link QLParser#statement}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitIfStatement(QLParser.IfStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ifElseStatement} labeled
	 * alternative in {@link QLParser#statement}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterIfElseStatement(QLParser.IfElseStatementContext ctx);

	/**
	 * Exit a parse tree produced by the {@code ifElseStatement} labeled
	 * alternative in {@link QLParser#statement}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitIfElseStatement(QLParser.IfElseStatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code equalityExpressions} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterEqualityExpressions(QLParser.EqualityExpressionsContext ctx);

	/**
	 * Exit a parse tree produced by the {@code equalityExpressions} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitEqualityExpressions(QLParser.EqualityExpressionsContext ctx);

	/**
	 * Enter a parse tree produced by the {@code multiplicativeExpressions}
	 * labeled alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterMultiplicativeExpressions(QLParser.MultiplicativeExpressionsContext ctx);

	/**
	 * Exit a parse tree produced by the {@code multiplicativeExpressions}
	 * labeled alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitMultiplicativeExpressions(QLParser.MultiplicativeExpressionsContext ctx);

	/**
	 * Enter a parse tree produced by the {@code additiveExpressions} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterAdditiveExpressions(QLParser.AdditiveExpressionsContext ctx);

	/**
	 * Exit a parse tree produced by the {@code additiveExpressions} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitAdditiveExpressions(QLParser.AdditiveExpressionsContext ctx);

	/**
	 * Enter a parse tree produced by the {@code relationalExpressions} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterRelationalExpressions(QLParser.RelationalExpressionsContext ctx);

	/**
	 * Exit a parse tree produced by the {@code relationalExpressions} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitRelationalExpressions(QLParser.RelationalExpressionsContext ctx);

	/**
	 * Enter a parse tree produced by the {@code identifierExpression} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterIdentifierExpression(QLParser.IdentifierExpressionContext ctx);

	/**
	 * Exit a parse tree produced by the {@code identifierExpression} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitIdentifierExpression(QLParser.IdentifierExpressionContext ctx);

	/**
	 * Enter a parse tree produced by the {@code parenthesisExpression} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterParenthesisExpression(QLParser.ParenthesisExpressionContext ctx);

	/**
	 * Exit a parse tree produced by the {@code parenthesisExpression} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitParenthesisExpression(QLParser.ParenthesisExpressionContext ctx);

	/**
	 * Enter a parse tree produced by the {@code notExpression} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterNotExpression(QLParser.NotExpressionContext ctx);

	/**
	 * Exit a parse tree produced by the {@code notExpression} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitNotExpression(QLParser.NotExpressionContext ctx);

	/**
	 * Enter a parse tree produced by the {@code literalExpression} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterLiteralExpression(QLParser.LiteralExpressionContext ctx);

	/**
	 * Exit a parse tree produced by the {@code literalExpression} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitLiteralExpression(QLParser.LiteralExpressionContext ctx);

	/**
	 * Enter a parse tree produced by the {@code conditionalExpressions} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterConditionalExpressions(QLParser.ConditionalExpressionsContext ctx);

	/**
	 * Exit a parse tree produced by the {@code conditionalExpressions} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitConditionalExpressions(QLParser.ConditionalExpressionsContext ctx);

	/**
	 * Enter a parse tree produced by the {@code moneyLiteral} labeled
	 * alternative in {@link QLParser#literal}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterMoneyLiteral(QLParser.MoneyLiteralContext ctx);

	/**
	 * Exit a parse tree produced by the {@code moneyLiteral} labeled
	 * alternative in {@link QLParser#literal}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitMoneyLiteral(QLParser.MoneyLiteralContext ctx);

	/**
	 * Enter a parse tree produced by the {@code integerLiteral} labeled
	 * alternative in {@link QLParser#literal}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterIntegerLiteral(QLParser.IntegerLiteralContext ctx);

	/**
	 * Exit a parse tree produced by the {@code integerLiteral} labeled
	 * alternative in {@link QLParser#literal}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitIntegerLiteral(QLParser.IntegerLiteralContext ctx);

	/**
	 * Enter a parse tree produced by the {@code booleanliteral} labeled
	 * alternative in {@link QLParser#literal}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterBooleanliteral(QLParser.BooleanliteralContext ctx);

	/**
	 * Exit a parse tree produced by the {@code booleanliteral} labeled
	 * alternative in {@link QLParser#literal}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitBooleanliteral(QLParser.BooleanliteralContext ctx);

	/**
	 * Enter a parse tree produced by the {@code stringLiteral} labeled
	 * alternative in {@link QLParser#literal}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterStringLiteral(QLParser.StringLiteralContext ctx);

	/**
	 * Exit a parse tree produced by the {@code stringLiteral} labeled
	 * alternative in {@link QLParser#literal}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitStringLiteral(QLParser.StringLiteralContext ctx);

	/**
	 * Enter a parse tree produced by the {@code integerType} labeled
	 * alternative in {@link QLParser#questionType}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterIntegerType(QLParser.IntegerTypeContext ctx);

	/**
	 * Exit a parse tree produced by the {@code integerType} labeled alternative
	 * in {@link QLParser#questionType}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitIntegerType(QLParser.IntegerTypeContext ctx);

	/**
	 * Enter a parse tree produced by the {@code stringType} labeled alternative
	 * in {@link QLParser#questionType}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterStringType(QLParser.StringTypeContext ctx);

	/**
	 * Exit a parse tree produced by the {@code stringType} labeled alternative
	 * in {@link QLParser#questionType}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitStringType(QLParser.StringTypeContext ctx);

	/**
	 * Enter a parse tree produced by the {@code booleanType} labeled
	 * alternative in {@link QLParser#questionType}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterBooleanType(QLParser.BooleanTypeContext ctx);

	/**
	 * Exit a parse tree produced by the {@code booleanType} labeled alternative
	 * in {@link QLParser#questionType}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitBooleanType(QLParser.BooleanTypeContext ctx);

	/**
	 * Enter a parse tree produced by the {@code moneyType} labeled alternative
	 * in {@link QLParser#questionType}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void enterMoneyType(QLParser.MoneyTypeContext ctx);

	/**
	 * Exit a parse tree produced by the {@code moneyType} labeled alternative
	 * in {@link QLParser#questionType}.
	 * 
	 * @param ctx
	 *            the parse tree
	 */
	void exitMoneyType(QLParser.MoneyTypeContext ctx);
}