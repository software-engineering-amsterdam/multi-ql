package org.uva.sea.ql.ast.expr;

public abstract class UnaryExpr extends Expr {
	Expr child;
	
	public Expr getChild() {
		return child;
	}
}
