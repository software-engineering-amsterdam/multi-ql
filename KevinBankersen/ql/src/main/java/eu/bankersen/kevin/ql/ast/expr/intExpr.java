package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.var.Type;

public abstract class intExpr extends Expr { 
	
	protected intExpr lhs;
	protected intExpr rhs;
	private final Type type = Type.INTEGER;
	
	public abstract Integer result(SymbolTabel table);
	
	@Override
	public Boolean checkType() {
		return lhs.getType() == type && rhs.getType() == type;
	}
	
	@Override
	public Type getType(){
		return type;
	}
	
}
