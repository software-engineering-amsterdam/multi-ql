package nl.nicasso.ql.gui.questionFields;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.gui.questionFields.listeners.MoneyFieldListener;
import nl.nicasso.ql.gui.questionFields.listeners.TextFieldListener;

public class MoneyQuestionField extends QuestionField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3047877727295787300L;
	
	private Identifier identifier;
	
	JTextField field;

	public MoneyQuestionField(Identifier identifier) {
		this.identifier = identifier;
		//field = new JFormattedTextField(getMoneyFormatter());
		field = new JTextField();
		field.getDocument().addDocumentListener(new MoneyFieldListener(identifier));
		field.setColumns(20);
	}
	
	public void setValue(Object value) {
		field.setText(value.toString());
	}
	
	public JTextField getField() {
		return this.field;
	}
	
	private DecimalFormat getMoneyFormatter() {
		DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
		unusualSymbols.setDecimalSeparator('.');
		unusualSymbols.setGroupingSeparator(',');

		String strange = "###,###.##";
		DecimalFormat weirdFormatter = new DecimalFormat(strange, unusualSymbols);
		weirdFormatter.setGroupingSize(3);
	    
		return weirdFormatter;
	}
	
}