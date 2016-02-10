package eu.bankersen.kevin.ql.ast.expr.math;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.intExpr;
import eu.bankersen.kevin.ql.ast.expr.interpExpr;

public class Mul extends intExpr {

	private final intExpr lhs;
	private final intExpr rhs;

	public Mul(Expr lhs, Expr rhs) {
		this.lhs = (intExpr) lhs;
		this.rhs = (intExpr) rhs;
	}

	@Override
	public Integer result(interpExpr expr) {
		return lhs.result(expr) * rhs.result(expr);
	}

}
