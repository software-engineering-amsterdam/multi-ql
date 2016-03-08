package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.visit.Visitor;
import org.uva.sea.ql.type.*;

public class Add extends BinaryExpr {
	
	public Add(Expr lhs, Expr rhs) {
		super.lhs = lhs;
		super.rhs = rhs;
	}
	
	@Override
	public Integer eval() {
		return (Integer) lhs.eval() + (Integer) rhs.eval();
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	@Override
	public String toString() {
		return super.toString() + " (+)";
	}

	@Override
	public Type getType(Context context) {
		return new NumericalType();
	}
}
