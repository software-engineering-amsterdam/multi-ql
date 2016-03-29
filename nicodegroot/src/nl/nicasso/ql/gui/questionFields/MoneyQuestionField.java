package nl.nicasso.ql.gui.questionFields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

import javax.swing.JLabel;
import javax.swing.JTextField;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.gui.Observer;
import nl.nicasso.ql.gui.QuestionFieldArguments;
import nl.nicasso.ql.gui.evaluator.values.IntegerValue;
import nl.nicasso.ql.gui.evaluator.values.MoneyValue;
import nl.nicasso.ql.gui.evaluator.values.Value;

public class MoneyQuestionField extends QuestionField {

	private Identifier identifier;
	private JTextField field;
	private JLabel feedback;
	private Observer main;
	private MoneyValue value;
	
	public MoneyQuestionField(QuestionFieldArguments params) {
		this.identifier = params.getIdentifier();
		this.main = params.getMain();
		
		setupField(params.isEnabled(), (MoneyValue) params.getValue());	
	}
	
	public void setFeedbackField(JLabel feedback) {
		this.feedback = feedback;
	}
	
	private void setupField(boolean enabled, MoneyValue value) {
		field = new JTextField();
		field.setColumns(20);
		field.setEnabled(enabled);
		
		System.out.println("setupField");
		
		setValue(value);
		
		if (enabled) {
			addListenerToField();
		}
	}
	
	private void addListenerToField() {
		field.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				boolean parseSuccess = true;
				
				MoneyValue value = new MoneyValue(BigDecimal.valueOf(0.00));
				
				if (!field.getText().equals("")) {
					try {
						value = new MoneyValue(BigDecimal.valueOf(Double.parseDouble(field.getText())));
					} catch (Exception ex) {
						feedback.setText("This is not a valid decimal number.");
						parseSuccess = false;
					}
				}
				
				if (parseSuccess) {
					// Does too much?!
					if (getNumberOfDecimalPlaces(BigDecimal.valueOf(Double.parseDouble(field.getText()))) > 2) {
						feedback.setText("No more than 2 decimals allowed.");
						parseSuccess = false;
					} else {
						feedback.setText("");
						
						main.updateValueInStateTable(identifier, value);
						main.updateGUIPanels();
					}
				}
			}
			
		});
	}
	
	public void setValue(Value value) {

		if (value instanceof IntegerValue) {
			value = new MoneyValue(BigDecimal.valueOf(Double.parseDouble(value.getValue().toString())));
		}
		
		this.value = (MoneyValue) value;
		field.setText(value.getValue().toString());
	}
	
	public MoneyValue getValue() {
		return value;
	}
	
	public boolean equalValues(Value value) {
		
		if (value instanceof IntegerValue) {
			value = new MoneyValue(BigDecimal.valueOf(Double.parseDouble(value.getValue().toString())));
		}
		
		BigDecimal bd = (BigDecimal) value.getValue();
		BigDecimal bd2 = (BigDecimal) this.value.getValue();

		return bd.compareTo(bd2) == 0;
	}
	
	public JTextField getField() {
		return this.field;
	}
	
	private int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
	    return Math.max(0, bigDecimal.stripTrailingZeros().scale());
	}
	
}