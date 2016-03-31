package ql.gui.questionWidget;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ql.ast.literal.Variable;
import ql.ast.statement.question.ComputedQuestion;
import ql.ast.value.Value;
import ql.gui.DrawableElement;

public class ComputedQuestionWidget extends QuestionWidget implements DrawableElement{
	private JLabel questionString;
	private JTextField textField;
	private final ComputedQuestion computedQuestion;
	
	
	public ComputedQuestionWidget(ComputedQuestion computedQuestion){
		this.computedQuestion = computedQuestion;
		questionString = new JLabel(computedQuestion.getQuestionString());
		textField = new JTextField(6);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setEditable(false);
	}
	
	@Override
	public JPanel getDrawableItem() {
		JPanel jPanel = new JPanel(new GridLayout(1, 1));
		jPanel.add(questionString);
		jPanel.add(textField);
		jPanel.setVisible(true);
		return jPanel;
	}

	@Override
	public void updateValueLabel(Value newValue) {
		if(newValue == null){
			textField.setText("");
		}else{
			textField.setText(newValue.getValue().toString());
		}	
	}
	
	public ComputedQuestion getQuestion(){
		return computedQuestion;
	}

	@Override
	public Variable getVariable() {
		return computedQuestion.getVariable();
	}
}
