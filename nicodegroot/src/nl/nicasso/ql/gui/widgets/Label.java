package nl.nicasso.ql.gui.widgets;

import java.awt.Color;
import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.JLabel;

public class Label extends Widget {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4319574145660725127L;
	
	JLabel label;
	
	public Label(String labelText) {
		label = new JLabel(labelText);
		
		label.setVerticalTextPosition(AbstractButton.CENTER);
		label.setHorizontalTextPosition(AbstractButton.CENTER);
	}
	
	public void setLabelText(String labelText) {
		label.setText(labelText);
	}
	
	public void setLabelColor(Color color) {
		label.setForeground(color);
	}
	
	public Component getWidget() {
		return this.label;
	}
	
}
