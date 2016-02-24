package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.parser.Visitor;

public class AssignVariableNode extends Node {

	@Override
	public String getType() {
		return super.getLeft().getType();
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
