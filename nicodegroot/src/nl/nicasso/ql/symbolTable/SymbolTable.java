package nl.nicasso.ql.symbolTable;

import java.util.HashMap;
import java.util.Map;

import nl.nicasso.ql.ast.expression.Identifier;

public class SymbolTable {

	private Map<Identifier, SymbolTableEntry> symbols;

	public SymbolTable() {
		super();
		this.symbols = new HashMap<Identifier, SymbolTableEntry>();
	}

	public Map<Identifier, SymbolTableEntry> getSymbols() {
		return symbols;
	}
	
	public void addSymbol(Identifier key, SymbolTableEntry value) {
		symbols.put(key, value);
	}
	
	public SymbolTableEntry getEntry(Identifier key) {
		return symbols.get(key);
	}
	
}
