package nl.uva.ql.ast.expression.binaryexpression;

import nl.uva.ql.ast.expression.Expression;
import nl.uva.ql.visitors.ExpressionVisitor;

public class GreaterThanEqual extends BinaryExpression{

	public GreaterThanEqual(Expression leftExpression, Expression rightExprssion, int line) {
		super(leftExpression, rightExprssion, line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
		return expressionVisitor.visit(this);
	}
}
