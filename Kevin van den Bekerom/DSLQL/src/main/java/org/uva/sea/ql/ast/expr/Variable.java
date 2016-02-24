package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;
import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.form.Value;

public class Variable extends Expr implements Visitable {
	String identifier;
	Object value;

	public Variable(String identifier) {
		this.identifier = identifier;
		value = null;
	}
	
	public void setValue(Value value) {
		this.value = value;
	}
	
	public String getIdentifier() {
		return this.identifier;
	}

	@Override
	public Object eval() {
		return value;
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	@Override
	public String toString() {
		return value.toString();
	}
	
	@Override
	public Type getType(Context context) {
		return context.getTypeFromQuestion(identifier);
	}
}
