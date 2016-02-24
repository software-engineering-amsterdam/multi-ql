package ast.expression;

import ast.visitor.Visitor;
import ast.expression.Expression;

public class BinaryExpression extends Expression {
	private Expression lhs;
	private Expression rhs;

	public BinaryExpression(int lineNumber, Expression lhs, Expression rhs) {
		super(lineNumber);
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public Expression getLhs() {
		return lhs;
	}

	public Expression getRhs() {
		return rhs;
	}

	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
