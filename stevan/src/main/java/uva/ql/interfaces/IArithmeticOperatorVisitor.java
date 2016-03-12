package uva.ql.interfaces;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.expressions.abstracts.AbstractArithmeticOperator;
import uva.ql.ast.questions.QuestionComputed;

public interface IArithmeticOperatorVisitor {

	public void visitForm(Form form);
	public void visitBlock(Block block);
	
	public void visitQuestionComputed(QuestionComputed question);
	
	public void visitCondIfElseStatement(CondIfElseStatement condition);
	public void visitCondIfStatement(CondIfStatement condition);
	
	public void visitArithmeticOperator(AbstractArithmeticOperator arithmeticOperator);
	
}
