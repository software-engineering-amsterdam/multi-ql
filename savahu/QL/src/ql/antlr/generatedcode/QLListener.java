// Generated from A:\Users\sander\Documents\NetBeansProjects\multi-ql\savahu\QL\src\ql\antlr\QL.g4 by ANTLR 4.5.2

    package ql.antlr.generatedcode;
    import ql.ast.*;
    import ql.ast.expression.*;
    import ql.ast.type.*;
    import ql.ast.form.*;
    import ql.ast.literal.*;
    import ql.ast.statement.*;
    import ql.ast.question.*;

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
	 * Enter a parse tree produced by {@link QLParser#ifstatement}.
	 * @param ctx the parse tree
	 */
	void enterIfstatement(QLParser.IfstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#ifstatement}.
	 * @param ctx the parse tree
	 */
	void exitIfstatement(QLParser.IfstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#ifelsestatement}.
	 * @param ctx the parse tree
	 */
	void enterIfelsestatement(QLParser.IfelsestatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#ifelsestatement}.
	 * @param ctx the parse tree
	 */
	void exitIfelsestatement(QLParser.IfelsestatementContext ctx);
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
	 * Enter a parse tree produced by {@link QLParser#simplequestion}.
	 * @param ctx the parse tree
	 */
	void enterSimplequestion(QLParser.SimplequestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#simplequestion}.
	 * @param ctx the parse tree
	 */
	void exitSimplequestion(QLParser.SimplequestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#computedquestion}.
	 * @param ctx the parse tree
	 */
	void enterComputedquestion(QLParser.ComputedquestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#computedquestion}.
	 * @param ctx the parse tree
	 */
	void exitComputedquestion(QLParser.ComputedquestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(QLParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(QLParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(QLParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(QLParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(QLParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(QLParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(QLParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(QLParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(QLParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(QLParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#mulExpr}.
	 * @param ctx the parse tree
	 */
	void enterMulExpr(QLParser.MulExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#mulExpr}.
	 * @param ctx the parse tree
	 */
	void exitMulExpr(QLParser.MulExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#addExpr}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(QLParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#addExpr}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(QLParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#relExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelExpr(QLParser.RelExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#relExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelExpr(QLParser.RelExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(QLParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(QLParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(QLParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(QLParser.OrExprContext ctx);
}