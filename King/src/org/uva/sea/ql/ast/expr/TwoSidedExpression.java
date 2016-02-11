package org.uva.sea.ql.ast.expr;

public class TwoSidedExpression extends Expr {
	protected final Expr e1;
	protected final Expr e2;

	public TwoSidedExpression(Expr e1, Expr e2) {
		this.e1 = e1;
		this.e2 = e2;
	}

	public Expr getFirstExpression() {
		return e1;
	}

	public Expr getSecondExpression() {
		return e2;
	}
}
