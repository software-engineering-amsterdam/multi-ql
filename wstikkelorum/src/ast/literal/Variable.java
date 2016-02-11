package ast.literal;

import ast.expression.Expression;

public class Variable extends Expression{
	private String text;
	
	public Variable(Object object) {
		this.text = (String)object;
	}
}
