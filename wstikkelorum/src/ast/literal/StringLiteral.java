package ast.literal;

import ast.expression.Expression;
import ast.visitor.Visitor;

public class StringLiteral extends Expression{
	private String value;
	
	public StringLiteral(int lineNumber, String value){
		super(lineNumber);
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
	
	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
