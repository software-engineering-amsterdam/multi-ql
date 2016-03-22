package ql.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ql.ast.statement.ComputedQuestion;

public class ComputedQuestionWidget implements UIElement{
	private JLabel questionString;
	private JTextField textField;
	private final ComputedQuestion computedQuestion;
	
	
	public ComputedQuestionWidget(ComputedQuestion computedQuestion){
		this.computedQuestion = computedQuestion;
		questionString = new JLabel(computedQuestion.getQuestionString());
		textField = new JTextField(6);
		textField.setEditable(false);
	}
	
	@Override
	public JPanel getDrawableItem() {
		JPanel jPanel = new JPanel();
		jPanel.add(questionString);
		jPanel.add(textField);
		jPanel.setVisible(true);
		return jPanel;
	}

	@Override
	public void updateValueLabel(Object newValue) {
		if(newValue == null){
			textField.setText("");
		}else{
			textField.setText(newValue.toString());
		}	
	}
	
	public ComputedQuestion getQuestion(){
		return computedQuestion;
	}
}
