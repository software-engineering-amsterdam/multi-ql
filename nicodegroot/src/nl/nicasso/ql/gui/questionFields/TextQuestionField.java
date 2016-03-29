package nl.nicasso.ql.gui.questionFields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.gui.Observer;
import nl.nicasso.ql.gui.QuestionFieldArguments;
import nl.nicasso.ql.gui.evaluator.values.StringValue;
import nl.nicasso.ql.gui.evaluator.values.Value;

public class TextQuestionField extends QuestionField {

	private Identifier identifier;
	private JTextField field;
	private Observer main;
	private StringValue value;

	public TextQuestionField(QuestionFieldArguments params) {
		this.identifier = params.getIdentifier();
		this.main = params.getMain();
		
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
				StringValue value = new StringValue(field.getText());
				main.updateValueInStateTable(identifier, value);
				main.updateGUIPanels();
			}
			
		});
	}
	
	public void setValue(Value value) {
		this.value = (StringValue) value;
		field.setText(value.getValue().toString());
	}
	
	public StringValue getValue() {
		return value;
	}
	
	public boolean equalValues(Value value) {
		return value.equals(this.value);
	}
	
	public JTextField getField() {
		return this.field;
	}
	
}
