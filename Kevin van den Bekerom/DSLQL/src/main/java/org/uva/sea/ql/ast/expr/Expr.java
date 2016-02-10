package org.uva.sea.ql.ast.expr;

public abstract class Expr {
	Expr lhs, rhs;
	abstract Object eval() throws ClassCastException;
	//TODO: Implement try-catch blocks for all methods?!
}
