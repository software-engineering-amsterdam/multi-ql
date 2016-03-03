package nl.nicasso.ql.ast.expressions;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.types.Type;

public abstract class Binary extends Expression {

	Expression left;
	Expression right;
	
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
