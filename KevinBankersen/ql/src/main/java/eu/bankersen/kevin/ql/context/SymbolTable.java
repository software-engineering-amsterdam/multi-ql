package eu.bankersen.kevin.ql.context;

import java.util.LinkedHashMap;
import java.util.Map;


import eu.bankersen.kevin.ql.ast.type.Type;
import eu.bankersen.kevin.ql.ast.type.UndifinedType;

public class SymbolTable {

    private final Map<String, Symbol> symbolTable;

    public SymbolTable() {
	symbolTable = new LinkedHashMap<>();
    }

    public Symbol getSymbol(String symbol) {
	return retrieve(symbol);
    }

    public Boolean checkID(String name) {
	return symbolTable.containsKey(name);
    }

    public void addSymbol(Boolean computed, String name, String question, Type type, Object value) {
	symbolTable.put(name, new Symbol(computed, name, question, type, value));
    }
    
    public void addSymbol(Boolean computed, String name, String question, Type type) {
	symbolTable.put(name, new Symbol(computed, name, question, type, null));
    }

    public void updateSymbol(String name, Object value) {
	Symbol symbol = retrieve(name);

	symbol.setValue(value);    

	symbolTable.put(name, symbol);
    }

    public void setVisibility(String name, Boolean active) {
	Symbol symbol = retrieve(name);
	symbol.setActive(active);
	if (!active) {
	    symbol.setValue(null);
	}
	symbolTable.put(name, symbol);
    }  

    public Map<String, Symbol> getEntries() {
	return symbolTable;
    }

    private Symbol retrieve(String name) {
	if (symbolTable.containsKey(name)) {
	    return symbolTable.get(name);
	} else {
	    return new Symbol(null, name, "", new UndifinedType(), null);
	}
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();

	for (Symbol object : symbolTable.values()) {
		sb.append(object.toString()); 
	}
	return sb.toString();
    }

}
