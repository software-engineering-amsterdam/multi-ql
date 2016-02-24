package ql;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import ql.parser.QLBaseListener;
import ql.parser.QLParser;
import ql.parser.QLParser.AddExprContext;
import ql.parser.QLParser.AndExprContext;
import ql.parser.QLParser.IfelsestatementContext;
import ql.parser.QLParser.InputquestionContext;
import ql.parser.QLParser.MulExprContext;
import ql.parser.QLParser.OrExprContext;
import ql.parser.QLParser.PrimaryContext;
import ql.parser.QLParser.Question_typeContext;
import ql.parser.QLParser.RelExprContext;
import ql.parser.QLParser.StatementContext;
import ql.parser.QLParser.UnExprContext;



public class QLWalker extends QLBaseListener {

public void enterForm(QLParser.FormContext ctx) {
	System.out.println("Entered Form");
	
}

public void exitForm(QLParser.FormContext ctx) {
	System.out.println("Exiting Form");
	
}

public void enterBlock(QLParser.BlockContext ctx) {
	System.out.println("Entering Block");
}

public void exitBlock(QLParser.BlockContext ctx) {
	System.out.println("exit block");
}

public void enterQuestiontype(QLParser.Question_typeContext ctx) {
	System.out.println("Entering QuestionType");
}

public void enterIfstatement(QLParser.IfstatementContext ctx) {
	System.out.println("Entered If statement");
	System.out.println(ctx.cond);
}

public void exitIfstatement(QLParser.IfstatementContext ctx) {
	System.out.println("Entered If statement");
	System.out.println(ctx.cond);
}

public void enterInputQuestion(InputquestionContext ctx ) {
    System.out.println( "Entering Question : ");
    System.out.println(ctx.STR().getText() + " with the identifier: " + ctx.ID().getText() + " with type: " + ctx.question_type().getText() );
  }

  public void exitInputQuestion(QLParser.InputquestionContext ctx ) {
    System.out.println( "Exiting Question" );
  }

public void enterComputedquestion(ql.parser.QLParser.ComputedquestionContext ctx) { 
	System.out.println("entering computed Question");
}

public void exitComputedquestion(ql.parser.QLParser.ComputedquestionContext ctx) {
	System.out.println("Exiting computed question");
};
  
@Override
public void enterOrExpr(OrExprContext ctx) {
	// TODO Auto-generated method stub
	super.enterOrExpr(ctx);
}

@Override
public void exitOrExpr(OrExprContext ctx) {
	// TODO Auto-generated method stub
	super.exitOrExpr(ctx);
}

@Override
public void enterIfelsestatement(IfelsestatementContext ctx) {
	// TODO Auto-generated method stub
	super.enterIfelsestatement(ctx);
}

@Override
public void exitIfelsestatement(IfelsestatementContext ctx) {
	// TODO Auto-generated method stub
	super.exitIfelsestatement(ctx);
}

@Override
public void enterAddExpr(AddExprContext ctx) {
	// TODO Auto-generated method stub
	super.enterAddExpr(ctx);
}

@Override
public void exitAddExpr(AddExprContext ctx) {
	// TODO Auto-generated method stub
	super.exitAddExpr(ctx);
}

@Override
public void enterStatement(StatementContext ctx) {
	// TODO Auto-generated method stub
	System.out.println("ENTER STATEMTN");
	super.enterStatement(ctx);
}

@Override
public void exitStatement(StatementContext ctx) {
	// TODO Auto-generated method stub
	System.out.println("EXIT STATEMTN");

	super.exitStatement(ctx);
}

@Override
public void enterUnExpr(UnExprContext ctx) {
	// TODO Auto-generated method stub
	super.enterUnExpr(ctx);
}

@Override
public void exitUnExpr(UnExprContext ctx) {
	// TODO Auto-generated method stub
	super.exitUnExpr(ctx);
}

@Override
public void enterMulExpr(MulExprContext ctx) {
	// TODO Auto-generated method stub
	super.enterMulExpr(ctx);
}

@Override
public void exitMulExpr(MulExprContext ctx) {
	// TODO Auto-generated method stub
	super.exitMulExpr(ctx);
}


@Override
public void enterEveryRule(ParserRuleContext ctx) {
	// TODO Auto-generated method stub
	super.enterEveryRule(ctx);
}

@Override
public void exitEveryRule(ParserRuleContext ctx) {
	// TODO Auto-generated method stub
	super.exitEveryRule(ctx);
}

@Override
public void visitTerminal(TerminalNode node) {
	// TODO Auto-generated method stub
	super.visitTerminal(node);
}

@Override
public void visitErrorNode(ErrorNode node) {
	// TODO Auto-generated method stub
	super.visitErrorNode(node);
}

}