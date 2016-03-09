package org.uva.sea.ql.ast.expr.logic;

import org.uva.sea.ql.ast.expr.BinaryExpr;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.visit.Visitor;
import org.uva.sea.ql.type.Type;
import org.uva.sea.ql.type.WildType;

public class Eq extends BinaryExpr {	
	
	public Eq(Expr lhs, Expr rhs) {
		super(lhs, rhs);
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
		return new WildType(); //TODO: find better name!
	}
}
