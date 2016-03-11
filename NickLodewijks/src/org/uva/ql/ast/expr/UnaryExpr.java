package org.uva.ql.ast.expr;

public abstract class UnaryExpr extends Expr {

	private final Expr expr;

	public UnaryExpr(Expr expr) {
		this.expr = expr;
	}

	public final Expr expr() {
		return expr;
	}
}
