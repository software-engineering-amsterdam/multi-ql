package ast.expression;

import ast.visitor.Visitor;
import ast.expression.Expression;

public class BinaryExpression extends Expression{
	private Expression lhs;
	private Expression rhs;
	
	public BinaryExpression(Expression lhs, Expression rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
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
