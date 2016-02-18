package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;
import org.uva.sea.ql.ast.form.Context;

public class Add extends BinaryExpr implements Visitable {
	
	public Add(Expr lhs, Expr rhs) {
		super.lhs = lhs;
		super.rhs = rhs;
	}
	
	@Override
	public Integer eval() {
		return (Integer) lhs.eval() + (Integer) rhs.eval();
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this, null);
	}

	@Override
	public String toString() {
		return super.toString() + " (+)";
	}

	@Override
	public Type getType(Context context) {
		return Type.INT;
	}
}
