package org.uva.sea.ql.ast.expr;

public class Pos extends Expr {
	Expr expr;
	
	public Pos(Expr expr) {
		this.expr = expr;
	}
	
	@Override
	public Integer eval() {
		return Math.abs( (Integer) expr.eval());
	}
}
