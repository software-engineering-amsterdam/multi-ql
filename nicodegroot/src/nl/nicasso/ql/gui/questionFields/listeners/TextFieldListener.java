package nl.nicasso.ql.gui.questionFields.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.values.StringValue;

public class TextFieldListener extends FieldListener implements KeyListener {

	public TextFieldListener(Identifier identifier) {
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
		JTextField source = (JTextField) e.getSource();
		respondToAction(source.getText());
	}
	
	
	private void respondToAction(String value) {
		fieldValueChanged(new StringValue(value));
	}

}
