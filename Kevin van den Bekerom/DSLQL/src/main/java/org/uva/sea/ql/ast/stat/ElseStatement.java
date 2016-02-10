package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.ASTID;
import org.uva.sea.ql.ast.Visitor;

public class ElseStatement extends Stat {
	private Block block;
	
	public ElseStatement(Block block) {
		super(ASTID.ELSESTATEMENT);
		this.block = block;
	}
	
	public void accept(Visitor visitor) {
		block.accept(visitor);
		visitor.visit(this);
	}
}
