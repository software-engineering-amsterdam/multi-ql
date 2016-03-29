package nl.nicasso.ql.gui.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.statements.Question;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTable;
import nl.nicasso.ql.gui.questionFields.QuestionField;

public class QuestionPanel extends Panel {
	
	public QuestionPanel(Question question, QuestionField field, Expression condition, StateTable stateTable) {
		 panel = new JPanel(new GridLayout(2,2));
		 JLabel feedback = new JLabel("");
		 this.condition = condition;
		 this.stateTable = stateTable;
		 
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

}
