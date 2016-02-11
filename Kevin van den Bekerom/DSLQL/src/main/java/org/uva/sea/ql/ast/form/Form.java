package org.uva.sea.ql.ast.form;

import java.util.HashMap;
import java.util.Map;

import org.uva.sea.ql.ast.stat.*;

public class Form {
	private String name;
	private Block mainBlock;
	private Map<String, Value> variableValueMap;//name??
	
	public Form(String name, Block block) {
		this.name = name;
		this.mainBlock = block;
		variableValueMap = new HashMap<String, Value>();
	}

	public String getName() {
		return name;
	}
	
	public Value getVariableValueMap(String key) {
		return variableValueMap.get(key);
	}
	
}
