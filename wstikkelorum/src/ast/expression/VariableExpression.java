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
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
