package org.uva.sea.ql.ast;

public abstract class ASTNode implements Visitable {
	protected int startLine;
	protected int endLine;
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	public int getStartLine() {
		return this.startLine;
	}
	
	@Override
	public abstract String toString();

	
}
