package nl.uva.ql.ast.literal;

import nl.uva.ql.ast.expression.Expression;
import nl.uva.ql.visitors.ExpressionVisitor;

public class BooleanLiteral extends Expression{
	private boolean value;
	
	public BooleanLiteral(boolean value, int line) {
		super(line);
		this.value = value;
	}
	
	public boolean getValue() {
		return this.value;
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
		return expressionVisitor.visit(this);
	}	

}
