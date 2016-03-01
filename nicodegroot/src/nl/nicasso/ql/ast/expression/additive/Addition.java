package nl.nicasso.ql.ast.expression.additive;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.expression.Expression;

public class Addition extends Additive implements Traversable  {
	
	private final Expression left;
	private final Expression right;

	public Addition(Expression left, Expression right, CodeLocation location) {
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
		return left.toString() + "+" + right.toString();
	}
	
}