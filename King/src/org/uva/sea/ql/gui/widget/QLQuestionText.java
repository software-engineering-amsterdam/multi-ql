package org.uva.sea.ql.gui.widget;

import javax.swing.JLabel;

public class QLQuestionText extends Widget {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5789048913582742026L;
	private JLabel qlQuestionText;
	public QLQuestionText(String text) {
		qlQuestionText = new JLabel(text);
	}
	@Override
	public JLabel getQlComponent() {
		return qlQuestionText;
	}
	
}
