package ql.ast.literal;

import ql.ast.visitor.Visitor;

public class IntLiteral extends Literal {
	private final int value;

	public IntLiteral(Integer value, int lineNumber) {
		super(lineNumber);
		this.value = (int) value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
