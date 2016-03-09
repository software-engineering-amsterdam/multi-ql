package nl.nicasso.ql.ast.expressions.multiplicative;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expressions.Expression;
import nl.nicasso.ql.ast.types.IntegerType;
import nl.nicasso.ql.ast.types.MoneyType;
import nl.nicasso.ql.ast.types.Type;
import nl.nicasso.ql.ast.types.UnknownType;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class Division extends Multiplicative {
	
	private final Expression left;
	private final Expression right;

	public Division(Expression left, Expression right, CodeLocation location) {
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
		return left + "/" + right;
	}
	
	@Override
	public Type inferType(Type left, Type right) {
		
		if (left.equals(right)) {
			if (left.equals(new IntegerType())) {
				return new IntegerType();
			}
		} else if (left.equals(new MoneyType()) && right.equals(new IntegerType())) {
			return new MoneyType();
		}
		
		return new UnknownType();
	}
}
