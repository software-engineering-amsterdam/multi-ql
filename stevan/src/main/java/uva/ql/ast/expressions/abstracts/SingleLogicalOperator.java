package uva.ql.ast.expressions.abstracts;

import uva.ql.ast.abstracts.Node;

public abstract class SingleLogicalOperator extends Expression {

	private Node lhs;
	
	public SingleLogicalOperator(Node parent, int startLine, int startColumn, Node lhs) {
		
		super(parent, startLine, startColumn);
		
		this.lhs = lhs;
	}

	public Node getLhs() {
		
		return this.lhs;
	}
}
