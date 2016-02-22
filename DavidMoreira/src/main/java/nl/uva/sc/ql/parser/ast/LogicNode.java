package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.parser.Visitor;

public class LogicNode extends ExpressionNode {

	@Override
	public String getType() {
		return "boolean";
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
