package ql;

import ql.parser.QLBaseListener;
import ql.parser.QLParser;



public class QLWalker extends QLBaseListener {

public void enterForm(QLParser.FormContext ctx) {
	System.out.println("Entered Form statement");

}
	
public void enterIfStatement(QLParser.IfstatementContext ctx) {
	System.out.println("Entered If statement");
	System.out.println(ctx.cond);
}
	
public void enterQuestion(QLParser.QuestionContext ctx ) {
    System.out.println( "Entering Question : ");
    System.out.println(ctx.Str().getText() + " with the identifier: " + ctx.Ident().getText() + " with type: " + ctx.question_type().getText() );
  }

  public void exitQuestion(QLParser.QuestionContext ctx ) {
    System.out.println( "Exiting Question" );
  }
}