package ast.expression;

import ast.visitor.Visitor;

public class Sub extends BinaryExpression {
	public Sub(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
