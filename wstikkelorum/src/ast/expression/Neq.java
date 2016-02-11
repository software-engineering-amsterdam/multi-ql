package ast.expression;

public class Neq extends Expression {
	private Expression lhs;
	private Expression rhs;
	public Neq(Expression result, Expression result2) {
		this.lhs = result;
		this.rhs = result2;
	}

}
