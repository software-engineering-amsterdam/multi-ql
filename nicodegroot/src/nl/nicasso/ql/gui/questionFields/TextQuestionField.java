package nl.nicasso.ql.gui.questionFields;

import javax.swing.JTextField;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.gui.questionFields.listeners.TextFieldListener;

public class TextQuestionField extends QuestionField {

	private Identifier identifier;
	
	JTextField field;

	public TextQuestionField(Identifier identifier) {
		this.identifier = identifier;
		field = new JTextField();
		field.setColumns(20);
		
		TextFieldListener listener = new TextFieldListener(identifier); 
		
		field.addKeyListener(listener);
	}
	
	public void setValue(Object value) {
		field.setText((String) value);
	}
	
	public JTextField getField() {
		return this.field;
	}
	
}
