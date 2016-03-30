package uva.ql.typechecker;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.expressions.abstracts.ArithmeticOperatorBinary;
import uva.ql.ast.questions.QuestionComputed;
import uva.ql.ast.variables.Variable;
import uva.ql.typechecker.abstracts.AbstractTypeChecker;
import uva.ql.typechecker.errors.ErrorOperand;
import uva.ql.typechecker.visitors.IArithmeticOperatorVisitor;

public class OperandsToOperators extends AbstractTypeChecker implements IArithmeticOperatorVisitor {
		
	@Override
	public void visitForm(Form form) {
		
		for(int i=0; i<form.size(); i++) {
			
			Block block = (Block) form.get(i);
			block.accept(this);
		}
	}

	@Override
	public void visitBlock(Block block) {

		for(int i=0; i<block.size(); i++) {
			
			block.get(i).accept(this);
		}
	}

	@Override
	public void visitQuestionComputed(QuestionComputed questionComputed) {
		questionComputed.getExp().accept(this);
		Variable var = questionComputed.getVariable();
		errorMessages.add(new ErrorOperand(var.getName(), var.getLine(), var.getColumn()));
	}

	@Override
	public void visitCondIfStatement(CondIfStatement condIfStatement) {
		condIfStatement.getLhs().accept(this);
	}
	
	@Override
	public void visitCondIfElseStatement(CondIfElseStatement condIfElseStatement) {
		condIfElseStatement.getLhs().accept(this);
		condIfElseStatement.getRhs().accept(this);
	}

	@Override
	public void visitArithmeticOperator(ArithmeticOperatorBinary exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
		errorMessages.add(new ErrorOperand(exp.getType().toString(), exp.getLine(), exp.getColumn()));
	}
}
