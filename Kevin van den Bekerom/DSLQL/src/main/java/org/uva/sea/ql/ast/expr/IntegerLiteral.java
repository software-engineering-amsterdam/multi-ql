package org.uva.sea.ql.ast.expr;

public class IntegerLiteral extends Expr {
	final int value;
	
	public IntegerLiteral(int value) {
		this.value = value;
	}
	
	@Override
	public Integer eval() {
		return value;
	}
}
