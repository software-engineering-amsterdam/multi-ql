package ast.expression;

public class GEq extends Expression {
	private Expression lhs;
	private Expression rhs;
	public GEq(Expression result, Expression result2) {
		this.lhs = result;
		this.rhs = result2;
	}

}
