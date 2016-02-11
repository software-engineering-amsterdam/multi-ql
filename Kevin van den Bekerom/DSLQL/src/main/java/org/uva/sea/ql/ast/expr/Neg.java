package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;

public class Neg extends UnaryExpr implements Visitable {
	
	public Neg(Expr expr) {
		super.child = expr;
	}
	
	@Override
	public Integer eval() {
		return  (Integer) child.eval() * -1;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	//TODO{Check if this way of computation is correct}
}
