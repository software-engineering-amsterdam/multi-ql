package nl.nicasso.ql.ast.expression;

import nl.nicasso.ql.ast.Visitor;

public abstract class Monomial extends Expression {

	Expression expr;

	public Expression getExpr() {
		return expr;
	}
}
