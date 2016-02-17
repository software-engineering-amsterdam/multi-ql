package ast.literal;

import ast.expression.Expression;
import ast.visitor.Visitor;

public class StringLiteral extends Expression{
	private String value;
	
	public StringLiteral(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
