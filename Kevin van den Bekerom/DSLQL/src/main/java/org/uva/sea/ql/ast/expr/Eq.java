package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;
import org.uva.sea.ql.ast.form.Context;

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
	
	public void accept(Visitor visitor) {
		visitor.visit(this, null);
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
