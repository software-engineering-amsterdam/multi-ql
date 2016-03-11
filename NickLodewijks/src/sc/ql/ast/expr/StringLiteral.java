package sc.ql.ast.expr;

import sc.ql.ast.value.StringValue;

public final class StringLiteral extends Expr {

	private final StringValue value;

	public StringLiteral(String value) {
		this.value = new StringValue(value);
	}

	public StringValue getValue() {
		return value;
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
