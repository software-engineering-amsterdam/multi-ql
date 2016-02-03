package org.uva.sea.ql.ast.expr;

public class Pos extends IntegerExpr {
	private final IntegerExpr expr;

	public Pos(Expr expr) {
		this.expr = (IntegerExpr) expr;
	}

	@Override
	public Integer interpret(Context context) {
		return Math.abs(expr.interpret(context));
	}
}
