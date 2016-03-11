package org.uva.sea.ql.gui.widget;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.ast.stat.ComputedQuestion;
import org.uva.sea.ql.gui.FormDataManager;
import org.uva.sea.ql.value.UndefinedValue;
import org.uva.sea.ql.value.Value;

public class ComputedQuestionWidget extends Widget {
	private ComputedQuestion compQuestion;
	JLabel label;
	JTextField resultField;
	
	public ComputedQuestionWidget(ComputedQuestion compQuestion, 
			Box container, FormDataManager dataManager) {
		super.dataManager = dataManager;
		this.compQuestion = compQuestion;
		initialize(container);

	}
	
	private void initialize(Box container) {
		label = new JLabel(compQuestion.getLabel());
		resultField = new JTextField();
		resultField.setEditable(false);
		
		attachToPanel(container);
	}
	
	private void attachToPanel(Box b) {
		Box horBox = Box.createHorizontalBox();
		horBox.add(label);
		horBox.add(resultField);
		b.add(horBox);
	}
	
	public ComputedQuestion getCompQuestion() {
		return this.compQuestion;
	}

	@Override
	public void update(ValueMap valueMap) {
		Value oldValue = valueMap.getValueFromMap(compQuestion.getIdentifier());
		System.out.println("oldValue: " + String.valueOf(oldValue.getValue()));
		Value newValue = compQuestion.getExpr().eval(valueMap);
		System.out.println("newValue: " + String.valueOf(newValue.getValue()));
		
		if (!oldValue.getValue().equals(newValue.getValue())) {
			System.out.println("Current value: " + String.valueOf(newValue.getValue()));
			dataManager.updateValueState(compQuestion.getIdentifier(), newValue);
		}
		else if (!newValue.equals(new UndefinedValue())) {
			String result = String.valueOf(newValue.getValue());
			resultField.setText(result);
		} else {
			resultField.setText("");
		}
	
	}

	@Override
	public void update() {
		boolean isVisible = dataManager.getVisibilityState().getValueFromMap(compQuestion.getIdentifier());
		label.setVisible(isVisible);
		resultField.setVisible(isVisible);
	}
}
