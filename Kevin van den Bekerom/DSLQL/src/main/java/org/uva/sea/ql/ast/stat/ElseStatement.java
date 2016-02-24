package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;

public class ElseStatement extends ASTNode implements Visitable {
	private Block block;
	
	public ElseStatement(Block block) {
		this.block = block;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this, null);
	}

	public Block getBlock() {
		return block;
	}
	
	@Override
	public String toString() {
		return "ElseStatement";
	}
}
