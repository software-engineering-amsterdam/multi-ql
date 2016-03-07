package nl.uva.sc.ql.gui.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

import nl.uva.sc.ql.gui.State;
import nl.uva.sc.ql.parser.ast.ExpressionNode;
import nl.uva.sc.ql.parser.ast.IdentifierNode;
import nl.uva.sc.ql.parser.value.IntegerVal;
import nl.uva.sc.ql.parser.value.Value;

public class QuestionInteger extends Question {

	private static final long serialVersionUID = 1L;

	public QuestionInteger(State state, String question, String identifier, ExpressionNode expression, boolean editable) {
		super(state, question, identifier, expression, editable);
	}

	@Override
	public JComponent createComponentWithValue() {
		JTextField component = new JTextField(10);
		
		component.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				// Get the String entered into the input TextField, convert to int
	            int integer = Integer.parseInt(component.getText());
				IdentifierNode in = new IdentifierNode(getIdentifier());
				Value value = new IntegerVal(integer);
				getState().add(in, value);	
			}
	    });
		
		component.setEditable(isEditable());
		Value value = (getExpression() == null) ? null : getExpression().eval(getState());
		String valueText = (value == null) ? "" : value.toString();
		component.setText(valueText);
		
		return component;
	}
	
	@Override
	public void update() {
		IdentifierNode in = new IdentifierNode(getIdentifier());
		Value value = (getExpression() == null) ? getState().lookup(in) : getExpression().eval(getState());
		String valueText = (value == null) ? "" : value.toString();		
		((JTextField) getComponent()).setText(valueText);
	}
}
