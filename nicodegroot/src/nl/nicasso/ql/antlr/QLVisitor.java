// Generated from QL.g4 by ANTLR 4.5

package nl.nicasso.ql.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QLParser}.
 *
 * @param <T>
 *            The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public interface QLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QLParser#form}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitForm(QLParser.FormContext ctx);

	/**
	 * Visit a parse tree produced by {@link QLParser#block}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitBlock(QLParser.BlockContext ctx);

	/**
	 * Visit a parse tree produced by the {@code questionStatement} labeled
	 * alternative in {@link QLParser#statement}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitQuestionStatement(QLParser.QuestionStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code computedQuestionStatement}
	 * labeled alternative in {@link QLParser#statement}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitComputedQuestionStatement(QLParser.ComputedQuestionStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ifStatement} labeled
	 * alternative in {@link QLParser#statement}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(QLParser.IfStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ifElseStatement} labeled
	 * alternative in {@link QLParser#statement}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitIfElseStatement(QLParser.IfElseStatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code equalityExpressions} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpressions(QLParser.EqualityExpressionsContext ctx);

	/**
	 * Visit a parse tree produced by the {@code multiplicativeExpressions}
	 * labeled alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpressions(QLParser.MultiplicativeExpressionsContext ctx);

	/**
	 * Visit a parse tree produced by the {@code additiveExpressions} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpressions(QLParser.AdditiveExpressionsContext ctx);

	/**
	 * Visit a parse tree produced by the {@code relationalExpressions} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpressions(QLParser.RelationalExpressionsContext ctx);

	/**
	 * Visit a parse tree produced by the {@code identifierExpression} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpression(QLParser.IdentifierExpressionContext ctx);

	/**
	 * Visit a parse tree produced by the {@code parenthesisExpression} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitParenthesisExpression(QLParser.ParenthesisExpressionContext ctx);

	/**
	 * Visit a parse tree produced by the {@code notExpression} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(QLParser.NotExpressionContext ctx);

	/**
	 * Visit a parse tree produced by the {@code literalExpression} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpression(QLParser.LiteralExpressionContext ctx);

	/**
	 * Visit a parse tree produced by the {@code conditionalExpressions} labeled
	 * alternative in {@link QLParser#expression}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitConditionalExpressions(QLParser.ConditionalExpressionsContext ctx);

	/**
	 * Visit a parse tree produced by the {@code moneyLiteral} labeled
	 * alternative in {@link QLParser#literal}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitMoneyLiteral(QLParser.MoneyLiteralContext ctx);

	/**
	 * Visit a parse tree produced by the {@code integerLiteral} labeled
	 * alternative in {@link QLParser#literal}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteral(QLParser.IntegerLiteralContext ctx);

	/**
	 * Visit a parse tree produced by the {@code booleanliteral} labeled
	 * alternative in {@link QLParser#literal}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitBooleanliteral(QLParser.BooleanliteralContext ctx);

	/**
	 * Visit a parse tree produced by the {@code stringLiteral} labeled
	 * alternative in {@link QLParser#literal}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(QLParser.StringLiteralContext ctx);

	/**
	 * Visit a parse tree produced by the {@code integerType} labeled
	 * alternative in {@link QLParser#questionType}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitIntegerType(QLParser.IntegerTypeContext ctx);

	/**
	 * Visit a parse tree produced by the {@code stringType} labeled alternative
	 * in {@link QLParser#questionType}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitStringType(QLParser.StringTypeContext ctx);

	/**
	 * Visit a parse tree produced by the {@code booleanType} labeled
	 * alternative in {@link QLParser#questionType}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitBooleanType(QLParser.BooleanTypeContext ctx);

	/**
	 * Visit a parse tree produced by the {@code moneyType} labeled alternative
	 * in {@link QLParser#questionType}.
	 * 
	 * @param ctx
	 *            the parse tree
	 * @return the visitor result
	 */
	T visitMoneyType(QLParser.MoneyTypeContext ctx);
}