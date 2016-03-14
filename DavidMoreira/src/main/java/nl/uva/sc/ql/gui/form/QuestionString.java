package nl.uva.sc.ql.gui.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

import nl.uva.sc.ql.gui.state.State;
import nl.uva.sc.ql.parser.ast.ExpressionNode;
import nl.uva.sc.ql.parser.value.StringVal;
import nl.uva.sc.ql.parser.value.Value;

public class QuestionString extends Question {

	private static final long serialVersionUID = 1L;
	
	public QuestionString(State state, String question, String identifier, ExpressionNode expression, boolean editable) {
		super(state, question, identifier, expression, editable);
	}

	@Override
	public JComponent createComponentWithValue() {
		JTextField component = new JTextField(10);
		
		component.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				String string = component.getText();
				String identifier = getIdentifier();
				Value value = new StringVal(string);
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
