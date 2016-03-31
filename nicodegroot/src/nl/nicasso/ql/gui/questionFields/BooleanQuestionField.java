package nl.nicasso.ql.gui.questionFields;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import nl.nicasso.ql.gui.QuestionFieldArguments;
import nl.nicasso.ql.gui.evaluator.values.BooleanValue;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.widgets.CheckboxWidget;
import nl.nicasso.ql.gui.widgets.InterActiveWidget;
import nl.nicasso.ql.gui.widgets.Widget;

public class BooleanQuestionField extends QuestionField {

	private InterActiveWidget checkbox;
	private Value fieldValue;

	public BooleanQuestionField(QuestionFieldArguments params) {
		super(params);
		
		checkbox = new CheckboxWidget(params.isEnabled());

		updateValueAndTextfield(params.getValue());

		if (params.isEnabled()) {
			addListenerToField();
		}
	}

	private void addListenerToField() {
		checkbox.addListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent event) {
				BooleanValue newValue = new BooleanValue(false);

				if (event.getStateChange() == ItemEvent.SELECTED) {
					newValue = new BooleanValue(true);
				}

				updateValueAndTextfield(newValue);

				getMainWindow().updateValueInStateTable(getIdentifier(), newValue);
				getMainWindow().updateGUIPanels();
			}
		});
	}

	public void updateValueAndTextfield(Value value) {
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
	public void setFeedbackField(Widget feedback) {
		new AssertionError("BooleanQuestionField no feedback field.");
	}

	@Override
	public Value getValue() {
		return fieldValue;
	}
}