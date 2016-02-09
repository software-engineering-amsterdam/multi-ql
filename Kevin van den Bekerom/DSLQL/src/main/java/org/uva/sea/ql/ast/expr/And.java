package org.uva.sea.ql.ast.expr;

public class And extends Expr{
	Expr lhs, rhs;
	
	public And(Expr lhs, Expr rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	@Override
	public Boolean eval() {
		return (Boolean) lhs.eval() && (Boolean) rhs.eval();
	}
}
