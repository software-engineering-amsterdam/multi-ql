package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.gui.VisitorExpression;
import nl.uva.sc.ql.gui.state.State;
import nl.uva.sc.ql.parser.Visitor;
import nl.uva.sc.ql.parser.value.BooleanVal;
import nl.uva.sc.ql.parser.value.Value;

public class BooleanNode extends ExpressionNode {

	private BooleanVal value;
	
	public BooleanNode(BooleanVal value){
		this.value = value;
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
		return value;
	}
}
