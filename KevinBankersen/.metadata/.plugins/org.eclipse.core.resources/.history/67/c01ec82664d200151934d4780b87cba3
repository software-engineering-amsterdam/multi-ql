package eu.bankersen.kevin.ql.ast.expr.logic;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.boolExpr;
import eu.bankersen.kevin.ql.ast.expr.SymbolTabel;
import eu.bankersen.kevin.ql.ast.var.Type;

public class And extends boolExpr {

	public And(Expr lhs, Expr rhs) {
		super.lhs = lhs;
		super.rhs = rhs;
	}

	@Override
	public Boolean result(SymbolTabel table) {
		return (Boolean)lhs.result(table) && (Boolean)rhs.result(table);
	}

}
