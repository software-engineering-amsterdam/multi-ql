package ast.expression;

import ast.visitor.Visitor;

public class LEq extends BinaryExpression {
	public LEq(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
