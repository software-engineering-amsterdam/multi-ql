package eu.bankersen.kevin.ql.ast.expr.math;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.intExpr;
import eu.bankersen.kevin.ql.ast.expr.SymbolTabel;
import eu.bankersen.kevin.ql.ast.var.Type;

public class Add extends intExpr {

	public Add(Expr lhs, Expr rhs) {
		super.lhs = (intExpr) lhs;
		super.rhs = (intExpr) rhs;
	}

	@Override
	public Integer result(SymbolTabel table) {
		return lhs.result(table) + rhs.result(table);
	}

}
