package org.uva.ql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uva.ql.ast.Value;

public class UIContext {

	private Map<String, Value> valueMap;
	private List<ContextListener> contextListeners;

	public UIContext() {
		valueMap = new HashMap<String, Value>();
		contextListeners = new ArrayList<ContextListener>();
	}

	public void setValue(String key, Value value) {
		valueMap.put(key, value);

		notifyContextListeners();
	}

	public Value getValue(String key) {
		return valueMap.get(key);
	}

	public void notifyContextListeners() {
		for (ContextListener cl : contextListeners) {
			cl.contextChanged(this);
		}
	}

	@FunctionalInterface
	public static interface ContextListener {

		public void contextChanged(UIContext context);
	}

	public void removeContextListener(ContextListener listener) {
		contextListeners.remove(listener);
	}

	public void addContextListener(ContextListener listener) {
		contextListeners.add(listener);
	}
}
