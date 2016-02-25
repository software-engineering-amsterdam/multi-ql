package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.visit.Visitable;
import org.uva.sea.ql.ast.visit.Visitor;

public class Not extends UnaryExpr implements Visitable {
	
	public Not(Expr expr) {
		super.child = expr;
	}
	
	@Override
	public Boolean eval() {
		return ! (Boolean) child.eval();
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this, null);
	}

	@Override
	public String toString() {
		return super.toString() + " (!)";
	}
	
	@Override
	public Type getType(Context context) {
		return Type.BOOLEAN;
	}
}
