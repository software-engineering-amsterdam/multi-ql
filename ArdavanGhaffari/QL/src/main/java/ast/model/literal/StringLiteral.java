package ast.model.literal;

import ast.model.Expression;
import ast.visitor.ExpressionVisitor;

public class StringLiteral extends Expression {
	private String value;
	
	public StringLiteral(String value, int line) {
		super(line);
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
		return expressionVisitor.visit(this);
	}	
}
