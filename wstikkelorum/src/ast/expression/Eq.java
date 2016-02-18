package ast.expression;

import ast.visitor.Visitor;

public class Eq extends BinaryExpression {
	public Eq(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public Object accept(Visitor visitor) {
		 return visitor.visit(this);
	}

}
