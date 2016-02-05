package org.uva.sea.ql.ast.expr;

public class NEq extends Expr {
	Expr lhs, rhs;
	
	public NEq(Expr lhs, Expr rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	@Override
	public Boolean eval() {
		return lhs.eval() != rhs.eval();
	}
}
