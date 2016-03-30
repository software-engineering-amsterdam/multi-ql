package nl.nicasso.ql.gui.questionFields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.gui.Observer;
import nl.nicasso.ql.gui.QuestionFieldArguments;
import nl.nicasso.ql.gui.evaluator.values.IntegerValue;
import nl.nicasso.ql.gui.evaluator.values.Value;

public class IntegerQuestionField extends QuestionField {

	private Identifier identifier;
	private JTextField field;
	private JLabel feedback;
	private Observer main;
	private IntegerValue value;

	public IntegerQuestionField(QuestionFieldArguments params) {
		this.identifier = params.getIdentifier();
		this.main = params.getMain();
		
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
				
				IntegerValue value = new IntegerValue(0);
				
				if (!field.getText().equals("")) {
					try {
						value = new IntegerValue(Integer.parseInt(field.getText()));
					} catch (Exception ex) {
						feedback.setText("This is not a valid integer.");
						parseSuccess = false;
					}
				}
				
				if (parseSuccess) {
					feedback.setText("");
					
					main.updateValueInStateTable(identifier, value);
					main.updateGUIPanels();
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
	
	public JTextField getField() {
		return this.field;
	}
}