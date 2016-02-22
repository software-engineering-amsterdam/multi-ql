package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.parser.Visitor;

public class MathExpressionNode extends ExpressionNode {

	@Override
	public String getType() {
		return "money";
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}


}
