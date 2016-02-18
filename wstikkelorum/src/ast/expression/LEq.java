package ast.expression;

import ast.visitor.Visitor;

public class LEq extends BinaryExpression {
	public LEq(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
