// Generated from QL.g4 by ANTLR 4.4
package antlr;

	package antlr;
	import ast.expression.*;
	import ast.form.*;
	import ast.literal.*;
	import ast.statement.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLParser}.
 */
public interface QLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(@NotNull QLParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(@NotNull QLParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#relExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelExpression(@NotNull QLParser.RelExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#relExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelExpression(@NotNull QLParser.RelExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#intLiteral}.
	 * @param ctx the parse tree
	 */
	void enterIntLiteral(@NotNull QLParser.IntLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#intLiteral}.
	 * @param ctx the parse tree
	 */
	void exitIntLiteral(@NotNull QLParser.IntLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(@NotNull QLParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(@NotNull QLParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(@NotNull QLParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(@NotNull QLParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#boolLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBoolLiteral(@NotNull QLParser.BoolLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#boolLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBoolLiteral(@NotNull QLParser.BoolLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#variableExpression}.
	 * @param ctx the parse tree
	 */
	void enterVariableExpression(@NotNull QLParser.VariableExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#variableExpression}.
	 * @param ctx the parse tree
	 */
	void exitVariableExpression(@NotNull QLParser.VariableExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(@NotNull QLParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(@NotNull QLParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#orExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(@NotNull QLParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#orExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(@NotNull QLParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#variableType}.
	 * @param ctx the parse tree
	 */
	void enterVariableType(@NotNull QLParser.VariableTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#variableType}.
	 * @param ctx the parse tree
	 */
	void exitVariableType(@NotNull QLParser.VariableTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(@NotNull QLParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(@NotNull QLParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(@NotNull QLParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(@NotNull QLParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#unExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnExpression(@NotNull QLParser.UnExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#unExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnExpression(@NotNull QLParser.UnExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 */
	void enterForm(@NotNull QLParser.FormContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 */
	void exitForm(@NotNull QLParser.FormContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#addExpression}.
	 * @param ctx the parse tree
	 */
	void enterAddExpression(@NotNull QLParser.AddExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#addExpression}.
	 * @param ctx the parse tree
	 */
	void exitAddExpression(@NotNull QLParser.AddExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(@NotNull QLParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(@NotNull QLParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull QLParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull QLParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull QLParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull QLParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#mulExpression}.
	 * @param ctx the parse tree
	 */
	void enterMulExpression(@NotNull QLParser.MulExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#mulExpression}.
	 * @param ctx the parse tree
	 */
	void exitMulExpression(@NotNull QLParser.MulExpressionContext ctx);
}