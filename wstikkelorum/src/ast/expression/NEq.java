package ast.expression;

import ast.visitor.Visitor;

public class NEq extends BinaryExpression {
	public NEq(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
