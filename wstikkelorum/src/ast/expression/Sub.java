package ast.expression;

import ast.visitor.Visitor;

public class Sub extends BinaryExpression {
	public Sub(int lineNumber, Expression lhs, Expression rhs) {
		super(lineNumber, lhs, rhs);
	}

	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
