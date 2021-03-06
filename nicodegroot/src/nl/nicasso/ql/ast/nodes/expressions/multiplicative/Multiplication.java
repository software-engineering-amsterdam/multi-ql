package nl.nicasso.ql.ast.nodes.expressions.multiplicative;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.types.IntegerType;
import nl.nicasso.ql.ast.nodes.types.MoneyType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.ast.nodes.types.UnknownType;
import nl.nicasso.ql.visitors.ExpressionVisitor;

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
	public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	@Override
	public String toString() {
		return left + "*" + right;
	}

	@Override
	public Type inferType(Type left, Type right) {

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

		return new UnknownType();
	}

}
