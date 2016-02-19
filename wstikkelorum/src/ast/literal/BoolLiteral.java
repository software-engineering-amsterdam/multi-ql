package ast.literal;

import ast.expression.Expression;
import ast.visitor.Visitor;

public class BoolLiteral extends Expression {
	private boolean value;
	
	public BoolLiteral(int lineNumber, Boolean value){
		super(lineNumber);
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
