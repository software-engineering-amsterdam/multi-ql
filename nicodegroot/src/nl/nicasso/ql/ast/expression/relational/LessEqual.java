package nl.nicasso.ql.ast.expression.relational;

import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.expression.Expression;

public class LessEqual extends Relational implements Traversable  {
	
	private final Expression left;
	private final Expression right;

	public LessEqual(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return left + "<=" + right;
	}
	
}
