package nl.nicasso.ql.ast.expression.multiplicative;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.ast.type.IntegerType;
import nl.nicasso.ql.ast.type.MoneyType;
import nl.nicasso.ql.ast.type.Type;
import nl.nicasso.ql.visitor.Visitor;

public class Division extends Multiplicative implements Traversable  {
	
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
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return left + "/" + right;
	}
	
	public Type checkAllowedTypes(Type left, Type right) {
		
		if (left.getType().equals(right.getType())) {
			if (left.getType().equals(new IntegerType().getType()) || left.getType().equals(new MoneyType().getType())) {
				return new MoneyType();
			}
		} else {
			if (left.getType().equals(new IntegerType().getType()) && right.getType().equals(new MoneyType().getType())) {
				return null;
			} else {
				return new MoneyType();
			}
		}
		
		return null;		
	}
}
