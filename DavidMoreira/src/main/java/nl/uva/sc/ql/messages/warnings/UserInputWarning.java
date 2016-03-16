package nl.uva.sc.ql.messages.warnings;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UserInputWarning extends MyWarning {
	
	final JPanel panel = new JPanel();
	
	public UserInputWarning(String message, String question) {
		super(message);
		
		JOptionPane.showMessageDialog(panel, message, question, JOptionPane.WARNING_MESSAGE);
	}
}
