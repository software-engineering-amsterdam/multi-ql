package ast.model.binaryexpression;

import ast.model.Expression;
import ast.visitor.ExpressionVisitor;

public class Subtraction extends BinaryExpression{

	public Subtraction(Expression leftExpression, Expression rightExprssion, int line) {
		super(leftExpression, rightExprssion, line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
		return expressionVisitor.visit(this);
	}

}
