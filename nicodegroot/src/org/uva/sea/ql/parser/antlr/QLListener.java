// Generated from QL.g4 by ANTLR 4.5

	package org.uva.sea.ql.parser.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLParser}.
 */
public interface QLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 */
	void enterForm(QLParser.FormContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 */
	void exitForm(QLParser.FormContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(QLParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(QLParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code computedQuestionExpr}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterComputedQuestionExpr(QLParser.ComputedQuestionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code computedQuestionExpr}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitComputedQuestionExpr(QLParser.ComputedQuestionExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code questionExpr}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterQuestionExpr(QLParser.QuestionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code questionExpr}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitQuestionExpr(QLParser.QuestionExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifElseStatementExpr}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfElseStatementExpr(QLParser.IfElseStatementExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifElseStatementExpr}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfElseStatementExpr(QLParser.IfElseStatementExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStatementExpr}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatementExpr(QLParser.IfStatementExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStatementExpr}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatementExpr(QLParser.IfStatementExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(QLParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(QLParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mathLowExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMathLowExpr(QLParser.MathLowExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mathLowExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMathLowExpr(QLParser.MathLowExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(QLParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(QLParser.BoolExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRelExpr(QLParser.RelExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRelExpr(QLParser.RelExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code litExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLitExpr(QLParser.LitExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code litExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLitExpr(QLParser.LitExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(QLParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(QLParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mathHighExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMathHighExpr(QLParser.MathHighExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mathHighExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMathHighExpr(QLParser.MathHighExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code litIdExpr}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLitIdExpr(QLParser.LitIdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code litIdExpr}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLitIdExpr(QLParser.LitIdExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code litIntExpr}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLitIntExpr(QLParser.LitIntExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code litIntExpr}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLitIntExpr(QLParser.LitIntExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code litBoolExpr}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLitBoolExpr(QLParser.LitBoolExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code litBoolExpr}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLitBoolExpr(QLParser.LitBoolExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code litStringExpr}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLitStringExpr(QLParser.LitStringExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code litStringExpr}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLitStringExpr(QLParser.LitStringExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#questionType}.
	 * @param ctx the parse tree
	 */
	void enterQuestionType(QLParser.QuestionTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#questionType}.
	 * @param ctx the parse tree
	 */
	void exitQuestionType(QLParser.QuestionTypeContext ctx);
}