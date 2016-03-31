package nl.nicasso.ql.gui.questionFields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import nl.nicasso.ql.gui.QuestionFieldArguments;
import nl.nicasso.ql.gui.evaluator.values.StringValue;
import nl.nicasso.ql.gui.evaluator.values.Value;

public class TextQuestionField extends QuestionField {

	private JTextField field;
	private Value fieldValue;

	public TextQuestionField(QuestionFieldArguments params) {
		super(params);

		setupField(params.isEnabled(), (StringValue) params.getValue());
	}

	private void setupField(boolean enabled, StringValue value) {
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
				StringValue newValue = new StringValue(field.getText());

				if (!newValue.equals(fieldValue)) {
					setValue(newValue);

					getMain().updateValueInStateTable(getIdentifier(), newValue);
					getMain().updateGUIPanels();
				}
			}

		});
	}

	public void setValue(Value value) {
		this.fieldValue = (StringValue) value;
		field.setText(value.getValue().toString());
	}

	public boolean equalValues(Value value) {
		return value.equals(this.fieldValue);
	}

	public JTextField getField() {
		return this.field;
	}

	@Override
	public void setFeedbackField(JLabel feedback) {
		new AssertionError("TextQuestionField has no feedback field.");
	}

	@Override
	public Value getValue() {
		return fieldValue;
	}

}
