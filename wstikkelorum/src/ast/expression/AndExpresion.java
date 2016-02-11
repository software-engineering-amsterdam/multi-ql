package ast.expression;

public class AndExpresion extends Expression {
	private Expression lhs;
	private Expression rhs;
	public AndExpresion(Expression result, Expression result2) {
		this.lhs = result;
		this.rhs = result2;
	}

}
