package nl.nicasso.ql.gui.questionFields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

import javax.swing.JLabel;
import javax.swing.JTextField;

import nl.nicasso.ql.gui.QuestionFieldArguments;
import nl.nicasso.ql.gui.evaluator.values.IntegerValue;
import nl.nicasso.ql.gui.evaluator.values.MoneyValue;
import nl.nicasso.ql.gui.evaluator.values.Value;

public class MoneyQuestionField extends QuestionField {

	private JTextField field;
	private JLabel feedback;
	private Value fieldValue;

	public MoneyQuestionField(QuestionFieldArguments params) {
		super(params);

		setupField(params.isEnabled(), (MoneyValue) params.getValue());
	}

	public void setFeedbackField(JLabel feedback) {
		this.feedback = feedback;
	}

	private void setupField(boolean enabled, MoneyValue value) {
		field = new JTextField();
		field.setColumns(20);
		field.setEnabled(enabled);

		System.out.println("setupField");

		setValue(value);
		field.setText(value.getValue().toString());

		if (enabled) {
			addListenerToField();
		}
	}

	// THIS IS ONE BIG MESS!
	private void addListenerToField() {
		field.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				boolean parseSuccess = true;

				MoneyValue newValue = new MoneyValue(BigDecimal.valueOf(0.00));

				if (!field.getText().equals("")) {
					try {
						newValue = new MoneyValue(BigDecimal.valueOf(Double.parseDouble(field.getText())));
					} catch (Exception ex) {
						feedback.setText("This is not a valid decimal number.");
						parseSuccess = false;
					}
				} else {
					parseSuccess = true;
				}

				if (parseSuccess) {
					// Does too much?!
					if (getNumberOfDecimalPlaces(newValue.getValue()) > 2) {
						feedback.setText("No more than 2 decimals allowed.");
					} else {
						feedback.setText("");

						updateValue(newValue);

						getMain().updateValueInStateTable(getIdentifier(), newValue);
						getMain().updateGUIPanels();
					}
				}
			}

		});
	}

	// DIFFERENCE WITH SETVALUE?
	public void updateValue(Value value) {
		if (value instanceof IntegerValue) {
			value = new MoneyValue(BigDecimal.valueOf(Double.parseDouble(value.getValue().toString())));
		}

		fieldValue = (MoneyValue) value;
	}

	// WTF MAN!
	public void setValue(Value value) {
		fieldValue = (MoneyValue) value;
		field.setText(value.getValue().toString());
	}

	public boolean equalValues(Value value) {
		BigDecimal bd = (BigDecimal) value.getValue();
		BigDecimal bd2 = (BigDecimal) this.fieldValue.getValue();

		return bd.compareTo(bd2) == 0;
	}

	public JTextField getField() {
		return this.field;
	}

	private int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
		return Math.max(0, bigDecimal.stripTrailingZeros().scale());
	}

	@Override
	public Value getValue() {
		return fieldValue;
	}

}