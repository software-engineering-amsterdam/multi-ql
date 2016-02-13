package nl.nicasso.ql.ast.expression;

public class ParenthesisExpr extends Expression {

	Expression expr;

	public ParenthesisExpr(Expression expr) {
		this.expr = expr;
	}

	public Expression getExpr() {
		return expr;
	}
}
