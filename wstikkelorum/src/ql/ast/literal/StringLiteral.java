package ql.ast.literal;

import ql.ast.value.StringValue;
import ql.ast.visitor.Visitor;

public class StringLiteral extends Literal {
	private final StringValue value;

	public StringLiteral(String value, int lineNumber) {
		super(lineNumber);
		this.value = new StringValue(value);
	}

	public StringValue getValue() {
		return value;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
