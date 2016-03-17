package sc.ql.eval;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import sc.ql.ast.Expression;
import sc.ql.value.Value;

public class Environment {

	private final Map<String, Value> valueMap;
	private final Map<String, Expression> computedValueMap;
	private final List<ContextListener> contextListeners;

	public Environment() {
		valueMap = new HashMap<String, Value>();
		computedValueMap = new HashMap<String, Expression>();
		contextListeners = new CopyOnWriteArrayList<>();
	}

	public void addComputedValue(String key, Expression computation) {
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
		for (Map.Entry<String, Expression> entry : computedValueMap.entrySet()) {
			Expression computation;
			Value previousValue;
			Value newValue;

			computation = entry.getValue();

			newValue = Evaluator.evaluate(computation, this);
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

		public void contextChanged(Environment context);
	}

	public void removeContextListener(ContextListener listener) {
		contextListeners.remove(listener);
	}

	public void addContextListener(ContextListener listener) {
		contextListeners.add(listener);
	}
}
