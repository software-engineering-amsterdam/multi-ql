// Generated from /Users/Dominique/NetBeansProjects/multi-ql/Mickeydus/QL/src/antlr/Formulier.g4 by ANTLR 4.5.2

package antlr;
import AST.expressions.*;
import AST.types.*;
import ql.*;
import AST.form.*;
import AST.literals.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FormulierParser}.
 */
public interface FormulierListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FormulierParser#form}.
	 * @param ctx the parse tree
	 */
	void enterForm(FormulierParser.FormContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#form}.
	 * @param ctx the parse tree
	 */
	void exitForm(FormulierParser.FormContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulierParser#formName}.
	 * @param ctx the parse tree
	 */
	void enterFormName(FormulierParser.FormNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#formName}.
	 * @param ctx the parse tree
	 */
	void exitFormName(FormulierParser.FormNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulierParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(FormulierParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(FormulierParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulierParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(FormulierParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(FormulierParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulierParser#ifstatement}.
	 * @param ctx the parse tree
	 */
	void enterIfstatement(FormulierParser.IfstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#ifstatement}.
	 * @param ctx the parse tree
	 */
	void exitIfstatement(FormulierParser.IfstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulierParser#ifelsestatement}.
	 * @param ctx the parse tree
	 */
	void enterIfelsestatement(FormulierParser.IfelsestatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#ifelsestatement}.
	 * @param ctx the parse tree
	 */
	void exitIfelsestatement(FormulierParser.IfelsestatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulierParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(FormulierParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(FormulierParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulierParser#normalquestion}.
	 * @param ctx the parse tree
	 */
	void enterNormalquestion(FormulierParser.NormalquestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#normalquestion}.
	 * @param ctx the parse tree
	 */
	void exitNormalquestion(FormulierParser.NormalquestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulierParser#computedquestion}.
	 * @param ctx the parse tree
	 */
	void enterComputedquestion(FormulierParser.ComputedquestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#computedquestion}.
	 * @param ctx the parse tree
	 */
	void exitComputedquestion(FormulierParser.ComputedquestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulierParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(FormulierParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(FormulierParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulierParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(FormulierParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(FormulierParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormulierParser#questiontype}.
	 * @param ctx the parse tree
	 */
	void enterQuestiontype(FormulierParser.QuestiontypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormulierParser#questiontype}.
	 * @param ctx the parse tree
	 */
	void exitQuestiontype(FormulierParser.QuestiontypeContext ctx);
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