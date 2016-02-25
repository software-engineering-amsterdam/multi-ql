package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.visit.Visitable;
import org.uva.sea.ql.ast.visit.Visitor;

public class Eq extends BinaryExpr implements Visitable {	
	
	public Eq(Expr lhs, Expr rhs) {
		super.lhs = lhs;
		super.rhs = rhs;
	}
	
	//TODO: different check for Strings and booleans!!! Need to know the type
	@Override
	public Boolean eval() {
		return lhs.eval().equals(rhs.eval());
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	@Override
	public String toString() {
		return super.toString() + " (==)";
	}
	
	@Override
	public Type getType(Context context) {
		return Type.BOOLEAN;
	}
}
