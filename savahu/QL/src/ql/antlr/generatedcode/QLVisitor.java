// Generated from A:\Users\sander\Documents\NetBeansProjects\multi-ql\savahu\QL\src\ql\antlr\QL.g4 by ANTLR 4.5.2

    package ql.antlr.generatedcode;
    import ql.ast.*;
    import ql.ast.expression.*;
    import ql.ast.type.*;
    import ql.ast.form.*;
    import ql.ast.literal.*;
    import ql.ast.statement.*;
    import ql.ast.question.*;

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
	 * Visit a parse tree produced by {@link QLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(QLParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#ifstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfstatement(QLParser.IfstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#ifelsestatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfelsestatement(QLParser.IfelsestatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(QLParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#simplequestion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimplequestion(QLParser.SimplequestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#computedquestion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComputedquestion(QLParser.ComputedquestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#questiontype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestiontype(QLParser.QuestiontypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(QLParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(QLParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(QLParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#unaryExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(QLParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#mulExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExpr(QLParser.MulExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#addExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpr(QLParser.AddExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#relExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelExpr(QLParser.RelExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#andExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(QLParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#orExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(QLParser.OrExprContext ctx);
}