package org.uva.ql.ast.expr;

import org.uva.ql.ast.ValueType;

public abstract class ArithmeticExpr extends BinaryExpr {

	public ArithmeticExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}

	@Override
	public Integer interpret(Context context) {
		return (Integer) lhs.interpret(context) + (Integer) rhs.interpret(context);
	}

	@Override
	public ValueType type() {
		return ValueType.INTEGER;
	}
}
