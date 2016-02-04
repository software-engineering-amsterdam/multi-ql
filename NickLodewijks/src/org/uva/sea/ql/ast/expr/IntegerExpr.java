package org.uva.sea.ql.ast.expr;

public abstract class IntegerExpr extends Expr {

	public abstract Integer interpret(Context context);

}
