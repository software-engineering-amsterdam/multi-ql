package eu.bankersen.kevin.ql.ast.expr.math;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.intExpr;
import eu.bankersen.kevin.ql.ast.expr.SymbolTabel;

public class Div extends intExpr {

	public Div(Expr lhs, Expr rhs) {
		super.lhs =  lhs;
		super.rhs =  rhs;
	}

	@Override
	public Integer result(SymbolTabel table) {
		return (Integer) lhs.result(table) / (Integer) rhs.result(table);
	}

}
