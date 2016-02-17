package ast.model.expression.literal;

import ast.model.expression.Expression;

public class Identifier extends Expression {
	private String identifier;
	
	public Identifier(String identifier) {
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}
}
