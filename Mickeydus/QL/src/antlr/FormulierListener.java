// Generated from /Users/Dominique/NetBeansProjects/multi-ql/Mickeydus/QL/src/antlr/Formulier.g4 by ANTLR 4.5.2

package antlr;
import AST.expressions.*;
import AST.types.*;
import ql.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FormulierParser}.
 */
public interface FormulierListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FormulierParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(FormulierParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(FormulierParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulierParser#unExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnExpr(FormulierParser.UnExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#unExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnExpr(FormulierParser.UnExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulierParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(FormulierParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(FormulierParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulierParser#mulExpr}.
	 * @param ctx the parse tree
	 */
	void enterMulExpr(FormulierParser.MulExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#mulExpr}.
	 * @param ctx the parse tree
	 */
	void exitMulExpr(FormulierParser.MulExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulierParser#addExpr}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(FormulierParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#addExpr}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(FormulierParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulierParser#relExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelExpr(FormulierParser.RelExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#relExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelExpr(FormulierParser.RelExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulierParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(FormulierParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(FormulierParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulierParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(FormulierParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(FormulierParser.OrExprContext ctx);
}