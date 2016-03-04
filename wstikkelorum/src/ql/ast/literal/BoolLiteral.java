package ql.ast.literal;

import ql.ast.expression.Expression;
import ql.ast.visitor.Visitor;

public class BoolLiteral extends Expression {
	private final boolean value;

	public BoolLiteral(int lineNumber, Boolean value) {
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
