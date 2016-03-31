package nl.nicasso.ql.gui.widgets;

import java.awt.event.KeyListener;
import java.util.EventListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import nl.nicasso.ql.gui.evaluator.values.Value;

public class TextfieldWidget implements Widget {

	private JTextField field;
	
	public TextfieldWidget(Boolean enabled) {
		field = new JTextField();
		field.setColumns(20);
		field.setEnabled(enabled);
	}
	
	public void setValue(Value text) {
		field.setText(text.getValue().toString());
	}
	
	public String getValue() {
		return field.getText();
	}

	@Override
	public void addListener(EventListener listener) {
		field.addKeyListener((KeyListener) listener);
	}
	
	public void addSelfToPanel(JPanel panel) {
		panel.add(field);
	}

}