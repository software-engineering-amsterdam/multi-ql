package nl.nicasso.ql.gui.widgets;

import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.JLabel;

public class Label extends Widget {

	JLabel label;
	
	public Label(String labelText) {
		label = new JLabel(labelText);
		
		label.setVerticalTextPosition(AbstractButton.CENTER);
		label.setHorizontalTextPosition(AbstractButton.CENTER);
	}
	
	public Component getWidget() {
		return this.label;
	}
	
}
