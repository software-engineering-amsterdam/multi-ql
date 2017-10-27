package ql2.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ql2.ast.CalculatedQuestion;
import ql2.ast.InputQuestion;

public class UICalculatedQuestion extends JPanel {

	private static final long serialVersionUID = 5264087442353919285L;
	private CalculatedQuestion question; 
	private QlGui parent;
	private JTextField questionField;

	public UICalculatedQuestion(CalculatedQuestion question, QlGui gui) {
		this.question = question; 
		this.parent = gui;
		draw(); 
		
		parent.getPanel().add(this);
	}
	
	
	private void draw() {
		JLabel questionLabel = new JLabel(question.getInput().getQuestionText());
		this.setName(question.getQuestionID());
		
	    questionField = new JTextField();
		questionField.setEditable(false);
		
		this.add(questionLabel);
		this.add(questionField);
	}
	
	/**
	 * Update calculated question outcome (depends on other questions)
	 */
	private void update() {
		
	}
	public Object getValue() {
		return questionField.getText();
	}
	
	public CalculatedQuestion getQuestion() {
		return question;
	}
}
