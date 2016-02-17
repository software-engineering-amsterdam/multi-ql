package ast.model.expression.literal;

import ast.model.expression.Expression;
import ast.model.type.Type;

public class IntLiteral extends Expression {
	private int value;
	
	public IntLiteral(int value) {
		this.type = Type.INTEGER;
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
