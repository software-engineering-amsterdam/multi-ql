package ast.expression;

import ast.visitor.Visitor;

public class VariableExpression extends Expression {
	private String name;
	
	public VariableExpression(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
