package uva.ql.ast.expressions.abstracts;

import uva.ql.ast.abstracts.Node;

public abstract class RelationalOperator extends Expression {

	private Expression lhs, rhs;
	
	public RelationalOperator(Node parent, int startLine, int startColumn, Expression lhs, Expression rhs) {
		
		super(parent, startLine, startColumn);
		
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public Expression getLhs() {
		
		return this.lhs;
	}
	
	public Expression getRhs() {
		
		return this.rhs;
	}
}
