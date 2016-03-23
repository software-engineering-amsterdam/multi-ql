package sc.ql.ui.widget;

import javax.swing.JComponent;

import sc.ql.value.Value;

/**
 * A QLWidget represents the GUI component for an input field, or computed value
 * in the questionnaire.
 */
public interface UIWidget {

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

	public void setStyle(UIWidgetStyle style);

	public UIWidgetStyle getStyle();

	public JComponent getComponent();
}
