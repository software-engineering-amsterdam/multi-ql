package org.uva.ql.ui.widget;

import javax.swing.SwingUtilities;

import org.uva.ql.QLContext;
import org.uva.ql.ast.value.Value;
import org.uva.ql.ui.UIWidget;

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