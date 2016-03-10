package org.uva.ql.ast.expr;

public class Positive extends UnaryExpr {

	public Positive(Expr expr) {
		super(expr);
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
