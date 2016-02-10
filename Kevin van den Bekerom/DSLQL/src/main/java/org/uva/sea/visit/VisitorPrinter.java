package org.uva.sea.visit;

import org.uva.sea.utils.Utils;

public class VisitorPrinter extends Visitor {
	
	@Override
	public void visit(Expr expr) {
		if (expr.type == ExprEnum.ADD) {
			expr.eval();
		}
		
		System.out.println("Always print this: " + expr.name);
	}
}
