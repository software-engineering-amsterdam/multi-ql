package nl.nicasso.ql.ast.nodes.expressions;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.types.Type;

public abstract class Binary extends Expression {

	private Expression left;
	private Expression right;

	public Binary(CodeLocation location) {
		super(location);
	}

	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}

	public abstract Type inferType(Type left, Type right);
}
