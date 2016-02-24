package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;
import org.uva.sea.ql.ast.expr.Expr;

public class IfStatement extends ASTNode implements Visitable {
	private Block block;
	private Expr clause;
	
	public IfStatement(Block block, Expr clause, int startLine) {
		super.startLine = startLine;
		this.block = block;
		this.clause = clause;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this, null);
	}

	public Block getBlock() {
		return block;
	}

	public Expr getClause() {
		return clause;
	}
	
	@Override
	public String toString() {
		return "IfStatement";
	}
	
	public boolean getClauseValue() {
		try {
			return (Boolean) clause.eval();
		} catch (NullPointerException e) {
			System.out.println(e.toString());
			return false;
		}
	}
}
