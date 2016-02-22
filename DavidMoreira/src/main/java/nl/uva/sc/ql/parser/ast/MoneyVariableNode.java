package nl.uva.sc.ql.parser.ast;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class MoneyVariableNode extends VariableNode {

	@Override
	public String getType() {
		return "money";
	}

	@Override
	public JComponent getComponent() {		
		JTextField component = new JTextField(10);
		
		component.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				// Get the String entered into the input TextField, convert to int
	            boolean asd = Boolean.parseBoolean(component.getText());
	            //sum += numberIn;      // accumulate numbers entered into sum
	            //tfInput.setText("");  // clear input TextField
	            //tfOutput.setText(sum + ""); // display sum on the output TextField
	        }
	    });
		
		return component;
	}
	
}
