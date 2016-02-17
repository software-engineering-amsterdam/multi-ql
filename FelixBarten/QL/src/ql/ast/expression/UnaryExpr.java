package ql.ast.expression;

import ql.ast.Expr;

public class UnaryExpr extends Expr {
	private Expr expression;

	
	public UnaryExpr (Expr exp){

		this.expression = exp;

	}
}
