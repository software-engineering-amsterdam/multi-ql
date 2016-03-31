package nl.nicasso.ql.gui.panels;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JPanel;

import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.statements.ComputedQuestion;
import nl.nicasso.ql.gui.NotifyAboutGuiUpdates;
import nl.nicasso.ql.gui.evaluator.Evaluator;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTable;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.questionFields.QuestionField;

public class ComputedQuestionPanel extends Panel {

	private ComputedQuestion question;
	private NotifyAboutGuiUpdates mainWindow;

	public ComputedQuestionPanel(ComputedQuestion question, QuestionField field, Value value,
			Stack<Expression> conditions, StateTable stateTable, NotifyAboutGuiUpdates mainWindow) {
		this.mainWindow = mainWindow;
		this.stateTable = stateTable;
		this.question = question;

		this.conditions = new ArrayList<Expression>();

		indexVisiblityConditions(conditions);
		
		setupQuestionPanel(field, value);
	}
	
	private void setupQuestionPanel(QuestionField field, Value value) {
		panel = new JPanel(new GridLayout(2, 2));
		
		addQuestionLabel(question);

		panel.add(new JPanel());
		
		addQuestionField(question, field, value);
	}

	public boolean update() {
		boolean updated = false;
		
		boolean newVisiblity = checkPanelVisibilityConditions();

		if (newVisiblity) {
			Value questionValue = question.getExpression().accept(new Evaluator(), stateTable);

			if (!field.equalValues(questionValue)) {
				field.updateValueAndTextfield(questionValue);
				updated = true;
			}

			mainWindow.updateValueInStateTable(question.getIdentifier(), questionValue);
		}

		return updated;
	}

}