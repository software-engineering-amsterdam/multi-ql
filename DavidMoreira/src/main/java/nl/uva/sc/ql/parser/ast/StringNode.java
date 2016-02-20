package nl.uva.sc.ql.parser.ast;

public class StringNode extends VariableNode {

	@Override
	public String getType() {
		return "String";
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
