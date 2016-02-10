package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;
import org.uva.sea.ql.ast.expr.Expr;

public class IfStatement extends Stat implements Visitable {
	private Block block;
	private Expr clause;
	
	public IfStatement(Block block, Expr clause) {
		this.block = block;
		this.clause = clause;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public Block getBlock() {
		return block;
	}

	public Expr getClause() {
		return clause;
	}
	
	
}
