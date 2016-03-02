package nl.uva.sc.ql.gui.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

import nl.uva.sc.ql.parser.ast.Node;

public class QuestionString extends Question {

	private static final long serialVersionUID = 1L;
	
	public QuestionString(String question, Node node, boolean editable) {
		super(question, node, editable);
	}

	@Override
	public JComponent getComponent(boolean editable, String valueText) {
		JTextField component = new JTextField(10);
		
		component.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				// Get the String entered into the input TextField
	            String string = component.getText();
	            setValue(string);
	        }
	    });
		
		component.setEditable(editable);
		valueText = (valueText == null) ? "" : valueText;
		component.setText(valueText);
		
		return component;
	}
}
