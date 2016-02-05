package org.uva.sea.ql.ast.expr;

public class Add extends Expr {
	Expr lhs, rhs;
	
	public Add(Expr lhs, Expr rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	@Override
	public Integer eval() {
		return (Integer) lhs.eval() + (Integer) rhs.eval();
	}
}
