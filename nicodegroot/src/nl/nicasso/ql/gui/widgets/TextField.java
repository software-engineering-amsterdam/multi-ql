package nl.nicasso.ql.gui.widgets;

import javax.swing.JTextField;

public class TextField extends Widget {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2330752727699157381L;
	
	JTextField field;

	public TextField() {
		field = new JTextField();
	}
	
	public JTextField getField() {
		return this.field;
	}
	
}
