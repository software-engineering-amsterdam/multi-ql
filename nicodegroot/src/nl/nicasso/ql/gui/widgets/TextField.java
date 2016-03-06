package nl.nicasso.ql.gui.widgets;

import javax.swing.JTextField;

public class TextField extends Widget {

	JTextField field;

	public TextField() {
		field = new JTextField();
	}
	
	public JTextField getField() {
		return this.field;
	}
	
}
