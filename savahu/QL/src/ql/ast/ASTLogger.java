/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import ql.antlr.generatedcode.QLBaseListener;
import ql.antlr.generatedcode.QLParser;

/**
 *
 * @author sander
 */
public class ASTLogger extends QLBaseListener {

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterForm(QLParser.FormContext ctx) {
        System.out.println("Now entering a form with id: " + ctx.Ident.getText());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitForm(QLParser.FormContext ctx) {
        System.out.println("Now exiting the form with id: " + ctx.Ident.getText());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterBlock(QLParser.BlockContext ctx) {
        System.out.println("Now entering a block " + ctx.getText());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitBlock(QLParser.BlockContext ctx) {
        System.out.println("Now exiting the block");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterStatement(QLParser.StatementContext ctx) {
        System.out.println("Now entering a statement " + ctx.getText());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitStatement(QLParser.StatementContext ctx) {
        {
            System.out.println("Now exiting the statement");
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterIfstatement(QLParser.IfstatementContext ctx) {
        System.out.println("Now entering an if-statement " + ctx.getText());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitIfstatement(QLParser.IfstatementContext ctx) {
        {
            System.out.println("Now exiting the if-statement");
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterIfelsestatement(QLParser.IfelsestatementContext ctx) {
        System.out.println("Now entering an ifelse-statement " + ctx.getText());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitIfelsestatement(QLParser.IfelsestatementContext ctx) {
        System.out.println("Now exiting the ifelse-statement");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterQuestion(QLParser.QuestionContext ctx) {
        System.out.println("Now entering a question " + ctx.getText());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitQuestion(QLParser.QuestionContext ctx) {
        {
            System.out.println("Now exiting the question");
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterSimplequestion(QLParser.SimplequestionContext ctx) {
        System.out.println("Now entering a simple question " + ctx.getText());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitSimplequestion(QLParser.SimplequestionContext ctx) {
        {
            System.out.println("Now exiting the simple question");
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterComputedquestion(QLParser.ComputedquestionContext ctx) {
        System.out.println("Now entering a computed question " + ctx.getText());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitComputedquestion(QLParser.ComputedquestionContext ctx) {
        {
            System.out.println("Now exiting the computed question");
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterType(QLParser.TypeContext ctx) {
        {
            System.out.println("Now entering a questiontype " + ctx.getText());
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitType(QLParser.TypeContext ctx) {
        {
            System.out.println("Now exiting a questiontype");
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterLabel(QLParser.LabelContext ctx) {
        {
            System.out.println("Now entering a label " + ctx.getText());
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitLabel(QLParser.LabelContext ctx) {
        {
            System.out.println("Now exiting a label");
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterPrimary(QLParser.PrimaryContext ctx) {
        {
            System.out.println("Now entering a primary " + ctx.getText());
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitPrimary(QLParser.PrimaryContext ctx) {
        {
            System.out.println("Now exiting the primary");
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterBool(QLParser.BoolContext ctx) {
        {
            System.out.println("Now entering a bool " + ctx.getText());
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitBool(QLParser.BoolContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterUnaryExpr(QLParser.UnaryExprContext ctx) {
        System.out.println("Now entering a UnaryExpr " + ctx.getText());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitUnaryExpr(QLParser.UnaryExprContext ctx) {
        System.out.println("Now exiting a UnaryExpr");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterMulExpr(QLParser.MulExprContext ctx) {
        System.out.println("Now entering a MulExpr " + ctx.getText());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitMulExpr(QLParser.MulExprContext ctx) {
        System.out.println("Now exiting a MulExpr");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterAddExpr(QLParser.AddExprContext ctx) {
        System.out.println("Now entering a AddExpr " + ctx.getText());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitAddExpr(QLParser.AddExprContext ctx) {
        System.out.println("Now exiting a AddExpr ");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterRelExpr(QLParser.RelExprContext ctx) {
        System.out.println("Now entering a RelExpr " + ctx.getText());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitRelExpr(QLParser.RelExprContext ctx) {
        System.out.println("Now exiting a RelExpr");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterAndExpr(QLParser.AndExprContext ctx) {
        System.out.println("Now entering a AndExpr " + ctx.getText());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitAndExpr(QLParser.AndExprContext ctx) {
        System.out.println("Now exiting a AndExpr");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterOrExpr(QLParser.OrExprContext ctx) {
        System.out.println("Now entering a OrExpr " + ctx.getText());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitOrExpr(QLParser.OrExprContext ctx) {
        System.out.println("Now exiting a OrExpr");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void visitTerminal(TerminalNode node) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.</p>
     */
    @Override
    public void visitErrorNode(ErrorNode node) {
    }

}
