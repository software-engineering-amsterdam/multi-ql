package eu.bankersen.kevin.ql.ast.expr.logic;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.boolExpr;
import eu.bankersen.kevin.ql.ast.expr.SymbolTabel;
import eu.bankersen.kevin.ql.ast.var.Type;

public class And extends boolExpr {

	private final boolExpr lhs;
	private final boolExpr rhs;

	public And(Expr lhs, Expr rhs) {
		this.lhs = (boolExpr) lhs;
		this.rhs = (boolExpr) rhs;
	}

	@Override
	public Boolean result(SymbolTabel table) {
		return lhs.result(table) && rhs.result(table);
	}

	@Override
	public Boolean checkType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
