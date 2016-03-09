package org.uva.sea.ql.ast.expr.math;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.UnaryExpr;
import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.visit.Visitor;
import org.uva.sea.ql.type.NumericalType;
import org.uva.sea.ql.type.Type;

public class Neg extends UnaryExpr {
	
	public Neg(Expr expr) {
		super(expr);
	}
	
	@Override
	public Integer eval() {
		return  (Integer) child.eval() * -1;
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}
	//TODO{Check if this way of computation is correct}

	@Override
	public String toString() {
		return super.toString() + " (negation)";
	}
	
	@Override
	public Type getType(Context context) {
		return new NumericalType(); 
	}
}
