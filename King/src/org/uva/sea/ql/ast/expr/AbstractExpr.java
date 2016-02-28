package org.uva.sea.ql.ast.expr;


public abstract class AbstractExpr extends Expr {
	protected final Expr e1;
	protected final Expr e2;

	public AbstractExpr(Expr e1, Expr e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	public AbstractExpr(Expr e1) {
		this.e1 = e1;
		this.e2 = null;
	}

	public Expr getFirstExpression() {
		return e1;
	}

	public Expr getSecondExpression() {
		return e2;
	}
	
}
