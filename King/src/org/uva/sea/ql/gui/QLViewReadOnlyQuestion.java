package org.uva.sea.ql.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.uva.sea.ql.ast.domain.Question;

public class QLViewReadOnlyQuestion extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 182889614806773550L;
	private JLabel qLabel;
	private JTextField computed;
	
	public QLViewReadOnlyQuestion(Question q) {
		qLabel = new JLabel(q.getText());
		computed = new JTextField(5);
		computed.setName(q.getVariableId().getIdentifier().getName());
	}
	
	public JLabel getqLabel() {
		return qLabel;
	}
	
	public JTextField getqComputed() {
		return computed;
	}
	

}
