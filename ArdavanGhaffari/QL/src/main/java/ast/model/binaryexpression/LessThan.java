package ast.model.binaryexpression;

import ast.model.Expression;
import ast.visitor.ExpressionVisitor;

public class LessThan extends BinaryExpression{

	public LessThan(Expression leftExpression, Expression rightExprssion) {
		super(leftExpression, rightExprssion);
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
		return expressionVisitor.visit(this);
	}

}
