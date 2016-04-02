package ql.gui.inputComponent;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import ql.ast.value.StringValue;
import ql.ast.value.Value;
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
	public void updateValueLabel(Value newValue) {
		textField.setText((String) newValue.getValue());
	}

	@Override
	public Value getInput() {
		return new StringValue(textField.getText());
	}

	@Override
	public void onAction() {
		parent.onAction();
	}
}
