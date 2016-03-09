package org.uva.sea.ql.ast.expr.terminals;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.visit.Visitor;
import org.uva.sea.ql.type.BoolType;
import org.uva.sea.ql.type.Type;

public class BooleanLiteral extends Expr {
	final boolean value;
	
	public BooleanLiteral(boolean value) {
		this.value = value;
	}
	
	@Override
	public Boolean eval() {
		return value;
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Type getType(Context context) {
		return new BoolType();
	}
}