package ast.literal;

import ast.Visitor;
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
	
	public IntLiteral getIntLiteral(){
		return intLiteral;
	}
	
	public Variable getVariable(){
		return variable;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
