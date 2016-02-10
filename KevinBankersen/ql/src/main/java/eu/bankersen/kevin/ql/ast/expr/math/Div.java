package eu.bankersen.kevin.ql.ast.expr.math;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.intExpr;
import eu.bankersen.kevin.ql.ast.expr.SymbolTabel;

public class Div extends intExpr {

	private final intExpr lhs;
	private final intExpr rhs;

	public Div(Expr lhs, Expr rhs) {
		this.lhs = (intExpr) lhs;
		this.rhs = (intExpr) rhs;
	}

	@Override
	public Integer result(SymbolTabel table) {
		return lhs.result(table) / rhs.result(table);
	}

}
