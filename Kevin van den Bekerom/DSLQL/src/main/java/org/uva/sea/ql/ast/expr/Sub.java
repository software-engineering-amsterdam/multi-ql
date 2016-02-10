package org.uva.sea.ql.ast.expr;

public class Sub extends Expr {
	Expr lhs, rhs;
	
	public Sub(Expr lhs, Expr rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	@Override
	public Integer eval() {
		return (Integer) lhs.eval() - (Integer) rhs.eval();
	}
}
