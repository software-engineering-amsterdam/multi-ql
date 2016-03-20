package nl.uva.ql.gui.panel;

import nl.uva.ql.ast.expression.Expression;
import nl.uva.ql.ast.literal.Identifier;
import nl.uva.ql.evaluator.Evaluator;
import nl.uva.ql.evaluator.value.BooleanValue;
import nl.uva.ql.evaluator.value.Value;

public class IfPanel extends Panel{
	
	protected final Expression expression;
	protected Panel ifBoxPanel;
	
	public IfPanel(Expression expression, Panel ifBoxPanel){
		this.expression = expression;
		this.ifBoxPanel = ifBoxPanel;
		setVisibility(false);
		// hidemode 3 is for not showing (not allocating space for) ifboxpanel, in case it's visibility is false
		this.addToPanel(ifBoxPanel, "growx, hidemode 3");
	}

	@Override
	public void update(Evaluator evaluator, Identifier identifier) {
		Value value = evaluator.evaluate(expression);
		if (!value.isUnknownValue()) {
			Boolean ifCondition = ((BooleanValue) value).getValue();
			if (ifCondition) {
				ifBoxPanel.update(evaluator, identifier);
				setVisibility(true);
			} else {
				setVisibility(false);
			}
		} else {
			setVisibility(false);
		}
	}
	
	private void setVisibility(boolean flag) {
		this.setVisible(flag);
		this.ifBoxPanel.setVisible(flag);
	}

}
