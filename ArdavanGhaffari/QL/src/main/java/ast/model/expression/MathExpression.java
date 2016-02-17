package ast.model.expression;

import ast.model.expression.operation.MathOperation;

public class MathExpression extends Expression {
	private Expression leftExp;
	
	private Expression rightExp;
	
	private MathOperation operation;

	public MathExpression(Expression leftExp, Expression rightExp, MathOperation operation) {
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

	public MathOperation getOperation() {
		return operation;
	}
	
	
}
