package org.uva.sea.ql.gui.widget;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.ast.stat.Question;
import org.uva.sea.ql.gui.FormDataManager;
import org.uva.sea.ql.value.BoolValue;

public class BooleanWidget extends QuestionWidget {

	JRadioButton isTrue, isFalse;
	ButtonGroup editableGroup;
	JLabel label;
	
	public BooleanWidget(Question question, Box container, FormDataManager dataManager) {
		super(question, dataManager);
		initialize(container);
	}
	
	private void initialize(Box container) {
		// radio buttons
		isTrue = new JRadioButton("true");
		isFalse = new JRadioButton("false");
		label = new JLabel(getQuestion().getLabel());
		
		//The Group, make sure only one button is selected at a time in the group
	    editableGroup = new ButtonGroup();
	    editableGroup.add(isTrue);
	    editableGroup.add(isFalse);
	    
	    //add isTrue listener
		isTrue.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            dataManager.updateValueState(getQuestion().getIdentifier(), new BoolValue(true));
	        }
	    });
		
		//add isFalse listener
		isFalse.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dataManager.updateValueState(getQuestion().getIdentifier(), new BoolValue(false));
	        }
	    });
		
		// attach to frame
		attachToPanel(container);
	}
	
	private void attachToPanel(Box b) {
		Box horBox = Box.createHorizontalBox();
		horBox.add(label);
		horBox.add(isTrue);
		horBox.add(isFalse);
		b.add(horBox);
	}

	@Override
	public void update(ValueMap valueMap) {
//		Value value =  valueMap.getValueFromMap(getQuestion().getIdentifier());
//		if (! value.equals(new UndefinedValue())) {
//			if ((Boolean) value.getValue()) {
//				isTrue.setSelected(true);
//				isFalse.setSelected(false);
//			}
//		} else {
//			isTrue.setSelected(false);
//			isFalse.setSelected(true);
//		} 
	}

	@Override
	public void update() {
		boolean isVisible = dataManager.getVisibilityState().getValueFromMap(getQuestion().getIdentifier());
		label.setVisible(isVisible);
		isTrue.setVisible(isVisible);
		isFalse.setVisible(isVisible);
	}
	
}
