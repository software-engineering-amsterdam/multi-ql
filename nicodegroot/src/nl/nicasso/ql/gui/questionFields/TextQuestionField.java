package nl.nicasso.ql.gui.questionFields;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.gui.questionFields.listeners.TextFieldListener;

public class TextQuestionField extends QuestionField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8063194427157178583L;
	
	private Identifier identifier;
	
	JTextField field;

	public TextQuestionField(Identifier identifier) {
		this.identifier = identifier;
		field = new JTextField();
		field.getDocument().addDocumentListener(new TextFieldListener(identifier));
		field.setColumns(20);
	}
	
	public void setValue(Object value) {
		field.setText((String) value);
	}
	
	public JTextField getField() {
		return this.field;
	}
	
}
