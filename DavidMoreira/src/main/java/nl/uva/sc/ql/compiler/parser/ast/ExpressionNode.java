package nl.uva.sc.ql.compiler.parser.ast;

import nl.uva.sc.ql.compiler.parser.value.Value;
import nl.uva.sc.ql.gui.VisitorExpression;
import nl.uva.sc.ql.gui.state.State;

public abstract class ExpressionNode extends Node {

	public abstract Value eval(State state);
	
	@Override
	public void dump() {
		System.out.println(getClass());
	}
	
	public abstract void accept(VisitorExpression visitorExpression);

}
