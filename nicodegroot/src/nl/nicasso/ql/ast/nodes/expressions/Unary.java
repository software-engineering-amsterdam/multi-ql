package nl.nicasso.ql.ast.nodes.expressions;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.types.Type;

public abstract class Unary extends Expression {

	private Expression expression;

	public Unary(CodeLocation location) {
		super(location);
	}

	public Expression getExpression() {
		return expression;
	}

	public abstract Type inferType(Type exprType);
}
