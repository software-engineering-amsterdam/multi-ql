package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.var.Type;

public class Identifier extends Expr{


	private final String name;
	private Type type;

	public Identifier(String name) {
		this.name = name;
	}

	@Override
	public Object result(SymbolTabel table) {
		return table.getValue(name);
	}

	@Override
	public Boolean checkType() {
		// TODO Auto-generated method stub
		return true; // Should return the type according to the symbol table..
	}

	@Override
	public Type getType() {
		
		if(type == null){
			type = Type.BOOLEAN; // This should return from the symbol table.. symbolTable.get(name);
		}
		return type;
	}
}
