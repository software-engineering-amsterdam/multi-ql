package org.uva.sea.ql.ast.expr;

public class And extends BooleanExpr {
	private final BooleanExpr lhs;
	private final BooleanExpr rhs;

	public And(Expr lhs, Expr rhs) {
		this.lhs = (BooleanExpr) lhs;
		this.rhs = (BooleanExpr) rhs;
	}

	@Override
	public Boolean interpret(Context context) {
		return lhs.interpret(context) && rhs.interpret(context);
	}
}
