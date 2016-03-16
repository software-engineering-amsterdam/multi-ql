package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.gui.VisitorExpression;
import nl.uva.sc.ql.gui.state.State;
import nl.uva.sc.ql.parser.Visitor;
import nl.uva.sc.ql.parser.value.IntegerVal;
import nl.uva.sc.ql.parser.value.Value;

public class SubtractionNode extends BinaryExpressionNode {

	public SubtractionNode(ExpressionNode left, ExpressionNode right){
		super(left, right);
	}

	@Override
	public String getType() {
		return "int";
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
			return ((IntegerVal) valueLeft).sub((IntegerVal) valueRight);
		}
		
		return null;
	}
}
