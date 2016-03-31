package ql.ast.expression;

import ql.ast.literal.Literal;
import ql.ast.visitor.Visitor;

public class VariableExpression extends Literal {
	private final String identifier;

	public VariableExpression(String identifier, int lineNumber) {
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
