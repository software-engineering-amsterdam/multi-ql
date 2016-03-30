package uva.ql.gui.observers;

import java.math.BigDecimal;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JTextField;

import uva.ql.ast.expressions.abstracts.Expression;

public class ComputedQuestionObserver implements Observer {

	private final JTextField textField;
	private final Expression exp;
	
	public ComputedQuestionObserver(JComponent component, Expression exp) {
        this.textField = (JTextField) component;
        this.exp = exp;
        this.textField.setEditable(false);
        this.textField.setFocusable(false);
    }

	@Override
	public void update(Observable o, Object arg) {
		BigDecimal val = new BigDecimal(this.exp.eval().toString());
		val = val.divide(new BigDecimal("10000"));
		String temp = String.format("%.2f", val);
		temp = temp.replace(",", ".");
		this.textField.setText(temp);
	}
}
