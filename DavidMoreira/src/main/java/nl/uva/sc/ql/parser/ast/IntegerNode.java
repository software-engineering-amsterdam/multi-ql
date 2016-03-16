package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.gui.state.State;
import nl.uva.sc.ql.parser.Visitor;
import nl.uva.sc.ql.parser.VisitorExpression;
import nl.uva.sc.ql.parser.value.IntegerVal;
import nl.uva.sc.ql.parser.value.Value;

public class IntegerNode extends ExpressionNode {

	private IntegerVal value;
	
	public IntegerNode(IntegerVal value){
		this.value = value;
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
		return value;
	}
}
