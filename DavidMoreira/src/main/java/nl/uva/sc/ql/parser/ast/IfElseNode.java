package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.parser.Visitor;

public class IfElseNode extends Node {

	@Override
	public String getType() {
		return "None";
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
