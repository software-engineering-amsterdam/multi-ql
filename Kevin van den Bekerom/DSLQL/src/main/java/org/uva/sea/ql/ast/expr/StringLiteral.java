package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.visit.Visitable;
import org.uva.sea.ql.ast.visit.Visitor;

public class StringLiteral extends Expr implements Visitable {
	final String value;
	
	public StringLiteral(String value) {
		this.value = value;
	}
	
	@Override
	public String eval() {
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
		return Type.STRING;
	}
}
