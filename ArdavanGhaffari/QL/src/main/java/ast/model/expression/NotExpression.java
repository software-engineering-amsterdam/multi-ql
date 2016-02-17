package ast.model.expression;

public class NotExpression extends Expression {
	private Expression operand;
	
	public NotExpression(Expression operand) {
		this.operand = operand;
	}
}
