package ast.expression;

public class GT extends Expression {
	private Expression lhs;
	private Expression rhs;
	public GT(Expression result, Expression result2) {
		this.lhs = result;
		this.rhs = result2;
	}

}
