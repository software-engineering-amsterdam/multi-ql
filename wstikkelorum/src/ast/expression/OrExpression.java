package ast.expression;

import ast.visitor.Visitor;

public class OrExpression extends BinaryExpression {
	public OrExpression(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
