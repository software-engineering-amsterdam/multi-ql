package org.uva.sea.ql.gui.widget;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.ast.stat.Question;
import org.uva.sea.ql.gui.FormDataManager;
import org.uva.sea.ql.value.UndefinedValue;
import org.uva.sea.ql.value.Value;

public class FieldWidget extends QuestionWidget {
	JTextField inputField;
	JLabel label;

	public FieldWidget(Question question, Box container, FormDataManager dataManager) {
		super(question, dataManager);
		initialize(container);
	}
	
	private void initialize(Box container) {
		inputField = new JTextField();
		label = new JLabel(getQuestion().getLabel());
		
		//add isTrue listener
		inputField.addKeyListener(new KeyListener() {
	        public void keyTyped(KeyEvent keyEvent) {
	        	//
	        }

			public void keyPressed(KeyEvent keyEvent) {
	        	Value value = getQuestion().getType().parse(inputField.getText());
	        	if (value.equals(new UndefinedValue())) {
	        		inputField.setText("");
	        	} else {
	        		dataManager.updateValueState(getQuestion().getIdentifier(), value);
	        	}
        		
			}

			public void keyReleased(KeyEvent keyEvent) {
				// 
			}
	    });
		
		attachToPanel(container);
	}
	
	private void attachToPanel(Box b) {
		Box horBox = Box.createHorizontalBox();
		horBox.add(label);
		horBox.add(inputField);
		b.add(horBox);
	}

	@Override
	public void update(ValueMap valueMap) {
		//Value value = valueMap.getValueFromMap(getQuestion().getIdentifier());
		//inputValue = String.valueOf(value.getValue());
		//inputField.setText(inputValue);
	}

	@Override
	public void update() {
		boolean isVisible = dataManager.getVisibilityState().getValueFromMap(getQuestion().getIdentifier());
		label.setVisible(isVisible);
		inputField.setVisible(isVisible);
	}

}
