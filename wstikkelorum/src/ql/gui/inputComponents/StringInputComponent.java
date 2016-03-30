package ql.gui.inputComponents;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import ql.gui.UserInputElement;

public class StringInputComponent implements UserInputElement{
	private JTextField textField;
	private UserInputElement parent;
	
	public StringInputComponent(UserInputElement parent){
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
		JPanel inputComponentPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		inputComponentPanel.add(textField);
		inputComponentPanel.setVisible(true);
		return inputComponentPanel;
	}

	@Override
	public void updateValueLabel(Object newValue) {
		if(newValue == null){
			textField.setText("");
		}else{
			textField.setText(newValue.toString());
		}
	}

	@Override
	public Object getInput() {
		return textField.getText();
	}

	@Override
	public void onAction() {
		parent.onAction();
	}
}
