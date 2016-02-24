package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.visit.Visitable;
import org.uva.sea.ql.ast.visit.Visitor;

public class GEq extends BinaryExpr implements Visitable {
	
	public GEq(Expr lhs, Expr rhs) {
		super.lhs = lhs;
		super.rhs = rhs;
	}
	
	@Override
	public Boolean eval() {
		return (Integer) lhs.eval() >= (Integer) rhs.eval();
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this, null);
	}

	@Override
	public String toString() {
		return super.toString() + " (>=)";
	}
	
	@Override
	public Type getType(Context context) {
		return Type.BOOLEAN;
	}
}
