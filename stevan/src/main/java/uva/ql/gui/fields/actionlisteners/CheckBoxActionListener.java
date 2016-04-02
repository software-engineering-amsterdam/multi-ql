package uva.ql.gui.fields.actionlisteners;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;

import uva.ql.ast.variables.Variable;

public class CheckBoxActionListener extends AbstractAction {

	private JCheckBox cb;
	private Variable<Boolean> var;
	
	public CheckBoxActionListener(JCheckBox cb, Variable<Boolean> var) {
		this.cb = cb;
		this.var = var;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		var.setValue(cb.isSelected());
	}
}
