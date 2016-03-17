package nl.nicasso.ql.gui.questionFields;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;

import javax.swing.JCheckBox;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.gui.Observer;
import nl.nicasso.ql.gui.QuestionFieldParameter;
import nl.nicasso.ql.gui.evaluator.values.BooleanValue;
import nl.nicasso.ql.gui.evaluator.values.MoneyValue;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.widgets.Label;

public class BooleanQuestionField extends QuestionField {

	private Identifier identifier;
	private JCheckBox field;
	private Label label;
	private Observer main;
	private BooleanValue value;

	public BooleanQuestionField(QuestionFieldParameter params) {
		this.identifier = params.getIdentifier();
		this.main = params.getMain();
		
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
				BooleanValue value;
				
				if (e.getStateChange() == ItemEvent.SELECTED) {
					value = new BooleanValue(true);
				} else {
					value = new BooleanValue(false);
				}
				
				main.fieldValueChanged(identifier, value);
				main.updateAllPanels();
			}
		});
	}
	
	public void setValue(Value value) {
		this.value = (BooleanValue) value;
		field.setSelected((Boolean) value.getValue());
	}
	
	public BooleanValue getValue() {
		return value;
	}
	
	public boolean equalValues(Value value) {
		return value.equals(this.value);
	}
	
	public void setFeedbackLabel(Label label) {
		this.label = label;
	}
	
	public JCheckBox getField() {
		return this.field;
	}	
}