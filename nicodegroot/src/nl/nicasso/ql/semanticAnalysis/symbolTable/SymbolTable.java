package nl.nicasso.ql.semanticAnalysis.symbolTable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.ast.nodes.types.Type;

public class SymbolTable {

	private Map<Identifier, SymbolTableEntry> symbols;

	public SymbolTable() {
		this.symbols = new HashMap<Identifier, SymbolTableEntry>();
	}

	public Iterator<Entry<Identifier, SymbolTableEntry>> getIterator() {
		return symbols.entrySet().iterator();
	}
	
	public void addSymbol(Identifier key, SymbolTableEntry value) {
		symbols.put(key, value);
	}
	
	public Type getEntryType(Identifier key) {
		SymbolTableEntry entry = symbols.get(key);
		return entry.getType();
	}
	
}
