package nl.nicasso.ql.gui.questionFields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

import nl.nicasso.ql.gui.QuestionFieldArguments;
import nl.nicasso.ql.gui.evaluator.values.MoneyValue;
import nl.nicasso.ql.gui.evaluator.values.StringValue;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.widgets.InterActiveWidget;
import nl.nicasso.ql.gui.widgets.TextfieldWidget;
import nl.nicasso.ql.gui.widgets.Widget;

public class MoneyQuestionField extends QuestionField {

	private InterActiveWidget textField;
	private Widget feedback;
	private Value fieldValue;

	public MoneyQuestionField(QuestionFieldArguments params) {
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

				MoneyValue newValue = new MoneyValue(BigDecimal.valueOf(0.00));

				BigDecimal decimal = new BigDecimal(0.00);
				
				if (!textField.getValue().equals("")) {
					try {
						decimal = BigDecimal.valueOf(Double.parseDouble((String) textField.getValue()));
						newValue = new MoneyValue(decimal);
					} catch (Exception ex) {
						feedback.setValue(new StringValue("This is not a valid decimal number."));
						parseSuccess = false;
					}
				}
				
				if (parseSuccess) {
					if (getNumberOfDecimalPlaces(decimal) > 2) {
						feedback.setValue(new StringValue("No more than 2 decimals allowed."));
					} else {
						feedback.setValue(new StringValue(""));

						updateValue(newValue);

						getMainWindow().updateValueInStateTable(getIdentifier(), newValue);
						getMainWindow().updateGUIPanels();
					}
				}
			}

		});
	}

	public void updateValue(Value value) {
		fieldValue = (MoneyValue) value;
	}

	public void updateValueAndTextfield(Value value) {
		updateValue(value);
		textField.setValue(value);
	}

	public boolean equalValues(Value value) {
		BigDecimal decimal1 = (BigDecimal) value.getValue();
		BigDecimal decimal2 = (BigDecimal) this.fieldValue.getValue();

		return decimal1.compareTo(decimal2) == 0;
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