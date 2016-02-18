package ast.expression;

import ast.visitor.Visitor;

public class AndExpression extends BinaryExpression {
	public AndExpression(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
