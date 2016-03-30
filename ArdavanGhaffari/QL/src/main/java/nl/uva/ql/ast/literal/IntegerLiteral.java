package nl.uva.ql.ast.literal;

import nl.uva.ql.ast.expression.Expression;
import nl.uva.ql.visitors.ExpressionVisitor;

public class IntegerLiteral extends Expression {
	private int value;
	
	public IntegerLiteral(int value, int line) {
		super(line);
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
		return expressionVisitor.visit(this);
	}	
}
