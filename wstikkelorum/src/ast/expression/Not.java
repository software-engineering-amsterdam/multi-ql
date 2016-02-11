package ast.expression;

public class Not extends Expression {
	private Expression expression;
	public Not(Expression result) {
		this.expression = result;
	}

}
