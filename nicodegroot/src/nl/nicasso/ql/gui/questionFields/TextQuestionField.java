package nl.nicasso.ql.gui.questionFields;

import javax.swing.JTextField;

public class TextQuestionField extends QuestionField {

	JTextField field;

	public TextQuestionField() {
		field = new JTextField();
		field.setSize(200, 100);
	}
	
	public JTextField getField() {
		return this.field;
	}
	
}
