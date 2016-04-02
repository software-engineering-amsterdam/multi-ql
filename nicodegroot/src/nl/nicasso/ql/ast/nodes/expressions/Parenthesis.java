package nl.nicasso.ql.ast.nodes.expressions;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.types.BooleanType;
import nl.nicasso.ql.ast.nodes.types.IntegerType;
import nl.nicasso.ql.ast.nodes.types.MoneyType;
import nl.nicasso.ql.ast.nodes.types.StringType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.ast.nodes.types.UnknownType;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class Parenthesis extends Unary {

	private final Expression expression;

	public Parenthesis(Expression expression, CodeLocation location) {
		super(location);
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public String toString() {
		return "(" + expression + ")";
	}

	@Override
	public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	@Override
	public Type inferType(Type exprType) {
		if (exprType.equals(new BooleanType()) || exprType.equals(new IntegerType()) || exprType.equals(new MoneyType())
				|| exprType.equals(new StringType())) {
			return exprType;
		}

		return new UnknownType();
	}
}
