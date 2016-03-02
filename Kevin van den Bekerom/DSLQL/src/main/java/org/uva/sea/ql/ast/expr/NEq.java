package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.visit.Visitor;
import org.uva.sea.ql.type.Type;
import org.uva.sea.ql.type.WildType;

public class NEq extends BinaryExpr {
	
	public NEq(Expr lhs, Expr rhs) {
		super.lhs = lhs;
		super.rhs = rhs;
	}
	
	@Override
	public Boolean eval() {
		return !(lhs.eval().equals(rhs.eval()));
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	@Override
	public String toString() {
		return super.toString() + " (!=)";
	}
	
	@Override
	public Type getType(Context context) {
		return new WildType();
	}
}
