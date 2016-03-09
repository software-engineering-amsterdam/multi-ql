package org.uva.sea.ql.ast.expr.logic;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.UnaryExpr;
import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.visit.Visitor;
import org.uva.sea.ql.type.BoolType;
import org.uva.sea.ql.type.Type;

public class Not extends UnaryExpr {
	
	public Not(Expr expr) {
		super(expr);
	}
	
	@Override
	public Boolean eval() {
		return ! (Boolean) child.eval();
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	@Override
	public String toString() {
		return super.toString() + " (!)";
	}
	
	@Override
	public Type getType(Context context) {
		return new BoolType();
	}
}
