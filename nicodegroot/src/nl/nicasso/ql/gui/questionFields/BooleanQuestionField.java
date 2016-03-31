package nl.nicasso.ql.gui.questionFields;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;

import nl.nicasso.ql.gui.QuestionFieldArguments;
import nl.nicasso.ql.gui.evaluator.values.BooleanValue;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.widgets.CheckboxWidget;
import nl.nicasso.ql.gui.widgets.Widget;

public class BooleanQuestionField extends QuestionField {

	private Widget checkbox;
	private Value fieldValue;

	public BooleanQuestionField(QuestionFieldArguments params) {
		super(params);
		
		checkbox = new CheckboxWidget(params.isEnabled());

		setValue(params.getValue());

		if (params.isEnabled()) {
			addListenerToField();
		}
	}

	private void addListenerToField() {
		checkbox.addListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				BooleanValue newValue = new BooleanValue(false);

				if (e.getStateChange() == ItemEvent.SELECTED) {
					newValue = new BooleanValue(true);
				}

				setValue(newValue);

				getMain().updateValueInStateTable(getIdentifier(), newValue);
				getMain().updateGUIPanels();
			}
		});
	}

	public void setValue(Value value) {
		this.fieldValue = (BooleanValue) value;

		checkbox.setValue(value);
	}

	@Override
	public boolean equalValues(Value value) {
		return value.equals(this.fieldValue);
	}

	public Widget getField() {
		return checkbox;
	}

	@Override
	public void setFeedbackField(JLabel feedback) {
		new AssertionError("BooleanQuestionField no feedback field.");
	}

	@Override
	public Value getValue() {
		return fieldValue;
	}
}