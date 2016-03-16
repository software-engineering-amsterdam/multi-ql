package nl.uva.sc.ql.gui.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

import nl.uva.sc.ql.compiler.parser.ast.ExpressionNode;
import nl.uva.sc.ql.compiler.parser.value.IntegerVal;
import nl.uva.sc.ql.compiler.parser.value.Value;
import nl.uva.sc.ql.gui.state.State;
import nl.uva.sc.ql.messages.warnings.UserInputWarning;

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
	            String text = component.getText();					
				String identifier = getIdentifier();
				Value value;
				
				try {
					if(text.isEmpty()){ 
		            	value = null;
		            } else {
		            	value = new IntegerVal(Integer.parseInt(text));
		            }
					
				} catch (NumberFormatException nfe) {
					String[] error = nfe.getMessage().split("For input string: ");
					String message = "It was expecting an integer, but instead it was "+error[1];
					new UserInputWarning(message, getQuestion());
					
					value = null;
				}
				
				getState().add(identifier, value);
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
		String identifier = getIdentifier();
		Value value = (getExpression() == null) ? getState().lookup(identifier) : getExpression().eval(getState());
		String valueText = (value == null) ? "" : value.toString();		
		((JTextField) getComponent()).setText(valueText);
	}
}
