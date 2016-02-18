package ast.expression;

import ast.visitor.Visitor;

public class GEq extends BinaryExpression {
	public GEq(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public Object  accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
