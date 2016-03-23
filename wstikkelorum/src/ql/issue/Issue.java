package ql.issue;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class Issue {
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
}
