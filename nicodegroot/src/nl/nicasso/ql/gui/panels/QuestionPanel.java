package nl.nicasso.ql.gui.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.statements.Question;
import nl.nicasso.ql.gui.Observer;
import nl.nicasso.ql.gui.evaluator.Evaluator;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTable;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.questionFields.QuestionField;

public class QuestionPanel extends Panel {

	private Question question;
	private Observer main;
	private QuestionField field;

	public QuestionPanel(Question question, QuestionField field, Stack<Expression> conditions, StateTable stateTable,
			Observer main) {
		panel = new JPanel(new GridLayout(2, 2));
		JLabel feedback = new JLabel("");
		this.question = question;
		this.stateTable = stateTable;
		this.main = main;
		this.field = field;

		this.conditions = new ArrayList<Expression>();

		Iterator<Expression> it = conditions.iterator();
		while (it.hasNext()) {
			this.conditions.add(it.next());
		}

		field.setFeedbackField(feedback);

		addQuestionLabel(question);
		panel.add(new JPanel());
		addQuestionField(question, field, question.getType().getDefaultValue());
		addLabelForFeedback(feedback);
	}

	public void addLabelForFeedback(JLabel feedback) {
		feedback.setFont(new Font("Arial", 0, 12));
		feedback.setForeground(Color.RED);
		panel.add(feedback);
	}

	public boolean update() {
		boolean updated = false;
		Evaluator evaluator = new Evaluator();

		boolean visible = true;
		Iterator<Expression> it = conditions.iterator();
		while (it.hasNext()) {
			Value visibility = it.next().accept(evaluator, stateTable);
			if (!((Boolean) visibility.getValue())) {
				visible = false;
				break;
			}
		}

		if (panel.isVisible() == false && visible == true) {
			main.updateValueInStateTable(question.getIdentifier(), field.getValue());
			updated = true;
		}

		setVisible(visible);

		return updated;
	}

}