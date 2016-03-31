package org.uva.sea.ql.gui.widget;

import java.awt.Color;
import javax.swing.JLabel;

public class QLQuestionText extends Widget {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5789048913582742026L;
	private JLabel qlQuestionText;

	public QLQuestionText(String text, boolean dublicateText) {
		qlQuestionText = new JLabel();
		if(dublicateText){
			qlQuestionText.setForeground(Color.ORANGE);
			text = text.concat("[[DUPLICATE LABLE]]");
		}
		qlQuestionText.setText(text);
	}

	@Override
	public JLabel getQlComponent() {
		return qlQuestionText;
	}
	
}