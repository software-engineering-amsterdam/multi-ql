package ast.expression;

public class Neg extends Expression {
	private Expression expression;
	public Neg(Expression result) {
		this.expression = result;
	}

}
