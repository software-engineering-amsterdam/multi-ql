package org.uva.ql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.value.Value;

public class QLContext {

	private final Map<String, Value> valueMap;
	private final Map<String, Expr> computedValueMap;
	private final List<ContextListener> contextListeners;

	public QLContext() {
		valueMap = new HashMap<String, Value>();
		computedValueMap = new HashMap<String, Expr>();
		contextListeners = new ArrayList<ContextListener>();
	}

	public void addComputedValue(String key, Expr computation) {
		computedValueMap.put(key, computation);
	}

	public void setValue(String key, Value newValue) {
		Value previousValue;

		previousValue = valueMap.put(key, newValue);
		if (Objects.equals(previousValue, newValue)) {
			// Nothing changed.
			return;
		}

		runComputations();

		notifyContextListeners();
	}

	private void runComputations() {
		boolean valuesChanged;

		valuesChanged = false;
		for (Map.Entry<String, Expr> entry : computedValueMap.entrySet()) {
			Expr computation;
			Value previousValue;
			Value newValue;

			computation = entry.getValue();

			newValue = QLInterpreter.interpret(computation, this);
			previousValue = valueMap.put(entry.getKey(), newValue);

			// We have to re-run all the computations if some value has changed.
			if (!Objects.equals(newValue, previousValue)) {
				valuesChanged = true;
			}
		}

		if (valuesChanged) {
			runComputations();
		}
	}

	public Value getValue(String key) {
		return valueMap.get(key);
	}

	private void notifyContextListeners() {
		for (ContextListener cl : contextListeners) {
			cl.contextChanged(this);
		}
	}

	@FunctionalInterface
	public static interface ContextListener {

		public void contextChanged(QLContext context);
	}

	public void removeContextListener(ContextListener listener) {
		contextListeners.remove(listener);
	}

	public void addContextListener(ContextListener listener) {
		contextListeners.add(listener);
	}
}
