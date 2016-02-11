package org.uva.sea.ql.ast.expr;

public class OneSidedExpression extends Expr {
	protected final Expr expr;

	
	public OneSidedExpression(Expr expr) {
		this.expr = expr;
	}

	public Expr getExpression() {
		return expr;
	}

}
