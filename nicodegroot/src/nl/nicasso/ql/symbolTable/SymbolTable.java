package nl.nicasso.ql.symbolTable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.ast.types.Type;
import nl.nicasso.ql.values.Value;

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
	
	public Value getEntryValue(Identifier key) {
		SymbolTableEntry entry = symbols.get(key);
		return entry.getValue();
	}
	
	public Type getEntryType(Identifier key) {
		SymbolTableEntry entry = symbols.get(key);
		return entry.getType();
	}
	
	public void displaySymbolTable(SymbolTable symbolTable) {
		Iterator<Entry<Identifier, SymbolTableEntry>> it = symbolTable.getSymbols().entrySet().iterator();
	    while (it.hasNext()) {
	    	Entry<Identifier, SymbolTableEntry> pair = it.next();
	    	Identifier key = (Identifier) pair.getKey();
	        SymbolTableEntry value = (SymbolTableEntry) pair.getValue();
	        
	        String realValue;
	        if (value.getValue() == null) {
	        	realValue = "undefined";
	        } else {
	        	realValue = value.getValue().getValue().toString();
	        }
	        
	        System.out.println(key.getValue()+" ("+ value.getType().getType() +")"+ " = " + realValue);
	    }
	}
	
}
