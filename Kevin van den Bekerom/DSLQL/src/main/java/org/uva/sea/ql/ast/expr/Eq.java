package org.uva.sea.ql.ast.expr;

public class Eq extends Expr {	
	Expr lhs, rhs;
	
	public Eq(Expr lhs, Expr rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	@Override
	public Boolean eval() {
		return  lhs.eval() ==  rhs.eval();
	}
}
