package ast.expression;

import ast.visitor.Visitor;

public class OrExpression extends BinaryExpression {
	public OrExpression(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
