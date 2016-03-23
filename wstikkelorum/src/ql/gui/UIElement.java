package ql.gui;

import javax.swing.JPanel;

public interface UIElement {
	JPanel getDrawableItem();
	void updateValueLabel(Object newValue);
}
