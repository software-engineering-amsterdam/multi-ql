package nl.nicasso.ql.gui.questionFields.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.values.IntegerValue;
import nl.nicasso.ql.values.StringValue;

public class IntegerFieldListener extends FieldListener implements KeyListener {

	public IntegerFieldListener(Identifier identifier) {
		super(identifier);
	}
	
	private void respondToAction(Integer value) {
		fieldValueChanged(new IntegerValue(value));
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
		respondToAction(Integer.parseInt(source.getText()));
	}
}