package uva.ql.gui.observers;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.gui.EnableDisablePanel;

public class DoublePanelObserver extends EnableDisablePanel implements Observer {

	private final JPanel panelLhs, panelRhs;
	private final Expression<Boolean> exp;
	
	public DoublePanelObserver(JPanel panelLhs, JPanel panelRhs, Expression<Boolean> exp) {
        this.panelLhs = panelLhs;
        this.panelRhs = panelRhs;
        this.exp = exp;
        this.enablePanel(panelLhs, this.exp.eval());
		this.enablePanel(panelRhs, !this.exp.eval());
    }

	@Override
	public void update(Observable o, Object arg) {
		this.enablePanel(panelLhs, this.exp.eval());
		this.enablePanel(panelRhs, !this.exp.eval());
	}
}
