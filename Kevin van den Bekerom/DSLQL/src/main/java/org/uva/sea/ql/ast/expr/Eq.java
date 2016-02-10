package org.uva.sea.ql.ast.expr;

public class Eq extends Expr {	
	Expr lhs, rhs;
	
	public Eq(Expr lhs, Expr rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	//TODO: different check for Strings and booleans!!! Need to know the type
	@Override
	public Boolean eval() {
		return  lhs.eval() ==  rhs.eval();
	}
}
