package ql.ast.literal;

import ql.ast.expression.Expression;

public abstract class Literal extends Expression {
	public Literal(int lineNumber) {
		super(lineNumber);
	}
	
}
