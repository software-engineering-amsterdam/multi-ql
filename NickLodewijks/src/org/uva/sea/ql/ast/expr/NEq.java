package org.uva.sea.ql.ast.expr;

public class NEq extends BooleanExpr {
	private final Expr lhs;
	private final Expr rhs;

	public NEq(Expr lhs, Expr rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	@Override
	public Boolean interpret(Context context) {
		return !lhs.interpret(context).equals(rhs.interpret(context));
	}
}
