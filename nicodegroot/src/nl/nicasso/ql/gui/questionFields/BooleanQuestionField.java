package nl.nicasso.ql.gui.questionFields;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.gui.Observer;
import nl.nicasso.ql.gui.QuestionFieldParameter;
import nl.nicasso.ql.gui.evaluator.values.BooleanValue;
import nl.nicasso.ql.gui.widgets.Label;

public class BooleanQuestionField extends QuestionField {

	private Identifier identifier;
	private JCheckBox field;
	private Label label;
	private Observer main;

	public BooleanQuestionField(QuestionFieldParameter params) {
		this.identifier = params.getIdentifier();
		this.main = params.getMain();
		
		setupField(params.isEnabled());
	}
	
	private void setupField(boolean enabled) {
		field = new JCheckBox();
		field.setEnabled(enabled);
		
		addListenerToField();
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
	
	public void setValue(Object value) {
		field.setSelected((Boolean) value);
	}
	
	public boolean equalValues(Object value) {
		//System.out.println(value+" - "+field.isSelected() + " EQUALS? "+ value.equals((field.isSelected())));
		return value.equals(field.isSelected());
	}
	
	public void setFeedbackLabel(Label label) {
		this.label = label;
	}
	
	public JCheckBox getField() {
		return this.field;
	}	
}