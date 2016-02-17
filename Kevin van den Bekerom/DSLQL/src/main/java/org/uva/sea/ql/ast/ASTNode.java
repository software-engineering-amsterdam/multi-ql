package org.uva.sea.ql.ast;

public class ASTNode implements Visitable {
	protected int startLine;
	protected int endLine;
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
