package ast.model.expression.literal;

import ast.model.expression.Expression;
import ast.model.type.Type;

public class BoolLiteral extends Expression {
	private boolean value;
	
	public BoolLiteral(boolean value) {
		this.type = Type.BOOLEAN;
		this.value = value;
	}
	
	public boolean getValue() {
		return this.value;
	}
}
