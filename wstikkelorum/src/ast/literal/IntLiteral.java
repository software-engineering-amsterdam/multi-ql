package ast.literal;

import ast.expression.Expression;

public class IntLiteral extends Expression{

	private int value;
	
	public IntLiteral(Integer valueOf) {
		this.value = (int)valueOf;
	}
	
}
