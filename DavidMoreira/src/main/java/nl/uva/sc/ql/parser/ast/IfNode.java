package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.parser.Visitor;

public class IfNode extends IfElseNode {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
