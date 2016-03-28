package org.uva.sea.ql.ast.expr.unary;

import org.uva.sea.ql.ast.expr.Expr;

public abstract class UnaryExpression extends Expr {
	protected final Expr expr;

	public UnaryExpression(Expr expr) {
		this.expr = expr;
	}

	public Expr getExpression() {
		return expr;
	}

	@Override
	public String toString() {
		return "[ " + expr.toString() + " ]";
	}

}
