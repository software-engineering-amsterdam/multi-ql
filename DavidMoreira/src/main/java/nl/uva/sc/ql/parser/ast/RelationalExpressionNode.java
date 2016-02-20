package nl.uva.sc.ql.parser.ast;

public class RelationalExpressionNode extends ExpressionNode {

	@Override
	public String getType() {
		return "boolean";
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
