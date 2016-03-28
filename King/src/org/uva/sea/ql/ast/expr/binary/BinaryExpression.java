package org.uva.sea.ql.ast.expr.binary;

import org.uva.sea.ql.ast.expr.Expr;

public abstract class BinaryExpression extends Expr {
	protected final Expr e1;
	protected final Expr e2;

	public BinaryExpression(Expr e1, Expr e2) {
		this.e1 = e1;
		this.e2 = e2;
	}

	public Expr getFirstExpression() {
		return e1;
	}

	public Expr getSecondExpression() {
		return e2;
	}

	@Override
	public String toString() {
		return "[ " + e1.toString() + " and " + e2.toString() + " ]";
	}

}
