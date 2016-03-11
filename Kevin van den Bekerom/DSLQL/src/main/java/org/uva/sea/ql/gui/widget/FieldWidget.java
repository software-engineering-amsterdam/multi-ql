package org.uva.sea.ql.gui.widget;

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
				//
			}

			public void keyReleased(KeyEvent keyEvent) {
				System.out.println("DA!");
				Value value = getQuestion().getType().parse(inputField.getText());
				System.out.println("Actual value: " + String.valueOf(value.getValue()));
	        	if (value.equals(new UndefinedValue())) {
	        		inputField.setText("");
	        		dataManager.updateValueState(getQuestion().getIdentifier(), value);
	        	} else {
	        		System.out.println(inputField.getText());
	        		dataManager.updateValueState(getQuestion().getIdentifier(), value);
	        	}
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
