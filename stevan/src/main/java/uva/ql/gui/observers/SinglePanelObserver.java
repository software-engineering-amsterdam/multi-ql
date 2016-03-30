package uva.ql.gui.observers;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.gui.EnableDisablePanel;

public class SinglePanelObserver extends EnableDisablePanel implements Observer {

	private final JPanel panel;
	private final Expression<Boolean> exp;
	
	public SinglePanelObserver(JPanel panel, Expression<Boolean> exp) {
        this.panel = panel;
        this.exp = exp;
        this.enablePanel(panel, this.exp.eval());
    }

	@Override
	public void update(Observable o, Object arg) {
		this.enablePanel(panel, this.exp.eval());
	}
}
