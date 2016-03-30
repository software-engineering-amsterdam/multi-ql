package uva.ql.gui.observers;

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
    }

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Computed answer: " + this.exp.eval());
	}
}
