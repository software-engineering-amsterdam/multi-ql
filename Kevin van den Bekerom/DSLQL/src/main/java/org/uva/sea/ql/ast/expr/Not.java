package org.uva.sea.ql.ast.expr;

public class Not extends Expr {
	Expr expr;
	
	public Not(Expr expr) {
		this.expr = expr;
	}
	
	@Override
	public Boolean eval() {
		return ! (Boolean) expr.eval();
	}
}
