package nl.nicasso.ql.ast.expression;

import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;

public class Parenthesis extends Monomial implements Traversable {

	private final Expression expr;

	public Parenthesis(Expression expr) {
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
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
