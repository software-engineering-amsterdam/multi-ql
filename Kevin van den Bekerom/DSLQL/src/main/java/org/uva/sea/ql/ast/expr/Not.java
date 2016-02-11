package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;

public class Not extends UnaryExpr implements Visitable {
	
	public Not(Expr expr) {
		super.child = expr;
	}
	
	@Override
	public Boolean eval() {
		return ! (Boolean) child.eval();
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
