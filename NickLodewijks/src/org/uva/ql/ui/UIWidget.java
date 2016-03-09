package org.uva.ql.ui;

import javax.swing.JComponent;

import org.uva.ql.QLContext;
import org.uva.ql.ast.value.Value;

/**
 * A QLWidget represents the GUI component for an input field, or computed value
 * in the questionnaire.
 */
public interface UIWidget {

	public void setContext(QLContext context);

	/**
	 * Return the current value of the widget
	 * 
	 * @return the value of the widget.
	 */
	public Value getValue();

	/**
	 * Set the value and return whether the value has changed.
	 * 
	 * @param value
	 *            the value to set
	 * @return {@code true} if the value has changed, {@code false} otherwise.
	 */
	public void setValue(Value value);

	public void setVisible(boolean visible);

	public void setEditable(boolean editable);

	public JComponent getComponent();
}
