package ast.expression;

public class Mul extends Expression {
	private Expression lhs;
	private Expression rhs;
	public Mul(Expression result, Expression result2) {
		this.lhs = result;
		this.rhs = result2;
	}

}
