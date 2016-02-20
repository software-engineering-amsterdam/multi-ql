package nl.uva.sc.ql.parser.ast;

public class MoneyNode extends VariableNode {

	@Override
	public String getType() {
		return "Money";
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
