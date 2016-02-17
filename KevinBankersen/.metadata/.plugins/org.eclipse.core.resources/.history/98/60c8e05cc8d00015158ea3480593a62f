package eu.bankersen.kevin.ql.ast.expr.logic;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.boolExpr;
import eu.bankersen.kevin.ql.ast.expr.intExpr;
import eu.bankersen.kevin.ql.ast.expr.SymbolTabel;
import eu.bankersen.kevin.ql.ast.var.Type;

public class GT extends boolExpr {

	private final intExpr lhs;
	private final intExpr rhs;

	public GT(Expr lhs, Expr rhs) {
		this.lhs = (intExpr) lhs;
		this.rhs = (intExpr) rhs;
	}

	@Override
	public Boolean result(SymbolTabel table) {
		return lhs.result(table) > rhs.result(table);
	}

	@Override
	public Boolean checkType() {
		return lhs.getType() == rhs.getType();
	}
}
