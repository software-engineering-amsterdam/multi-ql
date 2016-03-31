package ql.ast.literal;

import ql.ast.value.IntegerValue;
import ql.ast.visitor.Visitor;

public class IntLiteral extends Literal {
	private final IntegerValue value;

	public IntLiteral(Integer value, int lineNumber) {
		super(lineNumber);
		this.value = new IntegerValue(value);
	}

	public IntegerValue getValue() {
		return value;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
