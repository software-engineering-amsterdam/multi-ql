package eu.bankersen.kevin.ql.ast.expr.logic;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.boolExpr;
import eu.bankersen.kevin.ql.ast.expr.intExpr;
import eu.bankersen.kevin.ql.ast.expr.SymbolTabel;
import eu.bankersen.kevin.ql.ast.var.Type;

public class LEq extends boolExpr {

	public LEq(Expr lhs, Expr rhs) {
		super.lhs = lhs;
		super.rhs = rhs;
	}

	@Override
	public Boolean result(SymbolTabel table) {
		return (Integer) lhs.result(table) <= (Integer) rhs.result(table);
	}

}
