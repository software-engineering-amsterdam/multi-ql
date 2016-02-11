package ast.expression;

public class Sub extends Expression {
	private Expression lhs;
	private Expression rhs;
	public Sub(Expression result, Expression result2) {
		this.lhs = result;
		this.rhs = result2;
	}

}
