package ql.ast.literal;

import ql.ast.value.BooleanValue;
import ql.ast.visitor.Visitor;

public class BoolLiteral extends Literal {
	private final BooleanValue value;

	public BoolLiteral(Boolean value, int lineNumber) {
		super(lineNumber);
		this.value = new BooleanValue(value);
	}

	public BooleanValue getValue() {
		return value;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
