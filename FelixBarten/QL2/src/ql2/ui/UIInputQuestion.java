package ql2.ui;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import ql2.ast.CalculatedQuestion;
import ql2.ast.InputQuestion;
import ql2.ast.type.BooleanType;
import ql2.ast.type.IntegerType;
import ql2.ast.type.QuestionType;
import ql2.ast.type.StringType;

public class UIInputQuestion extends JPanel {

	private static final long serialVersionUID = 1L;
	private InputQuestion question; 
	private QlGui parent;
	private JComponent questionField;

	public UIInputQuestion(InputQuestion question, QlGui gui) {
		this.question = question; 
		this.parent = gui;
		draw(); 
		
		parent.getPanel().add(this);
	}
	
	
	private void draw() {
		JLabel questionLabel = new JLabel(question.getQuestionText());
		this.setName(question.getQuestionID());
		questionField = null;
		QuestionType type = question.getType();
		
		// filter input based on type. 
		if (type instanceof BooleanType) {
			questionField = new JCheckBox();
		} else if (type instanceof StringType) {
			questionField = new JTextField();
		} else if (type instanceof IntegerType) {
			questionField = new JTextField(); //insert num validation 
		}
		
		this.add(questionLabel);
		this.add(questionField);
	}
	
	public Object getValue() {
		if (questionField instanceof JSpinner) {
			return ((JSpinner) questionField).getValue();
		}
		if (questionField instanceof JCheckBox) {
			return ((JCheckBox) questionField).isSelected();
		}
		if (questionField instanceof JTextField) {
			return ((JTextField) questionField).getText();
		}
		return null;
	}


	public InputQuestion getQuestion() {
		return question;
	}
}
