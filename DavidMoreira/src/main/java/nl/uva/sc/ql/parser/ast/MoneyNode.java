package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.parser.Visitor;

public class MoneyNode extends Node {

	@Override
	public String getType() {
		return "money";
	}
	
	@Override
	public void accept(Visitor visitor) {
	}

	@Override
	public void dump() {
		System.out.println(this.getClass());
		if (getLeft() != null) { getLeft().dump(); }
		if (getRight() != null) { getRight().dump(); }
	}

}
