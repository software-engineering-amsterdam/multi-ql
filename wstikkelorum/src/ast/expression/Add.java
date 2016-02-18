package ast.expression;

import ast.visitor.Visitor;

public class Add extends BinaryExpression {
	public Add(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
