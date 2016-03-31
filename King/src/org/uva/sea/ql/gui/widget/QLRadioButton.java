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
	
	public String getQlComponentName() {
		return qlBtnIdentifier.getName();
	}
	
	public String getQlComponentText() {
		return qlBtnIdentifier.getText();
	}
	public Boolean getQlComponentState() {
		return qlBtnIdentifier.isSelected();
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		int state = event.getStateChange();
		if (state == ItemEvent.SELECTED) {
			fireQLSelectedQuestion();

		} else if (state == ItemEvent.DESELECTED) {
			fireQLSelectedQuestion();
		}
		
	}
	public void addQLSelectedQuesionListener(QLSelectedQuesionListener listener) {
		radioBtnListeners.add(listener);

	}
	
	private void fireQLSelectedQuestion() {
		if (!radioBtnListeners.isEmpty()) {
			for (QLSelectedQuesionListener qlSelectedQuesionListener : radioBtnListeners) {
				qlSelectedQuesionListener.QLQuesionSelected(this);
			}
		}
	}


}
