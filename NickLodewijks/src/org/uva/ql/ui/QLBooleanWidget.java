package org.uva.ql.ui;

public interface QLBooleanWidget extends QLComponent {

	/**
	 * Return the current value of the widget
	 * 
	 * @return the value of the widget.
	 */
	public boolean getValue();

	/**
	 * Set the value and return whether the value was changed..
	 * 
	 * @param value
	 *            the value to set
	 * @return {@code true} if the value has changed, {@code false} otherwise.
	 */
	public boolean setValue(boolean value);

}
