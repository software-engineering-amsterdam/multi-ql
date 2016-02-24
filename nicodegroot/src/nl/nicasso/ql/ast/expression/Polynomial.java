package nl.nicasso.ql.ast.expression;

public abstract class Polynomial extends Expression {

	Expression left;
	Expression right;
	
	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}
}
