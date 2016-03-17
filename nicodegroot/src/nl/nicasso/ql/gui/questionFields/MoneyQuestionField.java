package nl.nicasso.ql.gui.questionFields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

import javax.swing.JTextField;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.gui.Observer;
import nl.nicasso.ql.gui.QuestionFieldParameter;
import nl.nicasso.ql.gui.evaluator.values.MoneyValue;
import nl.nicasso.ql.gui.evaluator.values.StringValue;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.widgets.Label;

public class MoneyQuestionField extends QuestionField {

	private Identifier identifier;
	private JTextField field;
	private Label label;
	private Observer main;
	private MoneyValue value;
	
	public MoneyQuestionField(QuestionFieldParameter params) {
		this.identifier = params.getIdentifier();
		this.main = params.getMain();
		
		System.out.println("MoneyQuestionField");
		
		setupField(params.isEnabled(), (MoneyValue) params.getValue());	
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
						label.setLabelText("This is not a valid decimal number.");
						parseSuccess = false;
					}
				}
				
				if (parseSuccess) {
					// Does too much?!
					if (getNumberOfDecimalPlaces(BigDecimal.valueOf(Double.parseDouble(field.getText()))) > 2) {
						label.setLabelText("No more than 2 decimals allowed.");
						parseSuccess = false;
					} else {
						label.setLabelText("");
						
						main.fieldValueChanged(identifier, value);
						main.updateAllPanels();
					}
				}
			}
			
		});
	}
	
	public void setValue(Value value) {
		System.out.println("SETVALUE FOR MONEY: "+value.getValue().toString());
		this.value = (MoneyValue) value;
		field.setText(value.getValue().toString());
	}
	
	public MoneyValue getValue() {
		return value;
	}
	
	public boolean equalValues(Value value) {
		BigDecimal bd = (BigDecimal) value.getValue();
		BigDecimal bd2 = (BigDecimal) this.value.getValue();

		return bd.compareTo(bd2) == 0;
	}
	
	public void setFeedbackLabel(Label label) {
		this.label = label;
	}
	
	public JTextField getField() {
		return this.field;
	}
	
	private int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
	    return Math.max(0, bigDecimal.stripTrailingZeros().scale());
	}
	
}