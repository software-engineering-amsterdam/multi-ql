package org.uva.sea.ql.gui.widgets;

import javax.swing.JCheckBox;

public class CheckBox extends Widget {

	private JCheckBox checkBox;
	
	public CheckBox() {
		this.checkBox = new JCheckBox();
	}

	public JCheckBox getCheckBox() {
		return checkBox;
	}

}