package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.visit.Visitable;
import org.uva.sea.ql.ast.visit.Visitor;

public class IntegerLiteral extends Expr implements Visitable {
	final int value;
	
	public IntegerLiteral(int value) {
		this.value = value;
	}
	
	@Override
	public Integer eval() {
		return value;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this, null);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Type getType(Context context) {
		return Type.INT;
	}
}
