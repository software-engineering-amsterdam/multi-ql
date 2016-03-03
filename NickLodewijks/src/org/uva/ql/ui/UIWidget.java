package org.uva.ql.ui;

import javax.swing.JComponent;

/**
 * A QLWidget represents the GUI component for an input field, or computed value
 * in the questionnaire.
 */
public interface UIWidget extends UIComponent {

	/**
	 * Return the current value of the widget
	 * 
	 * @return the value of the widget.
	 */
	public Object getValue();

	/**
	 * Set the value and return whether the value has changed.
	 * 
	 * @param value
	 *            the value to set
	 * @return {@code true} if the value has changed, {@code false} otherwise.
	 */
	public boolean setValue(Object value);

	public void setVisible(boolean visible);

	public JComponent getComponent();
}
