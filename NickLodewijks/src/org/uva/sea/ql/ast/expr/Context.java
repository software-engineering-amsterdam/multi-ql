package org.uva.sea.ql.ast.expr;

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

	public Boolean getBooleanValue(String key) {
		return (Boolean) getValue(key);
	}

	public String getStringValue(String key) {
		return (String) getValue(key);
	}

	public Integer getIntegerValue(String key) {
		return (Integer) getValue(key);
	}

}
