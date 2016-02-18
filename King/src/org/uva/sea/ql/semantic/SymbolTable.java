package org.uva.sea.ql.semantic;

import java.util.HashMap;
import java.util.Map;

import org.uva.sea.ql.ast.ValueType;

public class SymbolTable {
	
	private Map<String, ValueType> symTable = new HashMap<>();

	public SymbolTable() {
		symTable = new HashMap<>();
	}

	public boolean contains(String name) {
		return symTable.containsKey(name);
	}

	public void add(String name, ValueType type) {
		symTable.put(name, type);
	}

	public ValueType lookupType(String name) {
		return symTable.get(name);
	}

}


