package nl.uva.sc.ql.parser.ast;

public class StatementNode extends Node {

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
