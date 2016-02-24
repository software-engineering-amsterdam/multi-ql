package ast.model.binaryexpression;

import ast.model.Expression;
import ast.visitor.ExpressionVisitor;

public class Disjunction extends BinaryExpression{

	public Disjunction(Expression leftExpression, Expression rightExprssion) {
		super(leftExpression, rightExprssion);
	}
	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
		return expressionVisitor.visit(this);
	}
}
