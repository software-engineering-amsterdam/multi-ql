package ql.ast.literal;

import ql.ast.visitor.Visitor;

public class StringLiteral extends Literal {
	private final String value;

	public StringLiteral(String value, int lineNumber) {
		super(lineNumber);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
