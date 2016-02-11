package ast.expression;

public class Add extends Expression {
	private Expression lhs;
	private Expression rhs;
	public Add(Expression result, Expression result2) {
		this.lhs = result;
		this.rhs = result2;
	}

}
