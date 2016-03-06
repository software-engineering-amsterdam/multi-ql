package nl.nicasso.ql.gui.questionFields;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.JFormattedTextField;

public class IntegerQuestionField extends QuestionField {

	JFormattedTextField field;

	public IntegerQuestionField() {
		field = new JFormattedTextField(getIntegerFormatter());
	}
	
	public JFormattedTextField getField() {
		return this.field;
	}
	
	private DecimalFormat getIntegerFormatter() {
		DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
		unusualSymbols.setDecimalSeparator('.');
		unusualSymbols.setGroupingSeparator(',');

		String strange = "###,###";
		DecimalFormat weirdFormatter = new DecimalFormat(strange, unusualSymbols);
		weirdFormatter.setGroupingSize(3);

		String bizarre = weirdFormatter.format(12345.678);
		System.out.println(bizarre);
		    
		return weirdFormatter;
	}
	
}
