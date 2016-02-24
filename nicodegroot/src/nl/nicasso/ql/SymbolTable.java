package nl.nicasso.ql;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nl.nicasso.ql.ast.literal.BooleanLit;
import nl.nicasso.ql.ast.literal.IdentifierLit;
import nl.nicasso.ql.ast.literal.IntegerLit;
import nl.nicasso.ql.ast.literal.Literal;
import nl.nicasso.ql.ast.literal.StringLit;
import nl.nicasso.ql.ast.statement.Question;

public class SymbolTable {

	private HashMap<Question, Literal> symbols;

	public SymbolTable() {
		super();
		this.symbols = new HashMap<Question, Literal>();
	}

	public HashMap<Question, Literal> getSymbols() {
		return symbols;
	}
	
	public void addSymbol(Question key, Literal value) {
		if (value == null) {
			value = createLiteralWithDefaultValue(key);
		}
		symbols.put(key, value);
	}
	
	public Literal getSymbolValue(Question key) {
		return symbols.get(key);
	}
	
	public Literal getSymbolValueFromIdentifier(IdentifierLit key) {
		Iterator it = symbols.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
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
	
}
