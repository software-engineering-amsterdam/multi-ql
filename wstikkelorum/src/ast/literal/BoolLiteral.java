package ast.literal;

import ast.expression.Expression;
import ast.visitor.Visitor;

public class BoolLiteral extends Expression {
	private boolean value;
	
	public BoolLiteral(Boolean value){
		this.value = value;
	}
	
	public boolean getValue(){
		return value;
	}
	
	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
