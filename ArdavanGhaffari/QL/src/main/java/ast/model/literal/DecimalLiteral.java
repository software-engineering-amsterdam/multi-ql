package ast.model.literal;

import ast.model.Expression;
import ast.visitor.ExpressionVisitor;

public class DecimalLiteral extends Expression {

	private double value;
	
	public DecimalLiteral(double value, int line) {
		super(line);
		this.value = value;
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
		return expressionVisitor.visit(this);
	}
	
	public double getValue() {
		return this.value;
	}


}
