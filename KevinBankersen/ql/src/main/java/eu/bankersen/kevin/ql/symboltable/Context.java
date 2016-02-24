package eu.bankersen.kevin.ql.symboltable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.esotericsoftware.minlog.Log;

import eu.bankersen.kevin.ql.ast.Type;

public final class Context {
    
    private static HashMap<String, Symbol> symbolTable = new HashMap<>();;
    private static Set<String> errorList = new HashSet<>();
    
    private static Context instance = new Context();

    private Context() { }

    public static Context getInstance() {
       return instance;
    }
    
    public Symbol getSymbol(final String symbol) {
	return symbolTable.get(symbol);
    }
    
    public Boolean checkID(final String name) {
	return symbolTable.containsKey(name);
    }
    
    public Symbol addSymbol(final String symbol, final Type type) {
	return symbolTable.put(symbol, new Symbol(type));
    }
    
    public void updateSymbol(final String name, final Object value) {
	Symbol symbol = symbolTable.get(name);
	symbol.setValue(value);
	symbolTable.put(name, symbol);
    }
    
    public void setVisibility(final String name, final Boolean active) {
	Symbol symbol = symbolTable.get(name);
	symbol.setActive(active);
	symbolTable.put(name, symbol);
    }
    
    public void addError(final String error) {
	Log.debug(error);
	errorList.add(error);
    }
    
    public Set<String> getErrors() {
	return errorList;
	
    }

}