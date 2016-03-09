package nl.nicasso.ql.gui.questionFields.listeners;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.values.IntegerValue;

public class IntegerFieldListener extends FieldListener implements DocumentListener {

	public IntegerFieldListener(Identifier identifier) {
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
		Integer value;
				
		try {
			value = Integer.parseInt(e.getDocument().getText(0, e.getDocument().getLength()));
			fieldValueChanged(new IntegerValue(value));
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}

	

}