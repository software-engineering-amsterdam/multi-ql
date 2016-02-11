package ast.expression;

public class Div extends Expression {
	private Expression lhs;
	private Expression rhs;
	public Div(Expression result, Expression result2) {
		this.lhs = result;
		this.rhs = result2;
	}

}
