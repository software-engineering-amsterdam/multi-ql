package eu.bankersen.kevin.ql.ast.expr.math;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.intExpr;
import eu.bankersen.kevin.ql.ast.expr.SymbolTabel;

public class Pos extends intExpr {

	private final intExpr value;

	public Pos(Expr expr) {
		this.value = (intExpr) expr;
	}

	@Override
	public Integer result(SymbolTabel table) {
		return (value.result(table) < 0) ? -1 * value.result(table) : value.result(table);
	}

}
