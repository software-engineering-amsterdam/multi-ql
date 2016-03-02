package nl.nicasso.ql.ast.expression.equality;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.visitor.ExpressionVisitor;

public class NotEqual extends Equality {
	
	private final Expression left;
	private final Expression right;

	public NotEqual(Expression left, Expression right, CodeLocation location) {
		super(location);
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
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return left + "!=" + right;
	}
	
}
