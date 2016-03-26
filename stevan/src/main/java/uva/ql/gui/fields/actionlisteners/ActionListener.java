package uva.ql.gui.fields.actionlisteners;

import javax.swing.JPanel;

import uva.ql.ast.expressions.abstracts.Expression;

public abstract class ActionListener extends AbstractActionListener {
	
	public static void enablePanel( JPanel panel, Expression<Boolean> exp) {
		
		panel.setEnabled(exp.eval());
	}
}
