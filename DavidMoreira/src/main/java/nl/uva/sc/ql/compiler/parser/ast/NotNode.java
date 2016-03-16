package nl.uva.sc.ql.compiler.parser.ast;

import nl.uva.sc.ql.compiler.parser.value.BooleanVal;
import nl.uva.sc.ql.compiler.parser.value.Value;
import nl.uva.sc.ql.compiler.typechecker.Visitor;
import nl.uva.sc.ql.gui.VisitorExpression;
import nl.uva.sc.ql.gui.state.State;

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
	public void accept(VisitorExpression visitorExpression){
		visitorExpression.visit(this);
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
