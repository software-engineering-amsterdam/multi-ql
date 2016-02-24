package ast.model.literal;

import ast.model.Expression;
import ast.visitor.ExpressionVisitor;

public class BooleanLiteral extends Expression{
	private boolean value;
	
	public BooleanLiteral(boolean value) {
		this.value = value;
	}
	
	public boolean getValue() {
		return this.value;
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
		return expressionVisitor.visit(this);
	}	

}
