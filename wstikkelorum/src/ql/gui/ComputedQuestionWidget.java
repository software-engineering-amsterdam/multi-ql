package ql.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ql.ast.statement.ComputedQuestion;

public class ComputedQuestionWidget extends UIElement{
	private JLabel valueLabel;
	
	public ComputedQuestionWidget(ComputedQuestion computedQuestion){
		super(new JLabel(computedQuestion.getVariable().getIdentifier()), 
		      new JLabel(computedQuestion.getStr()));
		//TODO:Evaluate the expression!
		valueLabel = new JLabel("...");
	}
	
	@Override
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		panel.add(this.getVariableLabel());
		panel.add(this.getStringLabel());
		panel.add(valueLabel);
		panel.setVisible(true);
		return panel;
	}

}
