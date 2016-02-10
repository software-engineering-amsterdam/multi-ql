package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.ASTID;
import org.uva.sea.ql.ast.Visitor;
import org.uva.sea.ql.ast.expr.Expr;

public class IfStatement extends Stat {
	private Block block;
	private Expr clause;
	
	public IfStatement(Block block, Expr clause) {
		super(ASTID.IFSTATEMENT);
		this.block = block;
		this.clause = clause;
	}
	
	public void accept(Visitor visitor) {
		clause.accept(visitor);
		block.accept(visitor);
		visitor.visit(this);
	}
}
