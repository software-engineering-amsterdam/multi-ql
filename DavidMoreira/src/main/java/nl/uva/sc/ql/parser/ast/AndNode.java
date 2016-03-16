package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.gui.state.State;
import nl.uva.sc.ql.parser.Visitor;
import nl.uva.sc.ql.parser.VisitorExpression;
import nl.uva.sc.ql.parser.value.BooleanVal;
import nl.uva.sc.ql.parser.value.Value;

public class AndNode extends BinaryExpressionNode {

	public AndNode(ExpressionNode left, ExpressionNode right){
		super(left, right);
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
		ExpressionNode left = this.getLeft();
		ExpressionNode right = this.getRight();
		
		// update node values
		Value valueLeft = left.eval(state);
		Value valueRight = right.eval(state);

		if (valueLeft != null && valueRight != null){
			return ((BooleanVal) valueLeft).and((BooleanVal) valueRight);
		}
		
		return null;
	}
}
