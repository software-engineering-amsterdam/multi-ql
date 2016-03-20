package nl.uva.ql.ast.expression.unaryexpression;

import nl.uva.ql.ast.expression.Expression;
import nl.uva.ql.visitors.ExpressionVisitor;

public class Negation extends Expression{
	private Expression expression;
	
	public Negation(Expression expression, int line) {
		super(line);
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
