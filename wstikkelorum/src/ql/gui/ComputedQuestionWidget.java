package ql.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ql.ast.statement.ComputedQuestion;

public class ComputedQuestionWidget extends UIElement {
	private JLabel valueLabel;

	public ComputedQuestionWidget(ComputedQuestion computedQuestion, String value) {
		super(new JLabel(computedQuestion.getVariable().getIdentifier()), new JLabel(computedQuestion.getQuestionString()));

		if (value == null) {
			valueLabel = new JLabel("No value");
		} else {
			valueLabel = new JLabel(value);
		}
	}

	@Override
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		// panel.add(this.getVariableLabel());
		panel.add(this.getQuestionStringLabel());
		panel.add(valueLabel);
		panel.setVisible(true);
		return panel;
	}
}
