package org.uva.sea.visit;

public class IntLiteral extends Expr {
	IntLiteral(String name, Object value) {
		super.name = name;
		super.value = value;
	}
	
	public Integer eval() {
		return 0;
	}
}
