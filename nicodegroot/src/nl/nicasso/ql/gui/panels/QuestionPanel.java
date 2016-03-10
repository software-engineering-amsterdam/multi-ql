package nl.nicasso.ql.gui.panels;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;

import nl.nicasso.ql.ast.expressions.Expression;
import nl.nicasso.ql.ast.statements.Question;
import nl.nicasso.ql.gui.questionFields.QuestionField;
import nl.nicasso.ql.gui.widgets.Label;
import nl.nicasso.ql.values.Value;

public class QuestionPanel extends Panel {

	private JPanel panel;
	
	// Is 4 params too long? Should I use the ParamObject here?
	public QuestionPanel(Question q, QuestionField field, Value value, Expression condition) {
		 panel = new JPanel(new GridLayout(2,2));
		 
		 addQuestionLabel(q);
		 panel.add(new JPanel());		 
		 addQuestionField(q, field, value);
		 addLabelForFeedback();
	}
	
	public void addQuestionLabel(Question q) {
		Label questionLabel = new Label(q.getLabel());
		questionLabel.setFont(new Font("Arial", 0, 100));
		panel.add(questionLabel.getWidget());
	}
	
	public void addLabelForFeedback() {
		Label feedback = new Label("");
		feedback.setFont(new Font("Arial", 0, 100));
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
