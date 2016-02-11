package org.uva.ql.ast.expr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uva.ql.ast.ValueType;

public class Context {

	private Map<String, Object> valueMap;
	private List<ContextListener> contextListeners;

	public Context() {
		valueMap = new HashMap<String, Object>();
		contextListeners = new ArrayList<ContextListener>();
	}

	public void setValue(String key, Object value) {
		valueMap.put(key, value);

		notifyContextListeners();
	}

	public Object getValue(String key) {
		return valueMap.get(key);
	}

	private void notifyContextListeners() {
		for (ContextListener cl : contextListeners) {
			cl.contextChanged(this);
		}
	}

	public static interface ContextListener {
		public void contextChanged(Context context);
	}

	public void removeContextListener(ContextListener listener) {
		contextListeners.remove(listener);
	}

	public void addContextListener(ContextListener listener) {
		contextListeners.add(listener);
	}
}
