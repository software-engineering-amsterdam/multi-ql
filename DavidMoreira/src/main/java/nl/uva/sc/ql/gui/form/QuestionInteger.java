package nl.uva.sc.ql.gui.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

import nl.uva.sc.ql.parser.ast.Node;

public class QuestionInteger extends Question {

	private static final long serialVersionUID = 1L;

	public QuestionInteger(String question, Node node, boolean editable) {
		super(question, node, editable);
	}

	@Override
	public JComponent createComponentWithValue() {
		JTextField component = new JTextField(10);
		
		component.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				// Get the String entered into the input TextField, convert to int
	            int integer = Integer.parseInt(component.getText());
	            setValue(integer);
	        }
	    });
		
		component.setEditable(isEditable());
		String valueText = getValuetoString();
		valueText = (valueText == null) ? "" : valueText;
		component.setText(valueText);
		
		return component;
	}
	
	@Override
	public void update() {
		String valueText = getValuetoString();
		valueText = (valueText == null) ? "" : valueText;
		((JTextField) getComponent()).setText(valueText);
	}
}
