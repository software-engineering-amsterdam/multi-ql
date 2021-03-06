package nl.uva.sc.ql.compiler.parser.ast;

import nl.uva.sc.ql.compiler.parser.value.IntegerVal;
import nl.uva.sc.ql.compiler.parser.value.Value;
import nl.uva.sc.ql.compiler.typechecker.Visitor;
import nl.uva.sc.ql.gui.VisitorExpression;
import nl.uva.sc.ql.gui.state.State;

public class LessThanNode extends BinaryExpressionNode {

	public LessThanNode(ExpressionNode left, ExpressionNode right){
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
			return ((IntegerVal) valueLeft).lessThan((IntegerVal) valueRight);
		}
		
		return null;
	}
}
