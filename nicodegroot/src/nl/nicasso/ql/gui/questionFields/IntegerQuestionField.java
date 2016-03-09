package nl.nicasso.ql.gui.questionFields;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.gui.questionFields.listeners.IntegerFieldListener;
import nl.nicasso.ql.gui.questionFields.listeners.MoneyFieldListener;

public class IntegerQuestionField extends QuestionField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1175647935989853207L;
	
	private Identifier identifier;
	
	JTextField field;

	public IntegerQuestionField(Identifier identifier) {
		this.identifier = identifier;
		//field = new JFormattedTextField(getIntegerFormatter());
		field = new JTextField();
		field.getDocument().addDocumentListener(new IntegerFieldListener(identifier));
		field.setColumns(20);
	}
	
	public void setValue(Object value) {
		field.setText(value.toString());
	}
	
	public JTextField getField() {
		return this.field;
	}
	
	private DecimalFormat getIntegerFormatter() {
		DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
		unusualSymbols.setDecimalSeparator('.');
		unusualSymbols.setGroupingSeparator(',');

		String strange = "###,###";
		DecimalFormat weirdFormatter = new DecimalFormat(strange, unusualSymbols);
		weirdFormatter.setGroupingSize(3);
		    
		return weirdFormatter;
	}
	
}