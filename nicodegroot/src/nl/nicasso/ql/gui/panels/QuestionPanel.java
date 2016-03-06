package nl.nicasso.ql.gui.panels;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;

import nl.nicasso.ql.ast.statements.Question;
import nl.nicasso.ql.gui.questionFields.QuestionField;
import nl.nicasso.ql.gui.widgets.Label;

public class QuestionPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8940568963006272156L;
	
	private JPanel panel;
	
	public QuestionPanel(Question q) {
		 panel = new JPanel(new GridLayout(1, 2));
		 
		 addQuestionLabel(q);
		 addQuestionField(q);
	}
	
	public void addQuestionLabel(Question q) {
		Label questionLabel = new Label(q.getId().getValue()+" - "+q.getLabel());
		questionLabel.setFont(new Font("Arial", 0, 100));
		
		panel.add(questionLabel.getWidget());
	}
	
	public void addQuestionField(Question q) {
		QuestionField field = q.getType().getRelatedField();
		
		panel.add(field.getField());
	}
	
	@Override
	public JPanel getPanel() {
		System.out.println("GETPANEL QUESTION");
		panel.setVisible(true);
		
		return this.panel;
	}
	
}
