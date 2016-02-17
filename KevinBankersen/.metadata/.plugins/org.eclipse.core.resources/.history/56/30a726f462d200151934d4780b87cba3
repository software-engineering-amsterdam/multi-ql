package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.var.Type;

public abstract class Expr {
	
	public abstract Object result(SymbolTabel table);
	
	protected Expr lhs;
	protected Expr rhs;
	
	public abstract Boolean checkType(SymbolTabel table);
	
	public abstract Type getType();
	
}