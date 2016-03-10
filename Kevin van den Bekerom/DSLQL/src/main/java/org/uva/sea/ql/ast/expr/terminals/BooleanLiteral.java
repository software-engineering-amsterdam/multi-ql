package org.uva.sea.ql.ast.expr.terminals;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.ast.visit.Visitor;
import org.uva.sea.ql.type.BoolType;
import org.uva.sea.ql.type.Type;
import org.uva.sea.ql.value.BoolValue;
import org.uva.sea.ql.value.Value;

public class BooleanLiteral extends Expr {
	final boolean value;
	
	public BooleanLiteral(boolean value) {
		super(-1);
		this.value = value;
	}
	
	@Override
	public Value eval(ValueMap valueMap) {
		return new BoolValue(value);
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
