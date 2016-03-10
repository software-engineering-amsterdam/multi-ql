package ql.gui;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ql.ast.statement.InputQuestion;
import ql.ast.visitor.Type;

public class InputQuestionWidget extends UIElement{
	private JComponent inputComponent;
	
	public InputQuestionWidget(InputQuestion inputQuestion){
		super(new JLabel(inputQuestion.getVariable().getIdentifier()),
			  new JLabel(inputQuestion.getStr()));
		//input component must show a (default) value as well
		if(inputQuestion.getVariable().getType().getType() == Type.BOOLEAN){
			inputComponent = new JRadioButton();
		}else{
			inputComponent = new JTextField();
		}
	}
	
	public JComponent getInputComponent(){
		return inputComponent;
	}

	@Override
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		panel.add(this.getVariableLabel());
		panel.add(this.getStringLabel());
		panel.add(inputComponent);
		panel.setVisible(true);
		return panel;
	}
}
