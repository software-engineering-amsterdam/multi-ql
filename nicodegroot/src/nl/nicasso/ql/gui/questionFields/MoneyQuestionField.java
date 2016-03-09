package nl.nicasso.ql.gui.questionFields;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.JFormattedTextField;

public class MoneyQuestionField extends QuestionField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3047877727295787300L;
	
	JFormattedTextField field;

	public MoneyQuestionField() {
		field = new JFormattedTextField(getMoneyFormatter());
		field.setSize(200,100);
		/*
		field.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("JKDSLADKAKRAW");
				
			}
		});
		
		field.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		*/
	}
	
	public void setValue(Object value) {
		field.setValue((BigDecimal) value);
	}
	
	public JFormattedTextField getField() {
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