package ast.expression;

public class Eq extends Expression {
	private Expression lhs;
	private Expression rhs;
	public Eq(Expression result, Expression result2) {
		this.lhs = result;
		this.rhs = result2;
	}

}
