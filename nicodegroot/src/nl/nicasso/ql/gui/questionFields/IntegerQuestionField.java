package nl.nicasso.ql.gui.questionFields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import nl.nicasso.ql.gui.QuestionFieldArguments;
import nl.nicasso.ql.gui.evaluator.values.IntegerValue;
import nl.nicasso.ql.gui.evaluator.values.StringValue;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.widgets.InterActiveWidget;
import nl.nicasso.ql.gui.widgets.TextfieldWidget;
import nl.nicasso.ql.gui.widgets.Widget;

public class IntegerQuestionField extends QuestionField {

	private InterActiveWidget textField;
	private Widget feedback;
	private Value fieldValue;

	public IntegerQuestionField(QuestionFieldArguments params) {
		super(params);
		
		textField = new TextfieldWidget(params.isEnabled());

		updateValueAndTextfield(params.getValue());

		if (params.isEnabled()) {
			addListenerToField();
		}
	}

	public void setFeedbackField(Widget feedback) {
		this.feedback = feedback;
	}

	private void addListenerToField() {
		textField.addListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				boolean parseSuccess = true;

				IntegerValue newValue = new IntegerValue(0);

				if (!textField.getValue().equals("")) {
					try {
						newValue = new IntegerValue(Integer.parseInt((String) textField.getValue()));
					} catch (Exception ex) {
						feedback.setValue(new StringValue("This is not a valid integer."));
						parseSuccess = false;
					}
				}

				if (parseSuccess) {
					feedback.setValue(new StringValue(""));

					updateValueAndTextfield(newValue);

					getMainWindow().updateValueInStateTable(getIdentifier(), newValue);
					getMainWindow().updateGUIPanels();
				}
			}

		});
	}

	public void updateValueAndTextfield(Value value) {
		this.fieldValue = (IntegerValue) value;
		textField.setValue(value);
	}

	public boolean equalValues(Value value) {
		return value.equals(this.fieldValue);
	}

	public Widget getField() {
		return this.textField;
	}

	@Override
	public Value getValue() {
		return fieldValue;
	}
}