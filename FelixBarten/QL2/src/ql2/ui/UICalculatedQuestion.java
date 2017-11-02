package ql2.ui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ql2.ast.CalculatedQuestion;
import ql2.ast.InputQuestion;
import ql2.ast.expression.Expr;
import ql2.ast.type.BooleanType;
import ql2.ast.type.QuestionType;

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
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
		this.setLayout(layout);
		JLabel questionLabel = new JLabel(stripQuotation(question.getInput().getQuestionText()));
		this.setName(question.getQuestionID());
		
	    questionField = new JTextField();
		questionField.setEditable(false);
		questionField.setColumns(15);
		questionField.setBackground(Color.LIGHT_GRAY);
		questionField.setToolTipText(question.getQuestionID());
		
		this.add(questionLabel);
		this.add(questionField);
		update();
	}
	
	private String computeValue() {
		Object value = parent.getContext().getVariable(question.getQuestionID());
		QuestionType qt = question.getInput().getType();
		if (value != null && !(value instanceof Expr) ) {
			if (qt instanceof BooleanType) {
				if ((boolean) value) {
					return "Yes";
				}
				return "No";
			}
			return String.valueOf(parent.getContext().getVariable(question.getQuestionID()));
		}	
		return "";
	}
	
	private String stripQuotation(String input) {
		return input.substring(1, input.length()-1);
	}
	
	/**
	 * Update calculated question outcome (depends on other questions)
	 */
	public void update() {
		questionField.setText(computeValue());
	}
	
	public Object getValue() {
		return questionField.getText();
	}
	
	public CalculatedQuestion getQuestion() {
		return question;
	}
}
