package org.uva.sea.ql.ast.expr;

public class Neg extends Expr {
	Expr expr;
	
	public Neg(Expr expr) {
		this.expr = expr;
	}
	
	@Override
	public Integer eval() {
		return  (Integer) expr.eval() * -1;
	}
	//TODO{Check if this way of computation is correct}
}
