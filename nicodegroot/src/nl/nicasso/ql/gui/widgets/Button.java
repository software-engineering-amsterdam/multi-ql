package nl.nicasso.ql.gui.widgets;

import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.JButton;

public class Button extends Widget {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1138812327448304211L;
	
	JButton button;
	
	public Button(String label) {
		button = new JButton(label);
		
		button.setVerticalTextPosition(AbstractButton.CENTER);
		button.setHorizontalTextPosition(AbstractButton.CENTER);
	}
	
	public Component getWidget() {
		return this.button;
	}
	
}
