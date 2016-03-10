package nl.nicasso.ql.gui.panels;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;

import nl.nicasso.ql.ast.statements.Question;
import nl.nicasso.ql.gui.questionFields.QuestionField;
import nl.nicasso.ql.gui.widgets.Label;
import nl.nicasso.ql.symbolTable.SymbolTable;
import nl.nicasso.ql.values.Value;

public class QuestionPanel extends Panel {

	private JPanel panel;
	
	public QuestionPanel(Question q, QuestionField field, SymbolTable symbolTable) {
		 panel = new JPanel(new GridLayout(2,2));
		 
		 addQuestionLabel(q);
		 panel.add(new JPanel());		 
		 addQuestionField(q, field, symbolTable);
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
	
	public void addQuestionField(Question q, QuestionField field, SymbolTable symbolTable) {	
		Value v = symbolTable.getEntryValue(q.getId());
		
		System.out.println(q.getId().getValue() + " = " + v.getValue().toString());
		
		field.setValue(v.getValue());
		
		panel.add(field.getField());
	}
	
	@Override
	public JPanel getPanel() {
		panel.setVisible(true);
		
		return this.panel;
	}
	
}
