package org.uva.sea.ql.gui.widget;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;

import org.uva.sea.ql.gui.QLSelectedQuesionListener;

public class QLRadioButton extends Widget implements ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7943662144107052056L;
	private JRadioButton qlBtnIdentifier;
	private List<QLSelectedQuesionListener> radioBtnListeners = new ArrayList<QLSelectedQuesionListener>();

	public QLRadioButton(String variable, boolean btnState) {
		qlBtnIdentifier = new JRadioButton("Yes", btnState);
		qlBtnIdentifier.setName(variable);
		qlBtnIdentifier.addItemListener(this);
	}

	public void setQlBtnIdentifier(JRadioButton qlBtnIdentifier) {
		this.qlBtnIdentifier = qlBtnIdentifier;
	}

	@Override
	public JRadioButton getQlComponent() {
		return qlBtnIdentifier;
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		int state = event.getStateChange();
		if (state == ItemEvent.SELECTED) {
			fireQLSelectedQuestion(this.qlBtnIdentifier, true);

		} else if (state == ItemEvent.DESELECTED) {
			fireQLSelectedQuestion(this.qlBtnIdentifier, false);
		}
		
	}
	public void addQLSelectedQuesionListener(QLSelectedQuesionListener listener) {
		radioBtnListeners.add(listener);

	}
	
	private void fireQLSelectedQuestion(JRadioButton btn, boolean isSelected) {
		if (!radioBtnListeners.isEmpty()) {
			for (QLSelectedQuesionListener qlSelectedQuesionListener : radioBtnListeners) {
				qlSelectedQuesionListener.QLQuesionSelected(btn, isSelected);
			}
		}
	}


}
