package org.uva.ql.ui;

import javax.swing.JComponent;

import org.uva.ql.QLContext;
import org.uva.ql.ast.Value;

/**
 * A QLWidget represents the GUI component for an input field, or computed value
 * in the questionnaire.
 */
public interface UIWidget<T extends Value> {

	public void setContext(QLContext context);

	/**
	 * Return the current value of the widget
	 * 
	 * @return the value of the widget.
	 */
	public T getValue();

	/**
	 * Set the value and return whether the value has changed.
	 * 
	 * @param value
	 *            the value to set
	 * @return {@code true} if the value has changed, {@code false} otherwise.
	 */
	public boolean setValue(T value);

	public void setVisible(boolean visible);

	public JComponent getComponent();
}
