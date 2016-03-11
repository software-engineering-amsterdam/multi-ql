package org.uva.ql.ast.expr;

public class GreaterThanOrEqual extends BinaryExpr {

	public GreaterThanOrEqual(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
