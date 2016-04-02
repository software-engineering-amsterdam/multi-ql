/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql;

import antlr.FormulierBaseListener;
import antlr.FormulierParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 *
 * @author Dominique
 */
public class TreeWalker extends FormulierBaseListener  {
    
    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterForm(FormulierParser.FormContext ctx) {
        
            System.out.println("Start entering form with ID " + ctx.formName.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitForm(FormulierParser.FormContext ctx) { 
            
            System.out.println("Exiting form with ID " + ctx.formName.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterFormName(FormulierParser.FormNameContext ctx) {
        
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitFormName(FormulierParser.FormNameContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterBlock(FormulierParser.BlockContext ctx) { 
        
            System.out.println("Start entering block " + ctx.getText() );
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitBlock(FormulierParser.BlockContext ctx) {
        
            System.out.println("Exiting Block " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterStatement(FormulierParser.StatementContext ctx) {
        
            System.out.println("Start entering statement " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitStatement(FormulierParser.StatementContext ctx) {
        
            System.out.println("Exiting statement " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterIfstatement(FormulierParser.IfstatementContext ctx) {
        
            System.out.println("Start entering IFstatement " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitIfstatement(FormulierParser.IfstatementContext ctx) {
        
            System.out.println("Exiting IFstatement " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterIfelsestatement(FormulierParser.IfelsestatementContext ctx) {
        
            System.out.println("Start entering IFELSEstatement " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitIfelsestatement(FormulierParser.IfelsestatementContext ctx) { 
        
            System.out.println("Exiting IFELSEstatement " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterQuestion(FormulierParser.QuestionContext ctx) { 
        
            System.out.println("Start entering question " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitQuestion(FormulierParser.QuestionContext ctx) { 
        
            System.out.println("Start exiting question " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterNormalquestion(FormulierParser.NormalquestionContext ctx) {
        
            System.out.println("Start entering normal question " + ctx.nqlabel.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitNormalquestion(FormulierParser.NormalquestionContext ctx) {
        
            System.out.println("Exiting normal question " + ctx.nqlabel.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterComputedquestion(FormulierParser.ComputedquestionContext ctx) {
        
            System.out.println("Start entering computed question " + ctx.normalquestion.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitComputedquestion(FormulierParser.ComputedquestionContext ctx) { 
        
            System.out.println("Exiting computed question " + ctx.normalquestion.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterVariable(FormulierParser.VariableContext ctx) {
            
            System.out.println("Entering variable " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitVariable(FormulierParser.VariableContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterLabel(FormulierParser.LabelContext ctx) { 
        
              System.out.println("Entering label " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitLabel(FormulierParser.LabelContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterQuestiontype(FormulierParser.QuestiontypeContext ctx) {
        
             System.out.println("Entering questiontype " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitQuestiontype(FormulierParser.QuestiontypeContext ctx) { 
        
                System.out.println("Exiting questiontype " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterPrimary(FormulierParser.PrimaryContext ctx) { 
        
          System.out.println("Entering primary " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitPrimary(FormulierParser.PrimaryContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterUnExpr(FormulierParser.UnExprContext ctx) { 
         System.out.println("Entering unary expression " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitUnExpr(FormulierParser.UnExprContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterBool(FormulierParser.BoolContext ctx) { 
        
         System.out.println("Entering boolean " + ctx.getText());}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitBool(FormulierParser.BoolContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterMulExpr(FormulierParser.MulExprContext ctx) {
        
             System.out.println("Entering multiply expression " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitMulExpr(FormulierParser.MulExprContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterAddExpr(FormulierParser.AddExprContext ctx) {
        
              System.out.println("Entering Add expression " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitAddExpr(FormulierParser.AddExprContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterRelExpr(FormulierParser.RelExprContext ctx) {
        
             System.out.println("Entering relative expression " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitRelExpr(FormulierParser.RelExprContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterAndExpr(FormulierParser.AndExprContext ctx) { 
        
             System.out.println("Entering And expression " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitAndExpr(FormulierParser.AndExprContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterOrExpr(FormulierParser.OrExprContext ctx) { 
        
            System.out.println("Entering Or Expression " + ctx.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitOrExpr(FormulierParser.OrExprContext ctx) { 
        
            System.out.println("Exiting Or Expression " + ctx.getText());
        }

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterEveryRule(ParserRuleContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void visitTerminal(TerminalNode node) 
        { 
            System.out.println("Now visiting a terminal node" + node.getText());
        }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void visitErrorNode(ErrorNode node) { }
}
