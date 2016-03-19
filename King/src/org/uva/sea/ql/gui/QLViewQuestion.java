package org.uva.sea.ql.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.uva.sea.ql.ast.domain.Question;
import org.uva.sea.ql.ast.expr.type.BooleanType;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.evalutor.Value;
public class QLViewQuestion extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5263675203520807284L;
	private JLabel qLabel;
	private JRadioButton qIdentifier;
	private boolean qIdentifierState = false;
	private JTextField computed;
	private boolean isComputed;
	public QLViewQuestion(Question q) {
		qLabel = new JLabel(q.getText());
		if(q.getVariableId().getIdentifier().getType().equals(new BooleanType())){
			
			qIdentifier = new JRadioButton("yes",qIdentifierState);
			qIdentifier.setName(q.getVariableId().getIdentifier().getName());
		}else{
			isComputed = true;
			computed = new JTextField(5);
			computed.setName(q.getVariableId().getIdentifier().getName());
		}
		
	}
	
	public JLabel getqLabel() {
		return qLabel;
	}
	
	public boolean isComputedQuestion() {
		return isComputed;
	}
	
	public JRadioButton getqIdentifier() {
		return qIdentifier;
	}
	public JTextField getqComputed() {
		return computed;
	}


}
