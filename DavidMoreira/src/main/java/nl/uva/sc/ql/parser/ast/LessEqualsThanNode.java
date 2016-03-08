package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.gui.State;
import nl.uva.sc.ql.parser.Visitor;
import nl.uva.sc.ql.parser.value.IntegerVal;
import nl.uva.sc.ql.parser.value.Value;

public class LessEqualsThanNode extends BinaryExpressionNode {

	public LessEqualsThanNode(ExpressionNode left, ExpressionNode right){
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
	public Value eval(State state) {
		ExpressionNode left = this.getLeft();
		ExpressionNode right = this.getRight();
		
		// update node values
		Value valueLeft = left.eval(state);
		Value valueRight = right.eval(state);

		if (valueLeft != null && valueRight != null){
			return ((IntegerVal) valueLeft).lessEqualsThan((IntegerVal) valueRight);
		}
		
		return null;
	}
}
