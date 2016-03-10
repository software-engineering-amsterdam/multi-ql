package nl.nicasso.ql.gui.questionFields;

import javax.swing.JTextField;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.gui.questionFields.listeners.IntegerFieldListener;

public class IntegerQuestionField extends QuestionField {

	private Identifier identifier;
	
	JTextField field;

	public IntegerQuestionField(Identifier identifier) {
		this.identifier = identifier;
		field = new JTextField();
		
		field.setColumns(20);
		
		IntegerFieldListener listener = new IntegerFieldListener(identifier); 
		
		field.addKeyListener(listener);
	}
	
	public void setValue(Object value) {
		field.setText(value.toString());
	}
	
	public JTextField getField() {
		return this.field;
	}
}