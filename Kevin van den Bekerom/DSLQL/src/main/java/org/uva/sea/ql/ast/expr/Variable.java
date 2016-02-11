package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;
import org.uva.sea.ql.ast.form.Value;

public class Variable extends Expr implements Visitable {
	String identifier;
	Value value;

	public Variable(String identifier) {
		this.identifier = identifier;
	}
	
	public void setValue(Value value) {
		this.value = value;
	}
	
	public String getIdentifier() {
		return this.identifier;
	}

	@Override
	Object eval() throws ClassCastException {
		return value;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
