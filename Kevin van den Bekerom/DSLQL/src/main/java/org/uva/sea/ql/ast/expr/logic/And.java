package org.uva.sea.ql.ast.expr.logic;

import org.uva.sea.ql.ast.expr.BinaryExpr;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.ast.visit.Visitor;
import org.uva.sea.ql.type.BoolType;
import org.uva.sea.ql.type.Type;
import org.uva.sea.ql.value.BoolValue;
import org.uva.sea.ql.value.Value;

public class And extends BinaryExpr {

	public And(final Expr lhs, final Expr rhs, int startLine) {
		super(lhs, rhs, startLine);
	}

	@Override
	public Value eval(ValueMap valueMap) {
		return new BoolValue((Boolean) lhs.eval(valueMap).getValue() 
							&& (Boolean) rhs.eval(valueMap).getValue());
	}

	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	@Override
	public final String toString() {
		return super.toString() + " (&&)";
	}

	@Override
	public final Type getType(Context context) {
		return new BoolType();
	}
}
