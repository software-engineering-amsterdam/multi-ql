package eu.bankersen.kevin.ql.form.ast.expressions;

public abstract class Unary extends Expression {

	private final Expression expr;

	public Unary(int line, Expression expr) {
		super(line);
		this.expr = expr;
	}

	public Expression expr() {
		return expr;
	}
}
