package ql.ast.literal;

import ql.ast.ASTNode;
import ql.ast.type.BooleanType;
import ql.ast.type.IntegerType;
import ql.ast.type.StringType;
import ql.ast.type.ValueType;

public class VariableType extends ASTNode {
	private ValueType type;

	public VariableType(int lineNumber, String type) {
		super(lineNumber);
		createTypeFromString(type);
	}
	
	private void createTypeFromString(String type){
		if(type.equals("boolean")){
			this.type = new BooleanType();
			return;
		}
		if(type.equals("int")){
			this.type = new IntegerType();
			return;
		}
		//else type equals "string"
		this.type = new StringType();
	}

	public ValueType getType() {
		return type;
	}
}
