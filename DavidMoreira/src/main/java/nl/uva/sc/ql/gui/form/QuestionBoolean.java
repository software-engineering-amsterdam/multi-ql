package nl.uva.sc.ql.gui.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;

import nl.uva.sc.ql.gui.State;
import nl.uva.sc.ql.parser.ast.ExpressionNode;
import nl.uva.sc.ql.parser.ast.IdentifierNode;
import nl.uva.sc.ql.parser.value.BooleanVal;
import nl.uva.sc.ql.parser.value.Value;

public class QuestionBoolean extends Question {

	private static final long serialVersionUID = 1L;

	public QuestionBoolean(State state, String question, String identifier, ExpressionNode expression, boolean editable) {
		super(state, question, identifier, expression, editable);
	}

	@Override
	public JComponent createComponentWithValue() {
		JCheckBox component = new JCheckBox();
		
		component.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				if (component.isSelected()){
					IdentifierNode in = new IdentifierNode(getIdentifier());
					Value value = new BooleanVal(true);
					getState().add(in, value);					
				} else {
					IdentifierNode in = new IdentifierNode(getIdentifier());
					Value value = new BooleanVal(false);
					getState().add(in, value);					
				}				
	        }
	    });
				
		component.setEnabled(isEditable());
		Value value = (getExpression() == null) ? null : getExpression().eval(getState());
		boolean bool = (value == null) ? false : (boolean) value.getValue();
		component.setSelected(bool);
		
		return component;
	}
	
	@Override
	public void update() {
		IdentifierNode in = new IdentifierNode(getIdentifier());
		Value value = getState().lookup(in);		
		boolean bool = (value == null) ? false : (boolean) value.getValue();		
		((JCheckBox) getComponent()).setSelected(bool);
	}
}
