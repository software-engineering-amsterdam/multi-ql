package eu.bankersen.kevin.ql.ast.expr.math;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.intExpr;
import eu.bankersen.kevin.ql.ast.expr.SymbolTabel;

public class Neg extends intExpr {


	public Neg(Expr expr) {
		super.rhs =  expr;
	}

	@Override
	public Integer result(SymbolTabel table) {
		return (Integer) rhs.result(table) * -1;
	}

}
