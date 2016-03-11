package sc.ql.ast.expr;

import sc.ql.ast.value.NumberValue;

public final class IntegerLiteral extends Expr {

	private final NumberValue value;

	public IntegerLiteral(Integer value) {
		this.value = new NumberValue(value);
	}

	public NumberValue getValue() {
		return value;
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
