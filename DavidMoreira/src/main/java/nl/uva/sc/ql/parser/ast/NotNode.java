package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.gui.State;
import nl.uva.sc.ql.parser.Visitor;
import nl.uva.sc.ql.parser.value.BooleanVal;
import nl.uva.sc.ql.parser.value.Value;

public class NotNode extends UnaryExpression {
	
	public NotNode(ExpressionNode expression){
		super(expression);
	}
	
	@Override
	public String getType() {
		return "boolean";
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Value eval(State state) {
		ExpressionNode expression = this.getExpression();
		
		// update node value
		Value valueExpression = expression.eval(state);

		if (valueExpression != null){
			return ((BooleanVal) valueExpression).not();
		}
		
		return null;
	}
}
