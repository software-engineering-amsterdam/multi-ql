package org.uva.sea.ql.ast.expr;

public class GEq extends BooleanExpr {
	private final IntegerExpr lhs;
	private final IntegerExpr rhs;

	public GEq(Expr lhs, Expr rhs) {
		this.lhs = (IntegerExpr) lhs;
		this.rhs = (IntegerExpr) rhs;
	}

	@Override
	public Boolean interpret(Context context) {
		return lhs.interpret(context) >= rhs.interpret(context);
	}
}
