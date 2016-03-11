package sc.ql.ui.widget;

import javax.swing.SwingUtilities;

import sc.ql.QLContext;
import sc.ql.ast.value.Value;
import sc.ql.ui.UIWidget;

public abstract class AbstractWidgetImpl implements UIWidget {

	private final String variableName;
	private final QLContext context;
	private final Value defaultValue;

	public AbstractWidgetImpl(QLContext context, String variableName, Value defaultValue) {
		this.variableName = variableName;
		this.context = context;
		this.defaultValue = defaultValue;

		context.setValue(variableName, defaultValue);
	}

	protected final Value getDefaultValue() {
		return defaultValue;
	}

	@Override
	public final void setValue(Value value) {
		context.setValue(variableName, value);

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