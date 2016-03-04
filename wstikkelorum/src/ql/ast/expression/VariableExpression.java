package ql.ast.expression;

import ql.ast.visitor.Visitor;

public class VariableExpression extends Expression {
	private final String identifier;

	public VariableExpression(int lineNumber, String identifier) {
		super(lineNumber);
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
