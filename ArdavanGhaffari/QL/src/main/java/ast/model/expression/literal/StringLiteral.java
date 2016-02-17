package ast.model.expression.literal;

import ast.model.expression.Expression;
import ast.model.type.Type;

public class StringLiteral extends Expression {
	private String value;
	
	public StringLiteral(String value) {
		this.value = value;
		this.type = Type.STRING;
	}
	
	public String getValue() {
		return this.value;
	}
}
