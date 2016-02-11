package ast.expression;

public class Pos extends Expression {
	private Expression expression;
	public Pos(Expression result) {
		this.expression = result;
	}

}
