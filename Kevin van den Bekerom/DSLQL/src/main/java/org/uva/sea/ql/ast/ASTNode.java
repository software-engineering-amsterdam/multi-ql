package org.uva.sea.ql.ast;

public class ASTNode implements Visitable {

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
