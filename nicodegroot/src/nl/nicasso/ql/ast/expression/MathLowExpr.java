package nl.nicasso.ql.ast.expression;

public class MathLowExpr extends Expression  {

	Expression expr_left;
	Expression expr_right;
	
	public MathLowExpr(Expression expr_left, Expression expr_right) {
		this.expr_left = expr_left;
		this.expr_right = expr_right;
	}

	public Expression getExpr_left() {
		return expr_left;
	}

	public Expression getExpr_right() {
		return expr_right;
	}
	
}
