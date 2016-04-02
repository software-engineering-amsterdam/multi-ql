package uva.ql.gui.observers;

import java.util.Observable;

import javax.swing.JPanel;

import uva.ql.ast.expressions.abstracts.Expression;

public class DoublePanelObserver extends SinglePanelObserver {

	private final JPanel panelRhs;
	
	public DoublePanelObserver(JPanel panelLhs, JPanel panelRhs, Expression<Boolean> exp) {
		super(panelLhs, exp);
        this.panelRhs = panelRhs;
		this.enablePanel(panelRhs, !this.exp.eval());
    }

	@Override
	public void update(Observable o, Object arg) {
		this.enablePanel(panelLhs, this.exp.eval());
		this.enablePanel(panelRhs, !this.exp.eval());
	}
}
