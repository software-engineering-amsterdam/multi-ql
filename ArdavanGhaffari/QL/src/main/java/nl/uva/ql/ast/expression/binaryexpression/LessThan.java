package nl.uva.ql.ast.expression.binaryexpression;

import nl.uva.ql.ast.expression.Expression;
import nl.uva.ql.visitors.ExpressionVisitor;

public class LessThan extends BinaryExpression{

	public LessThan(Expression leftExpression, Expression rightExprssion, int line) {
		super(leftExpression, rightExprssion, line);
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
		return expressionVisitor.visit(this);
	}

}
