package nl.nicasso.ql.symbolTable;

import java.util.HashMap;

import nl.nicasso.ql.ast.expression.Identifier;

public class SymbolTable {

	private HashMap<Identifier, SymbolTableEntry> symbols;

	public SymbolTable() {
		super();
		this.symbols = new HashMap<Identifier, SymbolTableEntry>();
	}

	public HashMap<Identifier, SymbolTableEntry> getSymbols() {
		return symbols;
	}
	
	public void addSymbol(Identifier key, SymbolTableEntry value) {
		symbols.put(key, value);
	}
	
	public SymbolTableEntry getEntry(Identifier key) {
		return symbols.get(key);
	}
	
	/*
	public Literal getSymbolValueFromIdentifier(Identifier key) {
		Iterator<Entry<Question, Literal>> it = symbols.entrySet().iterator();
	    while (it.hasNext()) {
	    	Entry<Question, Literal> pair = it.next();
	        Question qKey = (Question) pair.getKey();
	        if (qKey.getId().getValue().equals(key.getValue())) {
	        	return (Literal) pair.getValue();
	        }
	    }
	    return null;
	}
	
	private Literal createLiteralWithDefaultValue(Question question) {
		Literal lit;
		
		switch(question.getType().getType()) {
			case "Boolean":
				lit = new BooleanLit(false);
				break;
			case "Integer":
				lit = new IntegerLit(0);      			
				break;
			case "Money":
				lit = new IntegerLit(0);
				break;
			case "String":
				lit = new StringLit("");
				break;
			default:
				lit = new BooleanLit(false);
				// Throw exception here!
				break;
		}
		return lit;
	}
	*/
}
