package nl.nicasso.ql.gui.panels;

import java.awt.Font;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.statements.Question;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTable;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.questionFields.QuestionField;

public abstract class Panel {

	// PROTECTED
	protected JPanel panel;
	protected StateTable stateTable;
	protected List<Expression> conditions;
	protected QuestionField field;

	// REQUIRED?
	public Panel() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	}

	protected void addQuestionLabel(Question q) {
		JLabel questionLabel = new JLabel(q.getLabel());
		questionLabel.setFont(new Font("Arial", 0, 12));
		panel.add(questionLabel);
	}

	public void setVisible(boolean visible) {
		panel.setVisible(visible);
		panel.updateUI();
	}

	public JPanel getPanel() {
		return this.panel;
	}

	protected void addQuestionField(Question q, QuestionField field, Value value) {
		this.field = field;

		field.setValue(value);
		panel.add(field.getField());
	}

	abstract public boolean update();

}