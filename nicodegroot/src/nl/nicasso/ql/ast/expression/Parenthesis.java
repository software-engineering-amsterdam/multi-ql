package nl.nicasso.ql.ast.expression;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.visitor.ExpressionVisitor;

public class Parenthesis extends Monomial {

	private final Expression expr;

	public Parenthesis(Expression expr, CodeLocation location) {
		super(location);
		this.expr = expr;
	}

	public Expression getExpr() {
		return expr;
	}
	
	@Override
	public String toString() {
		return "(" + expr + ")";
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
