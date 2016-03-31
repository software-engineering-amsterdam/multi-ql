package nl.nicasso.ql.ast.nodes.expressions.conditional;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.expressions.Unary;
import nl.nicasso.ql.ast.nodes.types.BooleanType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.ast.nodes.types.UnknownType;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class Not extends Unary {

	private final Expression expression;

	public Not(Expression expression, CodeLocation location) {
		super(location);
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	@Override
	public String toString() {
		return "!" + expression;
	}

	@Override
	public Type inferType(Type exprType) {
		if (exprType.equals(new BooleanType())) {
			return exprType;
		}

		return new UnknownType();
	}
}
