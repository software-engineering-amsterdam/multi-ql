package nl.nicasso.ql.gui.questionFields;

import javax.swing.JFormattedTextField;

public class MoneyQuestionField extends QuestionField {

	JFormattedTextField field;

	public MoneyQuestionField() {
		field = new JFormattedTextField(java.text.NumberFormat.getCurrencyInstance());
		field.setSize(200,100);
	}
	
	public JFormattedTextField getField() {
		return this.field;
	}
	
}