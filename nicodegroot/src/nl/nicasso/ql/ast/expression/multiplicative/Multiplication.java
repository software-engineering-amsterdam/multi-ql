package nl.nicasso.ql.ast.expression.multiplicative;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.ast.type.IntegerType;
import nl.nicasso.ql.ast.type.MoneyType;
import nl.nicasso.ql.ast.type.Type;
import nl.nicasso.ql.visitor.ExpressionVisitor;

public class Multiplication extends Multiplicative {
	
	private final Expression left;
	private final Expression right;

	public Multiplication(Expression left, Expression right, CodeLocation location) {
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
		return left + "*" + right;
	}
	
	public Type checkAllowedTypes(Type left, Type right) {
		
		if (left.equals(right)) {
			if (left.equals(new IntegerType())) {
				return left;
			}
		} else {
			if (left.equals(new IntegerType()) && right.equals(new MoneyType())) {
				return right;
			} else if (right.equals(new IntegerType()) && left.equals(new MoneyType())) {
				return left;
			}
		}
	
		return null;		
	}
	
}
