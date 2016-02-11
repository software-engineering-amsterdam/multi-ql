package ast.expression;

public class LT extends Expression {
	private Expression lhs;
	private Expression rhs;
	public LT(Expression result, Expression result2) {
		this.lhs = result;
		this.rhs = result2;
	}

}
