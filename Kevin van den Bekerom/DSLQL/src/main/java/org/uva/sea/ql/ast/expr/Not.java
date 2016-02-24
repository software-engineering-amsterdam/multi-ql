package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;
import org.uva.sea.ql.ast.form.Context;

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
