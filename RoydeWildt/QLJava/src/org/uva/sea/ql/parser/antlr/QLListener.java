// Generated from /Users/roydewildt/Workspace/UvA/SC/multi-ql/RoydeWildt/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g4 by ANTLR 4.5.1
package org.uva.sea.ql.parser.antlr;

import java.util.List;
import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.val.*;
import org.uva.sea.ql.ast.form.*;

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
	 * Enter a parse tree produced by {@link QLParser#forms}.
	 * @param ctx the parse tree
	 */
	void enterForms(QLParser.FormsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#forms}.
	 * @param ctx the parse tree
	 */
	void exitForms(QLParser.FormsContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(QLParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(QLParser.VarDeclContext ctx);
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
	 * Enter a parse tree produced by {@link QLParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(QLParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(QLParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#stats}.
	 * @param ctx the parse tree
	 */
	void enterStats(QLParser.StatsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#stats}.
	 * @param ctx the parse tree
	 */
	void exitStats(QLParser.StatsContext ctx);
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
	 * Enter a parse tree produced by {@link QLParser#unExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnExpr(QLParser.UnExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#unExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnExpr(QLParser.UnExprContext ctx);
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
	 * Enter a parse tree produced by {@link QLParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(QLParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(QLParser.BoolContext ctx);
}