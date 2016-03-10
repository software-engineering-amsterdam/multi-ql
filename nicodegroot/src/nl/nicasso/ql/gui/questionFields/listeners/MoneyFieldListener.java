package nl.nicasso.ql.gui.questionFields.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;

import javax.swing.JTextField;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.values.MoneyValue;

public class MoneyFieldListener extends FieldListener implements KeyListener {

	public MoneyFieldListener(Identifier identifier) {
		super(identifier);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		try {
			JTextField source = (JTextField) e.getSource();
			respondToAction(BigDecimal.valueOf(Double.parseDouble(source.getText())));
		} catch (Exception ex) {
			System.out.println("LEER TYPEN");
		}
	}
	
	private void respondToAction(BigDecimal value) {
		fieldValueChanged(new MoneyValue(value));
	}

}
