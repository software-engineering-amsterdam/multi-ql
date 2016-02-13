package nl.nicasso.ql.ast.expression;

public class NotExpr extends Expression  {
	
	Expression expr;

	public NotExpr(Expression expr) {
		this.expr = expr;
	}

	public Expression getExpr() {
		return expr;
	}
	
}
