package nl.nicasso.ql.gui.questionFields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.gui.Observer;
import nl.nicasso.ql.gui.QuestionFieldParameter;
import nl.nicasso.ql.gui.widgets.Label;
import nl.nicasso.ql.values.IntegerValue;

public class IntegerQuestionField extends QuestionField {

	private Identifier identifier;
	private JTextField field;
	private Label label;
	private Observer main;

	public IntegerQuestionField(QuestionFieldParameter params) {
		this.identifier = params.getIdentifier();
		this.main = params.getMain();
		
		setupField(params.isEnabled());
	}
	
	private void setupField(boolean enabled) {
		field = new JTextField();
		field.setColumns(20);
		field.setEnabled(enabled);
		
		addListenerToField();
	}
	
	private void addListenerToField() {
		field.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				boolean parseSuccess = true;
				
				try {
					IntegerValue value = new IntegerValue(Integer.parseInt(field.getText()));
					main.fieldValueChanged(identifier, value);
					main.updateAllPanels();
				} catch (Exception ex) {
					label.setLabelText("This is not a valid integer.");
					parseSuccess = false;
				}
				
				if (parseSuccess) {
					label.setLabelText("");
				}
			}
			
		});
	}
	
	public void setValue(Object value) {
		field.setText(value.toString());
	}
	
	public boolean equalValues(Object value) {
		//System.out.println(value+" - "+field.getText() + " EQUALS? "+ value.equals(Integer.parseInt(field.getText())));
		return value.equals(Integer.parseInt(field.getText()));
	}
	
	public void setFeedbackLabel(Label label) {
		this.label = label;
	}
	
	public JTextField getField() {
		return this.field;
	}
}