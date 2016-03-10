package nl.nicasso.ql.gui.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;

import nl.nicasso.ql.ast.expressions.Expression;
import nl.nicasso.ql.ast.statements.Question;
import nl.nicasso.ql.gui.Observer;
import nl.nicasso.ql.gui.questionFields.QuestionField;
import nl.nicasso.ql.gui.widgets.Label;
import nl.nicasso.ql.values.UnknownValue;
import nl.nicasso.ql.values.Value;

public class QuestionPanel extends Panel {

	private JPanel panel;
	private Label feedback;
	private Expression condition;
	
	public QuestionPanel(Question q, QuestionField field, Expression condition) {
		 panel = new JPanel(new GridLayout(2,2));
		 feedback = new Label("");
		 this.condition = condition;
		 field.setFeedbackLabel(feedback);
		 
		 addQuestionLabel(q);
		 panel.add(new JPanel());		 
		 addQuestionField(q, field, new UnknownValue());
		 addLabelForFeedback();
	}
	
	public void addQuestionLabel(Question q) {
		Label questionLabel = new Label("Q: "+q.getLabel());
		questionLabel.setFont(new Font("Arial", 0, 100));
		panel.add(questionLabel.getWidget());
	}
	
	public void addLabelForFeedback() {
		feedback.setFont(new Font("Arial", 0, 100));
		feedback.setLabelColor(Color.RED);
		panel.add(feedback.getWidget());
	}
	
	public void addQuestionField(Question q, QuestionField field, Value value) {		
		field.setValue(value.getValue());
		panel.add(field.getField());
	}
	
	@Override
	public JPanel getPanel() {		
		return this.panel;
	}

}
