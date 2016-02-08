package org.uva.sea.visit;

public class Add extends Expr {
	Add(Expr lhs, Expr rhs) {
		super.name = "add";
		super.lhs = lhs;
		super.rhs = rhs;
		super.type = ExprEnum.ADD;
	}
	
	public Integer eval() {
		return 0;
	}
}
