package org.uva.sea.ql.gui.widgets;

import javax.swing.JCheckBox;

public class CheckBox extends Widget<Object> {

	//private JCheckBox checkBox;
	
//	public CheckBox() {
//		super(new JCheckBox());
//	}
	
	public CheckBox(String label) {
		super(new JCheckBox(), label);
	}

}