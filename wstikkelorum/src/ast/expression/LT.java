package ast.expression;

import ast.visitor.Visitor;

public class LT extends BinaryExpression {
	public LT(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
