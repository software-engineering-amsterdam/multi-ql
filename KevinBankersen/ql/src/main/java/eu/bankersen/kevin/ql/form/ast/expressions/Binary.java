package eu.bankersen.kevin.ql.form.ast.expressions;

public abstract class Binary extends Expression {

	private final Expression lhs;
	private final Expression rhs;

	public Binary(int line, Expression lhs, Expression rhs) {
		super(line);
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public Expression lhs() {
		return lhs;
	}

	public Expression rhs() {
		return rhs;
	}
}
