package ast.expression;

import ast.visitor.Visitor;

public class GT extends BinaryExpression {
	public GT(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
