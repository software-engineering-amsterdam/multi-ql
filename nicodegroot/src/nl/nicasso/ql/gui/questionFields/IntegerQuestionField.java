package nl.nicasso.ql.gui.questionFields;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

public class IntegerQuestionField extends QuestionField {

	JFormattedTextField field;

	public IntegerQuestionField() {
		field = new JFormattedTextField(NumberFormat.getIntegerInstance());
	}
	
	public JFormattedTextField getField() {
		return this.field;
	}
	
}
