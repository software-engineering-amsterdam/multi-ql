package nl.nicasso.ql.ast.expression;

import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;

public class MathLowExpr extends Expression implements Traversable {

	Expression expr_left;
	Expression expr_right;
	String operator;
	
	public MathLowExpr(Expression expr_left, Expression expr_right, String operator) {
		this.expr_left = expr_left;
		this.expr_right = expr_right;
		this.operator = operator;
	}

	public Expression getExpr_left() {
		return expr_left;
	}

	public Expression getExpr_right() {
		return expr_right;
	}
	
	public String getOperator() {
		return operator;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
