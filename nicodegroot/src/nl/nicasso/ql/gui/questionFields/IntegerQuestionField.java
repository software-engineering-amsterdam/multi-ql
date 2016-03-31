package nl.nicasso.ql.gui.questionFields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import nl.nicasso.ql.gui.QuestionFieldArguments;
import nl.nicasso.ql.gui.evaluator.values.IntegerValue;
import nl.nicasso.ql.gui.evaluator.values.Value;

public class IntegerQuestionField extends QuestionField {

	private JTextField field;
	private JLabel feedback;
	private Value fieldValue;

	public IntegerQuestionField(QuestionFieldArguments params) {
		super(params);

		setupField(params.isEnabled(), (IntegerValue) params.getValue());
	}

	public void setFeedbackField(JLabel feedback) {
		this.feedback = feedback;
	}

	private void setupField(boolean enabled, IntegerValue value) {
		field = new JTextField();
		field.setColumns(20);
		field.setEnabled(enabled);

		setValue(value);

		if (enabled) {
			addListenerToField();
		}
	}

	private void addListenerToField() {
		field.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				boolean parseSuccess = true;

				IntegerValue newValue = new IntegerValue(0);

				if (!field.getText().equals("")) {
					try {
						newValue = new IntegerValue(Integer.parseInt(field.getText()));
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
		field.setText(value.getValue().toString());
	}

	public boolean equalValues(Value value) {
		return value.equals(this.fieldValue);
	}

	public JTextField getField() {
		return this.field;
	}

	@Override
	public Value getValue() {
		return fieldValue;
	}
}