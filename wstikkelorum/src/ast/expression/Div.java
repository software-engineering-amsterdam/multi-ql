package ast.expression;

import ast.visitor.Visitor;

public class Div extends BinaryExpression {
	public Div(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}

	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
