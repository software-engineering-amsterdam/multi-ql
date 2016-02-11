package ast.literal;

import ast.Visitor;
import ast.expression.Expression;

public class IntLiteral extends Expression{
	private int value;
	
	public IntLiteral(Integer valueOf) {
		this.value = (int)valueOf;
	}
	
	public int getValue(){
		return value;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
