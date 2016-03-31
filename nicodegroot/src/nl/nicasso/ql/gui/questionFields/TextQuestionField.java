package nl.nicasso.ql.gui.questionFields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import nl.nicasso.ql.gui.QuestionFieldArguments;
import nl.nicasso.ql.gui.evaluator.values.StringValue;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.widgets.InterActiveWidget;
import nl.nicasso.ql.gui.widgets.TextfieldWidget;
import nl.nicasso.ql.gui.widgets.Widget;

public class TextQuestionField extends QuestionField {

	private InterActiveWidget textField;
	private Value fieldValue;

	public TextQuestionField(QuestionFieldArguments params) {
		super(params);

		textField = new TextfieldWidget(params.isEnabled());

		updateValueAndTextfield(params.getValue());

		if (params.isEnabled()) {
			addListenerToField();
		}
	}

	private void addListenerToField() {
		textField.addListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				StringValue newValue = new StringValue((String) textField.getValue());

				if (!newValue.equals(fieldValue)) {
					updateValueAndTextfield(newValue);

					getMainWindow().updateValueInStateTable(getIdentifier(), newValue);
					getMainWindow().updateGUIPanels();
				}
			}

		});
	}

	public void updateValueAndTextfield(Value value) {
		this.fieldValue = (StringValue) value;
		textField.setValue(value);
	}

	public boolean equalValues(Value value) {
		return value.equals(this.fieldValue);
	}

	public Widget getField() {
		return this.textField;
	}

	@Override
	public void setFeedbackField(Widget feedback) {
		new AssertionError("TextQuestionField has no feedback field.");
	}

	@Override
	public Value getValue() {
		return fieldValue;
	}

}
