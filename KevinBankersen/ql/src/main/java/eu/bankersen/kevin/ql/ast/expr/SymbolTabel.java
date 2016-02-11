package eu.bankersen.kevin.ql.ast.expr;

import java.util.HashMap;

import eu.bankersen.kevin.ql.ast.var.Variable;

public class SymbolTabel {
	
	private HashMap<String, Variable> table;

	public SymbolTabel() {
		table = new HashMap<String, Variable>();
	}

	public void setValue(String key, Variable value) {
		table.put(key, value);
	}

	public Variable getValue(String key) {
		return table.get(key);
	}
}
