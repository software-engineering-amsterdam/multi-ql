package uva.ql.ast.expressions.abstracts;

import uva.ql.ast.abstracts.Node;

public abstract class ArithmeticOperator extends Expression {

	private Expression lhs, rhs;
	
	public ArithmeticOperator(Node parent, int startLine, int startColumn, Expression lhs, Expression rhs) {
		super(parent, startLine, startColumn);
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public Expression getLhs() {
		return this.lhs;
	}
	
	public void setLhs(Expression lhs) {
		this.lhs = lhs;
	}
	
	public Expression getRhs() {
		return this.rhs;
	}
	
	public void setRhs(Expression rhs) {
		this.rhs = rhs;
	}
}
