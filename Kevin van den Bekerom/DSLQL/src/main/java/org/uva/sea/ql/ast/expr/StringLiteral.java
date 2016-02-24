package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;
import org.uva.sea.ql.ast.form.Context;

public class StringLiteral extends Expr implements Visitable {
	final String value;
	
	public StringLiteral(String value) {
		this.value = value;
	}
	
	@Override
	public String eval() {
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
		return Type.STRING;
	}
}
