package nl.uva.sc.ql.gui.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;

import nl.uva.sc.ql.parser.ast.Node;

public class QuestionBoolean extends Question {

	private static final long serialVersionUID = 1L;

	public QuestionBoolean(String question, Node node, boolean editable) {
		super(question, node, editable);
	}

	@Override
	public JComponent getComponent(boolean editable, String valueText) {
		JCheckBox component = new JCheckBox();
		
		component.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				if (component.isSelected()){
					setValue(true);
				} else {
					setValue(false);
				}				
	        }
	    });
				
		component.setEnabled(editable);
		boolean value = (valueText == null) ? false : Boolean.parseBoolean(valueText);
		component.setSelected(value);
		
		return component;
	}
}
