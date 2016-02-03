package org.uva.sea.ql.ast.expr;

public class Not extends Expr {
	private final BooleanExpr expr;

	public Not(Expr expr) {
		this.expr = (BooleanExpr) expr;
	}

	@Override
	public Boolean interpret(Context context) {
		return !expr.interpret(context);
	}
}
