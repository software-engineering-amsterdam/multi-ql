package eu.bankersen.kevin.ql.ast.expr.logic;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.boolExpr;
import eu.bankersen.kevin.ql.ast.expr.SymbolTabel;
import eu.bankersen.kevin.ql.ast.var.Type;

public class Not extends boolExpr {

	private final Expr expr;

	public Not(Expr expr) {
		this.expr = expr;
	}

	@Override
	public Boolean result(SymbolTabel table) {
		return !(Boolean) expr.result(table);
	}

}
