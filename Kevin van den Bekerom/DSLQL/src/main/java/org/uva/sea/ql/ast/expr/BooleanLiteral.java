package org.uva.sea.ql.ast.expr;

public class BooleanLiteral extends Expr {
	final boolean value;
	
	public BooleanLiteral(boolean value) {
		this.value = value;
	}
	
	@Override
	public Boolean eval() {
		return value;
	}
}
