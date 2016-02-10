package org.uva.ql.ast.expr;

import java.util.HashMap;
import java.util.Map;

public class Context {
	
	private Map<String, Object> valueMap;

	public Context() {
		valueMap = new HashMap<String, Object>();
	}

	public void setValue(String key, Object value) {
		valueMap.put(key, value);
	}

	public Object getValue(String key) {
		return valueMap.get(key);
	}
}
