package nl.nicasso.ql.gui.questionFields;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.gui.Observer;
import nl.nicasso.ql.gui.QuestionFieldArguments;
import nl.nicasso.ql.gui.evaluator.values.BooleanValue;
import nl.nicasso.ql.gui.evaluator.values.Value;

public class BooleanQuestionField extends QuestionField {

	private Identifier identifier;
	private JCheckBox field;
	private Observer main;
	private BooleanValue value;

	public BooleanQuestionField(QuestionFieldArguments params) {
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
				
				main.updateValueInStateTable(identifier, value);
				main.updateGUIPanels();
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
	
	public JCheckBox getField() {
		return this.field;
	}	
}