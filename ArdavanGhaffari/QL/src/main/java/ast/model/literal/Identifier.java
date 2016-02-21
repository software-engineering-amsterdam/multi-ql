package ast.model.literal;

import ast.model.Expression;
import ast.visitor.ExpressionVisitor;

public class Identifier extends Expression {
	private String identifier;
	
	public Identifier(String identifier) {
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
		return expressionVisitor.visit(this);
	}	
}
