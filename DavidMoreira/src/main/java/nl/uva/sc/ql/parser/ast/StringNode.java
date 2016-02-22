package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.parser.Visitor;

public class StringNode extends Node {

	@Override
	public String getType() {
		return "String";
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
