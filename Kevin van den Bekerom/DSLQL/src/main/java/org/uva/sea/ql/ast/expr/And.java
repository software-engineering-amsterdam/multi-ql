package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;
import org.uva.sea.ql.ast.form.Context;

public class And extends BinaryExpr implements Visitable{
	
	public And(Expr lhs, Expr rhs) {
		super.lhs = lhs;
		super.rhs = rhs;
	}
	
	@Override
	public Boolean eval() {
		return (Boolean) lhs.eval() && (Boolean) rhs.eval();
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this, null);
	}
	
	@Override
	public String toString() {
		return super.toString() + " (&&)";
	}

	@Override
	public Type getType(Context context) {
		return Type.BOOLEAN;
	}
}
