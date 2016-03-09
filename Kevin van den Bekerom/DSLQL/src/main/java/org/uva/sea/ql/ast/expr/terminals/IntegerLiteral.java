package org.uva.sea.ql.ast.expr.terminals;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.visit.Visitor;
import org.uva.sea.ql.type.IntType;
import org.uva.sea.ql.type.Type;

public class IntegerLiteral extends Expr {
	final int value;
	
	public IntegerLiteral(int value) {
		this.value = value;
	}
	
	@Override
	public Integer eval() {
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
		return new IntType();
	}
}
