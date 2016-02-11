package ast.literal;

import ast.expression.Expression;

public class Literal extends Expression{
	private IntLiteral intLiteral;
	private Variable variable;
	
	public Literal(IntLiteral result) {
		this.intLiteral = result;
	}

	public Literal(Variable result) {
		this.variable = result;
	}
}
