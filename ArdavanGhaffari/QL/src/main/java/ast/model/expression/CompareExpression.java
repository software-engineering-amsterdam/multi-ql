package ast.model.expression;

import ast.model.expression.operation.CompareOperation;

public class CompareExpression extends Expression {
	private Expression letfExp;
	
	private Expression rightExp;
	
	private CompareOperation operation;

	public CompareExpression(Expression letfExp, Expression rightExp, CompareOperation operation) {
		this.letfExp = letfExp;
		this.rightExp = rightExp;
		this.operation = operation;
	}

	public Expression getLetfExp() {
		return letfExp;
	}

	public Expression getRightExp() {
		return rightExp;
	}

	public CompareOperation getOperation() {
		return operation;
	}
	
}
