package ast.model.literal;

import ast.model.Expression;
import ast.visitor.ExpressionVisitor;

public class IntegerLiteral extends Expression {
	private int value;
	
	public IntegerLiteral(int value, int line) {
		super(line);
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
		return expressionVisitor.visit(this);
	}	
}
