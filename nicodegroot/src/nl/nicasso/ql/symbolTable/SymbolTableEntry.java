package nl.nicasso.ql.symbolTable;

import nl.nicasso.ql.ast.types.Type;

public class SymbolTableEntry {

	private Type type;
	
	public SymbolTableEntry(Type type) {
		super();
		this.type = type;
	}

	public Type getType() {
		return type;
	}

}