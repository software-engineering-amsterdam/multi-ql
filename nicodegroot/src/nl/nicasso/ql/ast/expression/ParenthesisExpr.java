package nl.nicasso.ql.ast.expression;

import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;

public class ParenthesisExpr extends Expression implements Traversable {

	Expression expr;

	public ParenthesisExpr(Expression expr) {
		this.expr = expr;
	}

	public Expression getExpr() {
		return expr;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
