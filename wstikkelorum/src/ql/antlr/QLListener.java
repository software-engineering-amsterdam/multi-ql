// Generated from QL.g4 by ANTLR 4.5.2

	package ql.antlr;
	import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLParser}.
 */
public interface QLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(QLParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(QLParser.FileContext ctx);
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
	 * Enter a parse tree produced by {@link QLParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(QLParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(QLParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(QLParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(QLParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(QLParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(QLParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(QLParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(QLParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(QLParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(QLParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#variableType}.
	 * @param ctx the parse tree
	 */
	void enterVariableType(QLParser.VariableTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#variableType}.
	 * @param ctx the parse tree
	 */
	void exitVariableType(QLParser.VariableTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#orExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(QLParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#orExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(QLParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(QLParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(QLParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#relExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelExpression(QLParser.RelExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#relExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelExpression(QLParser.RelExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#addExpression}.
	 * @param ctx the parse tree
	 */
	void enterAddExpression(QLParser.AddExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#addExpression}.
	 * @param ctx the parse tree
	 */
	void exitAddExpression(QLParser.AddExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#mulExpression}.
	 * @param ctx the parse tree
	 */
	void enterMulExpression(QLParser.MulExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#mulExpression}.
	 * @param ctx the parse tree
	 */
	void exitMulExpression(QLParser.MulExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#unExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnExpression(QLParser.UnExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#unExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnExpression(QLParser.UnExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(QLParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(QLParser.LiteralContext ctx);
}