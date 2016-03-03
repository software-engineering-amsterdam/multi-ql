package ast.literal;

import ast.expression.Expression;
import ast.visitor.Visitor;

public class Variable extends Expression {
	private final String identifier;
	private final VariableType type;

	public Variable(int lineNumber, String identifier, VariableType type) {
		super(lineNumber);
		this.identifier = identifier;
		this.type = type;
	}

	public String getIdentifier() {
		return identifier;
	}

	public VariableType getType() {
		return type;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
