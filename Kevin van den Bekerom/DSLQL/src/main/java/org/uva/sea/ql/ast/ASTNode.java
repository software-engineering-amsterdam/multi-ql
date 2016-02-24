package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.visit.Visitable;

public abstract class ASTNode implements Visitable {
	protected int startLine = 0; // default value
	
	public int getStartLine() {
		return this.startLine;
	}
	
	@Override
	public abstract String toString();

	
}
