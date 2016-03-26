package uva.ql.gui.fields.actionlisteners;

import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import uva.ql.ast.expressions.abstracts.Expression;

public class JCheckBoxActionListener extends AbstractActionListener {

	private JCheckBox cb;
	private final JPanel panel;
	private final Expression<Boolean> exp; 
	
	public JCheckBoxActionListener(final JCheckBox cb, Expression<Boolean> exp, final JPanel panel) {
		this.cb = cb;
		this.panel = panel;
		this.exp = exp;
		
		this.panel.setEnabled(this.exp.eval());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (cb.isSelected()) {
			this.panel.setEnabled(this.exp.eval());
			//enablePanel( panel, true);
		} else {
			this.panel.setEnabled(this.exp.eval());
			//enablePanel( panel, false);
			resetPanel(this.panel);
		}
	}

}
