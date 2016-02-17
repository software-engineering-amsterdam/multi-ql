package ast.model.statement;

import ast.model.Box;
import ast.model.expression.Expression;

public class IfStatement extends Statement {
	private Expression expression;
	private Box ifBox;
	
	public IfStatement(Expression expression, Box box) {
		this.expression = expression;
		this.ifBox = box;
	}

	public Expression getExpression() {
		return expression;
	}

	public Box getIfBox() {
		return ifBox;
	}
	
}
