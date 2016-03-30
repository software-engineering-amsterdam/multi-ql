package ql.gui;

import javax.swing.JPanel;

public interface DrawableElement {
	JPanel getDrawableItem();
	void updateValueLabel(Object newValue);
}
