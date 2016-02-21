package nl.uva.sc.ql.parser.ast;

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
