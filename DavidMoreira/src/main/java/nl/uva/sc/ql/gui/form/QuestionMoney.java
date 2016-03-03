package nl.uva.sc.ql.gui.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

import nl.uva.sc.ql.parser.ast.Node;

public class QuestionMoney extends Question {

	private static final long serialVersionUID = 1L;

	public QuestionMoney(String question, Node node, boolean editable) {
		super(question, node, editable);
	}

	@Override
	public JComponent createComponentWithValue() {		
		JTextField component = new JTextField(10);
		
		component.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				// Get the String entered into the input TextField, convert to double
	            double money = Double.parseDouble(component.getText());
	            setValue(money);
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
