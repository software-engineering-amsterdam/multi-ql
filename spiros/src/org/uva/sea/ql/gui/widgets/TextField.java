package org.uva.sea.ql.gui.widgets;

import javax.swing.JTextField;

public class TextField extends Widget {

	private JTextField textField;
	
	public TextField() {
		this.textField = new JTextField();
	}

	public JTextField getTextField() {
		return textField;
	}

}