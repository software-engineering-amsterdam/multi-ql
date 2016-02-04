package org.uva.sea.ql.ast.expr;

public class Or extends Expr {
	Expr lhs, rhs;
	
	public Or(Expr lhs, Expr rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	@Override
	public Boolean eval() {
		return (Boolean) lhs.eval() || (Boolean) rhs.eval();
	}
}
