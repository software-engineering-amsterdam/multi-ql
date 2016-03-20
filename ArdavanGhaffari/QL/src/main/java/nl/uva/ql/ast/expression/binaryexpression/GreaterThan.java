package nl.uva.ql.ast.expression.binaryexpression;

import nl.uva.ql.ast.expression.Expression;
import nl.uva.ql.visitors.ExpressionVisitor;

public class GreaterThan extends BinaryExpression{

	public GreaterThan(Expression leftExpression, Expression rightExpression, int line) {
		super(leftExpression, rightExpression, line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
		return expressionVisitor.visit(this);
	}
}
