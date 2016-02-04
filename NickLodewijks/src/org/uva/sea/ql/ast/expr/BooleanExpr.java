package org.uva.sea.ql.ast.expr;

public abstract class BooleanExpr extends Expr {

	public abstract Boolean interpret(Context context);

}
