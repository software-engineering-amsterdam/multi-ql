package org.uva.sea.ql.ast.expr.terminals;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.form.Value;
import org.uva.sea.ql.ast.visit.Visitor;
import org.uva.sea.ql.type.Type;

public class Variable extends Expr {
	String identifier;

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
		if (value == null) {
			return "";
		} else {
			return value.toString();
		}
	}
	
	@Override
	public Type getType(Context context) {
		return context.getTypeFromQuestion(identifier);
	}
}
