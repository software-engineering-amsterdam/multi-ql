package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.visit.Visitable;
import org.uva.sea.ql.ast.visit.Visitor;

public class IfStatement extends Statement implements Visitable {
	private Block block;
	private Expr condition;
	
	public IfStatement(Block block, Expr clause, int startLine) {
		super(startLine);
		this.block = block;
		this.condition = clause;
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	public Block getBlock() {
		return block;
	}

	public Expr getCondition() {
		return condition;
	}
	
	@Override
	public String toString() {
		return "IfStatement";
	}
	
	public boolean getClauseValue() {
		try {
			return (Boolean) condition.eval();
		} catch (NullPointerException e) {
			System.out.println(e.toString());
			return false;
		}
	}
}
