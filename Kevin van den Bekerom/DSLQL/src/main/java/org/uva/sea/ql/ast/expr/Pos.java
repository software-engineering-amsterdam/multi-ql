package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;
import org.uva.sea.ql.ast.form.Context;

public class Pos extends UnaryExpr implements Visitable {
	
	public Pos(Expr expr) {
		super.child = expr;
	}
	
	@Override
	public Integer eval() {
		return Math.abs( (Integer) child.eval());
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this, null);
	}

	@Override
	public String toString() {
		return super.toString() + " (positive)";
	}
	
	@Override
	public Type getType(Context context) {
		return Type.INT;
	}
}
