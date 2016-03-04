package ql.ast.literal;

import ql.ast.expression.Expression;
import ql.ast.visitor.Visitor;

public class StringLiteral extends Expression {
	private final String value;

	public StringLiteral(int lineNumber, String value) {
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
