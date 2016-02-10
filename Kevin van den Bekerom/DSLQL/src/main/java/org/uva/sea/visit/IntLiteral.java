package org.uva.sea.visit;

public class IntLiteral extends Expr {
	IntLiteral(String name, Object value) {
		super.name = name;
		super.value = value;
		super.type = ExprEnum.INTLITERAL;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	public Integer eval() {
		return 0;
	}
}
