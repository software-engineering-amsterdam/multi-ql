package ql.issue;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ql.ast.value.Value;
import ql.gui.DrawableElement;

public abstract class Issue implements DrawableElement{
	protected String errorMessage;

	public void print() {
		System.out.println(errorMessage);
	}
	
	public JPanel getDrawableItem() {
		JPanel jPanel = new JPanel();
		JLabel jLabel = new JLabel(errorMessage);
		jPanel.add(jLabel);
		jPanel.setVisible(true);
		return jPanel;
	}

	public void updateValueLabel(Value newValue) {
		errorMessage = newValue.toString();
	}
	
	public abstract boolean isProblem();
	
	public abstract boolean isWarning();
}
