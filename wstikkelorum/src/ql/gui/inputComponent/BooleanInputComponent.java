package ql.gui.inputComponent;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import ql.ast.value.BooleanValue;
import ql.ast.value.Value;
import ql.gui.UserInputElement;

public class BooleanInputComponent implements UserInputElement{
	private JCheckBox checkBox;
	private UserInputElement parent;
	
	public BooleanInputComponent(UserInputElement parent){
		this.parent = parent;
		checkBox = new JCheckBox();
		checkBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onAction();
			}
		});
	}
	
	@Override
	public JPanel getDrawableItem() {
		JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jPanel.add(checkBox);
		jPanel.setVisible(true);
		return jPanel;
	}

	@Override
	public void updateValueLabel(Value newValue) {
		boolean value = (boolean) newValue.getValue();
		if(value){
			checkBox.setSelected(true);
			return;
		}	
		checkBox.setSelected(false);
	}

	@Override
	public Value getInput() {
		if(checkBox.isSelected()){
			return new BooleanValue(true);
		}
		return new BooleanValue(false);
	}

	@Override
	public void onAction() {
		parent.onAction();
	}
}
