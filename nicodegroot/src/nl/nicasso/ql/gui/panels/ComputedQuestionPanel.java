package nl.nicasso.ql.gui.panels;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JPanel;

import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.statements.ComputedQuestion;
import nl.nicasso.ql.gui.Observer;
import nl.nicasso.ql.gui.evaluator.Evaluator;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTable;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.questionFields.QuestionField;

public class ComputedQuestionPanel extends Panel {

	private ComputedQuestion question;
	private Observer main;

	public ComputedQuestionPanel(ComputedQuestion question, QuestionField field, Value value,
			Stack<Expression> conditions, StateTable stateTable, Observer main) {
		this.main = main;
		this.stateTable = stateTable;
		this.question = question;

		this.conditions = new ArrayList<>();

		Iterator<Expression> it = conditions.iterator();
		while (it.hasNext()) {
			this.conditions.add(it.next());
		}

		panel = new JPanel(new GridLayout(2, 2));

		addQuestionLabel(question);
		addQuestionField(question, field, value);
	}

	public boolean update() {
		boolean updated = false;

		Evaluator evaluator = new Evaluator();

		System.out.println("COMPU QUESTION PANEL UPDATE:: " + question.getLabel());

		System.out.println("COMPU QU PANEL UPDATE SIZE: " + conditions.size());

		boolean visible = true;
		Iterator<Expression> it = conditions.iterator();
		while (it.hasNext()) {
			Value visibility = it.next().accept(evaluator, stateTable);
			System.out.println("VISI: " + visibility.getValue().toString());
			if (!((Boolean) visibility.getValue())) {
				visible = false;
				break;
			}
		}

		setVisible(visible);

		if (visible == true) {
			Value questionValue = question.getExpression().accept(evaluator, stateTable);

			if (!field.equalValues(questionValue)) {
				field.setValue(questionValue);
				updated = true;
			}

			main.updateValueInStateTable(question.getIdentifier(), questionValue);
		}

		return updated;
	}

}