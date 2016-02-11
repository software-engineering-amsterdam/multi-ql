package ast.expression;

public class OrExpression extends Expression {
	private Expression lhs;
	private Expression rhs;
	public OrExpression(Expression result, Expression result2) {
		this.lhs = result;
		this.rhs = result2;
	}

}
