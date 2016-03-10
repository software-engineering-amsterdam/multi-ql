package org.uva.sea.ql.ast.expr.terminals;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.ast.visit.Visitor;
import org.uva.sea.ql.type.Type;
import org.uva.sea.ql.value.Value;

public class Variable extends Expr {
	final String identifier;

	public Variable(String identifier, int startLine) {
		super(startLine);
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	@Override
	public Value eval(ValueMap valueMap) {
		return valueMap.getValueFromMap(identifier);
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	@Override
	public String toString() {
		return "TODO!!!";
	}
	
	@Override
	public Type getType(Context context) {
		return context.getTypeFromQuestion(identifier);
	}
}
