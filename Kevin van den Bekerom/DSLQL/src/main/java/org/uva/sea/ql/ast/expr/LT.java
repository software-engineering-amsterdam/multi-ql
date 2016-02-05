package org.uva.sea.ql.ast.expr;

public class LT extends Expr {
	Expr lhs, rhs;
	
	public LT(Expr lhs, Expr rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	@Override
	public Boolean eval() {
		return (Integer) lhs.eval() < (Integer) rhs.eval();
	}
}
