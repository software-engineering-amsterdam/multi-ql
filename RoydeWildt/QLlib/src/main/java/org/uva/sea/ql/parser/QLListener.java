// Generated from /home/roy/Workspace/SC/multi-ql/RoydeWildt/QLlib/src/main/java/org/uva/sea/ql/parser/QL.g4 by ANTLR 4.5.1
package org.uva.sea.ql.parser;

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
	 * Enter a parse tree produced by {@link QLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(QLParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(QLParser.ExprContext ctx);
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
	/**
	 * Enter a parse tree produced by {@link QLParser#num}.
	 * @param ctx the parse tree
	 */
	void enterNum(QLParser.NumContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#num}.
	 * @param ctx the parse tree
	 */
	void exitNum(QLParser.NumContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#str}.
	 * @param ctx the parse tree
	 */
	void enterStr(QLParser.StrContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#str}.
	 * @param ctx the parse tree
	 */
	void exitStr(QLParser.StrContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(QLParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(QLParser.IdContext ctx);
}