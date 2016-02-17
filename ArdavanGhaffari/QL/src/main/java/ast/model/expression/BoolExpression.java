package ast.model.expression;

import ast.model.expression.operation.BoolOperation;

public class BoolExpression extends Expression {
	private Expression leftExp;
	
	private Expression rightExp;
	
	private BoolOperation operation;

	public BoolExpression(Expression leftExp, Expression rightExp, BoolOperation operation) {
		this.leftExp = leftExp;
		this.rightExp = rightExp;
		this.operation = operation;
	}

	public Expression getLeftExp() {
		return leftExp;
	}

	public Expression getRightExp() {
		return rightExp;
	}

	public BoolOperation getOperation() {
		return operation;
	}
	
}
