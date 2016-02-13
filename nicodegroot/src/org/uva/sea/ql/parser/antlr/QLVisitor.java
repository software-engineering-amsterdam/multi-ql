// Generated from QL.g4 by ANTLR 4.5

	package org.uva.sea.ql.parser.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForm(QLParser.FormContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(QLParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code computedQuestionExpr}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComputedQuestionExpr(QLParser.ComputedQuestionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code questionExpr}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionExpr(QLParser.QuestionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifElseStatementExpr}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElseStatementExpr(QLParser.IfElseStatementExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStatementExpr}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatementExpr(QLParser.IfStatementExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(QLParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mathLowExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathLowExpr(QLParser.MathLowExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpr(QLParser.BoolExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelExpr(QLParser.RelExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code litExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLitExpr(QLParser.LitExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(QLParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mathHighExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathHighExpr(QLParser.MathHighExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code litIdExpr}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLitIdExpr(QLParser.LitIdExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code litIntExpr}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLitIntExpr(QLParser.LitIntExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code litBoolExpr}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLitBoolExpr(QLParser.LitBoolExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code litStringExpr}
	 * labeled alternative in {@link QLParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLitStringExpr(QLParser.LitStringExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#questionType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionType(QLParser.QuestionTypeContext ctx);
}