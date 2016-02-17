package ast.expression;

import ast.visitor.Visitor;

public class Mul extends BinaryExpression {
	public Mul(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
