package nl.nicasso.ql.symbolTable;

import nl.nicasso.ql.ast.type.Type;

public class SymbolTableEntry {

	private Type type;
	private Object value;
	
	public SymbolTableEntry() {
		
	}
	
	public SymbolTableEntry(Type type) {
		super();
		this.type = type;
	}
	
	public SymbolTableEntry(Type type, Object value) {
		super();
		this.type = type;
		this.value = value;
	}

	public Type getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
	
}