package ql.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ql.ast.statement.InputQuestion;
import ql.ast.types.BooleanType;
import ql.ast.types.IntegerType;
import ql.ast.types.StringType;
import ql.ast.types.ValueType;

public class InputQuestionWidget implements UserInputElement{
	private JLabel questionString;
	private UserInterface parent;
	private UserInputElement inputComponent;
	private final InputQuestion inputQuestion;
	
	public InputQuestionWidget(InputQuestion inputQuestion, UserInterface parent){
		this.parent = parent;
		this.inputQuestion = inputQuestion;
		this.questionString = new JLabel(inputQuestion.getQuestionString());
		this.inputComponent = createInputComponent(inputQuestion.getType());
	}

	private UserInputElement createInputComponent(ValueType type) {
		if(type instanceof BooleanType){
			return new BooleanInputComponent(this);
		}
		if(type instanceof StringType){
			return new StringInputComponent(this);
		}
		if(type instanceof IntegerType){
			return new IntegerInputComponent(this);
		}
		return null;
	}

	@Override
	public JPanel getDrawableItem() {
		JPanel jPanel = new JPanel();
		jPanel.add(questionString);
		jPanel.add(inputComponent.getDrawableItem());
		jPanel.setVisible(true);
		return jPanel;
	}

	@Override
	public void updateValueLabel(Object newValue) {
		inputComponent.updateValueLabel(newValue);
	}

	@Override
	public Object getInput() {
		return inputComponent.getInput();
	}

	@Override
	public void onAction() {
		parent.drawContent();
	}
	
	public InputQuestion getQuestion(){
		return inputQuestion;
	}
}
