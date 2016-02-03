package org.uva.sea.ql.ast.expr;

public class Sub extends IntegerExpr {
	private final IntegerExpr lhs;
	private final IntegerExpr rhs;

	public Sub(Expr lhs, Expr rhs) {
		this.lhs = (IntegerExpr) lhs;
		this.rhs = (IntegerExpr) rhs;
	}

	@Override
	public Integer interpret(Context context) {
		return lhs.interpret(context) - rhs.interpret(context);
	}
}
