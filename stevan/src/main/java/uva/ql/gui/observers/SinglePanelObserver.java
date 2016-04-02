package uva.ql.gui.observers;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.gui.EnableDisablePanel;

public class SinglePanelObserver extends EnableDisablePanel implements Observer {

	protected final JPanel panelLhs;
	protected final Expression<Boolean> exp;
	
	public SinglePanelObserver(JPanel panelLhs, Expression<Boolean> exp) {
        this.panelLhs = panelLhs;
        this.exp = exp;
        this.enablePanel(panelLhs, this.exp.eval());
    }

	@Override
	public void update(Observable o, Object arg) {
		this.enablePanel(panelLhs, this.exp.eval());
	}
}
