package nl.uva.ql.ast.literal;

import java.math.BigDecimal;

import nl.uva.ql.ast.expression.Expression;
import nl.uva.ql.visitors.ExpressionVisitor;

public class MoneyLiteral extends Expression {

	private BigDecimal value;
	
	public MoneyLiteral(BigDecimal value, int line) {
		super(line);
		this.value = value;
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
		return expressionVisitor.visit(this);
	}
	
	public BigDecimal getValue() {
		return this.value;
	}


}
