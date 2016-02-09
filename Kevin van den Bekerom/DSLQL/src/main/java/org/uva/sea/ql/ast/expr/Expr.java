package org.uva.sea.ql.ast.expr;

public abstract class Expr {
	
	abstract Object eval() throws ClassCastException;
	//TODO: Implement try-catch blocks for all methods?!
}
