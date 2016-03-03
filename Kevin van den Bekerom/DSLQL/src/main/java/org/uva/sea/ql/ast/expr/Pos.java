package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.visit.Visitor;
import org.uva.sea.ql.type.NumericalType;
import org.uva.sea.ql.type.Type;

public class Pos extends UnaryExpr {
	
	public Pos(Expr expr) {
		super.child = expr;
	}
	
	@Override
	public Integer eval() {
		return Math.abs( (Integer) child.eval());
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	@Override
	public String toString() {
		return super.toString() + " (positive)";
	}
	
	@Override
	public Type getType(Context context) {
		return new NumericalType();
	}
}
