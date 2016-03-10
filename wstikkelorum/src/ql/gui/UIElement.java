package ql.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class UIElement {
	private JLabel variableLabel;
	private JLabel stringLabel;
	
	public UIElement(JLabel variableLabel, JLabel stringLabel){
		this.variableLabel = variableLabel;
		this.stringLabel = stringLabel;
	}
	
	public JLabel getVariableLabel(){
		return variableLabel;
	}
	
	public JLabel getStringLabel(){
		return stringLabel;
	}
	
	public abstract JPanel getPanel();
}
