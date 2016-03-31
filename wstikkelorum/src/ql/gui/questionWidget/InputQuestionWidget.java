package ql.gui.questionWidget;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ql.ast.literal.Variable;
import ql.ast.statement.question.InputQuestion;
import ql.ast.type.BooleanType;
import ql.ast.type.IntegerType;
import ql.ast.type.StringType;
import ql.ast.type.ValueType;
import ql.ast.value.Value;
import ql.gui.QLWindow;
import ql.gui.UserInputElement;
import ql.gui.inputComponent.BooleanInputComponent;
import ql.gui.inputComponent.IntegerInputComponent;
import ql.gui.inputComponent.StringInputComponent;

public class InputQuestionWidget extends QuestionWidget implements UserInputElement{
	private JLabel questionString;
	private QLWindow parent;
	private UserInputElement inputComponent;
	private final InputQuestion inputQuestion;
	
	public InputQuestionWidget(InputQuestion inputQuestion, QLWindow parent){
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
		JPanel jPanel = new JPanel(new GridLayout(1,1));
		jPanel.add(questionString);
		jPanel.add(inputComponent.getDrawableItem());
		jPanel.setVisible(true);
		return jPanel;
	}

	@Override
	public void updateValueLabel(Value newValue) {
		inputComponent.updateValueLabel(newValue);
	}

	@Override
	public Value getInput() {
		return inputComponent.getInput();
	}

	@Override
	public void onAction() {
		parent.drawContent();
	}
	
	public InputQuestion getQuestion(){
		return inputQuestion;
	}

	@Override
	public Variable getVariable() {
		return inputQuestion.getVariable();
	}
}
