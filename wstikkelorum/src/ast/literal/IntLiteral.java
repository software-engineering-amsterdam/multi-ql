package ast.literal;

import ast.expression.Expression;
import ast.visitor.Visitor;

public class IntLiteral extends Expression {
	private final int value;

	public IntLiteral(int lineNumber, Integer valueOf) {
		super(lineNumber);
		this.value = (int) valueOf;
	}

	public int getValue() {
		return value;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
