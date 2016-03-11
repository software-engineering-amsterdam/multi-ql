package uva.ql.ast.expressions.abstracts;

import uva.ql.ast.abstracts.Node;

public abstract class SingleLogicalOperator extends Expression {

	private Expression lhs;
	
	public SingleLogicalOperator(Node parent, int startLine, int startColumn, Expression lhs) {
		
		super(parent, startLine, startColumn);
		
		this.lhs = lhs;
	}

	public Expression getLhs() {
		
		return this.lhs;
	}
}
