package ast.expression;

import ast.visitor.Visitor;

public class LT extends BinaryExpression {
	public LT(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
