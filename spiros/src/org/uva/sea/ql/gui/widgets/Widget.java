package org.uva.sea.ql.gui.widgets;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JLabel;

public abstract class Widget<T> {
	
	private final JComponent component;
	private final JLabel label;
	
	public Widget(JComponent value, String label) {
		this.component = value;
		this.label = new JLabel(label);
	}
	
	public JComponent getComponent() {
		return this.component;
	}
	
	public JLabel getLabel() {
		return this.label;
	}

}