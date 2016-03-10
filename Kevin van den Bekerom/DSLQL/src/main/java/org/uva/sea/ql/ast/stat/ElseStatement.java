package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.visit.Visitable;
import org.uva.sea.ql.ast.visit.Visitor;

public class ElseStatement extends Statement implements Visitable {
	private Block block;
	
	public ElseStatement(Block block, int startLine) {
		super(startLine);
		this.block = block;
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	public Block getBlock() {
		return block;
	}
	
	@Override
	public String toString() {
		return "ElseStatement";
	}
}
