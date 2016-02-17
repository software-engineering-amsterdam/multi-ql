package ast.literal;

import ast.expression.Expression;
import ast.visitor.Visitor;

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
