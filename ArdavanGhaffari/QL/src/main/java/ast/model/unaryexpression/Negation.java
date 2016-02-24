package ast.model.unaryexpression;

import ast.model.Expression;
import ast.visitor.ExpressionVisitor;

public class Negation extends Expression{
	private Expression expression;
	
	public Negation(Expression expression) {
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return expression;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
		return expressionVisitor.visit(this);
	}	
}
