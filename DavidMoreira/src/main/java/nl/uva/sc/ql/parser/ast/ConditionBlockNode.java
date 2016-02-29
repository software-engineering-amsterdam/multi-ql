package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.parser.Visitor;

public class ConditionBlockNode extends Node {
			
	@Override
	public String getType() {
		return "None";
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void dump() {
		System.out.println(this.getClass());
		if (getLeft() != null) { getLeft().dump(); }
		if (getRight() != null) { getRight().dump(); }
	}

}
