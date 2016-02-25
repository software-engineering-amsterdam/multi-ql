package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.visit.Visitable;
import org.uva.sea.ql.ast.visit.Visitor;

public class Neg extends UnaryExpr implements Visitable {
	
	public Neg(Expr expr) {
		super.child = expr;
	}
	
	@Override
	public Integer eval() {
		return  (Integer) child.eval() * -1;
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}
	//TODO{Check if this way of computation is correct}

	@Override
	public String toString() {
		return super.toString() + " (negation)";
	}
	
	@Override
	public Type getType(Context context) {
		return Type.INT;
	}
}
