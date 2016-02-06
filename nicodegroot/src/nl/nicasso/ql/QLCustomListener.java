package nl.nicasso.ql;

import org.uva.sea.ql.parser.antlr.QLBaseListener;
import org.uva.sea.ql.parser.antlr.QLParser;

public class QLCustomListener extends QLBaseListener {
	
	@Override
	public void enterForm(QLParser.FormContext ctx) {
		System.out.println("ENTER FORM: "+ctx.getText());
	}
	
	@Override public void enterBlock(QLParser.BlockContext ctx) {
		System.out.println("ENTER BLOCK: "+ctx.getText());
	}
	
	@Override public void enterIfStatement(QLParser.IfStatementContext ctx) {
		System.out.println("ENTER IFSTATEMENT: "+ctx.getText());
	}
	
	@Override public void enterComputedQuestion(QLParser.ComputedQuestionContext ctx) {
		System.out.println("ENTER COMPUTED QUESTION: "+ctx.getText());
	}
	
	@Override
	public void enterQuestion(QLParser.QuestionContext ctx) {
		System.out.println("ENTER QUESTION: "+ctx.getText());
	}
	
	@Override
	public void exitQuestion(QLParser.QuestionContext ctx) {
		System.out.println("EXIT QUESTION: "+ctx.getText());
	}
	
}
