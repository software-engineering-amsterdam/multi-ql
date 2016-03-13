package org.uva.sea.ql.gui.widgets;

import java.awt.Dimension;

import javax.swing.JTextField;

public class TextField extends Widget<Object> {

	//private JTextField textField;
	
//	public TextField() {
//		super(new JTextField());
//	}

	public TextField(String label) {
		//JTextField textField = new JTextField();
		super(new JTextField(),label);
	}

}