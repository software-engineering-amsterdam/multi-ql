package nl.nicasso.ql.symbolTable;

import nl.nicasso.ql.ast.types.Type;
import nl.nicasso.ql.values.Value;

public class SymbolTableEntry {

	private Type type;
	private Value value;
	
	public SymbolTableEntry() {
		
	}
	
	public SymbolTableEntry(Type type) {
		super();
		this.type = type;
		this.value = type.getDefaultValue();
	}
	
	public SymbolTableEntry(Type type, Value value) {
		super();
		this.type = type;
		this.value = value;
	}

	public Type getType() {
		return type;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}
	
}