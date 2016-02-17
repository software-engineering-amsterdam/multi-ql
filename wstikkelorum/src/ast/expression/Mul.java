package ast.expression;

import ast.visitor.Visitor;

public class Mul extends Expression {
	private Expression lhs;
	private Expression rhs;
	
	public Mul(Expression result, Expression result2) {
		this.lhs = result;
		this.rhs = result2;
	}
	
	public Expression getLhs() {
		return lhs;
	}

	public Expression getRhs() {
		return rhs;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
