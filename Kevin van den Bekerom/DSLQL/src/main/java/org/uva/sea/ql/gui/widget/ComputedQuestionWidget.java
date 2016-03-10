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
		label = new JLabel(compQuestion.getIdentifier());
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
		Value value = valueMap.getValueFromMap(compQuestion.getIdentifier());
		if (! value.equals(new UndefinedValue())) {
			String result = String.valueOf(value.getValue());
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
