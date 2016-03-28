package ql.issue;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ql.gui.UIElement;

public abstract class Issue implements UIElement{
	protected String errorMessage;

	public void print() {
		System.out.println(errorMessage);
	}
	
	JPanel getUIElement() {
		JPanel jPanel = new JPanel();
		jPanel.add(new JLabel(errorMessage));
		jPanel.setVisible(true);
		return jPanel;
	}
	
	public JPanel getDrawableItem() {
		JPanel jPanel = new JPanel();
		JLabel jLabel = new JLabel(errorMessage);
		jPanel.add(jLabel);
		jPanel.setVisible(true);
		return jPanel;
	}

	@Override
	public void updateValueLabel(Object newValue) {
		errorMessage = newValue.toString();
	}
}
