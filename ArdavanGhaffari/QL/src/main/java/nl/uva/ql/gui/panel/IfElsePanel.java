package nl.uva.ql.gui.panel;

import nl.uva.ql.ast.expression.Expression;
import nl.uva.ql.ast.literal.Identifier;
import nl.uva.ql.evaluator.Evaluator;
import nl.uva.ql.evaluator.value.BooleanValue;
import nl.uva.ql.evaluator.value.Value;

public class IfElsePanel extends IfPanel{
	
	private Panel elseBoxPanel;
	
	public IfElsePanel(Expression expression, Panel ifBoxPanel, Panel elseBoxPanel) {
		super(expression, ifBoxPanel);
		this.elseBoxPanel = elseBoxPanel;
		this.elseBoxPanel.setVisible(false);
		addToPanel(elseBoxPanel, "growx, hidemode 3");
	}
	
	@Override
	public void update(Evaluator evaluator, Identifier identifier) {
		Value value = evaluator.evaluate(expression);
		if (!value.isUnknownValue()) {
			Boolean ifElseCondition = ((BooleanValue)value).getValue();
			if(ifElseCondition){
				ifBoxPanel.update(evaluator, identifier);
				switchVisibility(true);
			} else {
				elseBoxPanel.update(evaluator, identifier);
				switchVisibility(false);
			}
		} else {
			this.setVisible(false);
			this.ifBoxPanel.setVisible(false);
			this.elseBoxPanel.setVisible(false);
		}
	}
	
	private void switchVisibility(boolean flag) {
		this.setVisible(true);
		ifBoxPanel.setVisible(flag);
		elseBoxPanel.setVisible(!flag);
	}
	
}
