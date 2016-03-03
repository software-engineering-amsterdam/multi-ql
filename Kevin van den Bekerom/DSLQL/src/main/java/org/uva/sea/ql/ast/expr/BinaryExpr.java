package org.uva.sea.ql.ast.expr;

public abstract class BinaryExpr extends Expr {
	protected Expr lhs, rhs;

	public Expr getLhs() {
		return lhs;
	}

	public Expr getRhs() {
		return rhs;
	}

}
