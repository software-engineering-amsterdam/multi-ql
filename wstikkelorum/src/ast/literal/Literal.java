package ast.literal;

import ast.expression.Expression;
import ast.expression.VariableExpression;
import ast.visitor.Visitor;

public class Literal extends Expression{
	private IntLiteral intLiteral;
	private BoolLiteral boolLiteral;
	private StringLiteral stringLiteral;
	private VariableExpression variableExpression;
	
	public Literal(IntLiteral result) {
		this.intLiteral = result;
	}
	
	public Literal(BoolLiteral result) {
		this.boolLiteral = result;
	}

	public Literal(StringLiteral result) {
		this.stringLiteral = result;
	}

	public Literal(VariableExpression result) {
		this.variableExpression = result;
	}

	public IntLiteral getIntLiteral(){
		return intLiteral;
	}
	
	public BoolLiteral getBoolLiteral(){
		return boolLiteral;
	}
	
	public StringLiteral getStringLiteral(){
		return stringLiteral;
	}
	
	public VariableExpression getVariableExpression(){
		return variableExpression;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
