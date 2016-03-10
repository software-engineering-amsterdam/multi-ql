package ql.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class UIElement {
	private final JLabel variableIdentifierLabel;
	private final JLabel questionStringLabel;

	public UIElement(JLabel variableIdentifierLabel, JLabel questionStringLabel) {
		this.variableIdentifierLabel = variableIdentifierLabel;
		this.questionStringLabel = questionStringLabel;
	}

	public JLabel getVariableIdentifierLabel() {
		return variableIdentifierLabel;
	}

	public JLabel getQuestionStringLabel() {
		return questionStringLabel;
	}

	public abstract JPanel getPanel();
}
