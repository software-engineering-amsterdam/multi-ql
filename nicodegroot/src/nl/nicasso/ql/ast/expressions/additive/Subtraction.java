package nl.nicasso.ql.ast.expressions.additive;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expressions.Expression;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class Subtraction extends Additive {
	
	private final Expression left;
	private final Expression right;

	public Subtraction(Expression left, Expression right, CodeLocation location) {
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
		return left + "-" + right;
	}
	
}
