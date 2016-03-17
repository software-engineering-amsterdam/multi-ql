package nl.nicasso.ql.gui.questionFields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.gui.Observer;
import nl.nicasso.ql.gui.QuestionFieldParameter;
import nl.nicasso.ql.gui.evaluator.values.BooleanValue;
import nl.nicasso.ql.gui.evaluator.values.IntegerValue;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.widgets.Label;

public class IntegerQuestionField extends QuestionField {

	private Identifier identifier;
	private JTextField field;
	private Label label;
	private Observer main;
	private IntegerValue value;

	public IntegerQuestionField(QuestionFieldParameter params) {
		this.identifier = params.getIdentifier();
		this.main = params.getMain();
		
		setupField(params.isEnabled(), (IntegerValue) params.getValue());
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
				
				IntegerValue value = new IntegerValue(0);
				
				if (!field.getText().equals("")) {
					try {
						value = new IntegerValue(Integer.parseInt(field.getText()));
					} catch (Exception ex) {
						label.setLabelText("This is not a valid integer.");
						parseSuccess = false;
					}
				}
				
				if (parseSuccess) {
					label.setLabelText("");
					
					main.fieldValueChanged(identifier, value);
					main.updateAllPanels();
				}
			}
			
		});
	}
	
	public void setValue(Value value) {
		this.value = (IntegerValue) value;
		field.setText(value.getValue().toString());
	}
	
	public IntegerValue getValue() {
		return value;
	}
	
	public boolean equalValues(Value value) {
		return value.equals(this.value);
	}
	
	public void setFeedbackLabel(Label label) {
		this.label = label;
	}
	
	public JTextField getField() {
		return this.field;
	}
}