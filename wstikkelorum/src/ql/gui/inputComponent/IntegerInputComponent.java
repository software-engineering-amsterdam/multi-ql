package ql.gui.inputComponent;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import ql.ast.value.IntegerValue;
import ql.ast.value.Value;
import ql.gui.UserInputElement;

public class IntegerInputComponent implements UserInputElement{
	private JTextField textField;
	private UserInputElement parent;
	
	public IntegerInputComponent(UserInputElement parent){
		this.parent = parent;
		textField = new JTextField(6);
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onAction();
			}
		});
	}

	@Override
	public JPanel getDrawableItem() {
		JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jPanel.add(textField);
		jPanel.setVisible(true);
		return jPanel;
	}

	@Override
	public void onAction() {
		parent.onAction();
	}
	
	@Override
	public void updateValueLabel(Value newValue) {
		textField.setText(newValue.getValue().toString());
	}

	@Override
	public Value getInput() {
		try{
			return new IntegerValue(Integer.parseInt(textField.getText()));
		}catch(NumberFormatException nfe){
			textField.setBackground(new Color(255, 0, 0));
			return null;//TODO:return already stored number!
		}
	}

}
