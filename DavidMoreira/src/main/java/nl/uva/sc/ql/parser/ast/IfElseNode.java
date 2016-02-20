package nl.uva.sc.ql.parser.ast;

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
