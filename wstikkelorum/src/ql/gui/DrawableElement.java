package ql.gui;

import javax.swing.JPanel;

import ql.ast.value.Value;

public interface DrawableElement {
	JPanel getDrawableItem();
	void updateValueLabel(Value newValue);
}
