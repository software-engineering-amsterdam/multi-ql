package nl.nicasso.ql.gui.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.statements.Question;
import nl.nicasso.ql.gui.Observer;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTable;
import nl.nicasso.ql.gui.questionFields.QuestionField;
import nl.nicasso.ql.gui.widgets.LabelWidget;
import nl.nicasso.ql.gui.widgets.Widget;

public class QuestionPanel extends Panel {

	private Question question;
	private Observer mainWindow;
	private QuestionField field;

	public QuestionPanel(Question question, QuestionField field, Stack<Expression> conditions, StateTable stateTable,
			Observer mainWindow) {
		this.question = question;
		this.stateTable = stateTable;
		this.mainWindow = mainWindow;
		this.field = field;

		this.conditions = new ArrayList<Expression>();
		
		indexVisiblityConditions(conditions);
		
		setupQuestionPanel();
	}
	
	private void setupQuestionPanel() {
		panel = new JPanel(new GridLayout(2, 2));
		
		addQuestionLabel(question);
		
		panel.add(new JPanel());
		
		addQuestionField(question, field, question.getType().getDefaultValue());
		
		setupFeedbackLabel();
	}
	
	private void setupFeedbackLabel() {
		Widget feedback = new LabelWidget("");
		feedback.addSelfToPanel(panel);
		
//		JLabel feedback = new JLabel("");
//		feedback.setFont(new Font("Arial", 0, 12));
//		feedback.setForeground(Color.RED);
//		panel.add(feedback);
		field.setFeedbackField(feedback);
	}

	public boolean update() {
		boolean updated = false;
		
		boolean currentVisibility = panel.isVisible();
		boolean newVisibility = checkPanelVisibilityConditions();
		
		if (!currentVisibility && newVisibility) {
			mainWindow.updateValueInStateTable(question.getIdentifier(), field.getValue());
			updated = true;
		}

		return updated;
	}

}