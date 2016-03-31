package nl.nicasso.ql.gui.questionFields;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import nl.nicasso.ql.gui.QuestionFieldArguments;
import nl.nicasso.ql.gui.evaluator.values.BooleanValue;
import nl.nicasso.ql.gui.evaluator.values.Value;

public class BooleanQuestionField extends QuestionField {

	private JCheckBox field;
	private Value fieldValue;

	public BooleanQuestionField(QuestionFieldArguments params) {
		super(params);

		setupField(params.isEnabled(), params.getValue());
	}

	private void setupField(boolean enabled, Value value) {
		field = new JCheckBox();
		field.setEnabled(enabled);

		setValue(value);

		if (enabled) {
			addListenerToField();
		}
	}

	private void addListenerToField() {
		field.addItemListener(new ItemListener() {

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
		field.setSelected((Boolean) value.getValue());
	}

	@Override
	public boolean equalValues(Value value) {
		return value.equals(this.fieldValue);
	}

	public JCheckBox getField() {
		return this.field;
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