// Generated from QL.g4 by ANTLR 4.5.2

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
	 * Enter a parse tree produced by the {@code questionStatement}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterQuestionStatement(QLParser.QuestionStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code questionStatement}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitQuestionStatement(QLParser.QuestionStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code computedQuestionStatement}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterComputedQuestionStatement(QLParser.ComputedQuestionStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code computedQuestionStatement}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitComputedQuestionStatement(QLParser.ComputedQuestionStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(QLParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(QLParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifElseStatement}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfElseStatement(QLParser.IfElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifElseStatement}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfElseStatement(QLParser.IfElseStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lessEqExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLessEqExpression(QLParser.LessEqExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lessEqExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLessEqExpression(QLParser.LessEqExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lessExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLessExpression(QLParser.LessExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lessExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLessExpression(QLParser.LessExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code divExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDivExpression(QLParser.DivExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code divExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDivExpression(QLParser.DivExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpression(QLParser.ParenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpression(QLParser.ParenExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code noteqExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNoteqExpression(QLParser.NoteqExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code noteqExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNoteqExpression(QLParser.NoteqExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(QLParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(QLParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eqExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqExpression(QLParser.EqExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eqExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqExpression(QLParser.EqExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(QLParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(QLParser.AndExpressionContext ctx);
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
	 * Enter a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddExpression(QLParser.AddExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddExpression(QLParser.AddExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code greatEqExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterGreatEqExpression(QLParser.GreatEqExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code greatEqExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitGreatEqExpression(QLParser.GreatEqExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code litExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLitExpression(QLParser.LitExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code litExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLitExpression(QLParser.LitExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubExpression(QLParser.SubExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubExpression(QLParser.SubExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code greatExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterGreatExpression(QLParser.GreatExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code greatExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitGreatExpression(QLParser.GreatExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulExpression(QLParser.MulExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulExpression(QLParser.MulExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierLiteral}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierLiteral(QLParser.IdentifierLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierLiteral}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierLiteral(QLParser.IdentifierLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(QLParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(QLParser.IntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanliteral}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterBooleanliteral(QLParser.BooleanliteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanliteral}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitBooleanliteral(QLParser.BooleanliteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(QLParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(QLParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integerType}
	 * labeled alternative in {@link QLParser#questionType}.
	 * @param ctx the parse tree
	 */
	void enterIntegerType(QLParser.IntegerTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerType}
	 * labeled alternative in {@link QLParser#questionType}.
	 * @param ctx the parse tree
	 */
	void exitIntegerType(QLParser.IntegerTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link QLParser#questionType}.
	 * @param ctx the parse tree
	 */
	void enterStringType(QLParser.StringTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link QLParser#questionType}.
	 * @param ctx the parse tree
	 */
	void exitStringType(QLParser.StringTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanType}
	 * labeled alternative in {@link QLParser#questionType}.
	 * @param ctx the parse tree
	 */
	void enterBooleanType(QLParser.BooleanTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanType}
	 * labeled alternative in {@link QLParser#questionType}.
	 * @param ctx the parse tree
	 */
	void exitBooleanType(QLParser.BooleanTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moneyType}
	 * labeled alternative in {@link QLParser#questionType}.
	 * @param ctx the parse tree
	 */
	void enterMoneyType(QLParser.MoneyTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moneyType}
	 * labeled alternative in {@link QLParser#questionType}.
	 * @param ctx the parse tree
	 */
	void exitMoneyType(QLParser.MoneyTypeContext ctx);
}