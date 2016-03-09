package nl.nicasso.ql.gui.questionFields.listeners;

import java.math.BigDecimal;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.values.MoneyValue;

public class MoneyFieldListener extends FieldListener implements DocumentListener {

	public MoneyFieldListener(Identifier identifier) {
		super(identifier);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		documentEventGetValue(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		documentEventGetValue(e);	
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		documentEventGetValue(e);
	}

	private void documentEventGetValue(DocumentEvent e) {
		BigDecimal value;
		
		try {
			value = BigDecimal.valueOf(Double.valueOf(e.getDocument().getText(0, e.getDocument().getLength())));
			fieldValueChanged(new MoneyValue(value));
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}

}
