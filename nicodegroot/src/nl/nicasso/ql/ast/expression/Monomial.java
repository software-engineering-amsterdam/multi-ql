package nl.nicasso.ql.ast.expression;

public abstract class Monomial extends Expression {

	Expression expr;

	public Expression getExpr() {
		return expr;
	}
}
