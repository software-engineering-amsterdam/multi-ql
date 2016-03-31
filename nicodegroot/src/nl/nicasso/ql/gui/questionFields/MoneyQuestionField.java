package nl.nicasso.ql.gui.questionFields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

import javax.swing.JLabel;

import nl.nicasso.ql.gui.QuestionFieldArguments;
import nl.nicasso.ql.gui.evaluator.values.IntegerValue;
import nl.nicasso.ql.gui.evaluator.values.MoneyValue;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.widgets.InterActiveWidget;
import nl.nicasso.ql.gui.widgets.TextfieldWidget;
import nl.nicasso.ql.gui.widgets.Widget;

public class MoneyQuestionField extends QuestionField {

	private InterActiveWidget textField;
	private JLabel feedback;
	private Value fieldValue;

	public MoneyQuestionField(QuestionFieldArguments params) {
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

	// THIS IS ONE BIG MESS!
	private void addListenerToField() {
		textField.addListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				boolean parseSuccess = true;

				MoneyValue newValue = new MoneyValue(BigDecimal.valueOf(0.00));

				if (!textField.getValue().equals("")) {
					try {
						newValue = new MoneyValue(BigDecimal.valueOf(Double.parseDouble((String) textField.getValue())));
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
		textField.setValue(value);
	}

	public boolean equalValues(Value value) {
		BigDecimal bd = (BigDecimal) value.getValue();
		BigDecimal bd2 = (BigDecimal) this.fieldValue.getValue();

		return bd.compareTo(bd2) == 0;
	}

	public Widget getField() {
		return this.textField;
	}

	private int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
		return Math.max(0, bigDecimal.stripTrailingZeros().scale());
	}

	@Override
	public Value getValue() {
		return fieldValue;
	}

}