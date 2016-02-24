package ast.expression;

import ast.visitor.Visitor;

public class VariableExpression extends Expression {
	private String name;

	public VariableExpression(int lineNumber, String name) {
		super(lineNumber);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
