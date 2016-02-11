package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;

public class VariableLiteral extends Expr implements Visitable {
	String identifier;
	Object value;
	
	public VariableLiteral(String identifier) {
		this.identifier = identifier;
		this.value = null;
	}

	@Override
	Object eval() throws ClassCastException {
		return value;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
