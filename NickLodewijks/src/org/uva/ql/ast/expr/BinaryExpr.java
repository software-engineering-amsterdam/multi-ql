package org.uva.ql.ast.expr;

public abstract class BinaryExpr extends Expr {

	protected final Expr lhs;
	protected final Expr rhs;

	public BinaryExpr(Expr lhs, Expr rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public Expr left() {
		return lhs;
	}

	public Expr right() {
		return rhs;
	}
}
