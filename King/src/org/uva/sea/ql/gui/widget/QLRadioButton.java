package org.uva.sea.ql.gui.widget;

import javax.swing.JRadioButton;

public class QLRadioButton extends Widget {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7943662144107052056L;
	private JRadioButton qlBtnIdentifier;
	public QLRadioButton(String variable, boolean btnState) {
		qlBtnIdentifier = new JRadioButton("", btnState);
		qlBtnIdentifier.setName(variable);
	}
	
	public void setQlBtnIdentifier(JRadioButton qlBtnIdentifier) {
		this.qlBtnIdentifier = qlBtnIdentifier;
	}

	@Override
	public JRadioButton getQlComponent() {
		return qlBtnIdentifier;
	}

}
