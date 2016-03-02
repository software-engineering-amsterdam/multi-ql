package org.uva.ql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QLInterpreterContext {

	private Map<String, Object> valueMap;
	private List<ContextListener> contextListeners;

	public QLInterpreterContext() {
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

	public void notifyContextListeners() {
		for (ContextListener cl : contextListeners) {
			cl.contextChanged(this);
		}
	}

	@FunctionalInterface
	public static interface ContextListener {

		public void contextChanged(QLInterpreterContext context);
	}

	public void removeContextListener(ContextListener listener) {
		contextListeners.remove(listener);
	}

	public void addContextListener(ContextListener listener) {
		contextListeners.add(listener);
	}
}
