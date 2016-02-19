package ast.expression;

import ast.visitor.Visitor;

public class Mul extends BinaryExpression {
	public Mul(int lineNumber, Expression lhs, Expression rhs) {
		super(lineNumber, lhs, rhs);
	}
	
	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
