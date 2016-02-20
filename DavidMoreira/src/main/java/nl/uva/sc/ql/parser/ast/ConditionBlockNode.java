package nl.uva.sc.ql.parser.ast;

public class ConditionBlockNode extends Node {
		
	@Override
	public String getType() {
		return "None";
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
