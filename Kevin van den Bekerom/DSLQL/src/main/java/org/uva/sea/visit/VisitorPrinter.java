package org.uva.sea.visit;

import org.uva.sea.utils.Utils;

public class VisitorPrinter extends Visitor {
	
	@Override
	public void visit(Expr expr) {
		if (expr.equals(Add.class)) {
			System.out.println(expr.name);
		}
		
		System.out.println("Always print this: " + expr.name);
	}
}
