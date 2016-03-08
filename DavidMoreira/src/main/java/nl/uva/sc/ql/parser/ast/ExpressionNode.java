package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.gui.State;
import nl.uva.sc.ql.parser.value.Value;

public abstract class ExpressionNode extends Node {

	public abstract Value eval(State state);
	
	@Override
	public void dump() {
		System.out.println(getClass());
	}

}
