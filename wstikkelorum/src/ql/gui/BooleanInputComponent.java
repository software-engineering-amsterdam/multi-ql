package ql.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

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
	public void updateValueLabel(Object newValue) {
		if(newValue == null){
			checkBox.setSelected(false);
			return;
		}
		
		boolean value = (boolean)newValue;
		if(value){
			checkBox.setSelected(true);
		}else{		
			checkBox.setSelected(false);
		}
	}

	@Override
	public Object getInput() {
		if(checkBox.isSelected()){
			return true;
		}
		return false;
	}

	@Override
	public void onAction() {
		parent.onAction();
	}

}
