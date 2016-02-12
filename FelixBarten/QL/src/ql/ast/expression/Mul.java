package ql.ast.expression;

import ql.ast.Expr;

public class Mul extends BinaryExpr {
	public Mul (Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
}
