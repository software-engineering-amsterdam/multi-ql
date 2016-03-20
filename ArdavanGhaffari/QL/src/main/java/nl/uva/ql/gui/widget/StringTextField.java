package nl.uva.ql.gui.widget;

import java.awt.Dimension;
import java.text.MessageFormat;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import nl.uva.ql.evaluator.Evaluator;
import nl.uva.ql.evaluator.value.StringValue;
import nl.uva.ql.evaluator.value.Value;
import nl.uva.ql.gui.QLFrame;

public class StringTextField extends Widget implements DocumentListener{
	
	private JTextField stringTextField;

	public StringTextField(Evaluator evaluator, QLFrame form) {
		super(evaluator, form);
		this.stringTextField = new JTextField();
		this.stringTextField.setPreferredSize(new Dimension(100, 25));
		this.stringTextField.getDocument().addDocumentListener(this);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		updateValue(e);
		stringTextField.requestFocus();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		updateValue(e);
		stringTextField.requestFocus();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	@Override
	public JComponent getComponent() {
		return stringTextField;
	}

	@Override
	public void setValue(Value value) {
		stringTextField.setText(value.toString());
	}
	
	private void updateValue(DocumentEvent event) {
		try {
			String input = event.getDocument().getText(0, event.getDocument().getLength());
			valueChanged(new StringValue(input));
		} catch (BadLocationException e) {
			giveError(MessageFormat.format("Error in String text field ''{0}'' : {1}", this.getIdentifier().getName(), e.getMessage()));
		}
	}

}
