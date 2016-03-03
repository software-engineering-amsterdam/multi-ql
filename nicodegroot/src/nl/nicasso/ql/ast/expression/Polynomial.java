package nl.nicasso.ql.ast.expression;

import nl.nicasso.ql.ast.CodeLocation;

public abstract class Polynomial extends Expression {

	public Polynomial(CodeLocation location) {
		super(location);
	}

	Expression left;
	Expression right;
	
	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}
}
