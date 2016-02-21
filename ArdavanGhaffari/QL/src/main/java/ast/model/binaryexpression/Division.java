package ast.model.binaryexpression;

import ast.model.Expression;
import ast.visitor.ExpressionVisitor;

public class Division extends BinaryExpression{

	public Division(Expression leftExpression, Expression rightExprssion) {
		super(leftExpression, rightExprssion);
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
		return expressionVisitor.visit(this);
	}
}
