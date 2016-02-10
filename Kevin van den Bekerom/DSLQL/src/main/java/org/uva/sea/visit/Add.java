package org.uva.sea.visit;

public class Add extends Expr implements Visitable {
	Add(Expr lhs, Expr rhs) {
		super.name = "add";
		super.lhs = lhs;
		super.rhs = rhs;
		super.type = ExprEnum.INTLITERAL;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	public Integer eval() {
		return 0;
	}
}
