package org.uva.sea.ql.ast.expr.logic;

import org.uva.sea.ql.ast.expr.BinaryExpr;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.visit.Visitor;
import org.uva.sea.ql.type.BoolType;
import org.uva.sea.ql.type.Type;

public class Or extends BinaryExpr {
	
	public Or(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public Boolean eval() {
		return (Boolean) lhs.eval() || (Boolean) rhs.eval();
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	@Override
	public String toString() {
		return super.toString() + " (||)";
	}
	
	@Override
	public Type getType(Context context) {
		return new BoolType();
	}
}
