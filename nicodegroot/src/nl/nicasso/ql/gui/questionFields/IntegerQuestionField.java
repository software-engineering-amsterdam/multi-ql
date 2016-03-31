package nl.nicasso.ql.gui.questionFields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import nl.nicasso.ql.gui.QuestionFieldArguments;
import nl.nicasso.ql.gui.evaluator.values.IntegerValue;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.widgets.TextfieldWidget;
import nl.nicasso.ql.gui.widgets.Widget;

public class IntegerQuestionField extends QuestionField {

	private Widget textField;
	private JLabel feedback;
	private Value fieldValue;

	public IntegerQuestionField(QuestionFieldArguments params) {
		super(params);
		
		textField = new TextfieldWidget(params.isEnabled());

		setValue(params.getValue());

		if (params.isEnabled()) {
			addListenerToField();
		}
	}

	public void setFeedbackField(JLabel feedback) {
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
						feedback.setText("This is not a valid integer.");
						parseSuccess = false;
					}
				}

				if (parseSuccess && !newValue.equals(fieldValue)) {
					feedback.setText("");

					setValue(newValue);

					getMain().updateValueInStateTable(getIdentifier(), newValue);
					getMain().updateGUIPanels();
				}
			}

		});
	}

	public void setValue(Value value) {
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