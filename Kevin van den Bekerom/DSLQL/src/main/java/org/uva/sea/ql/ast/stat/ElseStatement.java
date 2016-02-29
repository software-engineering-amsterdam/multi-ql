package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.visit.Visitable;
import org.uva.sea.ql.ast.visit.Visitor;

public class ElseStatement extends ASTNode implements Visitable {
	private Block block;
	
	public ElseStatement(Block block) {
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
