package ast.literal;

import ast.Visitor;
import ast.expression.Expression;

public class Variable extends Expression{
	private String text;
	
	public Variable(Object object) {
		this.text = (String)object;
	}
	
	public String getText(){
		return text;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
