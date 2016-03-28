package org.uva.sea.ql.semantic;

import java.util.HashMap;
import java.util.Map;
import org.uva.sea.ql.ast.expr.type.Type;

public class SymbolTable {

	private Map<String, Type> symTable;

	public SymbolTable() {
		symTable = new HashMap<String, Type>();
	}

	public boolean contains(String varName) {
		return symTable.containsKey(varName);
	}

	public void add(String varName, Type type) {
		symTable.put(varName, type);
	}

	public Type lookupType(String varName) {
		return symTable.get(varName);
	}

	public Boolean isEmpty() {
		return symTable.isEmpty();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return symTable.toString();
	}

}
