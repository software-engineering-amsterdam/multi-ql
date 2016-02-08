package org.uva.sea.visit;

public class Add extends Expr {
	Add(Expr lhs, Expr rhs) {
		super.name = "add";
		super.lhs = lhs;
		super.rhs = rhs;
	}
	
}
