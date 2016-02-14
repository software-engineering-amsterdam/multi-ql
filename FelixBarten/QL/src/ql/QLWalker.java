package ql;

import ql.parser.QLBaseListener;
import ql.parser.QLParser;



public class QLWalker extends QLBaseListener {
  public void enterQuestion(QLParser.QuestionContext ctx ) {
    System.out.println( "Entering Question : " + ctx.Str().getText() + " with the identifier: " + ctx.Ident().getText() + " with type: " + ctx.question_type().getText() );
  }

  public void exitQuestion(QLParser.QuestionContext ctx ) {
    System.out.println( "Exiting Question" );
  }
}