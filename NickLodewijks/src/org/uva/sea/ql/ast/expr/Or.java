package org.uva.sea.ql.ast.expr;

public class Or extends BooleanExpr {
	private final BooleanExpr lhs;
	private final BooleanExpr rhs;

	public Or(Expr lhs, Expr rhs) {
		this.lhs = (BooleanExpr) lhs;
		this.rhs = (BooleanExpr) rhs;
	}

	@Override
	public Boolean interpret(Context context) {
		return lhs.interpret(context) || rhs.interpret(context);
	}
}
