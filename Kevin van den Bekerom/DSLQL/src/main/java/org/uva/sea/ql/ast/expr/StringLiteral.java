package org.uva.sea.ql.ast.expr;

public class StringLiteral extends Expr {
	final String value;
	
	public StringLiteral(String value) {
		this.value = value;
	}
	
	@Override
	public String eval() {
		return value;
	}
}
