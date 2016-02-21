package nl.uva.sc.ql.parser.ast;

public class IfNode extends IfElseNode {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
