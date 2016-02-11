package org.uva.ql.ast.expr;

public abstract class ArithmeticExpr extends BinaryExpr {

	public ArithmeticExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
}
