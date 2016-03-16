package sc.ql.ui.widget;

import javax.swing.SwingUtilities;

import sc.ql.eval.Environment;
import sc.ql.ui.UIWidget;
import sc.ql.value.Value;

public abstract class AbstractUIWidget implements UIWidget {

	private final String variableName;
	private final Environment env;
	private final Value defaultValue;

	public AbstractUIWidget(Environment env, String variableName, Value defaultValue) {
		this.variableName = variableName;
		this.env = env;
		this.defaultValue = defaultValue;

		env.setValue(variableName, defaultValue);
	}

	protected final Value getDefaultValue() {
		return defaultValue;
	}

	@Override
	public final void setValue(Value value) {
		env.setValue(variableName, value);

		SwingUtilities.invokeLater(() -> {
			if (getViewValue().equals(value)) {
				return;
			}

			setViewValue(value);
		});
	}

	@Override
	public final Value getValue() {
		return getViewValue();
	}

	protected abstract Value getViewValue();

	protected abstract void setViewValue(Value value);
}