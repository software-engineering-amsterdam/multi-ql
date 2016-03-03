package nl.nicasso.ql.ast.expression;

import nl.nicasso.ql.ast.CodeLocation;

public abstract class Monomial extends Expression {

	public Monomial(CodeLocation location) {
		super(location);
	}

	Expression expr;

	public Expression getExpr() {
		return expr;
	}
}
