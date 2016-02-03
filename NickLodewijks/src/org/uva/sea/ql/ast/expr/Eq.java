package org.uva.sea.ql.ast.expr;

public class Eq extends BooleanExpr {
	private final Expr lhs;
	private final Expr rhs;

	public Eq(Expr lhs, Expr rhs) {
		this.lhs = (IntegerExpr) lhs;
		this.rhs = (IntegerExpr) rhs;
	}

	@Override
	public Boolean interpret(Context context) {
		return lhs.interpret(context).equals(rhs.interpret(context));
	}
}
