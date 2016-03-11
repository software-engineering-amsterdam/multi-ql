package ql.ast.literal;

import ql.ast.visitor.Visitor;

public class BoolLiteral extends Literal {
	private final boolean value;

	public BoolLiteral(Boolean value, int lineNumber) {
		super(lineNumber);
		this.value = value;
	}

	public boolean getValue() {
		return value;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
