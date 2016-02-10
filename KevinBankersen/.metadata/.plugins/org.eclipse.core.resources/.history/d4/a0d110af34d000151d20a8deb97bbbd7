package eu.bankersen.kevin.ql.ast.expr.logic;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.boolExpr;
import eu.bankersen.kevin.ql.ast.expr.interpExpr;

public class Eq extends boolExpr {

	private final boolExpr lhs;
	private final boolExpr rhs;

	public Eq(Expr lhs, Expr rhs) {
		this.lhs = (boolExpr) lhs;
		this.rhs = (boolExpr) rhs;
	}

	@Override
	public Boolean result(interpExpr expr) {
		return lhs.result(expr) == rhs.result(expr);
	}

}
