package uva.ql.ast.expressions.abstracts;

import uva.ql.ast.abstracts.Node;

public abstract class ArithmeticOperator extends Expression {

	private Node lhs, rhs;
	
	public ArithmeticOperator(Node parent, int startLine, int startColumn, Node lhs, Node rhs) {
		
		super(parent, startLine, startColumn);
		
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public Node getLhs() {
		
		return this.lhs;
	}
	
	public Node getRhs() {
		
		return this.rhs;
	}
}
